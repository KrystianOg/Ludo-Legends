package Players;

import Entities.PositionOnMap;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Blank extends Player{

    public Blank(Handler handler, PositionOnMap starting_pos, PositionOnMap ending_pos, BufferedImage counterColor) {
        super(handler,starting_pos, ending_pos, counterColor);
        counter=null;
        currentlyinbase=0;
    }

    @Override
    public void tick() {
        handler.getGameState().setTurnof();
    }

    @Override
    public void render(Graphics g) {
    }
}