package com.youngyeah;

import com.alipay.demo.trade.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 李凌耀 on 2017/8/7.
 */
public class Pay extends JFrame{
    String ordername;

    public Pay(String ordername) throws HeadlessException {
        this.ordername = ordername;
    }

    public Pay() throws HeadlessException {
        this.setLayout(new FlowLayout());
        ImageIcon imageIcon = new ImageIcon("D:\\out.png");
        JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        this.add(jLabel);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        confirm c = new confirm(ordername,this);
        c.start();
    }
}

class confirm extends Thread
{
    private String ordername;
    private JFrame jFrame;

    public confirm(String ordername, JFrame jFrame) {
        this.ordername = ordername;
        this.jFrame = jFrame;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i=0;
        while(!Main.confirm(ordername))
        {
            System.out.println("第"+i+"次查询");
            i++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i >= 59)
            {
                System.out.println("支付查询失败");
                jFrame.dispose();
                return;
            }
        }
        new Upload().upload();
        jFrame.dispose();
    }
}