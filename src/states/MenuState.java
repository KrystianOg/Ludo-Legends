package states;

import Entities.ui.ClickListener;
import Entities.ui.UIImageButton;
import GFX.Assets;
import ludogame.Game;
import ludogame.Handler;

import java.awt.*;

public class MenuState extends State{

	Entities.Button game, settings;


    public MenuState(Handler handler){
    super(handler);

    game=new Entities.Button(handler,(handler.getFrameWidth()-350)/2,500,350,90, Assets.game_button,handler.getGame().prepstate);
    
    settings=new Entities.Button(handler,(handler.getFrameWidth()-350)/2,600,350,90, Assets.settings_button,handler.getGame().settingstate);
    
    }


    @Override
    public void tick() {
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
