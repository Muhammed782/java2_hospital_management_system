package com.hospital.appointmentsystem.repository;

import com.hospital.appointmentsystem.model.User;
import model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by username
    Optional<User> findByUsername(String username);

    // Find user by email
    Optional<User> findByEmail(String email);

    // Check if username exists
    boolean existsByUsername(String username);

    // Check if email exists
    boolean existsByEmail(String email);

    // Find all users by role
    List<User> findByRole(Role role);

    // Find all active users
    List<User> findByActiveTrue();
}