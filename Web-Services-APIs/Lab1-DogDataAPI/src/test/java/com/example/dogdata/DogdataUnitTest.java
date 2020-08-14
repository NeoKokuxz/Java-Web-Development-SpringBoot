package com.example.dogdata;

import com.example.dogdata.controller.DogController;
import com.example.dogdata.service.DogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//Test DogController class
@WebMvcTest(DogController.class)
public class DogdataUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DogService dogService;

    @Test
    @WithMockUser(username = "admin", password = "password", roles = "USER")
    public void getAllDogs() throws Exception{
        //Simulate HTTP request
        mockMvc.perform(get("/dogs"))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(content().json("[]"));

        verify(dogService, times(1)).retrieveDogs();
    }

}
