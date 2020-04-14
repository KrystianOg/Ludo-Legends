package states;

import GFX.Assets;
import Entities.Button;
import ludogame.Handler;

import java.awt.*;

public class MenuState extends State{

	Entities.Button game, settings;


    public MenuState(Handler handler){
        super(handler);
        game=new Button(handler,(handler.getFrameWidth()-350)/2,500,350,90, Assets.game_button);
        settings=new Button(handler,(handler.getFrameWidth()-350)/2,600,350,90, Assets.settings_button);
    }


    @Override
    public void tick() {

        if(this.game.getHitbox().contains(handler.getMouseClickX(),handler.getMouseClickY()))
            setState(handler.getGame().gamestate);
        else if(this.settings.getHitbox().contains(handler.getMouseClickX(),handler.getMouseClickY()))
            setState(handler.getGame().settingstate);

    	game.tick();
    	settings.tick();
    }

    @Override
    public void render(Graphics g) {
    	g.setColor(new Color(153,153,153));
        g.fillRect(0,0,handler.getFrameWidth(),handler.getFrameHeight());
        g.drawImage(Assets.logo, 220, 25, 500, 500, null);

        game.render(g);
        settings.render(g);
    }
}
