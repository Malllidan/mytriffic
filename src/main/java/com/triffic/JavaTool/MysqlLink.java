package com.triffic.JavaTool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public class MysqlLink {
    private static String url= "jdbc:mysql://localhost:3306/test";
    private static String username="root";
    private static String password="123456";
    private static String jdbc_driver="com.mysql.jdbc.Driver";
    private static Connection con;
    private static Statement state;
    private static ResultSet result;

    public static void main(String...arg){
        try {
            Connect();
            state.close();
            con.close();
            System.out.println("测试成功");
        }catch (SQLException e){
            System.out.println("测试失败");
        }

    }


    public static void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("数据库驱动加载成功");

        }catch (ClassNotFoundException e){
            System.out.println("数据库驱动加载失败");
            e.printStackTrace();
        }
        try{
            con=DriverManager.getConnection(url,username,password);
            state=con.createStatement();
            System.out.println("数据库连接成功");
        }catch (SQLException e){
            System.out.println("数据库连接失败");
        }
    }


}
