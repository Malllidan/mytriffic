package com.triffic.JavaTool;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MysqlLink_Tsclane extends MysqlLink {
    private List<TscLaneData> tsclanelist=new ArrayList<>();
    public static void main(String...args) {


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

}
