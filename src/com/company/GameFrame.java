package com.company;

import javax.swing.*;

public class GameFrame extends JFrame {
    
    public GameFrame(){

        ImageIcon image = new ImageIcon("Snake.jpg"); // Creating an IconImage
        this.add(new GamePanel()); // Creating an instance of the GamePane class
        this.setIconImage(image.getImage());// Changing the image of the Icon on top the Frame
        this.setTitle("SNAKE"); // Sets the title of the Frame to ""Snake"
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exiting the program after clicking the close button
        this.setResizable(false); // Disabling the Re sizing of the frame
        this.pack(); // Adding all the Components to the frame
        this.setVisible(true); // Setting frame to be visible
        this.setLocationRelativeTo(null); // Location of the frame to the middle of the window
    }
}
