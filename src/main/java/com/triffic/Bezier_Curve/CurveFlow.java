package com.triffic.Bezier_Curve;

import com.triffic.JavaTool.CarData;
import com.triffic.JavaTool.MysqlLink;
import com.triffic.JavaTool.MysqlLink_Car;

import javax.xml.bind.Element;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurveFlow {
    public static void main(String...args){
        String sql="i056";
        String date1="2017-05-24 0:0:0";
        String date2="2017-05-25 0:0:0";
        List<CarData> mycarlist=new ArrayList<>();
        try {
            MysqlLink.Connect();
            MysqlLink_Car mysqllink= new MysqlLink_Car();
            mysqllink.Select(sql,date1,date2);
            mycarlist=mysqllink.GetCarDataList();
            mysqllink.ResultClose();
            mysqllink.StatetClose();
            MysqlLink.ConnectClose();

            float time=FlowCalculate(mycarlist);
            System.out.println(time);
        }catch (Exception e){
            e.printStackTrace();
        }
        Map<String,Object> directionlist=new HashMap<String,Object>();


        directionlist.put("flow0base",new ArrayList<CarData>());
        System.out.println(directionlist.get("flowbase").getClass());

    }

    public static float FlowCalculate(List<CarData> cardatalist) {
        float timelenth=1;
        float carflow=1;
        if(cardatalist.size()!=0){



            long timestart=1;
            long timeend=1;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                timestart=sdf.parse(cardatalist.get(0).GetPasstime()).getTime()/1000;
                timeend=sdf.parse(cardatalist.get(cardatalist.size()-1).GetPasstime()).getTime()/1000;
            }catch(ParseException e){
                e.printStackTrace();
                System.out.println("时间戳转换错误");
            }
            timelenth=(float) ((timeend-timestart)/(3600*1.0));
            carflow=cardatalist.size()/timelenth;
        }
        return carflow;

    }
//    public static Map<String,Object> DirectionSwitch(List<CarData> carDataList){//返回map位[0]
//        Map<String,Object> directionlist=new HashMap<String,Object>();
//
//
//        directionlist.put("flow0base",new ArrayList<CarData>());
//        directionlist.put("flow0to2",new ArrayList<CarData>());
//        directionlist.put("flow0to4",new ArrayList<CarData>());
//        directionlist.put("flow0to6",new ArrayList<CarData>());
//        directionlist.put("flow2base",new ArrayList<CarData>());
//        directionlist.put("flow2to0",new ArrayList<CarData>());
//        directionlist.put("flow2to4",new ArrayList<CarData>());
//        directionlist.put("flow2to6",new ArrayList<CarData>());
//        directionlist.put("flow4base",new ArrayList<CarData>());
//        directionlist.put("flow4to0",new ArrayList<CarData>());
//        directionlist.put("flow4to2",new ArrayList<CarData>());
//        directionlist.put("flow4to6",new ArrayList<CarData>());
//        directionlist.put("flow6base",new ArrayList<CarData>());
//        directionlist.put("flow6to0",new ArrayList<CarData>());
//        directionlist.put("flow6to2",new ArrayList<CarData>());
//        directionlist.put("flow6to4",new ArrayList<CarData>());
//
//
//        for(CarData c:carDataList){
//            if(c.GetDirection()==0){
//                directionlist.get("flow0base")
//            }
//        }
//
//
//
//    }
}
