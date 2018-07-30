package com.triffic.Bezier_Curve;

import com.sun.javaws.util.JfxHelper;
import com.triffic.PicturePaint.MyJFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CurvePicture extends MyJFrame {


    public static void main(String... args) {
        CurvePanel mp = new CurvePanel();
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
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(getWidth() / 2, getHeight() / 2);
        g2d.scale(1,-1);
        g2d.setColor(Color.red);
        g2d.drawLine(20,20,100,100);

        //g.drawRect(20,20,100,100);



    }
}
