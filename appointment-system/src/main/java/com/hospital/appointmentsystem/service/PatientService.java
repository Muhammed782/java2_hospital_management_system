package com.hospital.appointmentsystem.service;

import com.hospital.appointmentsystem.model.Patient;
import com.hospital.appointmentsystem.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // CREATE
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // READ - Get all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // READ - Get patient by ID
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
    }

    // READ - Get patient by user ID
    public Patient getPatientByUserId(Long userId) {
        return patientRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Patient not found for user id: " + userId));
    }

    // READ - Get patient by username
    public Patient getPatientByUsername(String username) {
        return patientRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Patient not found with username: " + username));
    }

    // READ - Search patients by name
    public List<Patient> searchPatientsByName(String searchTerm) {
        return patientRepository.searchByName(searchTerm);
    }

    // READ - Get patients by blood group
    public List<Patient> getPatientsByBloodGroup(String bloodGroup) {
        return patientRepository.findByBloodGroup(bloodGroup);
    }

    // UPDATE
    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient patient = getPatientById(id);

        patient.setFirstName(patientDetails.getFirstName());
        patient.setLastName(patientDetails.getLastName());
        patient.setDateOfBirth(patientDetails.getDateOfBirth());
        patient.setPhone(patientDetails.getPhone());
        patient.setAddress(patientDetails.getAddress());
        patient.setBloodGroup(patientDetails.getBloodGroup());
        patient.setEmergencyContact(patientDetails.getEmergencyContact());
        patient.setEmergencyPhone(patientDetails.getEmergencyPhone());

        return patientRepository.save(patient);
    }

    // DELETE
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient not found with id: " + id);
        }
        patientRepository.deleteById(id);
    }
}