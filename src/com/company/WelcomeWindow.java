package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeWindow extends JFrame implements ActionListener {

    JButton button = new JButton("OK");
    JLabel label = new JLabel("HELLO WELCOME TO SNAKE GAME");

    public WelcomeWindow (){

        label.setBounds(20,120,600,75);
        label.setFont(new Font("INK Free", Font.BOLD,30));
        button.setBounds(200,260,200,40);
        button.setFocusable(false);
        button.addActionListener(this);
        this.add(label);
        this.add(button);
        this.setTitle("WELCOME TO SNAKE GAME"); // Sets the title of the Frame to ""Snake"
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exiting the program after clicking the close button
        this.setResizable(false); // Disabling the Re sizing of the frame
        this.setLayout(null);
        this.setVisible(true); // Setting frame to be visible
        this.setLocationRelativeTo(null); // Location of the frame to the middle of the window

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {
            this.dispose();
            new GameFrame();
        }
    }
}
