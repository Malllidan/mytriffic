package com.triffic.JavaTool;

import java.util.ArrayList;
import java.util.List;

public class TscLaneData {
    private int sid;
    private int movement;
    private int direction;
    private int intersectionid;
    public TscLaneData(int sid,int movement,int direction,int intersectionid){
        this.sid=sid;
        this.movement=movement;
        this.direction=direction;
        this.intersectionid=intersectionid;
    }

    public int GetSid(){
        return sid;
    }

    public int GetMovement() {
        return movement;
    }

    public int GetDirection() {
        return direction;
    }

    public int GetIntersectionid() {
        return intersectionid;
    }
    public String GetTsclane(){
        return "[sid:"+GetSid()+" movement:"+GetMovement()+" direction:"+GetDirection()+" intersectionid:"+GetIntersectionid()+"]";
    }
    public static List<TscLaneData> GetIdTscLaneDataList(List<TscLaneData> tsclanelist,int inteid){
        List<TscLaneData> idtsclanelist=new ArrayList<>();
        for(TscLaneData t:tsclanelist){
            if(t.GetIntersectionid()==inteid){
                idtsclanelist.add(t);
            }
        }
        return idtsclanelist;
    }
    //得到指定路口指定方向指定车道的转向方向。
    public static int GetTurn(List<TscLaneData> idtsclanedate,int direction,int lane){
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
