package com.example.rh.Model.pay.salary;

import java.sql.Connection;


/**
 *
 * @author tiavi
 */
class MainSalary extends Salary {
    static final Salary[] SALARIES_TYPES = {new DefaultSalary(), new DeductibleAbsence()};

    @Override
    public void setDesignation(Connection connection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAmount(Connection connection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRate(Connection connection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCount(Connection connection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
