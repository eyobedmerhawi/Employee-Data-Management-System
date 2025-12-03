package com.companyZ.employeeapp;

import java.sql.*;

public class ReportService {

    public void fullTimeWithHistory() {
        String sql = """
        SELECT e.empid, e.first_name, e.last_name, e.job_title, p.pay_date, p.pay_amount
        FROM employees e
        LEFT JOIN pay_statements p ON e.empid = p.empid
        WHERE e.is_full_time = TRUE
        ORDER BY e.empid, p.pay_date;
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println("EmpID: " + rs.getInt("empid") +
                        " | " + rs.getString("first_name") + " " + rs.getString("last_name") +
                        " | Title: " + rs.getString("job_title") +
                        " | Pay Date: " + rs.getString("pay_date") +
                        " | Pay Amount: " + rs.getDouble("pay_amount"));
            }

        } catch (SQLException e) {
            System.out.println("Report error: " + e.getMessage());
        }
    }

    public void totalPayByJobTitle(String month) {
        String sql = """
        SELECT e.job_title, SUM(p.pay_amount) AS total
        FROM employees e
        JOIN pay_statements p ON e.empid = p.empid
        WHERE DATE_FORMAT(p.pay_date, '%Y-%m') = ?
        GROUP BY e.job_title;
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, month);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("job_title") + " | Total Pay: " + rs.getDouble("total"));
            }

        } catch (SQLException e) {
            System.out.println("Report error: " + e.getMessage());
        }
    }

    public void totalPayByDivision(String month) {
        String sql = """
        SELECT e.division, SUM(p.pay_amount) AS total
        FROM employees e
        JOIN pay_statements p ON e.empid = p.empid
        WHERE DATE_FORMAT(p.pay_date, '%Y-%m') = ?
        GROUP BY e.division;
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, month);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("division") + " | Total Pay: " + rs.getDouble("total"));
            }

        } catch (SQLException e) {
            System.out.println("Report error: " + e.getMessage());
        }
    }
}
