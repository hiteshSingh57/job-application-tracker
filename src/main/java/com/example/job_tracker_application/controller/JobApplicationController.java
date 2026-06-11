package com.example.job_tracker_application.controller;

import com.example.job_tracker_application.model.JobApplication;
import com.example.job_tracker_application.repository.JobApplicationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobApplicationController {

    private final JobApplicationRepository repository;
    public JobApplicationController(JobApplicationRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public JobApplication createJob(@RequestBody JobApplication job) {
        return repository.save(job);
    }

    @GetMapping
    public List<JobApplication> getAllJobs() {
        return  repository.findAll();
    }
}
