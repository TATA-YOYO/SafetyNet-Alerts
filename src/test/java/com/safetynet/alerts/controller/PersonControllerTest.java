package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.PersonDtoWithAgeAndOtherMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class PersonControllerTest {

    @Autowired
    private PersonController personController;

    @Test
    void getPersonDtoWithAgeAndOtherMemberTest() {
        List<PersonDtoWithAgeAndOtherMember> personDtoWithAgeAndOtherMemberList = personController.getPersonDtoWithAgeAndOtherMember("1509 Culver St");
    }
}
