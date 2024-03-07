package com.ohgiraffers.section03.delete;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application01 {

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            String query = prop.getProperty("deleteMenu");
            Scanner sc = new Scanner(System.in);
            System.out.print("삭제 코드 입력 : ");
            int categotyCode = sc.nextInt();

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,categotyCode);

            result = pstmt.executeUpdate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }

        if(result>0){
            System.out.println("메뉴 수정 성공 !");
        } else System.out.println("수정 실패");
    }
}
