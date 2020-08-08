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

/**
 * This class contains all method for Credential services
 *
 * @Author Neo Chen
 *
 */
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

    /**
     * The getCredential method will get credential by id
     *
     * @param id
     * @return credential object
     */
    public Credential getCredential(Integer id) {
        Credential credential = credentialsMapper.selectCredential(id);
        return credential;
    }

    /**
     * The getCredentialList returns a list of credentials under the same username
     * The encrypted password will shown on list
     * The decrypted password only shown on edit or update modal
     *
     * @param username
     * @return List of credentials or empty list if username not found
     */
    public List<Credential> getCredentialList(String username){
        User user = userMapper.getUser(username);
        List<Credential> credentialList = credentialsMapper.getCredentialList(user.getUserId());
        if(credentialList == null){
            return new ArrayList<>();
        }
        for (Credential credential : credentialList) {
            String decryptPassword = decryptPassword(credential.getPassword(), credential.getKey());
            credential.setDecryptedPassword(decryptPassword);
        }
        return credentialList;

    }

    /**
     * The insertCredential method insert the credential into credentialMapper
     *
     * @param credential
     * @return int value from credentialMapper
     */
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

    /**
     * The updateCredential method takes credential and reset the key of credential
     * then update the corresponding data via credentialMapper
     *
     * @param credential
     * @return int value
     */
    public int updateCredential(Credential credential){

        if(credential.getKey() == null){
            String key = generateKey(credential);
            credential.setKey(key);
        }

        String encryptedPassword = encryptPassword(credential.getPassword(), credential.getKey());

        credential.setPassword(encryptedPassword);

        return credentialsMapper.updateCredential(credential);
    }

    /**
     * The deleteCredential method deletes credential in credentialMapper via credentialId
     *
     * @param id
     */
    public void deleteCredential(Integer id){
        credentialsMapper.deleteCredentials(id);
    }

    /**
     * The encryptedPassword method takes in password and key in order to encrypt
     *
     * @param password
     * @param key
     * @return String of new encrypted password
     */
    private String encryptPassword(String password, String key){
        return encryptionService.encryptValue(password, key);
    }

    /**
     *The decryptedPassword method takes in password and key in order to decrypt
     *
     * @param password
     * @param key
     * @return String of new decrypted password
     */
    private String decryptPassword(String password, String key) {
        return encryptionService.decryptValue(password, key);
    }

    /**
     * The generateKey method will generate random key in Base64
     *
     * @param credential
     * @return String of encoded key
     */
    private String generateKey(Credential credential){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }


}
