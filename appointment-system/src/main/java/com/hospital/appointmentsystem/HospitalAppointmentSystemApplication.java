package com.hospital.appointmentsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.hospital.appointmentsystem")
public class HospitalAppointmentSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalAppointmentSystemApplication.class, args);
    }

}