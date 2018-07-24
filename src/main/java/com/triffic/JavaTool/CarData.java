/**
 * @Auther Malllidan
 * @Version 1.0
 * @date 2018.7.23
 */
package com.triffic.JavaTool;

public class CarData {
    private int id;
    private int inteid;
    private int direction;
    private int lane;
    private String carplate;
    private String passtime;
    private int traveltime;
    private int upinteid;
    private int updirection;
    private int uplane;
    public CarData(int id,int inteid,int direction,int lane,String carplate,String passtime,int traveltime,int upinteid,int updirection,int uplane){
        this.id=id;
        this.inteid=inteid;
        this.direction=direction;
        this.lane=lane;
        this.carplate=carplate;
        this.passtime=passtime;
        this.traveltime=traveltime;
        this.upinteid=upinteid;
        this.updirection=direction;
        this.uplane=uplane;
    }
   public int GetId(){
        return id;
   }
    public int GetInteid(){
        return inteid;
    }
    public int GetDirection(){
        return direction;
    }
    public int GetLane(){
        return lane;
    }
    public String GetCarplate(){
        return carplate;
    }
    public String GetPasstime(){
        return passtime;
    }
    public int GetTraveltime(){
        return traveltime;
    }
    public int GetUpInteid(){
        return upinteid;
    }
    public int GetUpLane(){
        return uplane;
    }

    public int GetUpDirection() {
        return updirection;
    }
}
