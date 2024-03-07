package com.ohgiraffers.test;

import com.mysql.cj.jdbc.ConnectionImpl;

import java.sql.*;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Test01 {

    public static void main(String[] args) {

        Connection con = getConnection();

        Statement stmt = null;

        ResultSet rset = null;

        try {
            stmt = con.createStatement();
            int empId = 210;
            String query = "SELECT * FROM EMPLOYEE WHERE emp_id = " + empId;
            rset = stmt.executeQuery(query);
            if(rset.next()){
                System.out.println(rset.getString("emp_id")+ "," + rset.getString("emp_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(stmt);
            close(rset);
        }
    }
}
