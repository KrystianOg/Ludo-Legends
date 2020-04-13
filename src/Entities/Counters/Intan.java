package Entities.Counters;

import ludogame.Handler;
import states.GameState;

import java.awt.*;

public class Intan extends Counter {
    //2 razy trzeba go zbic zeby przesunal sie do "bazy"



    public Intan(Handler handler, float x, float y, int width, int height,int counterNr) {
        super(handler,x, y, width, height,counterNr);
    }

    @Override
    public int getUltLoad() {
        return 0;
    }

    @Override
    public void move() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
