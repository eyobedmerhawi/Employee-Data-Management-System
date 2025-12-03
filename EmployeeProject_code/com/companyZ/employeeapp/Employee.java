package com.companyZ.employeeapp;

public class Employee {
    private int empid;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String division;
    private double salary;
    private String hireDate; // store as String "YYYY-MM-DD" for simplicity
    private String ssn;      // 9 digits, no dashes
    private boolean fullTime;

    public Employee() {}

    public Employee(int empid, String firstName, String lastName,
                    String jobTitle, String division, double salary,
                    String hireDate, String ssn, boolean fullTime) {
        this.empid = empid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.division = division;
        this.salary = salary;
        this.hireDate = hireDate;
        this.ssn = ssn;
        this.fullTime = fullTime;
    }

    // Getters
    public int getEmpid() {
        return empid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getDivision() {
        return division;
    }

    public double getSalary() {
        return salary;
    }

    public String getHireDate() {
        return hireDate;
    }

    public String getSsn() {
        return ssn;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    // Setters
    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    @Override
    public String toString() {
        return "\nEmployeeID: " + empid +
                "\nName: " + firstName + " " + lastName +
                "\nJob Title: " + jobTitle +
                "\nDivision: " + division +
                "\nSalary: $" + salary +
                "\nHire Date: " + hireDate +
                "\nSSN: " + ssn +
                "\nFull-Time: " + fullTime +
                "\n-----------------------------";
    }
}
