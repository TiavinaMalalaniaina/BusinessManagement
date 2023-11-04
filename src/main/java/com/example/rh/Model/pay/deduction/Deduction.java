package com.example.rh.Model.pay.deduction;

import com.example.rh.Model.pay.IDeduction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.rh.util.Utils.round;

public abstract class Deduction implements IDeduction {
    String designation;
    double rate;
    double amount;
    double grossWage;
    double taxableAmount;
    String payslipId;
    String id;

    public static Deduction newInstance() {
        return new MainDeduction();
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getAmount() {
        return round(amount);
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double getGrossWage() {
        return grossWage;
    }

    public void setGrossWage(double grossWage) {
        this.grossWage = grossWage;
    }

    public double getTaxableAmount() {
        return taxableAmount;
    }

    public void setTaxableAmount(double taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

    public String getPayslipId() {
        return payslipId;
    }

    public void setPayslipId(String payslipId) {
        this.payslipId = payslipId;
    }




    public static Deduction[] getDeductionsType() {
        return MainDeduction.DEDUCTIONS_TYPES;
    }
    public static Deduction[] getDeductionsIRSA() {
        return MainDeduction.DEDUCTIONS_IRSA;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void prepare(Connection connection) throws Exception {
        this.setRate(connection);
        this.setDesignation(connection);
        this.setAmount(connection);
    }

    public void save(Connection connection) throws SQLException {
        String sql = "INSERT INTO \"public\".deduction ( id, payslip_id, rate, amount, designation) VALUES ( default, ?, ?, ?, ? ) RETURNING id";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.getPayslipId());
            stmt.setDouble(2, this.getRate());
            stmt.setDouble(3, this.getAmount());
            stmt.setString(4, this.getDesignation());
            ResultSet rs = stmt.executeQuery();
            while( rs.next()) {
                this.setId(rs.getString("id"));
            }
        }
    }

    public static List<Deduction> findByPayslipId(Connection connection, String payslipId) throws SQLException {
        List<Deduction> models = new ArrayList<>();
        String sql = "SELECT * FROM deduction WHERE payslip_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql) ) {
            stmt.setString(1, payslipId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Deduction model = Deduction.newInstance();
                model.setId(rs.getString("id"));
                model.setDesignation(rs.getString("designation"));
                model.setAmount(rs.getDouble("amount"));
                model.setRate(rs.getDouble("rate"));
                model.setPayslipId(rs.getString("payslip_id"));
                models.add(model);
            }
        }
        return models;
    }
}
