package org.aviation.control.queue.web.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
public abstract class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void productList() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/enqueuedAircraftList").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
