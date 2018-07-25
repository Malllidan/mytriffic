package com.triffic.Bezier_Curve;

import com.triffic.JavaTool.*;

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
            mycarmap=DirectionSwitch(mycarlist,myidtsclanelist);
            myflowmap=AllFlowCalculate(mycarmap);


            System.out.println(myflowmap.get("flow4to2"));


        }catch (Exception e){
            e.printStackTrace();
        }


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
        else {
            return 0;
        }

        return carflow;

    }


    public static Map<String,List<CarData>> DirectionSwitch(List<CarData> carDataList,List<TscLaneData> idtsclanedate){//返回分完类的Cardata map。
        Map<String,List<CarData>> directionlist=new HashMap<String,List<CarData>>();



        directionlist.put("flow0base",new ArrayList<CarData>());
        directionlist.put("flow0to2",new ArrayList<CarData>());
        directionlist.put("flow0to4",new ArrayList<CarData>());
        directionlist.put("flow0to6",new ArrayList<CarData>());
        directionlist.put("flow2base",new ArrayList<CarData>());
        directionlist.put("flow2to0",new ArrayList<CarData>());
        directionlist.put("flow2to4",new ArrayList<CarData>());
        directionlist.put("flow2to6",new ArrayList<CarData>());
        directionlist.put("flow4base",new ArrayList<CarData>());
        directionlist.put("flow4to0",new ArrayList<CarData>());
        directionlist.put("flow4to2",new ArrayList<CarData>());
        directionlist.put("flow4to6",new ArrayList<CarData>());
        directionlist.put("flow6base",new ArrayList<CarData>());
        directionlist.put("flow6to0",new ArrayList<CarData>());
        directionlist.put("flow6to2",new ArrayList<CarData>());
        directionlist.put("flow6to4",new ArrayList<CarData>());


        for(CarData c:carDataList){
            if(c.GetDirection()==0){
                directionlist.get("flow0base").add(c);
            }
            else if(c.GetDirection()==2){
                directionlist.get("flow2base").add(c);
            }
            else if(c.GetDirection()==4){
                directionlist.get("flow4base").add(c);
            }
            else if(c.GetDirection()==6){
                directionlist.get("flow6base").add(c);
            }
        }
        List<CarData> flow0base=directionlist.get("flow0base");
        if(flow0base.size()!=0){
            for(CarData c:flow0base){
                int turn=TscLaneData.GetTurn(idtsclanedate,c.GetDirection(),c.GetLane());
                switch (turn){
                    case 2:
                        directionlist.get("flow0to2").add(c);
                        break;
                    case 4:
                    directionlist.get("flow0to4").add(c);
                    break;
                    case 6:
                    directionlist.get("flow0to6").add(c);
                    break;


                }

            }

        }
        List<CarData> flow2base=directionlist.get("flow2base");
        if(flow2base.size()!=0){
            for(CarData c:flow2base){
                int turn=TscLaneData.GetTurn(idtsclanedate,c.GetDirection(),c.GetLane());
                switch (turn){
                    case 0:
                        directionlist.get("flow2to0").add(c);
                        break;
                    case 4:
                        directionlist.get("flow2to4").add(c);
                        break;
                    case 6:
                        directionlist.get("flow2to6").add(c);
                        break;


                }

            }

        }
        List<CarData> flow4base=directionlist.get("flow4base");
        if(flow4base.size()!=0){
            for(CarData c:flow4base){
                int turn=TscLaneData.GetTurn(idtsclanedate,c.GetDirection(),c.GetLane());
                switch (turn){
                    case 0:
                        directionlist.get("flow4to0").add(c);
                        break;
                    case 2:
                        directionlist.get("flow4to2").add(c);
                        break;
                    case 6:
                        directionlist.get("flow4to6").add(c);
                        break;


                }

            }

        }
        List<CarData> flow6base=directionlist.get("flow6base");
        if(flow6base.size()!=0){
            for(CarData c:flow6base){
                int turn=TscLaneData.GetTurn(idtsclanedate,c.GetDirection(),c.GetLane());
                switch (turn){
                    case 0:
                        directionlist.get("flow6to0").add(c);
                        break;
                    case 2:
                        directionlist.get("flow6to2").add(c);
                        break;
                    case 4:
                        directionlist.get("flow6to4").add(c);
                        break;


                }

            }

        }

        return directionlist;
    }


    public static Map<String,Float> AllFlowCalculate(Map<String,List<CarData>> mycarmap){
       Map<String,Float> myflowdata=new HashMap<>();
       for(Map.Entry<String,List<CarData>> entry:mycarmap.entrySet()){
           String flowname=entry.getKey();
           float flow=CurveFlow.FlowCalculate(entry.getValue());
           myflowdata.put(flowname,flow);

       }


       return myflowdata;


    }


}
