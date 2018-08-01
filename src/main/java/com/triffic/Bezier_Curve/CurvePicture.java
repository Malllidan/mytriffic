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
            if(!directionname.substring(directionname.length()-4).equals("base")){
                GeneralPath mypath=new GeneralPath(GeneralPath.WIND_EVEN_ODD,this.myflowanglemap.get(directionname).intValue()*2);
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
                        mypath.moveTo(xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
                        //mypath.curveTo(0,0,0,0,xgroud1[xgroud1.length-1],ygroud[ygroud1.length-1]);
                        for(int i=xgroud1.length-2;i>=0;i--){
                            mypath.lineTo(xgroud1[i],ygroud1[i]);
                        }
                        //mypath.curveTo(0,0,0,0,xgroud[0],ygroud[0]);
                        mypathgroud.add(mypath);

                    }

                }
                //System.out.println(directionname.substring(directionname.length()-4));
                //System.out.println(directionname);

            }
        }

    return mypathgroud;

    }
    @Override
    public void paintComponent(Graphics g) {
        int rb=100;
        int rs=95;
//        int flow0baseangle=myflowanglemap.get("flow0base").intValue();
//        int flow0to2=myflowanglemap.get("flow0to2").intValue();
//        System.out.println(flow0to2);

        super.paintComponent(g);

        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(getWidth() / 2, getHeight() / 2);
        g2d.scale(1,-1);
        g2d.setColor(Color.black);
        g2d.drawLine(-200,0,200,0);
        g2d.drawLine(0,200,0,-200);//建立坐标轴

//        float[] xgroud=new float[flow0baseangle];
//        float[] ygroud=new float[flow0baseangle];
//        for(int i=0;i<flow0baseangle;i++){
//
//            xgroud[i]=(float) (rb*Math.cos(0.5*Math.PI+i/180.0*Math.PI));
//            ygroud[i]=(float) (rb*Math.sin(0.5*Math.PI+i/180.0*Math.PI));
//        }
//        float[] xgroud1=new float[flow0to2];
//        float[] ygroud1=new float[flow0to2];
//
//        for(int i=0;i<flow0to2;i++){
//
//            xgroud1[i]=(float) (rb*Math.cos(2*Math.PI-i/180.0*Math.PI));
//            ygroud1[i]=(float) (rb*Math.sin(2*Math.PI-i/180.0*Math.PI));
//        }
//
//        //System.out.println(xgroud[28]);
//        GeneralPath mypath=new GeneralPath(GeneralPath.WIND_EVEN_ODD,flow0baseangle+flow0to2);
//        mypath.moveTo(xgroud[0],ygroud[0]);
//        for(int i=1;i<xgroud.length;i++){
//            mypath.lineTo(xgroud[i],ygroud[i]);
//        }
//        mypath.moveTo(xgroud[flow0to2-1],ygroud[flow0to2-1]);
//        mypath.curveTo(0,0,0,0,xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
//        for(int i=xgroud1.length-2;i>0;i--){
//            mypath.lineTo(xgroud1[i],ygroud1[i]);
//        }
//
//        mypath.curveTo(0,0,0,0,xgroud[0],ygroud[0]);
//        CubicCurve2D c=new CubicCurve2D.Double();
//        c.setCurve(xgroud[29],ygroud[29],0,0,0,0,xgroud1[9],ygroud1[9]);
        List<GeneralPath> mypathgroud=DirectionaAngelPaint(this.mydirectionphase0,this.mydirectiondeviation0,0,100);
        g2d.setPaint(Color.black);
        Stroke mystroke=new BasicStroke(1);
        g2d.setStroke(mystroke);
        //g2d.setStroke();

        g2d.draw(mypathgroud.get(0));

        g2d.setPaint(Color.green);

        //g2d.fill(mypath);

        //g2d.draw(c);






        //g2d.drawLine(20,20,100,100);
        //g2d.scale(1,-1);
//        g2d.drawArc(-rb,-rb,2*rb,2*rb,90,flow0baseangle);//用g2d画图
//        g2d.drawArc(-rs,-rs,2*rs,2*rs,90,flow0baseangle);
//        g2d.setColor(Color.black);
//        g2d.fillArc(-rb,-rb,2*rb,2*rb,90,flow0baseangle);
//        g2d.setColor(Color.green);
//        g2d.fillArc(-rs,-rs,2*rs,2*rs,90,flow0baseangle);








        //g.drawRect(20,20,100,100);



    }
}
