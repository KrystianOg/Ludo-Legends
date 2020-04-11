package states;

import GFX.Assets;
import ludogame.Game;
import ludogame.Handler;

import java.awt.*;

public class SettingState extends State{


    public SettingState(Handler handler){
        super(handler);
    }


    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.rollimg[5],0,0,null);
    }
}
