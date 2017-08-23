/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Formation
 */
public class SingletonDB {

    // Assign values ​​for connection db mysql
    private static final String DB_URL = "jdbc:mysql://localhost:3306/airafpa";
    private static final String DB_JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "f58d8fg89";

    private Connection cn = null;

    private SingletonDB() {
        // import jdbc mysql
        try {
            Class.forName(SingletonDB.DB_JDBC_DRIVER);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Singleton for connexion DB
     *
     * @return
     */
    public static SingletonDB getInstance() {
        return ConnexionBdDHolder.INSTANCE;
    }

    /**
     * Assign Instance class SingletonDB
     */
    private static class ConnexionBdDHolder {

        private static final SingletonDB INSTANCE = new SingletonDB();
    }

    /**
     * check connect DB
     *
     * @return
     */
    public boolean connect() {
        // connect db is null
        if (this.cn == null) {
            // connect db
            try {
                this.cn = DriverManager.getConnection(SingletonDB.DB_URL, SingletonDB.DB_USER, SingletonDB.DB_PASSWORD);
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        } else {
            // check connect db
            try {
                Statement st = this.cn.createStatement();
                String requete = "SELECT 1";
                st.executeQuery(requete);

            } catch (SQLException ex) {
                try {
                    this.cn = DriverManager.getConnection(SingletonDB.DB_URL, SingletonDB.DB_USER, SingletonDB.DB_PASSWORD);
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * connect db
     *
     * @return connect db
     */
    public Connection getConnectionManager() {
        return this.cn;
    }
}
