package com.companyZ.employeeapp;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private Scanner input = new Scanner(System.in);
    private EmployeeDAO dao = new EmployeeDAOImpl();
    private EmployeeService service = new EmployeeService();
    private ReportService reports = new ReportService();

    public void start() {
        while (true) {
            System.out.println("""
                \n===== Employee Management System =====
                1. Insert Employee
                2. Update Employee
                3. Delete Employee
                4. Search Employee
                5. Apply Salary Increase (Range)
                6. Reports
                7. Exit
                Enter choice:
                """);

            int choice = input.nextInt();
            input.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> insertEmployee();
                case 2 -> updateEmployee();
                case 3 -> deleteEmployee();
                case 4 -> searchEmployee();
                case 5 -> applySalaryIncrease();
                case 6 -> showReports();
                case 7 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void insertEmployee() {
        Employee e = new Employee();

        System.out.print("First name: ");
        e.setFirstName(input.nextLine());

        System.out.print("Last name: ");
        e.setLastName(input.nextLine());

        System.out.print("Job title: ");
        e.setJobTitle(input.nextLine());

        System.out.print("Division: ");
        e.setDivision(input.nextLine());

        System.out.print("Salary: ");
        e.setSalary(input.nextDouble());
        input.nextLine();

        System.out.print("Hire date (YYYY-MM-DD): ");
        e.setHireDate(input.nextLine());

        System.out.print("SSN (9 digits, no dashes): ");
        e.setSsn(input.nextLine());

        System.out.print("Is full-time? (true/false): ");
        e.setFullTime(input.nextBoolean());
        input.nextLine();

        if (dao.insertEmployee(e))
            System.out.println("Employee inserted.");
        else
            System.out.println("Error inserting employee.");
    }

    private void updateEmployee() {
        System.out.print("Enter employee ID to update: ");
        int id = input.nextInt();
        input.nextLine();

        Employee e = dao.getEmployeeById(id);
        if (e == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.println("Current data: " + e);

        System.out.print("New job title: ");
        e.setJobTitle(input.nextLine());

        System.out.print("New division: ");
        e.setDivision(input.nextLine());

        System.out.print("New salary: ");
        e.setSalary(input.nextDouble());
        input.nextLine();

        if (dao.updateEmployee(e))
            System.out.println("Updated successfully.");
        else
            System.out.println("Update failed.");
    }

    private void deleteEmployee() {
        System.out.print("Enter employee ID to delete: ");
        int id = input.nextInt();
        input.nextLine();

        if (dao.deleteEmployee(id))
            System.out.println("Deleted successfully.");
        else
            System.out.println("Delete failed.");
    }

    private void searchEmployee() {
        System.out.println("""
            Search by:
            1. Employee ID
            2. Name
            3. SSN
            """);

        int type = input.nextInt();
        input.nextLine();

        switch (type) {
            case 1 -> {
                System.out.print("Enter ID: ");
                Employee e = dao.getEmployeeById(input.nextInt());
                System.out.println(e != null ? e : "Not found");
            }
            case 2 -> {
                System.out.print("Enter name: ");
                List<Employee> list = dao.getEmployeesByName(input.nextLine());
                list.forEach(System.out::println);
            }
            case 3 -> {
                System.out.print("Enter SSN: ");
                Employee e = dao.getEmployeeBySSN(input.nextLine());
                System.out.println(e != null ? e : "Not found");
            }
            default -> System.out.println("Invalid option.");
        }
    }

    private void applySalaryIncrease() {
        System.out.print("Min salary: ");
        double min = input.nextDouble();

        System.out.print("Max salary: ");
        double max = input.nextDouble();

        System.out.print("Increase percentage: ");
        double percent = input.nextDouble();
        input.nextLine();

        int updated = service.applySalaryIncrease(min, max, percent);
        System.out.println("Updated " + updated + " employees.");
    }

    private void showReports() {
        System.out.println("""
            1. Full-time employee report
            2. Total pay by job title (month)
            3. Total pay by division (month)
            """);

        int type = input.nextInt();
        input.nextLine();

        switch (type) {
            case 1 -> reports.fullTimeWithHistory();
            case 2 -> {
                System.out.print("Enter month (YYYY-MM): ");
                reports.totalPayByJobTitle(input.nextLine());
            }
            case 3 -> {
                System.out.print("Enter month (YYYY-MM): ");
                reports.totalPayByDivision(input.nextLine());
            }
            default -> System.out.println("Invalid option.");
        }
    }
}
