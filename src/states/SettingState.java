package states;

import Entities.ui.Button;
import Entities.ui.Slider;
import GFX.Assets;
import ludogame.Game;
import ludogame.Handler;

import java.awt.*;

public class SettingState extends State{

    //SETTINGS
    public static int FPS,BUTTON_SIZE,ULT_LOAD;
    public static boolean FPS_COUNTER;

	private Slider slider_fps, button_size,ultload;
	private Button back;
	
    public SettingState(Handler handler){
        super(handler);

        //default values
        this.FPS=60;                //ilość FPS
        this.BUTTON_SIZE=100;       //wartość procentowa
        this.ULT_LOAD=100;           //wartość procentowa
        this.FPS_COUNTER=false;

        slider_fps=new Slider(handler, 100, 100, 17, 29, 200, 25, 144, 60, "FPS");
        button_size=new Slider(handler, 100, 200, 17, 29, 200, 50, 150, 100, "HUD size");
        ultload=new Slider(handler, 100, 300, 17, 29, 200, 0, 400, 100, "Ultimate ability load speed");
        back=new Button(handler, handler.getFrameWidth() -100, handler.getFrameHeight() -100, 90, 90, Assets.back_button);

    }

    @Override
    public void tick() {
    	if(this.back.getHitbox().contains(handler.getMouseClickX(),handler.getMouseClickY())) {
            handler.resetMousePOS();
            setSettings();
    	    setState(handler.getGame().menustate);
            System.out.println("FPS: "+SettingState.FPS+"\tBUTTON_SIZE: "+SettingState.BUTTON_SIZE+"\tULTLOAD: "+SettingState.ULT_LOAD);
        }
    	slider_fps.tick();
    	button_size.tick();
    	ultload.tick();
    	back.tick();
    }

    @Override
    public void render(Graphics g) {

    	g.setColor(Game.MENU_GRAY);
        g.fillRect(0,0,handler.getFrameWidth(),handler.getFrameHeight());
        slider_fps.render(g);
        button_size.render(g);
        ultload.render(g);
        back.render(g);
    }

    public void setSettings(){
        SettingState.FPS=this.slider_fps.getValueInt();
        SettingState.BUTTON_SIZE=this.button_size.getValueInt();
        SettingState.ULT_LOAD=this.ultload.getValueInt();
    }
}
