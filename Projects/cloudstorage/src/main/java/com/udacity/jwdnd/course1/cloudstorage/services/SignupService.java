package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * This class contains all method for Sign up services
 *
 * @Author Neo Chen
 *
 */
@Service
public class SignupService {
    UserMapper userMapper;
    HashService hashService;

    public SignupService(UserMapper userMapper, HashService hashService){
        this.hashService = hashService;
        this.userMapper = userMapper;
    }

    /**
     * The checkUsername method will take input name and check if duplicate in userMapper
     *
     * @param name
     * @return true when no duplicate found, false when duplicate found
     */
    public boolean checkUsername(String name){
        return userMapper.getUser(name) == null;
    }

    /**
     * The createUser method will take in user object and encrypt the password with salt
     * using hash service then insert via userMapper.
     *
     * @param user
     * @return int value
     */
    public int createUser(User user){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insert(new User(null, user.getUsername(), encodedSalt, hashPassword, user.getFName(), user.getLName()));
    }

    /**
     * The getUser method select user via userMapper by username
     *
     * @param username
     * @return user object
     */
    public User getUser(String username){
        return userMapper.getUser(username);
    }
}
