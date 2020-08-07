package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private final CredentialsMapper credentialsMapper;
    private final UserMapper userMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialsMapper credentialsMapper, UserMapper userMapper, EncryptionService encryptionService) {
        this.credentialsMapper = credentialsMapper;
        this.userMapper = userMapper;
        this.encryptionService = encryptionService;
    }

    //Get credential - Get
    public Credential getCredential(Integer id) {
        Credential credential = credentialsMapper.selectCredential(id);
        return credential;
    }

    public List<Credential> getCredentialList(String username){
        User user = userMapper.getUser(username);
        List<Credential> credentialList = credentialsMapper.getCredentialList(user.getUserId());
        if(credentialList == null){
            return new ArrayList<>();
        }

        for (Credential credential : credentialList) {
            String decryptPassword = decryptPassword(credential.getPassword(), credential.getKey());

            credential.setPassword(decryptPassword);

        }

        return credentialList;

    }

    //Get credential count - Get
    public int getCount(){
        return credentialsMapper.getCredentialCount();
    }

    //Post credential - Create
    public int insertCredential(Credential credential){

        if(credential.getKey() == null){
            String key = generateKey(credential);
            credential.setKey(key);
        }

        String encryptedPassword = encryptPassword(credential.getPassword(), credential.getKey());

        credential.setPassword(encryptedPassword);

        return credentialsMapper.insertCredential(new Credential(  null,
                                                                    credential.getUrl(),
                                                                    credential.getUsername(),
                                                                    credential.getKey(),
                                                                    credential.getPassword(),
                                                                    credential.getUserId()));
    }

    //Put credential - Update
    public int updateCredential(Credential credential){

        if(credential.getKey() == null){
            String key = generateKey(credential);
            credential.setKey(key);
        }

        String encryptedPassword = encryptPassword(credential.getPassword(), credential.getKey());

        credential.setPassword(encryptedPassword);

        System.out.println(credential.getPassword());
        System.out.println(credential.getKey());


        return credentialsMapper.updateCredential(credential);
    }

    //Delete credential - Delete
    public void deleteCredential(Integer id){
        credentialsMapper.deleteCredentials(id);
    }

    private String encryptPassword(String password, String key){
        return encryptionService.encryptValue(password, key);
    }

    private String decryptPassword(String password, String key) {
        return encryptionService.decryptValue(password, key);
    }

    private String generateKey(Credential credential){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }


}
