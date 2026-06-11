package com.example.job_tracker_application.model;

import jakarta.persistence.*;

@Entity
@Table(name= "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     private String companyName;
     private String jobRole;
     private String location;
     private String status; //applied , pending , interview , rejected , offer
     private Double salary;
     private Boolean isRemote;
     private String applyDate;

     public JobApplication() {}
     public  JobApplication(String companyName, String jobRole, String location,
                            String status, Double salary, Boolean isRemote, String applyDate ) {
         this.companyName = companyName;
         this.jobRole = jobRole;
         this.location = location;
         this.status = status;
         this.salary = salary;
         this.isRemote = isRemote;
         this.applyDate = applyDate;

     }

     public Long getId () {
         return id;
     }

     public String getCompanyName() {
         return companyName;
     }
     public void setCompanyName(String companyName) {
         this.companyName = companyName;
     }

     public String getJobRole() {
         return  jobRole;
     }
     public  void setJobRole(String jobRole) {
         this.jobRole = jobRole;
     }

     public String getLocation() {
         return  location;
     }
     public void setLocation(String location) {
         this.location = location;
     }

     public String getStatus(){
         return status;
     }
     public void setStatus(String status) {
         this.status = status;
     }

     public Double getSalary() {
         return salary;
     }
     public void setSalary(Double salary) {
         this.salary = salary;
     }

     public Boolean getIsRemote() {
         return isRemote;
     }
     public void setIsRemote(Boolean isRemote) {
         this.isRemote = isRemote;
     }

     public String getApplyDate(){
         return applyDate;
     }
     public void setApplyDate(String applyDate) {
         this.applyDate = applyDate;
     }
}
