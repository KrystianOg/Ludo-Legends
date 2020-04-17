package Entities.Players;

import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Bot extends Player {
    public Bot(Handler handler, int startingPos, int endingPos, BufferedImage ccolor) {
        super(handler,startingPos,endingPos,ccolor);
    }

    @Override
    public void tick() {
        getInput();
    }

    @Override
    public void render(Graphics g) {

    }

    private int getInput(){
        return (int)(Math.random()*4+1);
    }
}
