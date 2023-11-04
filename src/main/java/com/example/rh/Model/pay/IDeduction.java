package com.example.rh.Model.pay;

import java.sql.Connection;

public interface IDeduction {
    void setDesignation(Connection connection) throws Exception;
    void setAmount(Connection connection) throws Exception;
    void setRate(Connection connection) throws Exception;
}
