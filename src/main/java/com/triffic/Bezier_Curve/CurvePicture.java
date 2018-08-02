package com.triffic.Bezier_Curve;


import com.sun.javaws.util.JfxHelper;
import com.triffic.PicturePaint.MyJFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurvePicture extends MyJFrame {


    public static void main(String... args) {


    }

    public static void CurvePictureMaker(Map<String,Integer> myflowanglemap){
        CurvePanel mp = new CurvePanel(myflowanglemap);
        mp.setBackground(Color.white);
        mp.setOpaque(false);
        PictureMaker(400, mp);
    }


    public static Map<String, Integer> AllFlowAngleCalculate(Map<String, Float> myflowmap) {
        Map<String, Integer> myflowanglemap = new HashMap<>();
        for (Map.Entry<String, Float> entry : myflowmap.entrySet()) {
            String myflowmapname = entry.getKey();
            int myangle = (int) (entry.getValue().floatValue() / 1000 * 30);
            myflowanglemap.put(myflowmapname, myangle);
        }
        return myflowanglemap;
    }
//    @Override
//    public  void Graphicsmake(Graphics g){
//        g.drawLine(100,100,200,200);
//
//
//    }
}
class CurvePanel extends JPanel {
    private Map<String,Integer> myflowanglemap;
    private  Map<String,Integer> mydirectionphase0=new HashMap<>();
    private  Map<String,Integer> mydirectionphase2=new HashMap<>();
    private  Map<String,Integer> mydirectionphase4=new HashMap<>();
    private  Map<String,Integer> mydirectionphase6=new HashMap<>();
    private  Map<String,Integer> mydirectiondeviation0=new HashMap<>();
    private  Map<String,Integer> mydirectiondeviation2=new HashMap<>();
    private  Map<String,Integer> mydirectiondeviation4=new HashMap<>();
    private  Map<String,Integer> mydirectiondeviation6=new HashMap<>();
//    private int[] phase0;
//    private int[] deviation0;

    public CurvePanel(Map<String,Integer> myflowanglemap){
        this.myflowanglemap=myflowanglemap;
//        this.phase0=new int[]{2,4,3,2};
//        this.deviation0=new int[]{0,0,this.myflowanglemap.get("flow2to4").intValue(),this.myflowanglemap.get("flow2to6").intValue()+this.myflowanglemap.get("flow4to6").intValue()};

        this.mydirectionphase0.put("flow0base",2);
        this.mydirectionphase0.put("flow0to2",4);
        this.mydirectionphase0.put("flow0to4",3);
        this.mydirectionphase0.put("flow0to6",2);
        this.mydirectionphase2.put("flow2base",1);
        this.mydirectionphase2.put("flow2to0",1);
        this.mydirectionphase2.put("flow2to4",3);
        this.mydirectionphase2.put("flow2to6",2);
        this.mydirectionphase4.put("flow4base",4);
        this.mydirectionphase4.put("flow4to0",1);
        this.mydirectionphase4.put("flow4to2",4);
        this.mydirectionphase4.put("flow4to6",2);
        this.mydirectionphase6.put("flow6base",3);
        this.mydirectionphase6.put("flow6to0",1);
        this.mydirectionphase6.put("flow6to2",4);
        this.mydirectionphase6.put("flow6to4",3);

        this.mydirectiondeviation0.put("flow0base",0);
        this.mydirectiondeviation0.put("flow0to2",0);
        this.mydirectiondeviation0.put("flow0to4",this.myflowanglemap.get("flow2to4").intValue());
        this.mydirectiondeviation0.put("flow0to6",this.myflowanglemap.get("flow2to6").intValue()+this.myflowanglemap.get("flow4to6").intValue());
        this.mydirectiondeviation2.put("flow2base",0);
        this.mydirectiondeviation2.put("flow2to0",this.myflowanglemap.get("flow4to0").intValue()+this.myflowanglemap.get("flow6to0").intValue());
        this.mydirectiondeviation2.put("flow2to4",0);
        this.mydirectiondeviation2.put("flow2to6",this.myflowanglemap.get("flow4to6").intValue());
        this.mydirectiondeviation4.put("flow4base",0);
        this.mydirectiondeviation4.put("flow4to0",this.myflowanglemap.get("flow6to0").intValue());
        this.mydirectiondeviation4.put("flow4to2",this.myflowanglemap.get("flow0to2").intValue()+this.myflowanglemap.get("flow6to2").intValue());
        this.mydirectiondeviation4.put("flow4to6",0);
        this.mydirectiondeviation6.put("flow6base",0);
        this.mydirectiondeviation6.put("flow6to0",0);
        this.mydirectiondeviation6.put("flow6to2",this.myflowanglemap.get("flow0to2").intValue());
        this.mydirectiondeviation6.put("flow6to4",this.myflowanglemap.get("flow0to4").intValue()+this.myflowanglemap.get("flow2to4").intValue());



    }


    public List<GeneralPath> DirectionaAngelPaint(Map<String,Integer> mydirectionphase,Map<String,Integer> mydirectiondeviation,int direction,int r){
        List<GeneralPath> mypathgroud= new ArrayList<>();
        int startphase=0;
        switch (direction){
            case 0:{
                startphase=mydirectionphase.get("flow0base").intValue();
                break;
            }
            case 2:{
                startphase=mydirectionphase.get("flow2base").intValue();
                break;
            }
            case 4:{
                startphase=mydirectionphase.get("flow4base").intValue();
                break;
            }
            case 6:{
                startphase=mydirectionphase.get("flow6base").intValue();
                break;
            }

        }

        for(Map.Entry<String,Integer>entry:mydirectionphase.entrySet()){
            String directionname=entry.getKey();
            if(!directionname.substring(directionname.length()-4).equals("base")&&this.myflowanglemap.get(directionname).intValue()!=0){
                GeneralPath mypath=new GeneralPath(GeneralPath.WIND_NON_ZERO,this.myflowanglemap.get(directionname).intValue()*2);
                int phase=mydirectionphase.get(directionname).intValue();
                int deviation=mydirectiondeviation.get(directionname).intValue();
                float[] xgroud=new float[this.myflowanglemap.get(directionname).intValue()];
                float[] ygroud=new float[this.myflowanglemap.get(directionname).intValue()];
                float[] xgroud1=new float[this.myflowanglemap.get(directionname).intValue()];
                float[] ygroud1=new float[this.myflowanglemap.get(directionname).intValue()];




                if(startphase==2){
                    if(phase==4) {//0-2
                        for(int i=0;i<xgroud.length;i++){
                            xgroud[i]=(float) (r*Math.cos(0.5*Math.PI+i/180.0*Math.PI));
                            ygroud[i]=(float) (r*Math.sin(0.5*Math.PI+i/180.0*Math.PI));
                        }

                        for(int i=0;i<xgroud1.length;i++){
                            xgroud1[i]=(float) (r*Math.cos(2*Math.PI-(i+deviation)/180.0*Math.PI));
                            ygroud1[i]=(float) (r*Math.sin(2*Math.PI-(i+deviation)/180.0*Math.PI));
                        }

                        mypath.moveTo(xgroud[0],ygroud[0]);
                        for(int i=1;i<xgroud.length;i++){
                            mypath.lineTo(xgroud[i],ygroud[i]);
                        }
                        //mypath.moveTo(xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        mypath.curveTo(0,0,0,0,xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        //mypath.quadTo(0,0,xgroud1[xgroud1.length-1],ygroud[ygroud1.length-1]);
                        for(int i=xgroud1.length-2;i>=0;i--){
                            mypath.lineTo(xgroud1[i],ygroud1[i]);
                        }
                        mypath.curveTo(0,0,0,0,xgroud[0],ygroud[0]);
                        mypathgroud.add(mypath);

                    }
                    else if(phase==3){//0-4
                        int thisdeviation=this.myflowanglemap.get("flow0to2");
                        for(int i=0;i<xgroud.length;i++){
                            xgroud[i]=(float) (r*Math.cos(0.5*Math.PI+(i+thisdeviation)/180.0*Math.PI));
                            ygroud[i]=(float) (r*Math.sin(0.5*Math.PI+(i+thisdeviation)/180.0*Math.PI));
                        }

                        for(int i=0;i<xgroud1.length;i++){
                            xgroud1[i]=(float) (r*Math.cos(1.5*Math.PI-(i+deviation)/180.0*Math.PI));
                            ygroud1[i]=(float) (r*Math.sin(1.5*Math.PI-(i+deviation)/180.0*Math.PI));
                        }

                        mypath.moveTo(xgroud[0],ygroud[0]);
                        for(int i=1;i<xgroud.length;i++){
                            mypath.lineTo(xgroud[i],ygroud[i]);
                        }
                        //mypath.moveTo(xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        mypath.curveTo(0,0,0,0,xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        //mypath.quadTo(0,0,xgroud1[xgroud1.length-1],ygroud[ygroud1.length-1]);
                        for(int i=xgroud1.length-2;i>=0;i--){
                            mypath.lineTo(xgroud1[i],ygroud1[i]);
                        }
                        mypath.curveTo(0,0,0,0,xgroud[0],ygroud[0]);
                        mypathgroud.add(mypath);


                    }
                    else if(phase==2){//0-6
                        int thisdeviation=this.myflowanglemap.get("flow0to2")+this.myflowanglemap.get("flow0to4");
                        for(int i=0;i<xgroud.length;i++){
                            xgroud[i]=(float) (r*Math.cos(0.5*Math.PI+(i+thisdeviation)/180.0*Math.PI));
                            ygroud[i]=(float) (r*Math.sin(0.5*Math.PI+(i+thisdeviation)/180.0*Math.PI));
                        }

                        for(int i=0;i<xgroud1.length;i++){
                            xgroud1[i]=(float) (r*Math.cos(Math.PI-(i+deviation)/180.0*Math.PI));
                            ygroud1[i]=(float) (r*Math.sin(Math.PI-(i+deviation)/180.0*Math.PI));
                        }

                        mypath.moveTo(xgroud[0],ygroud[0]);
                        for(int i=1;i<xgroud.length;i++){
                            mypath.lineTo(xgroud[i],ygroud[i]);
                        }
                        //mypath.moveTo(xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        mypath.curveTo(0,0,0,0,xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        //mypath.quadTo(0,0,xgroud1[xgroud1.length-1],ygroud[ygroud1.length-1]);
                        for(int i=xgroud1.length-2;i>=0;i--){
                            mypath.lineTo(xgroud1[i],ygroud1[i]);
                        }
                        mypath.curveTo(0,0,0,0,xgroud[0],ygroud[0]);
                        mypathgroud.add(mypath);


                    }

                }
                if(startphase==1){
                    if(phase==3) {//2-4
                        for(int i=0;i<xgroud.length;i++){
                            xgroud[i]=(float) (r*Math.cos(i/180.0*Math.PI));
                            ygroud[i]=(float) (r*Math.sin(i/180.0*Math.PI));
                        }

                        for(int i=0;i<xgroud1.length;i++){
                            xgroud1[i]=(float) (r*Math.cos(1.5*Math.PI-(i+deviation)/180.0*Math.PI));
                            ygroud1[i]=(float) (r*Math.sin(1.5*Math.PI-(i+deviation)/180.0*Math.PI));
                        }

                        mypath.moveTo(xgroud[0],ygroud[0]);
                        for(int i=1;i<xgroud.length;i++){
                            mypath.lineTo(xgroud[i],ygroud[i]);
                        }
                        //mypath.moveTo(xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        mypath.curveTo(0,0,0,0,xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        //mypath.quadTo(0,0,xgroud1[xgroud1.length-1],ygroud[ygroud1.length-1]);
                        for(int i=xgroud1.length-2;i>=0;i--){
                            mypath.lineTo(xgroud1[i],ygroud1[i]);
                        }
                        mypath.curveTo(0,0,0,0,xgroud[0],ygroud[0]);
                        mypathgroud.add(mypath);

                    }
                    else if(phase==2){//2-6
                        int thisdeviation=this.myflowanglemap.get("flow2to4");
                        for(int i=0;i<xgroud.length;i++){
                            xgroud[i]=(float) (r*Math.cos((i+thisdeviation)/180.0*Math.PI));
                            ygroud[i]=(float) (r*Math.sin((i+thisdeviation)/180.0*Math.PI));
                        }

                        for(int i=0;i<xgroud1.length;i++){
                            xgroud1[i]=(float) (r*Math.cos(Math.PI-(i+deviation)/180.0*Math.PI));
                            ygroud1[i]=(float) (r*Math.sin(Math.PI-(i+deviation)/180.0*Math.PI));
                        }

                        mypath.moveTo(xgroud[0],ygroud[0]);
                        for(int i=1;i<xgroud.length;i++){
                            mypath.lineTo(xgroud[i],ygroud[i]);
                        }
                        //mypath.moveTo(xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        mypath.curveTo(0,0,0,0,xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        //mypath.quadTo(0,0,xgroud1[xgroud1.length-1],ygroud[ygroud1.length-1]);
                        for(int i=xgroud1.length-2;i>=0;i--){
                            mypath.lineTo(xgroud1[i],ygroud1[i]);
                        }
                        mypath.curveTo(0,0,0,0,xgroud[0],ygroud[0]);
                        mypathgroud.add(mypath);


                    }
                    else if(phase==1){//2-0
                        int thisdeviation=this.myflowanglemap.get("flow2to4")+this.myflowanglemap.get("flow2to6");
                        for(int i=0;i<xgroud.length;i++){
                            xgroud[i]=(float) (r*Math.cos((i+thisdeviation)/180.0*Math.PI));
                            ygroud[i]=(float) (r*Math.sin((i+thisdeviation)/180.0*Math.PI));
                        }

                        for(int i=0;i<xgroud1.length;i++){
                            xgroud1[i]=(float) (r*Math.cos(0.5*Math.PI-(i+deviation)/180.0*Math.PI));
                            ygroud1[i]=(float) (r*Math.sin(0.5*Math.PI-(i+deviation)/180.0*Math.PI));
                        }

                        mypath.moveTo(xgroud[0],ygroud[0]);
                        for(int i=1;i<xgroud.length;i++){
                            mypath.lineTo(xgroud[i],ygroud[i]);
                        }
                        //mypath.moveTo(xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        mypath.curveTo(0,0,0,0,xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        //mypath.quadTo(0,0,xgroud1[xgroud1.length-1],ygroud[ygroud1.length-1]);
                        for(int i=xgroud1.length-2;i>=0;i--){
                            mypath.lineTo(xgroud1[i],ygroud1[i]);
                        }
                        mypath.curveTo(0,0,0,0,xgroud[0],ygroud[0]);
                        mypathgroud.add(mypath);


                    }

                }
                if(startphase==4){
                    if(phase==2) {//4-6
                        for(int i=0;i<xgroud.length;i++){
                            xgroud[i]=(float) (r*Math.cos(1.5*Math.PI+i/180.0*Math.PI));
                            ygroud[i]=(float) (r*Math.sin(1.5*Math.PI+i/180.0*Math.PI));
                        }

                        for(int i=0;i<xgroud1.length;i++){
                            xgroud1[i]=(float) (r*Math.cos(Math.PI-(i+deviation)/180.0*Math.PI));
                            ygroud1[i]=(float) (r*Math.sin(Math.PI-(i+deviation)/180.0*Math.PI));
                        }

                        mypath.moveTo(xgroud[0],ygroud[0]);
                        for(int i=1;i<xgroud.length;i++){
                            mypath.lineTo(xgroud[i],ygroud[i]);
                        }
                        //mypath.moveTo(xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        mypath.curveTo(0,0,0,0,xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        //mypath.quadTo(0,0,xgroud1[xgroud1.length-1],ygroud[ygroud1.length-1]);
                        for(int i=xgroud1.length-2;i>=0;i--){
                            mypath.lineTo(xgroud1[i],ygroud1[i]);
                        }
                        mypath.curveTo(0,0,0,0,xgroud[0],ygroud[0]);
                        mypathgroud.add(mypath);

                    }
                    else if(phase==1){//4-0
                        int thisdeviation=this.myflowanglemap.get("flow4to6");
                        for(int i=0;i<xgroud.length;i++){
                            xgroud[i]=(float) (r*Math.cos(1.5*Math.PI+(i+thisdeviation)/180.0*Math.PI));
                            ygroud[i]=(float) (r*Math.sin(1.5*Math.PI+(i+thisdeviation)/180.0*Math.PI));
                        }

                        for(int i=0;i<xgroud1.length;i++){
                            xgroud1[i]=(float) (r*Math.cos(0.5*Math.PI-(i+deviation)/180.0*Math.PI));
                            ygroud1[i]=(float) (r*Math.sin(0.5*Math.PI-(i+deviation)/180.0*Math.PI));
                        }

                        mypath.moveTo(xgroud[0],ygroud[0]);
                        for(int i=1;i<xgroud.length;i++){
                            mypath.lineTo(xgroud[i],ygroud[i]);
                        }
                        //mypath.moveTo(xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        mypath.curveTo(0,0,0,0,xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        //mypath.quadTo(0,0,xgroud1[xgroud1.length-1],ygroud[ygroud1.length-1]);
                        for(int i=xgroud1.length-2;i>=0;i--){
                            mypath.lineTo(xgroud1[i],ygroud1[i]);
                        }
                        mypath.curveTo(0,0,0,0,xgroud[0],ygroud[0]);
                        mypathgroud.add(mypath);


                    }
                    else if(phase==4){//4-2
                        int thisdeviation=this.myflowanglemap.get("flow4to6")+this.myflowanglemap.get("flow4to0");
                        for(int i=0;i<xgroud.length;i++){
                            xgroud[i]=(float) (r*Math.cos(1.5*Math.PI+(i+thisdeviation)/180.0*Math.PI));
                            ygroud[i]=(float) (r*Math.sin(1.5*Math.PI+(i+thisdeviation)/180.0*Math.PI));
                        }

                        for(int i=0;i<xgroud1.length;i++){
                            xgroud1[i]=(float) (r*Math.cos(2*Math.PI-(i+deviation)/180.0*Math.PI));
                            ygroud1[i]=(float) (r*Math.sin(2*Math.PI-(i+deviation)/180.0*Math.PI));
                        }

                        mypath.moveTo(xgroud[0],ygroud[0]);
                        for(int i=1;i<xgroud.length;i++){
                            mypath.lineTo(xgroud[i],ygroud[i]);
                        }
                        //mypath.moveTo(xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        mypath.curveTo(0,0,0,0,xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        //mypath.quadTo(0,0,xgroud1[xgroud1.length-1],ygroud[ygroud1.length-1]);
                        for(int i=xgroud1.length-2;i>=0;i--){
                            mypath.lineTo(xgroud1[i],ygroud1[i]);
                        }
                        mypath.curveTo(0,0,0,0,xgroud[0],ygroud[0]);
                        mypathgroud.add(mypath);


                    }

                }
                if(startphase==3){
                    if(phase==1) {//6-0
                        for(int i=0;i<xgroud.length;i++){
                            xgroud[i]=(float) (r*Math.cos(Math.PI+i/180.0*Math.PI));
                            ygroud[i]=(float) (r*Math.sin(Math.PI+i/180.0*Math.PI));
                        }

                        for(int i=0;i<xgroud1.length;i++){
                            xgroud1[i]=(float) (r*Math.cos(0.5*Math.PI-(i+deviation)/180.0*Math.PI));
                            ygroud1[i]=(float) (r*Math.sin(0.5*Math.PI-(i+deviation)/180.0*Math.PI));
                        }

                        mypath.moveTo(xgroud[0],ygroud[0]);
                        for(int i=1;i<xgroud.length;i++){
                            mypath.lineTo(xgroud[i],ygroud[i]);
                        }
                        //mypath.moveTo(xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        mypath.curveTo(0,0,0,0,xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        //mypath.quadTo(0,0,xgroud1[xgroud1.length-1],ygroud[ygroud1.length-1]);
                        for(int i=xgroud1.length-2;i>=0;i--){
                            mypath.lineTo(xgroud1[i],ygroud1[i]);
                        }
                        mypath.curveTo(0,0,0,0,xgroud[0],ygroud[0]);
                        mypathgroud.add(mypath);

                    }
                    else if(phase==4){//6-2
                        int thisdeviation=this.myflowanglemap.get("flow6to0");
                        for(int i=0;i<xgroud.length;i++){
                            xgroud[i]=(float) (r*Math.cos(Math.PI+(i+thisdeviation)/180.0*Math.PI));
                            ygroud[i]=(float) (r*Math.sin(Math.PI+(i+thisdeviation)/180.0*Math.PI));
                        }

                        for(int i=0;i<xgroud1.length;i++){
                            xgroud1[i]=(float) (r*Math.cos(2*Math.PI-(i+deviation)/180.0*Math.PI));
                            ygroud1[i]=(float) (r*Math.sin(2*Math.PI-(i+deviation)/180.0*Math.PI));
                        }

                        mypath.moveTo(xgroud[0],ygroud[0]);
                        for(int i=1;i<xgroud.length;i++){
                            mypath.lineTo(xgroud[i],ygroud[i]);
                        }
                        //mypath.moveTo(xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        mypath.curveTo(0,0,0,0,xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        //mypath.quadTo(0,0,xgroud1[xgroud1.length-1],ygroud[ygroud1.length-1]);
                        for(int i=xgroud1.length-2;i>=0;i--){
                            mypath.lineTo(xgroud1[i],ygroud1[i]);
                        }
                        mypath.curveTo(0,0,0,0,xgroud[0],ygroud[0]);
                        mypathgroud.add(mypath);


                    }
                    else if(phase==3){//6-4
                        int thisdeviation=this.myflowanglemap.get("flow6to0")+this.myflowanglemap.get("flow6to2");
                        for(int i=0;i<xgroud.length;i++){
                            xgroud[i]=(float) (r*Math.cos(Math.PI+(i+thisdeviation)/180.0*Math.PI));
                            ygroud[i]=(float) (r*Math.sin(Math.PI+(i+thisdeviation)/180.0*Math.PI));
                        }

                        for(int i=0;i<xgroud1.length;i++){
                            xgroud1[i]=(float) (r*Math.cos(1.5*Math.PI-(i+deviation)/180.0*Math.PI));
                            ygroud1[i]=(float) (r*Math.sin(1.5*Math.PI-(i+deviation)/180.0*Math.PI));
                        }

                        mypath.moveTo(xgroud[0],ygroud[0]);
                        for(int i=1;i<xgroud.length;i++){
                            mypath.lineTo(xgroud[i],ygroud[i]);
                        }
                        //mypath.moveTo(xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        mypath.curveTo(0,0,0,0,xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        //mypath.quadTo(0,0,xgroud1[xgroud1.length-1],ygroud[ygroud1.length-1]);
                        for(int i=xgroud1.length-2;i>=0;i--){
                            mypath.lineTo(xgroud1[i],ygroud1[i]);
                        }
                        mypath.curveTo(0,0,0,0,xgroud[0],ygroud[0]);
                        mypathgroud.add(mypath);


                    }

                }
                //System.out.println(directionname.substring(directionname.length()-4));
                //System.out.println(directionname);

            }
        }

    return mypathgroud;

    }
    public List<GeneralPath> BlackSidePaint(int ri,int ro){
        List<GeneralPath> mypathgroud= new ArrayList<>();
        int angle0base=this.myflowanglemap.get("flow0base");
        int angle2base=this.myflowanglemap.get("flow2base");
        int angle4base=this.myflowanglemap.get("flow4base");
        int angle6base=this.myflowanglemap.get("flow6base");
        GeneralPath mypath0=new GeneralPath();
        float [] xgroudo0= new float[angle0base];
        float [] ygroudo0= new float[angle0base];
        float [] xgroudi0= new float[angle0base];
        float [] ygroudi0= new float[angle0base];
        for(int i=0;i<angle0base;i++){
           xgroudo0[i]=(float) (ro*Math.cos(0.5*Math.PI+(i/180.0*Math.PI)));
           ygroudo0[i]=(float) (ro*Math.sin(0.5*Math.PI+(i/180.0*Math.PI)));
        }
        for(int i=0;i<angle0base;i++){
            xgroudi0[i]=(float) (ri*Math.cos(0.5*Math.PI+(i/180.0*Math.PI)));
            ygroudi0[i]=(float) (ri*Math.sin(0.5*Math.PI+(i/180.0*Math.PI)));
        }
        mypath0.moveTo(xgroudo0[0],ygroudo0[0]);
        for(int i=1;i<angle0base;i++){
            mypath0.lineTo(xgroudo0[i],ygroudo0[i]);

        }
        for(int i=angle0base-1;i>=0;i--){
            mypath0.lineTo(xgroudi0[i],ygroudi0[i]);

        }
        mypathgroud.add(mypath0);

        GeneralPath mypath2=new GeneralPath();
        float [] xgroudo2= new float[angle2base];
        float [] ygroudo2= new float[angle2base];
        float [] xgroudi2= new float[angle2base];
        float [] ygroudi2= new float[angle2base];
        for(int i=0;i<angle2base;i++){
            xgroudo2[i]=(float) (ro*Math.cos((i/180.0*Math.PI)));
            ygroudo2[i]=(float) (ro*Math.sin((i/180.0*Math.PI)));
        }
        for(int i=0;i<angle2base;i++){
            xgroudi2[i]=(float) (ri*Math.cos((i/180.0*Math.PI)));
            ygroudi2[i]=(float) (ri*Math.sin((i/180.0*Math.PI)));
        }
        mypath2.moveTo(xgroudo2[0],ygroudo2[0]);
        for(int i=1;i<angle2base;i++){
            mypath2.lineTo(xgroudo2[i],ygroudo2[i]);

        }
        for(int i=angle2base-1;i>=0;i--){
            mypath2.lineTo(xgroudi2[i],ygroudi2[i]);

        }
        mypathgroud.add(mypath2);

        GeneralPath mypath4=new GeneralPath();
        float [] xgroudo4= new float[angle4base];
        float [] ygroudo4= new float[angle4base];
        float [] xgroudi4= new float[angle4base];
        float [] ygroudi4= new float[angle4base];
        for(int i=0;i<angle4base;i++){
            xgroudo4[i]=(float) (ro*Math.cos(1.5*Math.PI+(i/180.0*Math.PI)));
            ygroudo4[i]=(float) (ro*Math.sin(1.5*Math.PI+(i/180.0*Math.PI)));
        }
        for(int i=0;i<angle4base;i++){
            xgroudi4[i]=(float) (ri*Math.cos(1.5*Math.PI+(i/180.0*Math.PI)));
            ygroudi4[i]=(float) (ri*Math.sin(1.5*Math.PI+(i/180.0*Math.PI)));
        }
        mypath4.moveTo(xgroudo4[0],ygroudo4[0]);
        for(int i=1;i<angle4base;i++){
            mypath4.lineTo(xgroudo4[i],ygroudo4[i]);

        }
        for(int i=angle4base-1;i>=0;i--){
            mypath4.lineTo(xgroudi4[i],ygroudi4[i]);

        }
        mypathgroud.add(mypath4);

        GeneralPath mypath6=new GeneralPath();
        float [] xgroudo6= new float[angle6base];
        float [] ygroudo6= new float[angle6base];
        float [] xgroudi6= new float[angle6base];
        float [] ygroudi6= new float[angle6base];
        for(int i=0;i<angle6base;i++){
            xgroudo6[i]=(float) (ro*Math.cos(1*Math.PI+(i/180.0*Math.PI)));
            ygroudo6[i]=(float) (ro*Math.sin(1*Math.PI+(i/180.0*Math.PI)));
        }
        for(int i=0;i<angle6base;i++){
            xgroudi6[i]=(float) (ri*Math.cos(1*Math.PI+(i/180.0*Math.PI)));
            ygroudi6[i]=(float) (ri*Math.sin(1*Math.PI+(i/180.0*Math.PI)));
        }
        mypath6.moveTo(xgroudo6[0],ygroudo6[0]);
        for(int i=1;i<angle6base;i++){
            mypath6.lineTo(xgroudo6[i],ygroudo6[i]);

        }
        for(int i=angle6base-1;i>=0;i--){
            mypath6.lineTo(xgroudi6[i],ygroudi6[i]);

        }
        mypathgroud.add(mypath6);





        return mypathgroud;
    }
    public List<GeneralPath> GarySidePaint(int ri,int ro){
        List<GeneralPath> mypathgroud= new ArrayList<>();
        int angle0base=this.myflowanglemap.get("flow4to0")+this.myflowanglemap.get("flow2to0")+this.myflowanglemap.get("flow6to0");
        int angle2base=this.myflowanglemap.get("flow0to2")+this.myflowanglemap.get("flow4to2")+this.myflowanglemap.get("flow6to2");
        int angle4base=this.myflowanglemap.get("flow0to4")+this.myflowanglemap.get("flow2to4")+this.myflowanglemap.get("flow6to4");
        int angle6base=this.myflowanglemap.get("flow0to6")+this.myflowanglemap.get("flow2to6")+this.myflowanglemap.get("flow4to6");
        GeneralPath mypath0=new GeneralPath();
        float [] xgroudo0= new float[angle0base];
        float [] ygroudo0= new float[angle0base];
        float [] xgroudi0= new float[angle0base];
        float [] ygroudi0= new float[angle0base];
        for(int i=0;i<angle0base;i++){
            xgroudo0[i]=(float) (ro*Math.cos(0.5*Math.PI-(i/180.0*Math.PI)));
            ygroudo0[i]=(float) (ro*Math.sin(0.5*Math.PI-(i/180.0*Math.PI)));
        }
        for(int i=0;i<angle0base;i++){
            xgroudi0[i]=(float) (ri*Math.cos(0.5*Math.PI-(i/180.0*Math.PI)));
            ygroudi0[i]=(float) (ri*Math.sin(0.5*Math.PI-(i/180.0*Math.PI)));
        }
        mypath0.moveTo(xgroudo0[0],ygroudo0[0]);
        for(int i=1;i<angle0base;i++){
            mypath0.lineTo(xgroudo0[i],ygroudo0[i]);

        }
        for(int i=angle0base-1;i>=0;i--){
            mypath0.lineTo(xgroudi0[i],ygroudi0[i]);

        }
        mypathgroud.add(mypath0);

        GeneralPath mypath2=new GeneralPath();
        float [] xgroudo2= new float[angle2base];
        float [] ygroudo2= new float[angle2base];
        float [] xgroudi2= new float[angle2base];
        float [] ygroudi2= new float[angle2base];
        for(int i=0;i<angle2base;i++){
            xgroudo2[i]=(float) (ro*Math.cos(2*Math.PI-(i/180.0*Math.PI)));
            ygroudo2[i]=(float) (ro*Math.sin(2*Math.PI-(i/180.0*Math.PI)));
        }
        for(int i=0;i<angle2base;i++){
            xgroudi2[i]=(float) (ri*Math.cos(2*Math.PI-(i/180.0*Math.PI)));
            ygroudi2[i]=(float) (ri*Math.sin(2*Math.PI-(i/180.0*Math.PI)));
        }
        mypath2.moveTo(xgroudo2[0],ygroudo2[0]);
        for(int i=1;i<angle2base;i++){
            mypath2.lineTo(xgroudo2[i],ygroudo2[i]);

        }
        for(int i=angle2base-1;i>=0;i--){
            mypath2.lineTo(xgroudi2[i],ygroudi2[i]);

        }
        mypathgroud.add(mypath2);

        GeneralPath mypath4=new GeneralPath();
        float [] xgroudo4= new float[angle4base];
        float [] ygroudo4= new float[angle4base];
        float [] xgroudi4= new float[angle4base];
        float [] ygroudi4= new float[angle4base];
        for(int i=0;i<angle4base;i++){
            xgroudo4[i]=(float) (ro*Math.cos(1.5*Math.PI-(i/180.0*Math.PI)));
            ygroudo4[i]=(float) (ro*Math.sin(1.5*Math.PI-(i/180.0*Math.PI)));
        }
        for(int i=0;i<angle4base;i++){
            xgroudi4[i]=(float) (ri*Math.cos(1.5*Math.PI-(i/180.0*Math.PI)));
            ygroudi4[i]=(float) (ri*Math.sin(1.5*Math.PI-(i/180.0*Math.PI)));
        }
        mypath4.moveTo(xgroudo4[0],ygroudo4[0]);
        for(int i=1;i<angle4base;i++){
            mypath4.lineTo(xgroudo4[i],ygroudo4[i]);

        }
        for(int i=angle4base-1;i>=0;i--){
            mypath4.lineTo(xgroudi4[i],ygroudi4[i]);

        }
        mypathgroud.add(mypath4);

        GeneralPath mypath6=new GeneralPath();
        float [] xgroudo6= new float[angle6base];
        float [] ygroudo6= new float[angle6base];
        float [] xgroudi6= new float[angle6base];
        float [] ygroudi6= new float[angle6base];
        for(int i=0;i<angle6base;i++){
            xgroudo6[i]=(float) (ro*Math.cos(1*Math.PI-(i/180.0*Math.PI)));
            ygroudo6[i]=(float) (ro*Math.sin(1*Math.PI-(i/180.0*Math.PI)));
        }
        for(int i=0;i<angle6base;i++){
            xgroudi6[i]=(float) (ri*Math.cos(1*Math.PI-(i/180.0*Math.PI)));
            ygroudi6[i]=(float) (ri*Math.sin(1*Math.PI-(i/180.0*Math.PI)));
        }
        mypath6.moveTo(xgroudo6[0],ygroudo6[0]);
        for(int i=1;i<angle6base;i++){
            mypath6.lineTo(xgroudo6[i],ygroudo6[i]);

        }
        for(int i=angle6base-1;i>=0;i--){
            mypath6.lineTo(xgroudi6[i],ygroudi6[i]);

        }
        mypathgroud.add(mypath6);





        return mypathgroud;
    }

    @Override
    public void paintComponent(Graphics g) {
        int rb=100;
        int rs=105;

        super.paintComponent(g);

        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(getWidth() / 2, getHeight() / 2);
        g2d.scale(1,-1);
        g2d.setColor(Color.black);
//        g2d.drawLine(-200,0,200,0);
//        g2d.drawLine(0,200,0,-200);//建立坐标轴

        List<GeneralPath> mypathgroud0=DirectionaAngelPaint(this.mydirectionphase0,this.mydirectiondeviation0,0,rb);
        List<GeneralPath> mypathgroud2=DirectionaAngelPaint(this.mydirectionphase2,this.mydirectiondeviation2,2,rb);
        List<GeneralPath> mypathgroud4=DirectionaAngelPaint(this.mydirectionphase4,this.mydirectiondeviation4,4,rb);
        List<GeneralPath> mypathgroud6=DirectionaAngelPaint(this.mydirectionphase6,this.mydirectiondeviation6,6,rb);
        List<GeneralPath> mypathgroudblacksise=BlackSidePaint(rb-1,rs);
        List<GeneralPath> mypathgroudgraysise=GarySidePaint(rb-1,rs);

        for(GeneralPath mypath:mypathgroud0){

            g2d.setPaint(new Color(66,199,81));
            Stroke mystroke=new BasicStroke(1);
            g2d.setStroke(mystroke);
            g2d.draw(mypath);
            g2d.setPaint(new Color(66,199,81));
            g2d.fill(mypath);
        }

        for(GeneralPath mypath:mypathgroud2){
            g2d.setPaint(new Color(243,112,63));
            Stroke mystroke=new BasicStroke(1);
            g2d.setStroke(mystroke);
            g2d.draw(mypath);
            g2d.setPaint(new Color(243,112,63));
            g2d.fill(mypath);
        }
        for(GeneralPath mypath:mypathgroud4){
            g2d.setPaint(Color.pink);
            Stroke mystroke=new BasicStroke(1);
            g2d.setStroke(mystroke);
            g2d.draw(mypath);
            g2d.setPaint(Color.pink);
            g2d.fill(mypath);
        }
        for(GeneralPath mypath:mypathgroud6){
            g2d.setPaint(new Color(83  ,143,169));
            Stroke mystroke=new BasicStroke(2);
            g2d.setStroke(mystroke);
            g2d.draw(mypath);
            g2d.setPaint(new Color(83  ,143,169));
            g2d.fill(mypath);
        }
        for(GeneralPath mypath:mypathgroudblacksise){
            //AlphaComposite myAl=AlphaComposite.getInstance(AlphaComposite.SRC,0f);

            g2d.setPaint(Color.black);
            Stroke mystroke=new BasicStroke(1);
            g2d.setStroke(mystroke);
            //g2d.setComposite(myAl);
            g2d.draw(mypath);
            g2d.setPaint(Color.black);
            g2d.fill(mypath);
        }
        for(GeneralPath mypath:mypathgroudgraysise){
            g2d.setPaint(Color.gray);
            Stroke mystroke=new BasicStroke(1);
            g2d.setStroke(mystroke);
            g2d.draw(mypath);
            g2d.setPaint(Color.gray);
            g2d.fill(mypath);
        }

    }
}
