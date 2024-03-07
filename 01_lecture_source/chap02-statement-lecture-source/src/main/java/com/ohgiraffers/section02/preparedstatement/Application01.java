package com.ohgiraffers.section02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application01 {

    public static void main(String[] args) {

        // 수업목표. PreparedStatement 에 대해 이해하고 사용할 수 있다.

    /* 필기.
        PreparedStatement 란?
        Statement 를 상속 받은 interface
        더욱 효율적인 작업을 진행할 수 있다.
        완성 된 SQL 문과 미완성 된 SQL 문을 모두 사용할 수 있다.
        위치홀더(placeholder) : ?
     */

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        try {
            pstmt = con.prepareStatement("SELECT emp_id,emp_name FROM employee");
            rset = pstmt.executeQuery();
            while (rset.next()) {
                System.out.println(rset.getString("emp_id") + ", " + rset.getString("emp_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
            close(rset);
        }
    }
}