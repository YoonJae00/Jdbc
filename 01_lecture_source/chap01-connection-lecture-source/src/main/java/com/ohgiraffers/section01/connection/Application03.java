package com.ohgiraffers.section01.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application03 {

    public static void main(String[] args) {

        // 수업목표. Properties 파일로 주요 정보를 은닉할 수 있다.

        /* 필기.
            Properties 파일을 사용하는 이유
            1. 수기로 작성 시 오타 발생 가능성 높음(한 곳에 모아둬서 유지보수 용이)
            2. 설정 속성에 수정 사항이 발생할 경우 파일마다 번거롭게 수정해야 하므로 유지보수 비용 증가
            3. Connection 을 사용하는 파일마다 동일한 코드의 중복을 막는다.
         */

        Properties prop = new Properties();

        Connection con = null;

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/section01/connection/jdbc-config.properties"));

            System.out.println("prop = " + prop);

            /* 필기. 값 꺼내오기 getProperty() */
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);

            System.out.println("con = " + con);

        } catch (IOException e) {
            throw new RuntimeException(e);
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
