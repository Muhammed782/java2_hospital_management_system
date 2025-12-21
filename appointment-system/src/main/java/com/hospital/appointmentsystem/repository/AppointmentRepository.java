package com.hospital.appointmentsystem.repository;

import com.hospital.appointmentsystem.model.Appointment;
import model.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Find all appointments for a specific patient
    List<Appointment> findByPatientId(Long patientId);

    // Find all appointments for a specific doctor
    List<Appointment> findByDoctorId(Long doctorId);

    // Find appointments by status
    List<Appointment> findByStatus(AppointmentStatus status);

    // Find appointments for a patient by status
    List<Appointment> findByPatientIdAndStatus(Long patientId, AppointmentStatus status);

    // Find appointments for a doctor by status
    List<Appointment> findByDoctorIdAndStatus(Long doctorId, AppointmentStatus status);

    // Find appointments on a specific date
    List<Appointment> findByAppointmentDate(LocalDate date);

    // Find doctor's appointments on a specific date
    List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate date);

    // Check if time slot is available
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN false ELSE true END " +
            "FROM Appointment a WHERE a.doctor.id = :doctorId " +
            "AND a.appointmentDate = :date AND a.appointmentTime = :time " +
            "AND a.status != 'CANCELLED'")
    boolean isTimeSlotAvailable(@Param("doctorId") Long doctorId,
                                @Param("date") LocalDate date,
                                @Param("time") LocalTime time);

    // Find upcoming appointments for a patient
    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientId " +
            "AND a.appointmentDate >= :today ORDER BY a.appointmentDate, a.appointmentTime")
    List<Appointment> findUpcomingAppointmentsByPatient(@Param("patientId") Long patientId,
                                                        @Param("today") LocalDate today);

    // Find upcoming appointments for a doctor
    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId " +
            "AND a.appointmentDate >= :today ORDER BY a.appointmentDate, a.appointmentTime")
    List<Appointment> findUpcomingAppointmentsByDoctor(@Param("doctorId") Long doctorId,
                                                       @Param("today") LocalDate today);
}