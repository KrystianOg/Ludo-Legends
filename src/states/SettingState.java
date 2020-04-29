package states;

import Entities.ui.Button;
import Entities.ui.Slider;
import GFX.Assets;
import GFX.DynamicBackground;
import ludogame.Game;
import ludogame.Handler;

import java.awt.*;
import java.util.ArrayList;

public class SettingState extends State{

    //SETTINGS
    public static int FPS,BUTTON_SIZE,ULT_LOAD,DICE_ANIM_TIME;
    public static boolean FPS_COUNTER,DYNAMIC_BACKGROUND;
    
    private int m;
	private final Slider slider_fps;
    private final Slider button_size;
    private final Slider ultload;
	private final Button back;
    private final Button reset_all;
    private final Button dynamic_background;
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
        
        ArrayList<Integer> szczebleFPS = new ArrayList<Integer>();
        szczebleFPS.add(25);
        szczebleFPS.add(50);
        szczebleFPS.add(60);
        szczebleFPS.add(100);
        szczebleFPS.add(120);
        szczebleFPS.add(144);
        slider_fps=new Slider(handler, 100, 100, 17, 29, 200, 25, 144, 60, "FPS", szczebleFPS);
        button_size=new Slider(handler, 100, 200, 17, 29, 200, 50, 150, 100, "HUD size");
        ultload=new Slider(handler, 100, 300, 17, 29, 200, 0, 400, 100, "Ultimate ability load speed");
        dice_anim_time=new Slider(handler, 100, 400, 17, 29, 200, 3, 30, 10, "Dice animation time");
        back=new Button(handler, handler.getFrameWidth() -100, handler.getFrameHeight() -100,(float)0.5,  Assets.big_button_template,Assets.defaults_button);
        dynamic_background=new Button(handler, 100, 500,(float)0.5, Assets.big_button_template,Assets.defaults_button, 1);
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
    	dynamic_background.tick();
    	if(this.dynamic_background.getHitbox().contains(handler.getHoverX(),handler.getHoverY()) && handler.getGame().getMousemanager().isLeftPressed()) {
            if (m==0)
            	if (dynamic_background.getOnOff()==1) {
            		dynamic_background.setOnOff(2);
            		dynamicBackground.setOnOff(false);
            		m++;
            	}
            	else if (dynamic_background.getOnOff()==2) {
            		dynamic_background.setOnOff(1);
            		dynamicBackground.setOnOff(true);
            		m++;
            	}   
    	}
    	 if (!handler.getGame().getMousemanager().isLeftPressed()) {
         	m=0;
         }
    	if(this.reset_all.getHitbox().contains(handler.getMouseClickX(),handler.getMouseClickY())) {
            slider_fps.reset();
            button_size.reset();
            ultload.reset();
            dice_anim_time.reset();
            dynamic_background.reset();
    	}
    	reset_all.tick();
    	if(dynamic_background.getOnOff()==1 && !dynamicBackground.isOnOff()) {
    		dynamicBackground.setOnOff(true);
    	}
    	if(dynamic_background.getOnOff()==2 && dynamicBackground.isOnOff()) {
    		dynamicBackground.setOnOff(false);
    	}
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
        dynamic_background.render(g);
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
