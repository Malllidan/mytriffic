package com.triffic.Bezier_Curve;

import java.util.HashMap;
import java.util.Map;

public class CurvePicture {
    public static void main(String...args){

    }
    public static Map<String,Integer> AllFlowAngleCalculate(Map<String,Float> myflowmap){
       Map<String,Integer> myflowanglemap=new HashMap<>();
       for(Map.Entry<String,Float> entry:myflowmap.entrySet()){
           String myflowmapname=entry.getKey();
           int myangle=(int)(entry.getValue().floatValue()/1000*30);
           myflowanglemap.put(myflowmapname,myangle);
       }
       return myflowanglemap;
    }
    public static void GraphicsTest(){

    }
    public static void CurvePictureMaker(Map<String,Integer> myflowanglemap){

    }


}
