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
import java.text.DateFormat;
import java.text.*;
import java.sql.PreparedStatement;


public  class  MysqlLink {
    private static String url= "jdbc:mysql://localhost:3306/test";
    private static String username="root";
    private static String password="123456";
    private static String jdbc_driver="com.mysql.jdbc.Driver";
    private static Connection con;
    private  PreparedStatement state;
    private  ResultSet result;
    private ResultSetMetaData  meta_data;

    public static void main(String...arg) throws Exception{
        String sql="i056";
        String date1="2017-05-24 0:0:0";
        String date2="2017-05-25 0:0:0";
        String sql1="tsclane";
        Connect();
        MysqlLink mysqllink= new MysqlLink();
        mysqllink.Select(sql,date1,date2);
        mysqllink.Select_NoDate(sql1);



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

            System.out.println("数据库连接成功");
        }catch (SQLException e){
            System.out.println("数据库连接失败");
        }
    }
    public  void Select(String sql,String astart,String aend) {//有日期列表的查询
        try {
            String sqlmid="select * from "+sql+' ';
            state = con.prepareStatement(sqlmid+"where passtime>? and passtime<?");
            Timestamp start = new Timestamp(str2Date(astart));//用str2Date的毫秒数来创建一个时间戳
            Timestamp end = new Timestamp(str2Date(aend));


            state.setObject(1, start);
            state.setObject(2, end);
            result = state.executeQuery();
//            state=con.prepareStatement();
//            result = state.executeQuery(sql);
            meta_data = result.getMetaData();
            for (int index = 1; index <= meta_data.getColumnCount(); index++) {
                System.out.print(meta_data.getColumnLabel(index) + " ");
            }
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("JDBC链接失败");

        }
    }
    public void Select_NoDate(String sql){//无日期表查询
        try {
            String sqlmid="select * from "+sql;
            state = con.prepareStatement(sqlmid);
            result = state.executeQuery();
//            state=con.prepareStatement();
//            result = state.executeQuery(sql);
            meta_data = result.getMetaData();
            for (int index = 1; index <= meta_data.getColumnCount(); index++) {
                System.out.print(meta_data.getColumnLabel(index) + " ");
            }
            System.out.println();


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("JDBC链接失败");

        }

    }

    public static void ConnectClose() throws Exception{
        con.close();
    }
    public  void StatetClose() throws Exception{
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
    public PreparedStatement GetState(){
        return state;
    }
    public Connection GetCon(){
        return con;
    }
    public long str2Date(String datestr) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            return format.parse(datestr).getTime();//format.parse:把字符串变成指定类型的date类型，gettime是获得这个date数据的毫秒数
        } catch (ParseException e) {
            e.printStackTrace();

            return 0;

        }
    }


}

