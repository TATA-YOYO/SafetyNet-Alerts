package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.MedicalRecordDto;
import com.safetynet.alerts.services.IServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/medicalRecord")
    public  MedicalRecordDto removeMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto){
        if(serviceAPI.removeMedicalRecord(medicalRecordDto)){
            return medicalRecordDto;
        }
        return new MedicalRecordDto();
    }
}
