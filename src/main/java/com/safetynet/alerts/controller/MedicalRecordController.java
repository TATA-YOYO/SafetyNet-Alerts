package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.MedicalRecordDto;
import com.safetynet.alerts.services.IServiceAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class MedicalRecordController {

    private static final Logger logger = LogManager.getLogger("PersonController");

    @Autowired
    private RequestCounter requestCounter;

    @Autowired
    private IServiceAPI serviceAPI;

    @PostMapping("/medicalRecord")
    public ResponseEntity<MedicalRecordDto> createMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto) {
        int requestNumber = requestCounter.addRequest();
        logger.info("Query N°" + requestNumber + " : " + "POST http://localhost:8080/medicalRecord" + medicalRecordDto);
        if (serviceAPI.saveMedicalRecord(medicalRecordDto)) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(medicalRecordDto.getFirstName()+medicalRecordDto.getLastName())
                    .toUri();
            logger.info("Response of Query N°" + requestNumber + ": " + "Code 201");
            return ResponseEntity.created(location).build();
        }
        logger.info("Response of Query N°" + requestNumber + ": " + "Code 204");
        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/medicalRecord")
    public MedicalRecordDto updateMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto){
        int requestNumber = requestCounter.addRequest();
        logger.info("Query N°" + requestNumber + " : " + "PUT http://localhost:8080/medicalRecord" + medicalRecordDto);
        if(serviceAPI.updateMedicalRecord(medicalRecordDto)){
            logger.info("Response of Query N°" + requestNumber + ": " + medicalRecordDto);
            return medicalRecordDto;
        }
        MedicalRecordDto medicalRecordDto1 = new MedicalRecordDto();
        logger.info("Response of Query N°" + requestNumber + ": " + medicalRecordDto1);
        return medicalRecordDto1;
    }

    @DeleteMapping("/medicalRecord")
    public  MedicalRecordDto removeMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto){
        int requestNumber = requestCounter.addRequest();
        logger.info("Query N°" + requestNumber + " : " + "DELETE http://localhost:8080/medicalRecord" + medicalRecordDto);
        if(serviceAPI.removeMedicalRecord(medicalRecordDto)){
            logger.info("Response of Query N°" + requestNumber + ": " + medicalRecordDto);
            return medicalRecordDto;
        }
        MedicalRecordDto medicalRecordDto1 = new MedicalRecordDto();
        logger.info("Response of Query N°" + requestNumber + ": " + medicalRecordDto1);
        return medicalRecordDto1;
    }
}
