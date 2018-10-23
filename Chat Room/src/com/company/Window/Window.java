package com.company.Window;
import javax.swing.*;
import java.awt.*;

public class Window implements Runnable{

    public void run() {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel jLabel = new JLabel();
        jLabel.setHorizontalAlignment(window.getWidth());
        window.getContentPane().add(jLabel, BorderLayout.CENTER);
        window.pack();
        window.setSize(500,500);
        window.setVisible(true);

        int nr = 0;
        while(true){

            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            nr = labelUpdate(jLabel,nr);
        }

    }

    public int labelUpdate(JLabel jLabel,int nr){
        nr++;
        jLabel.setText("" + nr);
        return nr;
    }

}
