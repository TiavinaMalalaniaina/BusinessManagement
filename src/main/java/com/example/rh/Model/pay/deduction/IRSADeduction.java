package com.example.rh.Model.pay.deduction;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.example.rh.util.Utils.round;

public class IRSADeduction extends Deduction {
    private static final String FORMAT_MIN="Tranche IRSA INF %s";
    private static final String FORMAT="Tranche IRSA De %s à %s";
    private static final String FORMAT_MAX="Tranche IRSA SUP %s";
    double min;
    double max;

    public IRSADeduction(double min, double max, double rate) {
        this.min = min;
        this.max = max;
        this.rate = rate;
    }

    @Override
    public void setDesignation(Connection connection) {
        if (this.min == 0) {
            this.designation = String.format(FORMAT_MIN, max);
        } else if (this.max == 0) {
            this.designation = String.format(FORMAT_MAX, min-1);
        } else {
            this.designation = String.format(FORMAT, min, max);
        }
    }

    @Override
    public void setAmount(Connection connection) {
        if (this.taxableAmount < this.min) {
            this.amount = 0;
            return;
        }
        if (this.min == 0) {
            this.amount = 0;
        } else if (this.max == 0) {
            this.amount = round((this.taxableAmount - (this.min - 1)) * this.rate * 0.01);
        } else {
            this.amount = round((this.max - (this.min - 1)) * this.rate * 0.01);
        }
    }

    @Override
    public void setRate(Connection connection) {

    }

    @Override
    public void save(Connection connection) throws SQLException {
        String sql = "INSERT INTO \"public\".deduction_irsa ( id, payslip_id, rate, amount, designation) VALUES ( default, ?, ?, ?, ? ) RETURNING id";
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
        String sql = "SELECT * FROM deduction_irsa WHERE payslip_id=?";
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
