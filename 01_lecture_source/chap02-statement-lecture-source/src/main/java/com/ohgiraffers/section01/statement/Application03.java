package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application03 {

    public static void main(String[] args) {

        // 1. Connection 생성
        Connection con = getConnection();
        // 2. Statement 생성
        Statement stmt = null;
        // 3. ResultSet 생성
        ResultSet rset = null;
        try {
            // 4. Connection 의 createStatement() 메소드를 이용해서 Statement 인스턴스 생성
            stmt = con.createStatement();

            // Scanner 사용해서 응용
            Scanner sc = new Scanner(System.in);
            System.out.print("empId 입력하세요 : ");
            String empId = sc.nextLine();
            String query = "SELECT emp_id, emp_name FROM employee WHERE emp_id = '" + empId + "'";
            // 5. executeQuery() 로 쿼리문을 실행하고 결과를 ResultSet 으로 반환받음
            rset = stmt.executeQuery(query);
            // 6. ResultSet 에 담긴 결과물을 컬럼 이름(label) 을 이용해 꺼내어 출력
            if(rset.next()){
                System.out.println(rset.getString("emp_id") + "," + rset.getString("emp_name"));
            } else System.out.println("결과 X");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 7. 마지막으로 사용한 자원 반납 (Connection, Statement, ResultSet)
            close(con);
            close(stmt);
            close(rset);
        }
    }
}