package Entities.Counters;

import ludogame.Handler;
import states.GameState;

import java.awt.*;

public class Albali extends Counter {
    //zbija wszystkie ktore minie podczas trwania umiejetnosci;

    public static final int ULT_LOAD=40;


    public Albali(Handler handler, float x, float y,int counterNr) {
        super(handler,x, y,counterNr);
    }

    @Override
    public int getUltLoad() {
        return ULT_LOAD;
    }

    @Override
    public void renderPick(Graphics g) {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
