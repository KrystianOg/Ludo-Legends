package states;

import GFX.Assets;
import Entities.ui.Button;
import GFX.DynamicBackground;
import ludogame.DBConnect;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GFX.Assets.logo;

public class MenuState extends State{

    private final Button game,settings,ranking,exit;
    private final DynamicBackground dynamicBackground;
    private DBConnect connect;
    private int idleTimer;
    public static int MAX_IDLE_TIME=100*SettingState.FPS;

    public MenuState(Handler handler){
        super(handler);

        float BUTTON_SCALE=(float)0.8;
        game=new Button(handler,(handler.getFrameWidth()-350*BUTTON_SCALE)/2,455,BUTTON_SCALE,Assets.big_button_template,"GAME",58);
        settings=new Button(handler,(handler.getFrameWidth()-350*BUTTON_SCALE)/2,535, BUTTON_SCALE,Assets.big_button_template,"SETTINGS",58);
        ranking=new Button(handler,(handler.getFrameWidth()-350*BUTTON_SCALE)/2,615,BUTTON_SCALE,Assets.big_button_template,"RANKING",58);
        exit=new Button(handler,(handler.getFrameWidth()-350*BUTTON_SCALE)/2,695,BUTTON_SCALE,Assets.big_button_template,"EXIT",58);
        dynamicBackground=new DynamicBackground(handler,handler.getFrameHeight());
    }

    @Override
    public void tick() {

        if(dynamicBackground!=null)
            dynamicBackground.tick();

        if(this.game.contains(handler.getMouseClickX(),handler.getMouseClickY())) {
            handler.resetMousePOS();
            handler.getPrepState().init(this.dynamicBackground);
            if(!handler.getGameState().isInGame())
                setState(handler.getGame().prepState);
            else
                setState(handler.getGame().gameState);
        }
        else if(this.settings.contains(handler.getMouseClickX(),handler.getMouseClickY())) {
            handler.resetMousePOS();
            setState(handler.getGame().settingState);
            handler.getSettingState().setDynamicBackground(this.dynamicBackground);
        }
        else if(this.ranking.contains(handler.getMouseClickX(),handler.getMouseClickY())){
            handler.resetMousePOS();
            handler.getHighScoresState().init(this.dynamicBackground,connect);
            setState(handler.getGame().highScoresState);

        }else if(this.exit.contains(handler.getMouseClickX(),handler.getMouseClickY())){
            System.exit(0);
        }

    	game.tick();
    	settings.tick();
    	ranking.tick();
    	exit.tick();
    	//idle();

    }

    @Override
    public void render(Graphics g) {
    	g.setColor(Color.white);
        g.fillRect(0,0,handler.getFrameWidth(),handler.getFrameHeight());

        if(dynamicBackground!=null)
            dynamicBackground.render(g);


        double LOGO_SCALE = 0.62;
        g.drawImage(logo, (handler.getFrameWidth()-(int)(logo.getWidth()* LOGO_SCALE))/2, 25,(int)(logo.getWidth()* LOGO_SCALE),(int)(logo.getHeight()* LOGO_SCALE),null);

        game.render(g);
        settings.render(g);
        ranking.render(g);
        exit.render(g);
    }

    public void setConnection(DBConnect connect){
        this.connect=connect;
    }

    private void idle(){
        idleTimer++;

        if(idleTimer>=MAX_IDLE_TIME){
            idleTimer=0;
            connect.close();
            connect=handler.getLoadingScreen().getConnection();
        }
    }

}
