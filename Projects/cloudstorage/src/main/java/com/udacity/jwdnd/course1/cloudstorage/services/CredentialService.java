package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {
    private final CredentialsMapper credentialsMapper;

    public CredentialService(CredentialsMapper credentialsMapper) {
        this.credentialsMapper = credentialsMapper;
    }

    //Get credential - Get
    public String getCredential(String username){
        Credential c = credentialsMapper.getCredential(username);
        return c.getUsername();
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
    public boolean updateCredential(Credential credential){
        return false;
    }

    //Delete credential - Delete
    public void deleteCredential(String username){
        credentialsMapper.deleteCredentials(username);
    }

}
