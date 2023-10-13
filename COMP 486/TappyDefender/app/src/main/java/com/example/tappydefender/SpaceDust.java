package com.example.tappydefender;

import java.util.Random;

/**
 * Created on 9/28/2015.
 */
public class SpaceDust {
    private int x, y;
    private int speed;

    // Detect dust leaving the screen
    private int maxX;
    private int minX;
    private int maxY;
    private int minY;

    public SpaceDust(int screenX, int screenY) {
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;

        // Set a speed between 0 and 9
        Random generator = new Random();
        speed = generator.nextInt(10);

        // Set the starting coordinates
        x = generator.nextInt(maxX);
        y = generator.nextInt(maxY);

    }

    public void update(int playerSpeed){
        // Speed up when the player does
        x -= playerSpeed;
        x -= speed;

        // respawn space dust
        if(x < 0){
            x = maxX;
            Random generator = new Random();
            y = generator.nextInt(maxY);
            speed = generator.nextInt(15);

        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}