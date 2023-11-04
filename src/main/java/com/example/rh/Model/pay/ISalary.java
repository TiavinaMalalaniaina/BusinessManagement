package com.example.rh.Model.pay;

import java.sql.Connection;

public interface ISalary {

    void setDesignation(Connection connection) throws Exception;
    void setAmount(Connection connection)throws Exception;
    void setRate(Connection connection)throws Exception;
    void setCount(Connection connection)throws Exception;
}
