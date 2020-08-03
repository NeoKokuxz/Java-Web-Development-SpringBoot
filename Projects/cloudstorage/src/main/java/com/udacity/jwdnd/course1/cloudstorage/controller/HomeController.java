package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.Mapper.CredentialsMapper;
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
    private CredentialsMapper credentialsMapper;

    public HomeController(CredentialService credentialService, UserMapper userMapper, CredentialsMapper credentialsMapper){
        this.credentialService = credentialService;
        this.userMapper = userMapper;
        this.credentialsMapper = credentialsMapper;
    }

    //Return Home Page View
    @GetMapping
    public String getHomeView(){
        return "home";
    }

    /*
        Credential
     */
    @PostMapping
    public String postCredential(Authentication a, Credential credential, Model model){
        credential.setUserId(userMapper.getUser(a.getName()).getUserId());
        credentialService.insertCredential(credential);
        return "home";
    }

//    @DeleteMapping
//    public String DeleteCredential(Credential credential, Model model){
//        credentialService.deleteCredential(credential.getUsername());
//        return "home";
//    }

//    @PutMapping
//    public String
}
