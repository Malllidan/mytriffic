package com.triffic.Bezier_Curve;

import com.sun.javaws.util.JfxHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CurvePicture extends JFrame {


    public static void main(String...args){

        MyPanel mp=new MyPanel();
        CurvePicture mypicture=new CurvePicture();



        mypicture.add(mp);
        mypicture.setSize(400,300);
        //mypicture.setVisible(true);
        mypicture.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BufferedImage img=new BufferedImage(mypicture.getWidth(),mypicture.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Container content=mypicture.getContentPane();
        Graphics g=img.createGraphics();
        g.drawLine(10,10,20,20);
        content.paintAll(g);
        //String mypath=System.getProperty("user.dir");
        String pathname=System.getProperty("user.dir")+"\\.idea\\static\\images\\";
        File f=new File(pathname+"save.jpg");
        try {
            ImageIO.write(img,"jpg",f);
        }catch (IOException e){
            e.printStackTrace();
        }



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

class MyPanel extends JPanel{
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawLine(20,20,100,100);



    }
}
