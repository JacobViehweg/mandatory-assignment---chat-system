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


        window.getContentPane().setBackground(Color.GREEN);
        jLabel.setFont(new Font("Courier", Font.BOLD,150));

        jLabel.setForeground(Color.MAGENTA);

        window.pack();
        window.setSize(500,500);
        window.setVisible(true);

        int nr = 0;

        while(true){

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            nr = labelUpdate(jLabel,nr);

        }

    }

    public int labelUpdate(JLabel jLabel,int nr){
        nr++;
        jLabel.setText("<html>" + nr + " $ aquired<br/>from lootboxes!</html>");
        return nr;
    }

}
