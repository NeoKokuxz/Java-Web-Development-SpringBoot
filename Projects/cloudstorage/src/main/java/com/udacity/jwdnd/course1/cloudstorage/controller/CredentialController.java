package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CredentialController {

    UserMapper userMapper;
    CredentialService credentialService;

    public CredentialController(UserMapper userMapper, CredentialService credentialService) {
        this.userMapper = userMapper;
        this.credentialService = credentialService;
    }

    @PostMapping(value = "/credential/create")
    public String postCredential(Authentication a, Credential credential, Model model){
        String errorMessage = null;
        int rowsAffected;
//        if(credential.getCredentialId() != null){
//            rowsAffected = credentialService.updateCredential(credential);
//        } else {
            credential.setUserId(userMapper.getUser(a.getName()).getUserId());
            rowsAffected = credentialService.insertCredential(credential);
//        }
//
//        if(rowsAffected <= 0){
//            errorMessage = "An unexpected error occurred! Try again later";
//            model.addAttribute("errorMessage", errorMessage);
//            return "result";
//        }
        return "result";
    }

    @GetMapping("/credential/delete/{credentialId}")
    public String deleteCredential(@PathVariable Integer credentialId){
        credentialService.deleteCredential(credentialId);
        return "result";
    }

}
