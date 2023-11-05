package com.example.rh.Model.contract;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Contract {
    String id;
    String personId;
    String positionId;
    Date start;
    Date end;
    boolean isTest;
    double baseSalary;
    String type;

    public String getId() { return id; }
    public void setId(String id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPositionId() {
        return positionId;
    }
    public void setPositionId(String positionId) {
        this.positionId = positionId;
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

    public boolean getIsTest() {
        return isTest;
    }
    public void setTest(boolean test) {
        isTest = test;
    }

    public double getBaseSalary() {
        return baseSalary;
    }
    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Contract(String id, String personId, String positionId, Date start, Date end, boolean isTest, double baseSalary, String type) {
        this.id = id;
        this.personId = personId;
        this.positionId = positionId;
        this.start = start;
        this.end = end;
        this.isTest = isTest;
        this.baseSalary = baseSalary;
        this.type = type;
    }

    public Contract(String personId, String positionId, Date start, Date end, boolean isTest, double baseSalary, String type) {
        this.personId = personId;
        this.positionId = positionId;
        this.start = start;
        this.end = end;
        this.isTest = isTest;
        this.baseSalary = baseSalary;
        this.type = type;
    }

    public void createContract(Connection connection) throws SQLException {
        String sql = "insert into contract(id, person_id, position_id, start, end, is_test, base_salary, type) values(default, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, getPersonId());
        statement.setString(2, getPositionId());
        statement.setDate(3, getStart());
        statement.setDate(4, getEnd());
        statement.setBoolean(5, getIsTest());
        statement.setDouble(6, getBaseSalary());
        statement.setString(7, getType());
        statement.executeUpdate();
    }
}