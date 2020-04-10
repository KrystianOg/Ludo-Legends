package states;

import Entities.Counters.Counter;
import Entities.Player;
import ludogame.Game;

import java.awt.*;

public class PrepState extends State {

    int[][] counter=new int[4][4];




    public PrepState(Game game) {
        super(game);
    }



    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
