package com.hospital.appointmentsystem.repository;

import com.hospital.appointmentsystem.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    // Find all medical records for a patient
    List<MedicalRecord> findByPatientId(Long patientId);

    // Find all medical records created by a doctor
    List<MedicalRecord> findByDoctorId(Long doctorId);

    // Find patient's medical records ordered by date (most recent first)
    List<MedicalRecord> findByPatientIdOrderByRecordDateDesc(Long patientId);

    // Find medical records by diagnosis keyword
    @Query("SELECT mr FROM MedicalRecord mr WHERE " +
            "LOWER(mr.diagnosis) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<MedicalRecord> findByDiagnosisContaining(@Param("keyword") String keyword);

    // Find records between dates
    @Query("SELECT mr FROM MedicalRecord mr WHERE " +
            "mr.recordDate BETWEEN :startDate AND :endDate " +
            "ORDER BY mr.recordDate DESC")
    List<MedicalRecord> findRecordsBetweenDates(@Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);

    // Find patient's records between dates
    @Query("SELECT mr FROM MedicalRecord mr WHERE mr.patient.id = :patientId " +
            "AND mr.recordDate BETWEEN :startDate AND :endDate " +
            "ORDER BY mr.recordDate DESC")
    List<MedicalRecord> findPatientRecordsBetweenDates(@Param("patientId") Long patientId,
                                                       @Param("startDate") LocalDate startDate,
                                                       @Param("endDate") LocalDate endDate);

    // Find records with upcoming follow-up dates
    @Query("SELECT mr FROM MedicalRecord mr WHERE mr.followUpDate >= :today " +
            "ORDER BY mr.followUpDate")
    List<MedicalRecord> findRecordsWithUpcomingFollowUp(@Param("today") LocalDate today);

    // Count records for a patient
    long countByPatientId(Long patientId);
}