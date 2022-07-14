package com.safetynet.alerts.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class FireStationControllerTest {
    @Autowired
    private FireStationController fireStationController;

    @Test
    public void getPhoneListTest(){
        Map<String, Integer> resultList = fireStationController.getPhoneList(1);

        //Assert
        assertEquals(4,resultList.size());
    }
}
