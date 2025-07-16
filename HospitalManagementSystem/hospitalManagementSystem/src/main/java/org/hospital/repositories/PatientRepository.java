package org.hospital.repositories;

import org.hospital.models.Patient;

import java.util.HashMap;
import java.util.Map;

public class PatientRepository {
    Map<Long, Patient> patients;

    public PatientRepository(){
        patients = new HashMap<>();
    }

    public void registerPatient(Patient patient){
        if(patients.containsKey(patient.getId())) throw new RuntimeException("Patient already present");
        patients.put(patient.getId(), patient);
    }

    public Boolean isPatientRegistered(Long patientId){
        return patients.containsKey(patientId);
    }
}
