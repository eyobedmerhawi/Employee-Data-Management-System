package com.companyZ.employeeapp;

import java.util.List;

public class EmployeeService {

    private EmployeeDAO dao = new EmployeeDAOImpl();

    public int applySalaryIncrease(double min, double max, double percentage) {
        List<Employee> employees = dao.getEmployeesBySalaryRange(min, max);
        int count = 0;

        for (Employee e : employees) {
            double newSalary = e.getSalary() * (1 + (percentage / 100));
            e.setSalary(Math.round(newSalary * 100.0) / 100.0);
            if (dao.updateEmployee(e)) count++;
        }

        return count;
    }
}
