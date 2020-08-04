package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CredentialService {
    private final CredentialsMapper credentialsMapper;
    private final UserMapper userMapper;

    public CredentialService(CredentialsMapper credentialsMapper, UserMapper userMapper) {
        this.credentialsMapper = credentialsMapper;
        this.userMapper = userMapper;
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
        return credentialList;

    }

    //Get credential count - Get
    public int getCount(){
        return credentialsMapper.getCredentialCount();
    }

    //Post credential - Create
    public int insertCredential(Credential credential){
        return credentialsMapper.insertCredential(new Credential(  null,
                                                                    credential.getUrl(),
                                                                    credential.getUsername(),
                                                                    credential.getKey(),
                                                                    credential.getPassword(),
                                                                    credential.getUserId()));
    }

    //Put credential - Update
    public int updateCredential(Credential credential){
        return credentialsMapper.updateCredential(credential);
    }

    //Delete credential - Delete
    public void deleteCredential(Integer id){
        credentialsMapper.deleteCredentials(id);
    }


}
