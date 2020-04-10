package states;

import GFX.Assets;
import ludogame.Game;

import java.awt.*;

public class MenuState extends State{



    public MenuState(Game game){
    super(game);

    }


    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.counter_b,0,0,null);
    }
}
