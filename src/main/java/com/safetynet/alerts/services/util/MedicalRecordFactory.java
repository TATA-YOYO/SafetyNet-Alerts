package com.safetynet.alerts.services.util;

import com.safetynet.alerts.controller.dto.MedicalRecordDto;
import com.safetynet.alerts.models.MedicalRecord;

public class MedicalRecordFactory {
   public static MedicalRecord getMedicalRecord(MedicalRecordDto medicalRecordDto){
       MedicalRecord medicalRecord = new MedicalRecord();
       medicalRecord.setFirstName(medicalRecordDto.getFirstName());
       medicalRecord.setLastName(medicalRecordDto.getLastName());
       medicalRecord.setBirthdate(medicalRecordDto.getBirthdate());
       medicalRecord.setMedications(medicalRecordDto.getMedications());
       medicalRecord.setAllergies(medicalRecordDto.getAllergies());
       medicalRecord.concatenate();
       return medicalRecord;
   }
}
