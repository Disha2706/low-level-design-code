package org.hospital.repositories;

import org.hospital.enums.Specialization;
import org.hospital.models.AvailableDoctor;
import org.hospital.models.Doctor;
import org.hospital.models.TimeSlot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorRepository {
    private final Map<Long, Doctor> doctors;
    private final Map<Specialization, List<Doctor>> doctorBySpecialization;
    public DoctorRepository() {
        this.doctors = new HashMap<>();
        this.doctorBySpecialization = new HashMap<>();
    }

    public void registerDoctor(Doctor doctor){
        if(doctors.containsKey(doctor.getId())) throw new RuntimeException("doctor already present");
        doctors.put(doctor.getId(), doctor);

        if(!doctorBySpecialization.containsKey(doctor.getSpecialization())){
            doctorBySpecialization.put(doctor.getSpecialization(), List.of(doctor));
        } else {
            doctorBySpecialization.get(doctor.getSpecialization()).add(doctor);
        }
    }

    public void registerAvailability(Long id, TimeSlot timeSlot){
        if(!doctors.containsKey(id)) throw new RuntimeException("Doctor not present");
        Doctor doctor = doctors.get(id);
        if(doctor.getSlotList().containsKey(timeSlot)) throw new RuntimeException(("Slot already registered"));
        doctor.getSlotList().put(timeSlot, false);
    }

    public List<Doctor> getDoctorsBySpecialization(Specialization specialization) {
        if(!doctorBySpecialization.containsKey(specialization)) return List.of();
        return doctorBySpecialization.get(specialization);
    }

    public List<AvailableDoctor> getAvailableDoctorsBySpecialization(Specialization specialization){
        List<Doctor> specializedDoctors = getDoctorsBySpecialization(specialization);
        List<AvailableDoctor> doctorList = new ArrayList<>();

        for(Doctor doctor: specializedDoctors){
            AvailableDoctor availableDoctor = new AvailableDoctor();
            availableDoctor.setDoctor(doctor);
            List<TimeSlot> timeSlots = new ArrayList<>();
            for(TimeSlot slot: doctor.getSlotList().keySet()){
                if(!doctor.getSlotList().get(slot)){
                    timeSlots.add(slot);
                }
            }
            availableDoctor.setTimeSlotList(timeSlots);
            doctorList.add(availableDoctor);
        }
        return doctorList;
    }

    public void freeSlot(Long id, TimeSlot timeSlot){
        if(!doctors.containsKey(id)) throw new RuntimeException("Doctor not present");
        Doctor doctor = doctors.get(id);
        if(!doctor.getSlotList().containsKey(timeSlot)) throw new RuntimeException(("Slot not registered"));
        doctor.getSlotList().put(timeSlot, false);
    }
}
