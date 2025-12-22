package com.hospital.appointmentsystem.controller;

import com.hospital.appointmentsystem.model.MedicalRecord;
import com.hospital.appointmentsystem.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
@CrossOrigin(origins = "*")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    // CREATE
    @PostMapping
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord record) {
        try {
            MedicalRecord created = medicalRecordService.createMedicalRecord(record);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ - Get all medical records
    @GetMapping
    public ResponseEntity<List<MedicalRecord>> getAllMedicalRecords() {
        try {
            List<MedicalRecord> records = medicalRecordService.getAllMedicalRecords();
            if (records.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(records, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ - Get medical record by ID
    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable Long id) {
        try {
            MedicalRecord record = medicalRecordService.getMedicalRecordById(id);
            return new ResponseEntity<>(record, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // READ - Get patient's medical records
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalRecord>> getPatientMedicalRecords(@PathVariable Long patientId) {
        try {
            List<MedicalRecord> records = medicalRecordService.getPatientMedicalRecords(patientId);
            return new ResponseEntity<>(records, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ - Get doctor's medical records
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<MedicalRecord>> getDoctorMedicalRecords(@PathVariable Long doctorId) {
        try {
            List<MedicalRecord> records = medicalRecordService.getDoctorMedicalRecords(doctorId);
            return new ResponseEntity<>(records, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ - Search by diagnosis
    @GetMapping("/search")
    public ResponseEntity<List<MedicalRecord>> searchByDiagnosis(@RequestParam String keyword) {
        try {
            List<MedicalRecord> records = medicalRecordService.searchByDiagnosis(keyword);
            return new ResponseEntity<>(records, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ - Get records with upcoming follow-ups
    @GetMapping("/follow-ups")
    public ResponseEntity<List<MedicalRecord>> getUpcomingFollowUps() {
        try {
            List<MedicalRecord> records = medicalRecordService.getRecordsWithUpcomingFollowUp();
            return new ResponseEntity<>(records, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(
            @PathVariable Long id,
            @RequestBody MedicalRecord record) {
        try {
            MedicalRecord updated = medicalRecordService.updateMedicalRecord(id, record);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMedicalRecord(@PathVariable Long id) {
        try {
            medicalRecordService.deleteMedicalRecord(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // BUSINESS LOGIC - Count patient records
    @GetMapping("/patient/{patientId}/count")
    public ResponseEntity<Long> countPatientRecords(@PathVariable Long patientId) {
        try {
            long count = medicalRecordService.countPatientRecords(patientId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}