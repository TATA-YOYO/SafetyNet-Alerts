package com.safetynet.alerts;

import com.safetynet.alerts.controller.FireStationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class FireStationControllerIT {
  @Autowired
    FireStationController fireStationController;

  @Test
    public void getPersonDtoWithAgeAndOtherMemberTest(){
      fireStationController.getPersonDtoWithAgeAndOtherMember("1509 Culver St");
  }

}
