package states;

import GFX.Assets;
import Entities.ui.Button;
import GFX.DynamicBackground;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuState extends State{

	private final Button game,settings,ranking;
    private final DynamicBackground dynamicBackground;
    private final BufferedImage logo;

    public MenuState(Handler handler){
        super(handler);

        float BUTTON_SCALE=(float)0.8;
        game=new Button(handler,(handler.getFrameWidth()-350*BUTTON_SCALE)/2,495,BUTTON_SCALE,Assets.big_button_template,Assets.game_text);
        settings=new Button(handler,(handler.getFrameWidth()-350*BUTTON_SCALE)/2,575, BUTTON_SCALE,Assets.big_button_template,Assets.settings_button);
        ranking=new Button(handler,(handler.getFrameWidth()-350*BUTTON_SCALE)/2,655,BUTTON_SCALE,Assets.big_button_template,Assets.ranking_text);
        dynamicBackground=new DynamicBackground(handler,handler.getFrameHeight());
        logo=Assets.logo;
    }

    @Override
    public void tick() {

        dynamicBackground.tick();

        if(this.game.contains(handler.getMouseClickX(),handler.getMouseClickY())) {
            handler.resetMousePOS();
            handler.getPrepState().init(this.dynamicBackground);
            setState(handler.getGame().prepState);
        }
        else if(this.settings.contains(handler.getMouseClickX(),handler.getMouseClickY())) {
            handler.resetMousePOS();
            setState(handler.getGame().settingState);
            handler.getSettingState().setDynamicBackground(this.dynamicBackground);
        }
        else if(this.ranking.contains(handler.getMouseClickX(),handler.getMouseClickY())){
            handler.resetMousePOS();
            handler.getHighScoresState().init(this.dynamicBackground);
            setState(handler.getGame().highScoresState);

        }

    	game.tick();
    	settings.tick();
    	ranking.tick();
    }

    @Override
    public void render(Graphics g) {
    	g.setColor(Color.white);
        g.fillRect(0,0,handler.getFrameWidth(),handler.getFrameHeight());

        dynamicBackground.render(g);

        g.drawImage(logo, (handler.getFrameWidth()-(int)(logo.getWidth()*0.7))/2, 25,(int)(logo.getWidth()*0.7),(int)(Assets.logo.getHeight()*0.7),null);

        game.render(g);
        settings.render(g);
        ranking.render(g);
    }
}
