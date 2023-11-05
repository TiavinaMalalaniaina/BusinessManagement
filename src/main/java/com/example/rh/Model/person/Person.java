package com.example.rh.Model.person;

import java.sql.*;
import java.util.Vector;

public class Person {
    String id;
    String firstName;
    String lastName;
    Date dateOfBirth;
    String gender;
    String address;
    String phoneNumber;
    String cinNumber;
    String serialNumber;
    String profile;

    public String getId() { return id; }
    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCinNumber() {
        return cinNumber;
    }
    public void setCinNumber(String cinNumber) {
        this.cinNumber = cinNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getProfile() {
        return profile;
    }
    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Person(String id, String firstName, String lastName, Date dateOfBirth, String gender, String address, String phoneNumber, String cinNumber, String serialNumber, String profile) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.cinNumber = cinNumber;
        this.serialNumber = serialNumber;
        this.profile = profile;
    }

    public Person(String firstName, String lastName, Date dateOfBirth, String gender, String address, String phoneNumber, String cinNumber, String serialNumber, String profile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.cinNumber = cinNumber;
        this.serialNumber = serialNumber;
        this.profile = profile;
    }

    public Person() {
    }

    public Vector<Person> getAllPerson(Connection connection) {
        Vector<Person> allPerson = new Vector<>();
        String sql = "select * from person";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getString("id"));
                person.setFirstName(resultSet.getString("first_name"));
                person.setLastName(resultSet.getString("last_name"));
                person.setDateOfBirth(resultSet.getDate("date_of_birth"));
                person.setGender(resultSet.getString("gender"));
                person.setAddress(resultSet.getString("address"));
                person.setPhoneNumber(resultSet.getString("phone_number"));
                person.setCinNumber(resultSet.getString("cin_number"));
                person.setSerialNumber(resultSet.getString("serial_number"));
                person.setProfile(resultSet.getString("profile"));
                allPerson.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allPerson;
    }
}
