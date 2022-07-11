package com.safetynet.alerts;

import com.safetynet.alerts.controller.dto.PersonDto;
import com.safetynet.alerts.services.ServiceAPI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private ServiceAPI serviceAPI;

    @Test
    void getPersonToShareList() {
        //ARRANGE
        List<String> addressList = new ArrayList<>();
        addressList.add("834 Binoc Ave");
        addressList.add("748 Townings Dr");
        addressList.add("112 Steppes Pl");
        addressList.add("1509 Culver St");
        //Act
List<PersonDto> personDtoList = serviceAPI.getPersonToShareList(addressList);
        //Assert
        assertEquals(11,personDtoList.size() );
    }

}
