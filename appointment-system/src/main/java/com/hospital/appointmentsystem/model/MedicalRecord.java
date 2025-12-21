package com.hospital.appointmentsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "medical_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private com.hospital.appointmentsystem.model.Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private com.hospital.appointmentsystem.model.Doctor doctor;

    @Column(nullable = false, length = 1000)
    private String diagnosis;

    @Column(length = 2000)
    private String prescription;

    @Column(name = "record_date", nullable = false)
    private LocalDate recordDate;

    @Column(length = 2000)
    private String notes;

    @Column(name = "symptoms", length = 1000)
    private String symptoms;

    @Column(name = "test_results", length = 2000)
    private String testResults;

    @Column(name = "follow_up_date")
    private LocalDate followUpDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (recordDate == null) {
            recordDate = LocalDate.now();
        }
    }
}