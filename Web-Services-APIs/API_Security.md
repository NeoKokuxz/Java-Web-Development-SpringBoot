# Security

## Authentication
- Authentication is about validating the identity of a client attempting to call a web service. Typically, identity is validated with user credentials, such as a user name and password.

## Authorization
- Authorization is the next step after authentication. So once a client is authenticated (they have proven who they are), what do they have access to? For example, what data can they view, are they allowed to change that data, etc.

# Web Security
- @EnableWebSecurity
- config Java class extends WEbSecurityConfigurerAdapter

## Web Config
- csrf() - Cross-site request forgery
- authorizeRequests()
- anyRequest()
- authenticated() - this combined with anyReuqest() means any API call requires authentication
- and().httpBasic() - allows user to use basic HTTP authentication

## Example Code for security config
```java
@Configuration
@EnableWebSecurity

//Extend the WSCA
public class DogConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    //Create internal username and password to access API service
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(encoder().encode("password"))
                .roles("USER");
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
```
