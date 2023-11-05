package com.example.rh.Model.position;

import java.sql.*;
import java.util.Vector;

public class Position {
    String id;
    String title;
    String description;
    String benefits;
    Date creationDate;
    String remarks;
    String upperHierarchy;
    String departementId;
    String statusPositionId;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getBenefits() {
        return benefits;
    }
    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUpperHierarchy() {
        return upperHierarchy;
    }
    public void setUpperHierarchy(String upperHierarchy) {
        this.upperHierarchy = upperHierarchy;
    }

    public String getDepartementId() {
        return departementId;
    }
    public void setDepartementId(String departementId) {
        this.departementId = departementId;
    }

    public String getStatusPositionId() {
        return statusPositionId;
    }
    public void setStatusPositionId(String statusPositionId) {
        this.statusPositionId = statusPositionId;
    }

    public Position(String id, String title, String description, String benefits, Date creationDate, String remarks, String upperHierarchy, String departementId, String statusPositionId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.benefits = benefits;
        this.creationDate = creationDate;
        this.remarks = remarks;
        this.upperHierarchy = upperHierarchy;
        this.departementId = departementId;
        this.statusPositionId = statusPositionId;
    }

    public Position(String title, String description, String benefits, Date creationDate, String remarks, String upperHierarchy, String departementId, String statusPositionId) {
        this.title = title;
        this.description = description;
        this.benefits = benefits;
        this.creationDate = creationDate;
        this.remarks = remarks;
        this.upperHierarchy = upperHierarchy;
        this.departementId = departementId;
        this.statusPositionId = statusPositionId;
    }

    public Position() {
    }

    public Vector<Position> getAllPosition(Connection connection) {
        Vector<Position> allPosition = new Vector<>();
        String sql = "select * from position";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Position position = new Position();
                position.setId(resultSet.getString("id"));
                position.setTitle(resultSet.getString("title"));
                position.setDescription(resultSet.getString("description"));
                position.setBenefits(resultSet.getString("benefits"));
                position.setCreationDate(resultSet.getDate("creation_date"));
                position.setRemarks(resultSet.getString("remarks"));
                position.setUpperHierarchy(resultSet.getString("upper_hierarchy"));
                position.setDepartementId(resultSet.getString("departement_id"));
                position.setStatusPositionId(resultSet.getString("status_position_id"));
                allPosition.add(position);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allPosition;
    }
}
