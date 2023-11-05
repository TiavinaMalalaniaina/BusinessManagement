package com.example.rh.Model.connections;

import java.sql.Connection;

public class ConnectionWrapper {
    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

        private Connection connection;
        private boolean open;

        public ConnectionWrapper(Connection connection) throws Exception {
            checkConnection(connection);
        }

        private void checkConnection(Connection connection) throws Exception {
            open = connection != null;
            if (connection == null) {
                this.connection = new Postgresql().connect();
            } else {
                this.connection = connection;
            }
        }

        public Connection getConnection() {
            return connection;
        }

        public boolean wasOpen() {
            return open;
        }




}
