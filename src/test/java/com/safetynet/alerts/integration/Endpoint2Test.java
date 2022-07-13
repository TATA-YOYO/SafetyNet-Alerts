package com.safetynet.alerts.integration;

import com.safetynet.alerts.controller.PersonController;
import com.safetynet.alerts.services.IServiceAPI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = PersonController.class)
@AutoConfigureMockMvc
public class Endpoint2Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IServiceAPI serviceAPI;

    @Test
    void getPersonDtoWithAgeAndOtherMemberTest() throws Exception {
        mockMvc.perform(get("/childAlert")).andExpect(status().isOk());
    }
}
