package com.companyZ.employeeapp;

import java.util.List;

public interface EmployeeDAO {
    boolean insertEmployee(Employee e);
    boolean updateEmployee(Employee e);
    boolean deleteEmployee(int empid);
    Employee getEmployeeById(int empid);
    Employee getEmployeeBySSN(String ssn);
    List<Employee> getEmployeesByName(String name);
    List<Employee> getEmployeesBySalaryRange(double min, double max);
    List<Employee> getAllEmployees();
}
