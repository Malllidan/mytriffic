/**
 * @Auther Malllidan
 * @Version 1.0
 * @date 2018.7.23
 */
package com.triffic.JavaTool;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

public class MysqlLink_Car extends MysqlLink {
    private List<CarData> cardatalist = new ArrayList<CarData>();

    public static void main(String...arg) throws Exception{

        String sql="i056";
        String date1="2017-05-24 0:0:0";
        String date2="2017-05-25 0:0:0";
        Connect();
        MysqlLink_Car mysqllink= new MysqlLink_Car();
        mysqllink.Select(sql,date1,date2);

    }
    @Override
    public void Select(String sql,String astart,String aend){
        super.Select(sql,astart,aend);
        ResultSet result=super.GetResult();
        try {
            while (result.next()){
              int id=result.getInt(1);
              int inteid=result.getInt(2);
               // System.out.println(id);
              int direction=result.getInt(3);
              int lane=result.getInt(4);
              String carplate=result.getString(5);
                //System.out.println(carplate);
              String passtim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getTimestamp("passtime"));;
              int traveltime=result.getInt(7);
              int upinteid=result.getInt(8);
              int updirection=result.getInt(9);
              int uplane=result.getInt(10);
              cardatalist.add(new CarData(id,inteid,direction,lane,carplate,passtim,traveltime,upinteid,updirection,uplane));

            }

        }catch (Exception e){
            System.out.println("数据库读取失败");
        }

    }
    public List<CarData> GetCarDataList(){
        return cardatalist;
    }
}
