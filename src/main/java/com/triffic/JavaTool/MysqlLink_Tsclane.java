package com.triffic.JavaTool;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MysqlLink_Tsclane extends MysqlLink {
    private List<TscLaneData> tsclanelist=new ArrayList<>();
    public static void main(String...args) {
        String sql="tsclane";
        MysqlLink.Connect();
        MysqlLink_Tsclane tsclanedatalist=new MysqlLink_Tsclane();
        tsclanedatalist.Select_NoDate(sql);
        List<TscLaneData> test=tsclanedatalist.GetTscLaneDataList();
        System.out.println(test.get(4).GetSid());
        List<TscLaneData> test1=tsclanedatalist.GetIdTscLaneDataList(56);
        System.out.println(test1.get(0).GetSid());
        int turn=tsclanedatalist.GetTurn(test1,0,1);
        System.out.println(turn);

    }
    @Override
    public void Select_NoDate(String sql){
        super.Select_NoDate(sql);
        ResultSet result=super.GetResult();
        try {
            while (result.next()){
                int sid=result.getInt(2);
                int movement=result.getInt(5);
                // System.out.println(id);
                int direction=result.getInt(7);
                int intersectionid=result.getInt(8);
                if(sid!=0){
                    tsclanelist.add(new TscLaneData(sid,movement,direction,intersectionid));
                }


            }

        }catch (Exception e){
            System.out.println("数据库读取失败");
        }
    }
    public List<TscLaneData> GetTscLaneDataList(){
        return tsclanelist;
    }
    //得到指定路口的渠化信息
    public List<TscLaneData> GetIdTscLaneDataList(int inteid){
        List<TscLaneData> idtsclanelist=new ArrayList<>();
        for(TscLaneData t:tsclanelist){
            if(t.GetIntersectionid()==inteid){
                idtsclanelist.add(t);
            }
        }
        return idtsclanelist;
    }
    //得到指定路口指定方向指定车道的转向方向。
    public int GetTurn(List<TscLaneData> idtsclanedate,int direction,int lane){
        int d0=0;
        int d2=0;
        int d4=0;
        int d6=0;
        int movement=0;
        int sid=0;
        int turn=0;
        for(TscLaneData t:idtsclanedate){
            if(t.GetDirection()==0){
                d0++;
            }
            else if (t.GetDirection()==2){
                d2++;
            }
            else if(t.GetDirection()==4){
                d4++;

            }
            else if(t.GetDirection()==6){
                d6++;
            }
        }
        if(direction==0){
            sid=lane+d6+d4+d2;
        }
        else if(direction==2){
            sid=lane+d6+d4;
        }
        else if(direction==4){
            sid=lane+d6;
        }
        else if(direction==6){
            sid=lane;
        }
        for(TscLaneData t:idtsclanedate){
            if(t.GetSid()==sid){
                movement=t.GetMovement();
                break;
            }
        }
        if(direction==0){
            switch (movement){
                case 1:
                    turn=6;
                    break;
                case 2:
                    turn=4;
                    break;
                case 3:
                    turn=2;
                    break;
                case 4:
                    turn=6;
                    break;
                case 5:
                    turn=4;
                    break;
                case 6:
                    turn=2;
                    break;
                case 7:
                    turn=-999;
                    break;
                case 8:
                    turn=6;
                    break;
                case 9:
                    turn=-999;
                    break;
            }

        }
        else if(direction==2){
            switch (movement){
                case 1:
                    turn=0;
                    break;
                case 2:
                    turn=6;
                    break;
                case 3:
                    turn=4;
                    break;
                case 4:
                    turn=0;
                    break;
                case 5:
                    turn=6;
                    break;
                case 6:
                    turn=4;
                    break;
                case 7:
                    turn=-999;
                    break;
                case 8:
                    turn=0;
                    break;
                case 9:
                    turn=-999;
                    break;
            }
        }
    else if(direction==4){
            switch (movement){
                case 1:
                    turn=2;
                    break;
                case 2:
                    turn=0;
                    break;
                case 3:
                    turn=6;
                    break;
                case 4:
                    turn=2;
                    break;
                case 5:
                    turn=0;
                    break;
                case 6:
                    turn=6;
                    break;
                case 7:
                    turn=-999;
                    break;
                case 8:
                    turn=2;
                    break;
                case 9:
                    turn=-999;
                    break;
            }
        }
    else if(direction==6){
            switch (movement){
                case 1:
                    turn=4;
                    break;
                case 2:
                    turn=2;
                    break;
                case 3:
                    turn=0;
                    break;
                case 4:
                    turn=4;
                    break;
                case 5:
                    turn=2;
                    break;
                case 6:
                    turn=0;
                    break;
                case 7:
                    turn=-999;
                    break;
                case 8:
                    turn=4;
                    break;
                case 9:
                    turn=-999;
                    break;
            }
        }
     return turn;


    }
}
