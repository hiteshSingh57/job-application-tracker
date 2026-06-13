package com.example.job_tracker_application.repository;

import com.example.job_tracker_application.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByUserEmail(String userEmail);
}
