package com.example.rh.Model.pay.deduction;

import java.sql.Connection;

public class HealthDeduction extends Deduction {

    @Override
    public void setDesignation(Connection connection) {
        this.designation = "Retenue Sanitaire";
    }

    @Override
    public void setAmount(Connection connection) {
        this.setAmount(this.getGrossWage()*0.01);
    }

    @Override
    public void setRate(Connection connection) {

    }
}
