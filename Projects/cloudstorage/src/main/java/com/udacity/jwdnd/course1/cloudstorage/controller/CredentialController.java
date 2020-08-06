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
        int rowAffected; //Check if it's a update not create
        if(credential.getCredentialId() == null){
            //Credential Id = null = not exist in the database
            credential.setUserId(userMapper.getUser(a.getName()).getUserId());
            rowAffected = credentialService.insertCredential(credential);
        } else {
            //Update the credential
            rowAffected = credentialService.updateCredential(credential);
        }
        if(rowAffected <= 0){
            errorMessage = "Error occurred during request. Please try again later";
            model.addAttribute("errorMessage", errorMessage);
            return "result";
        }
        String successMessage = "Success uploaded credential! ";
        model.addAttribute("successMessage", successMessage);
        return "result";
    }

    @GetMapping("/credential/delete/{credentialId}")
    public String deleteCredential(@PathVariable Integer credentialId, Model model){
        String successMessage = "Success deleted credential";
        credentialService.deleteCredential(credentialId);
        model.addAttribute("successMessage", successMessage);
        return "result";
    }

}
