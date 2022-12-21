package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    // All the static and No-Static Variable variables
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 60;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten = 0;
    int appleX;
    int appleY;
    char direction = 'D';
    boolean running = false;
    Timer timer;
    Random random;

    // The Create a Visible Window with Specified Dimension and Colors...
    public GamePanel() {

        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.GRAY);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    // The Method that Starts all the Gaming operations
    public void startGame() {

        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {

      if (running) {

          /* This For Loop Add a Grid on the panel
          for (int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++) {
              g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,SCREEN_HEIGHT);
              g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
          }
          */
          g.setColor(Color.GREEN); // Sets the Apple Color
          g.fillOval(appleX,appleY,UNIT_SIZE,UNIT_SIZE); // Sets the Apple Location Randomly

          for (int i = 0 ; i < bodyParts; i++) {

              g.setColor(Color.BLUE); // Sets the Head Color of the Snake

              if (i == 0){
                  g.fillOval(x[i],y[i],UNIT_SIZE,UNIT_SIZE);

              }else{
                  g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                  g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
              }
          }
      }else {
          gameOver(g);
      }
    }
    public void newApple() {

        appleX = random.nextInt((SCREEN_WIDTH/UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((SCREEN_HEIGHT/UNIT_SIZE)) * UNIT_SIZE;
    }
    public void move() {

        for (int i = bodyParts; i > 0; i--){

            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch (direction) {

            case 'W' -> y[0] = y[0] - UNIT_SIZE;
            case 'S' -> y[0] = y[0] + UNIT_SIZE;
            case 'A' -> x[0] = x[0] - UNIT_SIZE;
            case 'D' -> x[0] = x[0] + UNIT_SIZE;
        }
    }
    public void checkApple() {

        if ((x[0] == appleX) && (y[0] == appleY)){

            bodyParts++;
            applesEaten++;
            newApple();
        }
    }
    public void checkCollision() {

        // Checks if the Head collided with body
        for (int i = bodyParts; i > 0 ; i--) {

            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
                break;
            }
        }
        // Check if Head touched the left border
        if (x[0] < 0) {
            running = false;
        }
        // Check if Head touched Right border
        if (x[0] > SCREEN_WIDTH) {
            running = false;
        }
        // Checks if Head touched the top border
        if (y[0] < 0) {
            running = false;
        }
        // Check if head touched bottom border
        if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }
        // Stopping the timer
        if (!running) {
            timer.stop();
        }

    }
    public void gameOver(Graphics g) {

        // This will display the Score
        g.setColor(Color.RED);
        g.setFont(new Font("INK Free", Font.BOLD,40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Player's SCORE: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Player's SCORE: " + applesEaten))/2, g.getFont().getSize());

        // Game Over text
        g.setColor(Color.RED);
        g.setFont(new Font("INK Free", Font.BOLD,75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("GAME OVER!...", (SCREEN_WIDTH - metrics2.stringWidth("GAME OVER!..."))/2, SCREEN_HEIGHT/2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(running) {
            move();
            checkApple();
            checkCollision();
        }
        repaint();

    }
    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {

                case KeyEvent.VK_LEFT:

                    if (direction != 'D') {
                        direction = 'A';
                    }
                    break;
                case KeyEvent.VK_RIGHT:

                    if (direction != 'A') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_UP:

                    if (direction != 'S') {
                        direction = 'W';
                    }
                    break;
                case KeyEvent.VK_DOWN:

                    if (direction != 'W') {
                        direction = 'S';
                    }
                    break;
            }
        }
    }
}


