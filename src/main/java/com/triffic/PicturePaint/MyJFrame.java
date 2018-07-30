package com.triffic.PicturePaint;

import com.triffic.Bezier_Curve.CurvePicture;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyJFrame {

    public static void main(String... arg) {

        PictureMaker(400,new MyPanel());

    }


//    public static void Graphicsmake(Graphics g) {
//        if(g!=null){
//             g.drawLine(100, 100, 200, 200);
//            g.setColor(Color.red);
//        }
//
//
//    }


    public static void PictureMaker(int imgsizeint,JPanel mp) {

        mp.setSize(new Dimension(imgsizeint, imgsizeint));
        Dimension imgsize = mp.getSize();

        JFrame mypicture = new JFrame();
        mypicture.setContentPane(mp);
        mypicture.setSize(imgsize.width, imgsize.height);
        mypicture.setLocationRelativeTo(null);


        //Graphics g = mp.getGraphics();
        //Graphicsmake(g);

        mypicture.setVisible(true);
        mypicture.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//可视化部分



        BufferedImage img = new BufferedImage(imgsizeint, imgsizeint, BufferedImage.TYPE_INT_RGB);
        Graphics g=img.createGraphics();
        mp.paintComponents(g);


//        this.Graphicsmake(g);
//        mp.paint(g);
        //String mypath=System.getProperty("user.dir");

        String pathname = System.getProperty("user.dir") + "\\.idea\\static\\images\\";
        File f = new File(pathname + "save.jpg");
        try {
            ImageIO.write(img, "jpg", f);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}



class MyPanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawLine(100, 100, 200, 200);
        g.setColor(Color.red);

//        g.drawLine(100, 100, 200, 200);
      //g.setColor(Color.red);


    }

}



