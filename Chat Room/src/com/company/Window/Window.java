package com.company.Window;
import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class Window extends Thread{

    public static String message;

    public Window() {
    }

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


        int a=0,b=0,c=0;

        boolean acc = true;
        Color color1 = new Color(0, 0, 0);

        Random random = new Random();

        while(true){

            //if (a!=255 && acc){a++;c++;}else if (!acc && a!=0){a--;c--;}else if (a==0){acc=true;}else if (a == 255){acc=false;}
            //    window.getContentPane().setBackground(new Color(a,b,c));


            //window.getContentPane().setBackground(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
            //jLabel.setForeground(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            labelUpdate(jLabel);

        }

    }

    public void labelUpdate(JLabel jLabel){
        jLabel.setText("<html>" +message+ "</html>");
    }

}
