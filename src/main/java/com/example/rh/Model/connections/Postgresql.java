package com.example.rh.Model.connections;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class Postgresql {
    private String url = "jdbc:postgresql://localhost:5432/business_management";
    private String utilisateur = "postgres";
    private String motDePasse = "mdpprom15";


    public Connection connect() throws Exception {
            Class.forName("org.postgresql.Driver");
            Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

            return connexion;
    }
}
