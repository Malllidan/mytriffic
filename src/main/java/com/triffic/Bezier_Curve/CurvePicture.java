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

    public CurvePanel(Map<String,Integer> myflowanglemap){
        this.myflowanglemap=myflowanglemap;
    }


    public void DirectionaAngelSwitch(){
        //List<String> mypathgroud=

    }
    @Override
    public void paintComponent(Graphics g) {
        int rb=100;
        int rs=95;
        int flow0baseangle=myflowanglemap.get("flow0base").intValue();
        int flow0to2=myflowanglemap.get("flow0to2").intValue();
        System.out.println(flow0to2);

        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(getWidth() / 2, getHeight() / 2);
        g2d.scale(1,-1);
        g2d.setColor(Color.black);
        g2d.drawLine(-200,0,200,0);
        g2d.drawLine(0,200,0,-200);//建立坐标轴

        float[] xgroud=new float[flow0baseangle];
        float[] ygroud=new float[flow0baseangle];
        for(int i=0;i<flow0baseangle;i++){

            xgroud[i]=(float) (rb*Math.cos(0.5*Math.PI+i/180.0*Math.PI));
            ygroud[i]=(float) (rb*Math.sin(0.5*Math.PI+i/180.0*Math.PI));
        }
        float[] xgroud1=new float[flow0to2];
        float[] ygroud1=new float[flow0to2];

        for(int i=0;i<flow0to2;i++){

            xgroud1[i]=(float) (rb*Math.cos(2*Math.PI-i/180.0*Math.PI));
            ygroud1[i]=(float) (rb*Math.sin(2*Math.PI-i/180.0*Math.PI));
        }

        //System.out.println(xgroud[28]);
        GeneralPath mypath=new GeneralPath(GeneralPath.WIND_EVEN_ODD,flow0baseangle+flow0to2);
        mypath.moveTo(xgroud[0],ygroud[0]);
        for(int i=1;i<xgroud.length;i++){
            mypath.lineTo(xgroud[i],ygroud[i]);
        }
        mypath.moveTo(xgroud[flow0to2-1],ygroud[flow0to2-1]);
        mypath.curveTo(0,0,0,0,xgroud1[xgroud1.length-1],ygroud1[ygroud1.length-1]);
        for(int i=xgroud1.length-2;i>0;i--){
            mypath.lineTo(xgroud1[i],ygroud1[i]);
        }

        mypath.curveTo(0,0,0,0,xgroud[0],ygroud[0]);
//        CubicCurve2D c=new CubicCurve2D.Double();
//        c.setCurve(xgroud[29],ygroud[29],0,0,0,0,xgroud1[9],ygroud1[9]);
        g2d.setPaint(Color.green);
        Stroke mystroke=new BasicStroke(1);
        g2d.setStroke(mystroke);
        //g2d.setStroke();

        g2d.draw(mypath);

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
