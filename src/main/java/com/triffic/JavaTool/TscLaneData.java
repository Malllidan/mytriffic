package com.triffic.JavaTool;

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
}
