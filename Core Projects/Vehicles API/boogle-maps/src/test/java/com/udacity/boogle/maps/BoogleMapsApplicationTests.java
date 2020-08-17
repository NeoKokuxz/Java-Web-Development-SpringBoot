package com.udacity.boogle.maps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@WebMvcTest(MapsController.class)
public class BoogleMapsApplicationTests {

		@Autowired
		private MockMvc mockMvc;

		@Test
		public void getLocation() throws Exception {
			Double lat = 40.0;
			Double lon = 25.0;
			mockMvc.perform(get("/maps?lat=" + lat + "&lon=" + lon))
					.andExpect(status().isOk());
		}
}
