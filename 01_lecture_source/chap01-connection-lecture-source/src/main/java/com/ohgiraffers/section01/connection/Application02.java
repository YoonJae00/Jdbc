package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application02 {

    public static void main(String[] args) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost/employee";
        String user = "ohgiraffers";
        String password = "ohgiraffers";

        Connection con = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
            System.out.println("con = " + con);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if ( con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
