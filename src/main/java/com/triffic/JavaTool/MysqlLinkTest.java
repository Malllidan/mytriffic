package com.triffic.JavaTool;


import java.util.List;

public class MysqlLinkTest {
    public static void main(String...args)throws Exception{
        String sql_select = "select * from I056";
        MysqlLink.Connect();
        MysqlLink_Car mysqllink_car=new MysqlLink_Car();
        mysqllink_car.Select(sql_select);
        List<CarData> mycardatalist=mysqllink_car.GetCarDataList();
        mysqllink_car.ResultClose();
        MysqlLink.StatetClose();
        MysqlLink.ConnectClose();
       System.out.println(mycardatalist.get(0).GetId());
       System.out.println(mycardatalist.get(mycardatalist.size()-1).GetId());


    }
}
