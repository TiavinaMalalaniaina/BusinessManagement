package com.example.rh.Model.pay.salary;


import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;
/**
 * Salaire à déduire
 */
public class DeductibleAbsence extends Salary{
    List<Absence> absences;

    public DeductibleAbsence() {}

    public DeductibleAbsence(String designation, double rate, double money, double count, List<Absence> absences) {
        super(designation, rate, money, count);
        this.absences = absences;
    }

    /**
     * Récupère le nombre d'absence déductible du mois
     * @return
     */
    public double countDeductibleAbsence() {
        //TODO: implementation de la methode pour récupérer le nombre d'absence déductible
        return 0;
    }

    @Override
    public void setDesignation(Connection connection) {
        this.designation="Absence déductible";
    }

    @Override
    public void setAmount(Connection connection) {
        this.amount = this.getCount() * this.getRate() * -1;

    }

    @Override
    public void setRate(Connection connection) {
        this.rate = this.getDailyWage();
    }

    @Override
    public void setCount(Connection connection) throws SQLException {
        this.absences = Absence.findAbsence(connection, start, end, this.personId);
        this.count = countDeductibleAbsence();
    }

}
