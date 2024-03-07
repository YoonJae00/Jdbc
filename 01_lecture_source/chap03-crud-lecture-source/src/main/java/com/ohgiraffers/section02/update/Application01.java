package com.ohgiraffers.section02.update;

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
            String query = prop.getProperty("updateMenu");
            Scanner sc = new Scanner(System.in);
            System.out.print("변경할 메뉴코드를 입력해주세요 : ");
            int menuCode = sc.nextInt();
            System.out.print("변경할 메뉴의 이름을 입력해주세요 : ");
            sc.nextLine();
            String menuName = sc.nextLine();
            System.out.print("변경할 메뉴의 가격을 입력해주세요 : ");
            int menuPrice = sc.nextInt();

            pstmt = con.prepareStatement(query);
            pstmt.setString(1,menuName);
            pstmt.setInt(2,menuPrice);
            pstmt.setInt(3,menuCode);

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
        }

        if(result>0){
            System.out.println("메뉴 수정 성공 !");
        } else System.out.println("수정 실패");
    }
}
