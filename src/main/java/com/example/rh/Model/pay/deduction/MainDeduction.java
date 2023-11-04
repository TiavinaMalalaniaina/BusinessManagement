package com.example.rh.Model.pay.deduction;

import java.sql.Connection;

/**
 *
 * @author tiavi
 */
public class MainDeduction extends Deduction {
    public static final Deduction[] DEDUCTIONS_TYPES = {
            new CNAPSDeduction(),
            new HealthDeduction(),
    };

    public static final Deduction[] DEDUCTIONS_IRSA = {
            new IRSADeduction(0, 350000, 0),
            new IRSADeduction(350001, 400000, 5),
            new IRSADeduction(400001, 500000, 10),
            new IRSADeduction(500001, 600000, 15),
            new IRSADeduction(600001, 0, 20)
    };


    @Override
    public void setDesignation(Connection connection) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAmount(Connection connection) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRate(Connection connection) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

