package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.MedicalRecordDto;
import com.safetynet.alerts.services.IServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalRecordController {

    @Autowired
    private IServiceAPI serviceAPI;

    @PostMapping("/medicalRecord")
    public MedicalRecordDto createMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto) {
        if (serviceAPI.saveMedicalRecord(medicalRecordDto)) {
            return medicalRecordDto;
        }
        return new MedicalRecordDto();
    }

    @PutMapping("/medicalRecord")
    public MedicalRecordDto updateMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto){
        if(serviceAPI.updateMedicalRecord(medicalRecordDto)){
            return medicalRecordDto;
        }
        return new MedicalRecordDto();
    }
}
