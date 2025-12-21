package com.hospital.appointmentsystem.repository;

import com.hospital.appointmentsystem.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // Find doctor by user ID
    Optional<Doctor> findByUserId(Long userId);

    // Find doctor by username
    @Query("SELECT d FROM Doctor d WHERE d.user.username = :username")
    Optional<Doctor> findByUsername(@Param("username") String username);

    // Find doctors by specialization
    List<Doctor> findBySpecialization(String specialization);

    // Find all available doctors
    List<Doctor> findByAvailableTrue();

    // Find available doctors by specialization
    List<Doctor> findBySpecializationAndAvailableTrue(String specialization);

    // Search doctors by name
    @Query("SELECT d FROM Doctor d WHERE " +
            "LOWER(d.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(d.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Doctor> searchByName(@Param("searchTerm") String searchTerm);

    // Find doctors with experience greater than specified years
    @Query("SELECT d FROM Doctor d WHERE d.yearsOfExperience >= :years")
    List<Doctor> findByMinimumExperience(@Param("years") Integer years);
}