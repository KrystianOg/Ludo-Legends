package Entities;

import Entities.Entity;

import java.awt.*;

public class Dice extends Entity {

    public static final int DICE_WIDTH=75,
                            DICE_HEIGHT=75;


    private int x,y;
    private int width,height;

    public Dice(int x, int y, int width, int height) {
        super(x, y, width, height);



    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
