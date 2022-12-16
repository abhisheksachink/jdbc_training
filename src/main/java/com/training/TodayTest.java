//Then write a stored procedure which will take account number and month as input and output
//        the total amount due for that account.
//        Since this is a promotional card 5% discount will be given on total amount.
//        Then create a java class which will call this procedure to fetch the statement details and
//        show that in below format:
//
//        Account Number:xxxxxxx
//
//        Bill Month: xxxxxxx
//
//        Bill Amount: xxxxxxx


package com.training;

import java.sql.*;
import java.util.Scanner;

public class TodayTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/infinite";
        String user = "root";
        String password = "india@123";
        Connection con = null;

        try {
            con = DriverManager.getConnection(url, user, password);
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Account Details for getting Due Balance");
            System.out.println("Enter Account Number ");
            String account_n = sc.next();
            System.out.println("Enter Month of transaction ");
            Integer month_tr = sc.nextInt();

            //for input and output procedure
            String sql1 = "call get_amnt_due(?,?,?)";
            CallableStatement cstmt1 = con.prepareCall(sql1);
            cstmt1.setString(1,account_n);

            cstmt1.setInt(2,month_tr);
            cstmt1.registerOutParameter(3,Types.DOUBLE);


            cstmt1.executeQuery();
            Double Total_amount=cstmt1.getDouble(3);
            System.out.println("Account Number :" + account_n);
            System.out.println("Bill Month : " + month_tr);
            System.out.println("Bill Amount: " +Total_amount);





        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
