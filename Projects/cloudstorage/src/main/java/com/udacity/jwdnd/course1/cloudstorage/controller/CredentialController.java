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

    @PostMapping("/credential/create")
    public String postCredential(Authentication a, Credential credential, Model model){
        credential.setUserId(userMapper.getUser(a.getName()).getUserId());
        credentialService.insertCredential(credential);
        //
        model.addAttribute("cs", credentialService.getCredentialList());
        System.out.println();
        return "result";
    }

    @GetMapping("/credential/delete/{credentialId}")
    public String deleteCredential(@PathVariable Integer credentialId){
        System.out.println("Mapping test");
        credentialService.deleteCredential(credentialId);
        return "result";
    }

}
