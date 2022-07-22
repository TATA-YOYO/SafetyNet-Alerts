package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.MedicalRecordDto;
import com.safetynet.alerts.services.IServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class MedicalRecordController {

    @Autowired
    private IServiceAPI serviceAPI;

    @PostMapping("/medicalRecord")
    public ResponseEntity<MedicalRecordDto> createMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto) {
        if (serviceAPI.saveMedicalRecord(medicalRecordDto)) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(medicalRecordDto.getFirstName()+medicalRecordDto.getLastName())
                    .toUri();
            return ResponseEntity.created(location).build();
        }
        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/medicalRecord")
    public MedicalRecordDto updateMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto){
        if(serviceAPI.updateMedicalRecord(medicalRecordDto)){
            return medicalRecordDto;
        }
        return medicalRecordDto;
    }

    @DeleteMapping("/medicalRecord")
    public  MedicalRecordDto removeMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto){
        if(serviceAPI.removeMedicalRecord(medicalRecordDto)){
            return medicalRecordDto;
        }
        return medicalRecordDto;
    }
}
