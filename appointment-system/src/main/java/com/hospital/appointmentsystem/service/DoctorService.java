package com.hospital.appointmentsystem.service;

import com.hospital.appointmentsystem.model.Doctor;
import com.hospital.appointmentsystem.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // CREATE
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // READ - Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // READ - Get doctor by ID
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
    }

    // READ - Get doctor by user ID
    public Doctor getDoctorByUserId(Long userId) {
        return doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Doctor not found for user id: " + userId));
    }

    // READ - Get available doctors
    public List<Doctor> getAvailableDoctors() {
        return doctorRepository.findByAvailableTrue();
    }

    // READ - Get doctors by specialization
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }

    // READ - Get available doctors by specialization
    public List<Doctor> getAvailableDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecializationAndAvailableTrue(specialization);
    }

    // READ - Search doctors by name
    public List<Doctor> searchDoctorsByName(String searchTerm) {
        return doctorRepository.searchByName(searchTerm);
    }

    // UPDATE
    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        Doctor doctor = getDoctorById(id);

        doctor.setFirstName(doctorDetails.getFirstName());
        doctor.setLastName(doctorDetails.getLastName());
        doctor.setSpecialization(doctorDetails.getSpecialization());
        doctor.setPhone(doctorDetails.getPhone());
        doctor.setConsultationFee(doctorDetails.getConsultationFee());
        doctor.setQualifications(doctorDetails.getQualifications());
        doctor.setYearsOfExperience(doctorDetails.getYearsOfExperience());
        doctor.setAvailable(doctorDetails.getAvailable());

        return doctorRepository.save(doctor);
    }

    // UPDATE - Toggle availability
    public Doctor toggleAvailability(Long id) {
        Doctor doctor = getDoctorById(id);
        doctor.setAvailable(!doctor.getAvailable());
        return doctorRepository.save(doctor);
    }

    // DELETE
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new RuntimeException("Doctor not found with id: " + id);
        }
        doctorRepository.deleteById(id);
    }
}