/**
 * @Auther Malllidan
 * @Version 1.0
 * @date 2018.7.23
 */
package com.triffic.JavaTool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public  class  MysqlLink {
    private static String url= "jdbc:mysql://localhost:3306/test";
    private static String username="root";
    private static String password="123456";
    private static String jdbc_driver="com.mysql.jdbc.Driver";
    private static Connection con;
    private static Statement state;
    private  ResultSet result;
    private ResultSetMetaData  meta_data;

    public static void main(String...arg) throws Exception{

            String sql_select = "select * from I056";
            Connect();
            MysqlLink mysqllink=new MysqlLink() ;

            mysqllink.Select(sql_select);
            StatetClose();
            ConnectClose();
            System.out.println("测试成功");

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
    public  void Select(String sql) {
        try {
            result = state.executeQuery(sql);
            meta_data = result.getMetaData();
            for (int index = 1; index <= meta_data.getColumnCount(); index++) {
                System.out.print(meta_data.getColumnLabel(index) + " ");
            }
            System.out.println();

        } catch (Exception e) {

        }
    }
   // public  abstract void Select(String sql);
    public static void ConnectClose() throws Exception{
        con.close();
    }
    public static void StatetClose() throws Exception{
        state.close();
    }
    public  void ResultClose() throws Exception{
        result.close();
    }
    public ResultSet GetResult(){
        return result;
    }
    public ResultSetMetaData GetMetaData(){
        return meta_data;
    }


}
