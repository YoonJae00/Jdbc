package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application01 {

    public static void main(String[] args) {

        // 수업목표. Connection 인터페이스를 알 수 있다.

        /* 필기.
            DB 접속을 위한 Connection instance 생성을 위한 레퍼런스 변수 선언
         */
        Connection con = null;

        try {
            /* 필기.
                사용할 드라이버를 등록 Class 클래스는 Class 의 메타정보를 가지고 있다.
                forName 메소드에 풀 클래스 명을 등록하면
                해당 클래스를 메모리에 올려 사용할 준비를 마친다.
             */
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost/employee", "ohgiraffers", "ohgiraffers");

            System.out.println("con = " + con);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
