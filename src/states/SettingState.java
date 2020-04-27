package states;

import Entities.ui.Button;
import Entities.ui.Slider;
import GFX.Assets;
import GFX.DynamicBackground;
import ludogame.Game;
import ludogame.Handler;

import java.awt.*;

public class SettingState extends State{

    //SETTINGS
    public static int FPS,BUTTON_SIZE,ULT_LOAD,DICE_ANIM_TIME;
    public static boolean FPS_COUNTER,DYNAMIC_BACKGROUND;

	private final Slider slider_fps;
    private final Slider button_size;
    private final Slider ultload;
	private final Button back;
    private final Button reset_all;
    private final Slider dice_anim_time;

    DynamicBackground dynamicBackground;//time in seconds

    public SettingState(Handler handler){
        super(handler);

        //default values
        FPS=60;                //ilość FPS
        BUTTON_SIZE=100;       //wartość procentowa
        ULT_LOAD=100;           //wartość procentowa
        FPS_COUNTER=false;
        DICE_ANIM_TIME=10;
        DYNAMIC_BACKGROUND=true;

        handler.setSettingState(this);

        slider_fps=new Slider(handler, 100, 100, 17, 29, 200, 25, 144, 60, "FPS");
        button_size=new Slider(handler, 100, 200, 17, 29, 200, 50, 150, 100, "HUD size");
        ultload=new Slider(handler, 100, 300, 17, 29, 200, 0, 400, 100, "Ultimate ability load speed");
        dice_anim_time=new Slider(handler, 100, 400, 17, 29, 200, 3, 30, 10, "Dice animation time");
        back=new Button(handler, handler.getFrameWidth() -100, handler.getFrameHeight() -100,(float)0.5,  Assets.big_button_template,Assets.defaults_button);
        reset_all=new Button(handler, 100, 700, (float)0.5, Assets.big_button_template,Assets.defaults_button);
    }

    @Override
    public void tick() {

        dynamicBackground.tick();
    	if(this.back.getHitbox().contains(handler.getMouseClickX(),handler.getMouseClickY())) {
            handler.resetMousePOS();
            setSettings();
    	    setState(handler.getGame().menuState);
            System.out.println("FPS: "+SettingState.FPS+"\tBUTTON_SIZE: "+SettingState.BUTTON_SIZE+"\tULTLOAD: "+SettingState.ULT_LOAD);
        }
    	slider_fps.tick();
    	button_size.tick();
    	ultload.tick();
    	back.tick();
    	dice_anim_time.tick();
    	if(this.reset_all.getHitbox().contains(handler.getMouseClickX(),handler.getMouseClickY())) {
            slider_fps.reset();
            button_size.reset();
            ultload.reset();
            dice_anim_time.reset();
    	}

    	reset_all.tick();
    }

    @Override
    public void render(Graphics g) {

    	g.setColor(Color.white);
        g.fillRect(0,0,handler.getFrameWidth(),handler.getFrameHeight());
        if(SettingState.DYNAMIC_BACKGROUND)
        dynamicBackground.render(g);
        slider_fps.render(g);
        button_size.render(g);
        ultload.render(g);
        back.render(g);
        reset_all.render(g);
        dice_anim_time.render(g);
    }

    public void setDynamicBackground(DynamicBackground dynamicBackground){
        this.dynamicBackground=dynamicBackground;
    }

    public void setSettings(){
        SettingState.FPS=this.slider_fps.getValueInt();
        SettingState.BUTTON_SIZE=this.button_size.getValueInt();
        SettingState.ULT_LOAD=this.ultload.getValueInt();
        SettingState.DICE_ANIM_TIME=this.dice_anim_time.getValueInt();
    }
}
