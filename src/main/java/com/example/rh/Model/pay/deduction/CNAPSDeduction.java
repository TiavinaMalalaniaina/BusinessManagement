package com.example.rh.Model.pay.deduction;

import java.sql.Connection;

public class CNAPSDeduction extends Deduction{
    public static final double PLAFOND = 250000*8*0.01;


    @Override
    public void setDesignation(Connection connection) {
        this.designation = "Retenue CNAPS";
    }

    @Override
    public void setAmount(Connection connection) {
        double cnaps = this.getGrossWage() * 0.01;
        if (cnaps<PLAFOND) {
            this.setAmount(cnaps);
        } else {
            this.setAmount(PLAFOND);
        }
    }

    @Override
    public void setRate(Connection connection) {

    }
}
