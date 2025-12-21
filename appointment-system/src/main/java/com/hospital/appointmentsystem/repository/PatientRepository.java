package com.hospital.appointmentsystem.repository;

import com.hospital.appointmentsystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Find patient by user ID
    Optional<Patient> findByUserId(Long userId);

    // Find patient by username
    @Query("SELECT p FROM Patient p WHERE p.user.username = :username")
    Optional<Patient> findByUsername(@Param("username") String username);

    // Find patients by blood group
    List<Patient> findByBloodGroup(String bloodGroup);

    // Search patients by name (case-insensitive)
    @Query("SELECT p FROM Patient p WHERE " +
            "LOWER(p.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(p.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Patient> searchByName(@Param("searchTerm") String searchTerm);

    // Find patients by phone number
    Optional<Patient> findByPhone(String phone);
}