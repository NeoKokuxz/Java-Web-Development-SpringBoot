package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class SignupService {
    UserMapper userMapper;
    HashService hashService;

    //Initial both UserMapper and HashService
    public SignupService(UserMapper userMapper, HashService hashService){
        this.hashService = hashService;
        this.userMapper = userMapper;
    }

    //Check if username taken by other user
    public boolean checkUsername(String name){
        return userMapper.getUser(name) == null;
    }
    //Create user
    public int createUser(User user){
        //Get a random
        SecureRandom random = new SecureRandom();

        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insert(new User(null, user.getUsername(), encodedSalt, hashPassword, user.getfName(), user.getlName()));
    }

    //Get user by input username
    public User getUser(String username){
        return userMapper.getUser(username);
    }
}
