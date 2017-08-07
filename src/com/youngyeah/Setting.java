package com.youngyeah;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 李凌耀 on 2017/8/7.
 */
public class Setting extends JFrame {
    public Setting() throws HeadlessException {
        this.setLayout(new FlowLayout());
        JTextField copies = new JTextField("打印份数");

        JButton jButton1 = new JButton("双面打印");
        jButton1.addActionListener(new Acton(jButton1,copies,this));

        JButton jButton2 = new JButton("单面打印");
        jButton2.addActionListener(new Acton(jButton2,copies,this));

        this.add(copies);
        this.add(jButton1);
        this.add(jButton2);


        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}

class Acton implements ActionListener
{
    JButton jButton = null;
    JTextField copies = null;
    JFrame jFrame = null;

    public Acton(JButton jButton, JTextField copies, JFrame jFrame) {
        this.jButton = jButton;
        this.copies = copies;
        this.jFrame = jFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(jButton.getText().equals("双面打印"))
        {
            Factory.getInstance().getPrintSettingInstance().setDoublepages(true);
            Factory.getInstance().getPrintSettingInstance().setCopys(Integer.parseInt(copies.getText()));
        }
        else
        {
            Factory.getInstance().getPrintSettingInstance().setDoublepages(false);
            Factory.getInstance().getPrintSettingInstance().setCopys(Integer.parseInt(copies.getText()));
        }
        String pay = PayInfo.pay();
        new Pay(pay);
        this.jFrame.dispose();
    }
}