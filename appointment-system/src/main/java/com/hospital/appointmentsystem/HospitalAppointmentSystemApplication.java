package com.hospital.appointmentsystem;

import com.hospital.appointmentsystem.service.PatientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = "com.hospital.appointmentsystem")
public class HospitalAppointmentSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalAppointmentSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner testServices(PatientService patientService) {
        return args -> {
            System.out.println("========================================");
            System.out.println("TESTING: PatientService loaded successfully!");
            System.out.println("Number of patients: " + patientService.getAllPatients().size());
            System.out.println("========================================");
        };
    }

}