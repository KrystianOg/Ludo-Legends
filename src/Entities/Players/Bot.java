package Entities.Players;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Bot extends Player {
    public Bot(int starting_pos, BufferedImage ccolor) {
        super(starting_pos,ccolor);
    }

    @Override
    public void tick() {
        getInput();
        move();
    }

    @Override
    public void render(Graphics g) {

    }

    private int getInput(){
        return (int)(Math.random()*4+1);
    }
}
