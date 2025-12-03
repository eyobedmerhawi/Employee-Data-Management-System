package com.companyZ.employeeapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public boolean insertEmployee(Employee e) {
        String sql = "INSERT INTO employees (first_name, last_name, job_title, division, salary, hire_date, ssn, is_full_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getFirstName());
            ps.setString(2, e.getLastName());
            ps.setString(3, e.getJobTitle());
            ps.setString(4, e.getDivision());
            ps.setDouble(5, e.getSalary());
            ps.setString(6, e.getHireDate());
            ps.setString(7, e.getSsn());
            ps.setBoolean(8, e.isFullTime());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Insert error: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateEmployee(Employee e) {
        String sql = "UPDATE employees SET first_name=?, last_name=?, job_title=?, division=?, salary=?, hire_date=?, ssn=?, is_full_time=? WHERE empid=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getFirstName());
            ps.setString(2, e.getLastName());
            ps.setString(3, e.getJobTitle());
            ps.setString(4, e.getDivision());
            ps.setDouble(5, e.getSalary());
            ps.setString(6, e.getHireDate());
            ps.setString(7, e.getSsn());
            ps.setBoolean(8, e.isFullTime());
            ps.setInt(9, e.getEmpid());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Update error: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteEmployee(int empid) {
        String sql = "DELETE FROM employees WHERE empid=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, empid);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Delete error: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public Employee getEmployeeById(int empid) {
        String sql = "SELECT * FROM employees WHERE empid=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, empid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return extractEmployee(rs);

        } catch (SQLException ex) {
            System.out.println("Get by ID error: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public Employee getEmployeeBySSN(String ssn) {
        String sql = "SELECT * FROM employees WHERE ssn=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ssn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return extractEmployee(rs);

        } catch (SQLException ex) {
            System.out.println("Get by SSN error: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE first_name LIKE ? OR last_name LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + name + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(extractEmployee(rs));

        } catch (SQLException ex) {
            System.out.println("Search by name error: " + ex.getMessage());
        }
        return list;
    }

    @Override
    public List<Employee> getEmployeesBySalaryRange(double min, double max) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE salary >= ? AND salary < ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, min);
            ps.setDouble(2, max);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(extractEmployee(rs));

        } catch (SQLException ex) {
            System.out.println("Salary range error: " + ex.getMessage());
        }
        return list;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(extractEmployee(rs));

        } catch (SQLException ex) {
            System.out.println("Get all error: " + ex.getMessage());
        }
        return list;
    }

    private Employee extractEmployee(ResultSet rs) throws SQLException {
        Employee e = new Employee();
        e.setEmpid(rs.getInt("empid"));
        e.setFirstName(rs.getString("first_name"));
        e.setLastName(rs.getString("last_name"));
        e.setJobTitle(rs.getString("job_title"));
        e.setDivision(rs.getString("division"));
        e.setSalary(rs.getDouble("salary"));
        e.setHireDate(rs.getString("hire_date"));
        e.setSsn(rs.getString("ssn"));
        e.setFullTime(rs.getBoolean("is_full_time"));
        return e;
    }
}
