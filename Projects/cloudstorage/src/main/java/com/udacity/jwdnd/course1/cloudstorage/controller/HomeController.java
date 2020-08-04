package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class HomeController {

    private CredentialService credentialService;
    private UserMapper userMapper;


    public HomeController(CredentialService credentialService, UserMapper userMapper){
        this.credentialService = credentialService;
        this.userMapper = userMapper;
        //this.credentialsMapper = credentialsMapper;
    }

    //Return Home Page View
    @GetMapping
    public String getHomeView(){
        return "home";
    }
}

//    @GetMapping("/credential/delete/{credentialId}")
//    public String deleteCredential(@PathVariable Integer credentialId){
//        System.out.println("Mapping test");
//        credentialService.deleteCredential(credentialId);
//        return "home";
//    }

//    @DeleteMapping
//    public String DeleteCredential(Credential credential, Model model){
//        credentialService.deleteCredential(credential.getUsername());
//        return "home";
//    }

//    @PutMapping
//    public String

