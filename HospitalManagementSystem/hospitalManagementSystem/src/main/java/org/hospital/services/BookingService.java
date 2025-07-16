package org.hospital.services;

import lombok.extern.slf4j.Slf4j;
import org.hospital.exceptions.SlotUnavailableException;
import org.hospital.models.Doctor;
import org.hospital.models.Patient;
import org.hospital.models.TimeSlot;
import org.hospital.repositories.DoctorRepository;
import org.hospital.repositories.PatientRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class BookingService {
    private final DoctorRepository doctorsRepository;
    private final PatientRepository patientsRepository;
    private final Map<Long, List<TimeSlot>> patientSlots;

    public BookingService(DoctorRepository doctorsRepository, PatientRepository patientsRepository){
        this.doctorsRepository = doctorsRepository;
        this.patientsRepository = patientsRepository;
        this.patientSlots = new HashMap<>();
    }

    public void bookAppointment(Patient patient, Doctor doctor, String fromSlot){
        if(!patientsRepository.isPatientRegistered(patient.getId()))
            throw new RuntimeException("The patient does not exist in Flipmed!");

        //Checking if doctor exists

        if(patientSlots.containsKey(patient.getId())) {
            for(TimeSlot slot : patientSlots.get(patient.getId())) {
                if(slot.getStartTime().equals(fromSlot))
                    throw new RuntimeException("This slot is already booked for the patient!");
            }
        } else {
            patientSlots.put(patient.getId(), new ArrayList<>());
        }

        //Checking if doctor is available for that slot
        Map<TimeSlot, Boolean> doctorAvailability = doctor.getSlotList();

        for(TimeSlot slot : doctorAvailability.keySet()) {
            if(slot.getStartTime().equals(fromSlot) && !doctorAvailability.get(slot)) {
                doctorAvailability.put(slot, true);
                patientSlots.get(patient.getId()).add(slot);
                doctor.getAllBookings().put(patient,slot);
                patient.getBookedSlots().computeIfPresent(doctor, (key, val)-> {val.add(slot); return val;});
                patient.getBookedSlots().computeIfAbsent(doctor, (doctor1)->List.of(slot));
//                print.printData("Appointment Booked Successfully, Booking id "+ booking.getBookingId());
                System.out.println("Appointment Booked Successfully");
                return;
            }
        }
    }

    public void cancelAppointment(Doctor doctor, Patient patient, String startTime){

    }
}
