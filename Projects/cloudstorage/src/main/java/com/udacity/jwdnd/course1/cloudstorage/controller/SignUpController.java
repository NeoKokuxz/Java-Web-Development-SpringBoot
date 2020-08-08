package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.SignupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private final SignupService signupService;

    public SignUpController(SignupService signupService) {
        this.signupService = signupService;
    }

    //Return Signup View
    @GetMapping
    public String getSignupView(){
        return "signup";
    }

    //Post sign up data to service
    @PostMapping
    public String signupUser(@ModelAttribute User user, Model model){
        //Default no error message
        String signupError = null;

        if(!signupService.checkUsername(user.getUsername()) ){
            signupError = "Username already taken!";
        }

        if(signupError == null){
            //service will return int value if success
            int rowAdded = signupService.createUser(user);

            // <0 failed to sign up
            if(rowAdded < 0){
                signupError = "Error when signing up user!";
            }
        }

        if(signupError==null){
            model.addAttribute("signupSuccess", true);
            return "login";

        } else {
            model.addAttribute("signupError", signupError);
            return "signup";
        }
    }
}
