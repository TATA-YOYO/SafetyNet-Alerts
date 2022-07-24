package com.safetynet.alerts.services;

import com.safetynet.alerts.controller.dto.PersonDtoWithAddressAndPhone;
import com.safetynet.alerts.models.Person;
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
    private APIService APIService;

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
        List<PersonDtoWithAddressAndPhone> personDtoWithAddressAndPhoneList = new ArrayList<>();
        for (Person person : APIService.getPersonList()){
            for (String address:addressList){
                if (person.getAddress().equals(address)){
                    PersonDtoWithAddressAndPhone personDtoWithAddressAndPhone = new PersonDtoWithAddressAndPhone();
                    personDtoWithAddressAndPhone.setFirstName(person.getFirstName());
                    personDtoWithAddressAndPhone.setLastName(person.getLastName());
                    personDtoWithAddressAndPhone.setAddress(person.getAddress());
                    personDtoWithAddressAndPhone.setPhone(person.getPhone());
                    personDtoWithAddressAndPhoneList.add(personDtoWithAddressAndPhone);
                }
            }
        }
        //Assert
        assertEquals(13, personDtoWithAddressAndPhoneList.size());
    }

    @Test
    void getAddressTest() {
        //Act
        List<String> address = APIService.getAddressList(3);
        //Assert
        assertEquals(address, addressList);
    }

    @Test
    void getMedicalRecordList() {
        //Assert
        assertNotNull(APIService.getMedicalRecordList());
    }
}
