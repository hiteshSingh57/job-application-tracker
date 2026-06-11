package com.example.job_tracker_application.repository;

import com.example.job_tracker_application.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
}
