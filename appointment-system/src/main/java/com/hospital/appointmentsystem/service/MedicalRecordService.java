package com.hospital.appointmentsystem.service;

import com.hospital.appointmentsystem.model.MedicalRecord;
import com.hospital.appointmentsystem.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private com.hospital.appointmentsystem.service.PatientService patientService;

    @Autowired
    private com.hospital.appointmentsystem.service.DoctorService doctorService;

    // CREATE
    public MedicalRecord createMedicalRecord(MedicalRecord record) {
        // Verify patient and doctor exist
        patientService.getPatientById(record.getPatient().getId());
        doctorService.getDoctorById(record.getDoctor().getId());

        return medicalRecordRepository.save(record);
    }

    // READ - Get all records
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }

    // READ - Get record by ID
    public MedicalRecord getMedicalRecordById(Long id) {
        return medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical record not found with id: " + id));
    }

    // READ - Get patient's medical records
    public List<MedicalRecord> getPatientMedicalRecords(Long patientId) {
        return medicalRecordRepository.findByPatientIdOrderByRecordDateDesc(patientId);
    }

    // READ - Get records created by a doctor
    public List<MedicalRecord> getDoctorMedicalRecords(Long doctorId) {
        return medicalRecordRepository.findByDoctorId(doctorId);
    }

    // READ - Search by diagnosis
    public List<MedicalRecord> searchByDiagnosis(String keyword) {
        return medicalRecordRepository.findByDiagnosisContaining(keyword);
    }

    // READ - Get records with upcoming follow-ups
    public List<MedicalRecord> getRecordsWithUpcomingFollowUp() {
        return medicalRecordRepository.findRecordsWithUpcomingFollowUp(LocalDate.now());
    }

    // UPDATE
    public MedicalRecord updateMedicalRecord(Long id, MedicalRecord recordDetails) {
        MedicalRecord record = getMedicalRecordById(id);

        record.setDiagnosis(recordDetails.getDiagnosis());
        record.setPrescription(recordDetails.getPrescription());
        record.setNotes(recordDetails.getNotes());
        record.setSymptoms(recordDetails.getSymptoms());
        record.setTestResults(recordDetails.getTestResults());
        record.setFollowUpDate(recordDetails.getFollowUpDate());

        return medicalRecordRepository.save(record);
    }

    // DELETE
    public void deleteMedicalRecord(Long id) {
        if (!medicalRecordRepository.existsById(id)) {
            throw new RuntimeException("Medical record not found with id: " + id);
        }
        medicalRecordRepository.deleteById(id);
    }

    // BUSINESS LOGIC - Count patient records
    public long countPatientRecords(Long patientId) {
        return medicalRecordRepository.countByPatientId(patientId);
    }
}