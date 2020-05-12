package players;

import entities.counters.Counter;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Blank extends Player{

    public Blank(Handler handler, PositionOnMap starting_pos, PositionOnMap ending_pos, BufferedImage counterColor) {
        super(handler,starting_pos, ending_pos, counterColor);

        this.nickname="";
        counter=new Counter[4];
        counter[0]=null;
        counter[1]=null;
        counter[2]=null;
        counter[3]=null;
        won=true;
    }

    @Override
    public void tick() {
        handler.getGameState().setTurnof();
    }

    @Override
    public void render(Graphics g) {
    }
}
