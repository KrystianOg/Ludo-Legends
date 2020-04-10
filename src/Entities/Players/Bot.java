package Entities.Players;

import Entities.Counters.Counter;
import Entities.Player;

import java.awt.*;


public class Bot extends Player {
    public Bot(int starting_pos) {
        super(starting_pos);
    }

    @Override
    public void tick() {
        getInput();
        move();
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void move() {

    }

    private int getInput(){

        return (int)(Math.random()*4+1);

    }
}
