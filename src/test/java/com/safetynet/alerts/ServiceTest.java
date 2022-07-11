package com.safetynet.alerts;

import com.safetynet.alerts.controller.dto.PersonDto;
import com.safetynet.alerts.services.ServiceAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ServiceTest {
    private List<String> addressList = new ArrayList<>();
    @Autowired
    private ServiceAPI serviceAPI;

    @BeforeEach
    public void setings() {
        addressList.add("1509 Culver St");
        addressList.add("834 Binoc Ave");
        addressList.add("748 Townings Dr");
        addressList.add("112 Steppes Pl");
        addressList.add("748 Townings Dr");



    }


    @Test
    void getPersonToShareList() {
        //Act
        List<PersonDto> personDtoList = serviceAPI.getPersonToShareList(addressList);
        //Assert
        assertEquals(13, personDtoList.size());
    }

    @Test
    void getAddressTest() {
        //Act
        List<String> address = serviceAPI.getAddress(3);
        //Assert
        assertTrue(address.equals(addressList));
    }

}
