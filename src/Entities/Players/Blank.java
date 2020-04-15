package Entities.Players;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Blank extends Player{
    public Blank(int starting_pos, int ending_pos, BufferedImage counterColor) {
        super(starting_pos, ending_pos, counterColor);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
