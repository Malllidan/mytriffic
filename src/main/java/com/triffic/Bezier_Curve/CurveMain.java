package com.triffic.Bezier_Curve;

import com.triffic.JavaTool.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurveMain {
    public static void main(String...args){
        String sql="i056";
        String sql_tsc="tsclane";
        String date1="2017-05-24 0:0:0";
        String date2="2017-05-25 0:0:0";
        List<CarData> mycarlist=new ArrayList<>();
        List<TscLaneData> mytsclanelist=new ArrayList<>();
        List<TscLaneData> myidtsclanelist=new ArrayList<>();
        Map<String,List<CarData>> mycarmap=new HashMap<>();
        Map<String,Float> myflowmap=new HashMap<>();
        try {
            MysqlLink.Connect();
            MysqlLink_Car mysqllink_car= new MysqlLink_Car();
            mysqllink_car.Select(sql,date1,date2);
            mycarlist=mysqllink_car.GetCarDataList();
            mysqllink_car.ResultClose();
            mysqllink_car.StatetClose();
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            MysqlLink_Tsclane mysqllink_tsclane=new MysqlLink_Tsclane();
            mysqllink_tsclane.Select_NoDate(sql_tsc);
            mytsclanelist=mysqllink_tsclane.GetTscLaneDataList();
            myidtsclanelist=TscLaneData.GetIdTscLaneDataList(mytsclanelist,56);
            mycarmap=CurveFlow.DirectionSwitch(mycarlist,myidtsclanelist);
            myflowmap=CurveFlow.AllFlowCalculate(mycarmap);


            System.out.println(myflowmap.get("flow4base"));


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
