package com.safetynet.alerts;

import com.safetynet.alerts.controller.dto.PersonDto;
import com.safetynet.alerts.services.ServiceAPI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private ServiceAPI serviceAPI;

    @Test
    void getPersonToShareList() {
        //Act
        List<PersonDto> personList = serviceAPI.getPersonToShareList("1509 Culver St");
        int sizeList = personList.size();

        //Assert
        assertEquals(5, sizeList);
    }

}
