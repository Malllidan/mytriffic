package com.triffic.JavaTool;


import java.util.List;

public class MysqlLinkTest {
    public static void main(String...args)throws Exception{
        String date1="2017-05-24 0:0:0";
        String date2="2017-05-24 16:0:0";
        String sql="i056";

        MysqlLink.Connect();
        MysqlLink_Car mysqllink_car=new MysqlLink_Car();
        mysqllink_car.Select(sql,date1,date2);
        List<CarData> mycardatalist=mysqllink_car.GetCarDataList();
        mysqllink_car.ResultClose();
        mysqllink_car.StatetClose();
        MysqlLink.ConnectClose();
       System.out.println(mycardatalist.get(0).GetId());
       System.out.println(mycardatalist.get(mycardatalist.size()-1).GetId());


    }
}
