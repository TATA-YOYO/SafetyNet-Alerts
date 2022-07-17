package com.safetynet.alerts.services;

import com.safetynet.alerts.controller.dto.PersonDtoWithAddressAndPhone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ServiceTest {

    private final List<String> addressList = new ArrayList<>();

    @Autowired
    private ServiceAPI serviceAPI;

    @BeforeEach
    public void settings() {
        addressList.add("1509 Culver St");
        addressList.add("834 Binoc Ave");
        addressList.add("748 Townings Dr");
        addressList.add("112 Steppes Pl");
        addressList.add("748 Townings Dr");
    }


    @Test
    void getPersonToShareList() {
        //Act
        List<PersonDtoWithAddressAndPhone> personWithAddressAndPhoneDtoList = serviceAPI.getPersonDtoWithAddressAndPhoneList(addressList);
        //Assert
        assertEquals(13, personWithAddressAndPhoneDtoList.size());
    }

    @Test
    void getAddressTest() {
        //Act
        List<String> address = serviceAPI.getAddressList(3);
        //Assert
        assertEquals(address, addressList);
    }

    @Test
    void getMedicalRecordList() {
        //Assert
        assertNotNull(serviceAPI.getMedicalRecordList());
    }
}
