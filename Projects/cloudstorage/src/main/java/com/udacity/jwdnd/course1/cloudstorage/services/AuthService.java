package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService implements AuthenticationProvider {
    private UserMapper userMapper;
    private HashService hashService;

    public AuthService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //Let Spring security get username and password from form
        String username = authentication.getName();
        //Credentials are objects, set toString to see the actual value
        String password = authentication.getCredentials().toString();

        //Call the user object and wait for the Mapper class to call the SQL query
        //Fill the user object with Data or Null if not found in the database.
        User user = userMapper.getUser(username);
        if(user != null ){
            //Get the salt value from user object that stored in database
            String encodedSalt = user.getSalt();
            //Get the hashPassword to compare to the current password to see if there's match
            //Pass in password, and salt value to form a new hashed value
            String hashedPassword = hashService.getHashedValue(password, encodedSalt);
            if(user.getPassword().equals(hashedPassword) ){
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>() );
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //?????
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}