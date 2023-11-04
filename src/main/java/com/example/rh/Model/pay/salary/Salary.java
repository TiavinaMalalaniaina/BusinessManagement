package com.example.rh.Model.pay.salary;


import com.example.rh.Model.pay.ISalary;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.rh.util.Utils.round;

public abstract class Salary implements ISalary {
    String id;
    String payslipId;
    String designation;
    double rate;
    double amount;
    double count;

    // extension
    String personId;
    double baseSalary;
    Date start;
    Date end;

    /**
     * Save the actual salary
     * @param connection
     * @throws java.sql.SQLException
     */
    public void save(Connection connection) throws SQLException {
        String sql = "INSERT INTO \"public\".salary ( id, payslip_id, rate, amount, designation, count) VALUES ( default, ?, ?, ?, ?, ? ) RETURNING id";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.payslipId);
            stmt.setDouble(2, this.rate);
            stmt.setDouble(3, this.amount);
            stmt.setString(4, this.designation);
            stmt.setDouble(5, this.count);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.setId(rs.getString("id"));
            }
        }
    }

    /**
     * Find all salary byb paySlipId
     * @param paySlipId
     * @param connection
     * @return
     * @throws java.sql.SQLException
     */
    public List<Salary> findByPaySlipId(int paySlipId, Connection connection) throws SQLException {
        List<Salary> salaries = new ArrayList<>();
        String sql = "SELECT * FROM salary WHERE payslip_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, paySlipId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Salary model = newInstance();
                model.setId(rs.getString("id"));
                model.setPayslipId(rs.getString("payslip_id"));
                model.setAmount(rs.getDouble("amount"));
                model.setCount(rs.getDouble("count"));
                model.setDesignation(rs.getString("designation"));
                model.setRate(rs.getDouble("rate"));
                salaries.add(model);
            }
        }
        return salaries;
    }

    public Salary() {}

    public Salary(String designation, double rate, double money, double count) {
        this.designation = designation;
        this.rate = rate;
        this.amount = money;
        this.count = count;
    }

    public String getDesignation() {
        return designation;
    }

    public double getRate() {
        return round(rate);
    }


    public double getCount() {
        return round(count);
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getPayslipId() {
        return payslipId;
    }

    public void setPayslipId(String paySlipId) {
        this.payslipId = paySlipId;
    }

    public double getAmount() {
        return round(amount);
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public static Salary newInstance() {
        return new MainSalary();
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }


    public double getDailyWage() {
        return getBaseSalary()/30;
    }

    public double getHourlyWage() {
        return getBaseSalary()/173.33;
    }

    public void prepare(String personId, Date start, Date end, Connection connection) throws Exception {
        this.setRate(connection);
        this.setStart(start);
        this.setEnd(end);
        this.setDesignation(connection);
        this.setCount(connection);
        this.setAmount(connection);
    }

    public static Salary[] getSalariesType() {
        return MainSalary.SALARIES_TYPES;
    }

    /**
     * find all salaries by payslip
     * @param connection
     * @param payslipId
     * @return
     * @throws SQLException
     */
    public static List<Salary> findByPayslipId(Connection connection, String payslipId) throws SQLException {
        List<Salary> models = new ArrayList<>();
        String sql = "SELECT * FROM salary WHERE payslip_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql) ) {
            stmt.setString(1, payslipId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Salary model = Salary.newInstance();
                model.setId(rs.getString("id"));
                model.setDesignation(rs.getString("designation"));
                model.setAmount(rs.getDouble("amount"));
                model.setRate(rs.getDouble("rate"));
                model.setCount(rs.getDouble("count"));
                model.setPayslipId(rs.getString("payslip_id"));
                models.add(model);
            }
        }
        return models;
    }
}
