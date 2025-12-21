package com.hospital.appointmentsystem.service;

import com.hospital.appointmentsystem.model.Appointment;
import com.hospital.appointmentsystem.repository.AppointmentRepository;
import model.AppointmentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    // CREATE
    public Appointment createAppointment(Appointment appointment) {
        // Validate that the time slot is available
        boolean isAvailable = appointmentRepository.isTimeSlotAvailable(
                appointment.getDoctor().getId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime()
        );

        if (!isAvailable) {
            throw new RuntimeException("Time slot is not available");
        }

        // Verify doctor exists and is available
        doctorService.getDoctorById(appointment.getDoctor().getId());

        // Verify patient exists
        patientService.getPatientById(appointment.getPatient().getId());

        return appointmentRepository.save(appointment);
    }

    // READ - Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // READ - Get appointment by ID
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    }

    // READ - Get patient's appointments
    public List<Appointment> getPatientAppointments(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    // READ - Get doctor's appointments
    public List<Appointment> getDoctorAppointments(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    // READ - Get upcoming appointments for patient
    public List<Appointment> getUpcomingPatientAppointments(Long patientId) {
        return appointmentRepository.findUpcomingAppointmentsByPatient(patientId, LocalDate.now());
    }

    // READ - Get upcoming appointments for doctor
    public List<Appointment> getUpcomingDoctorAppointments(Long doctorId) {
        return appointmentRepository.findUpcomingAppointmentsByDoctor(doctorId, LocalDate.now());
    }

    // READ - Get appointments by status
    public List<Appointment> getAppointmentsByStatus(AppointmentStatus status) {
        return appointmentRepository.findByStatus(status);
    }

    // READ - Get doctor's appointments on specific date
    public List<Appointment> getDoctorAppointmentsByDate(Long doctorId, LocalDate date) {
        return appointmentRepository.findByDoctorIdAndAppointmentDate(doctorId, date);
    }

    // UPDATE
    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        Appointment appointment = getAppointmentById(id);

        // If date/time is being changed, check availability
        if (!appointment.getAppointmentDate().equals(appointmentDetails.getAppointmentDate()) ||
                !appointment.getAppointmentTime().equals(appointmentDetails.getAppointmentTime())) {

            boolean isAvailable = appointmentRepository.isTimeSlotAvailable(
                    appointmentDetails.getDoctor().getId(),
                    appointmentDetails.getAppointmentDate(),
                    appointmentDetails.getAppointmentTime()
            );

            if (!isAvailable) {
                throw new RuntimeException("New time slot is not available");
            }
        }

        appointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDetails.getAppointmentTime());
        appointment.setReason(appointmentDetails.getReason());
        appointment.setNotes(appointmentDetails.getNotes());
        appointment.setStatus(appointmentDetails.getStatus());

        return appointmentRepository.save(appointment);
    }

    // UPDATE - Change status
    public Appointment updateAppointmentStatus(Long id, AppointmentStatus status) {
        Appointment appointment = getAppointmentById(id);
        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }

    // DELETE
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Appointment not found with id: " + id);
        }
        appointmentRepository.deleteById(id);
    }

    // BUSINESS LOGIC - Cancel appointment
    public Appointment cancelAppointment(Long id) {
        return updateAppointmentStatus(id, AppointmentStatus.CANCELLED);
    }

    // BUSINESS LOGIC - Complete appointment
    public Appointment completeAppointment(Long id) {
        return updateAppointmentStatus(id, AppointmentStatus.COMPLETED);
    }
}