package com.mycoding.designpatterns;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingObserverExample {
    JFrame jFrame;

    public static void main(String[] args){
        SwingObserverExample example = new SwingObserverExample();
        example.go();
    }

    public void go(){
        jFrame = new JFrame();
        JButton button = new JButton("Should I do it?");
        button.addActionListener(new AngeListener());
        button.addActionListener(new DevilListener());
        jFrame.getContentPane().add(BorderLayout.CENTER, button);
        //在这里设置frame的属性
    }

    class AngeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Don't do it, you might regret it!");
        }
    }

    class DevilListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Come on, do it!");
        }
    }
}
