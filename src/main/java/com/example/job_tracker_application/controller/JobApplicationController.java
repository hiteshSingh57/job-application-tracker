package com.example.job_tracker_application.controller;

import com.example.job_tracker_application.model.JobApplication;
import com.example.job_tracker_application.repository.JobApplicationRepository;
import org.springframework.security.core.context.SecurityContextHolder;
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
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        job.setUserEmail(email);
        return repository.save(job);
    }

    @GetMapping
    public List<JobApplication> getAllJobs() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return  repository.findByUserEmail(email); }

    @PutMapping("/{id}")
    public JobApplication updateJob(@PathVariable Long id, @RequestBody JobApplication updatedJob) {
        JobApplication job = repository.findById(id).orElseThrow();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!job.getUserEmail().equals(email)) {
            throw new RuntimeException("You are not Authorized to update this job");
        }
        job.setCompanyName(updatedJob.getCompanyName());
        job.setLocation(updatedJob.getLocation());
        job.setJobRole(updatedJob.getJobRole());
        job.setSalary(updatedJob.getSalary());
        job.setStatus(updatedJob.getStatus());
        job.setIsRemote(updatedJob.getIsRemote());
        job.setApplyDate(updatedJob.getApplyDate());
        return repository.save(job);
    }

    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id ) {
        JobApplication job = repository.findById(id).orElseThrow();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!job.getUserEmail().equals(email)) {
            throw new RuntimeException("You are not Authorized to delete this job");
        }
        repository.deleteById(id);
        return "Job deleted Successfully" ;
    }
}
