package com.example.rh.Model.pay.salary;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

/**
 * Salaire normale de l'employe
 */
public class DefaultSalary extends Salary{
    private static final String FORMAT = "Salaire du %s au %s";

    public DefaultSalary(String designation, double rate, double count, double money, Date start, Date end) {
        super(designation, rate, count, money);
        this.start = start;
        this.end = end;
    }

    public DefaultSalary() {
    }

    @Override
    public void setDesignation(Connection connection) {
        this.designation = String.format(FORMAT, start, end);
    }

    @Override
    public void setAmount(Connection connection) {
        this.amount = this.getRate() * this.getCount();
    }

    @Override
    public void setRate(Connection connection) {
        this.rate = this.getDailyWage();
    }

    @Override
    public void setCount(Connection connection) {
        this.count = this.getNumberOfDays();
    }

    private double getNumberOfDays() {
        LocalDate start = this.start.toLocalDate();
        LocalDate end = this.end.toLocalDate();
        boolean isStart = start.getDayOfMonth() == 1;
        boolean isEnd = end.getDayOfMonth() == end.lengthOfMonth();
        if (isStart && isEnd) {
            return 30;
        }
        else {
            Period period = Period.between(start, end);
            return period.getDays()+1;
        }
    }


}
