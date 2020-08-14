package com.example.dogdata;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DogdataApplicationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllDogs(){
        ResponseEntity<List> response =
                this.restTemplate.withBasicAuth("admin", "password").getForEntity("http://localhost:" + port + "/dogs", List.class);
        System.out.println(response);

        assertThat(response.getStatusCode(), Matchers.equalTo(HttpStatus.OK));
    }

    @Test
    public void getDog(){
        ResponseEntity<String> response =
                this.restTemplate.withBasicAuth("admin", "password").getForEntity("http://localhost:" + port + "/dogs/breed/1", String.class);

        assertThat(response.getStatusCode(), Matchers.equalTo(HttpStatus.OK));
    }
}
