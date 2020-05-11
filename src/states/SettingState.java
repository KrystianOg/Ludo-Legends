package states;

import Entities.ui.Button;
import Entities.ui.Slider;
import Entities.ui.Switch;
import GFX.Assets;
import GFX.DynamicBackground;
import ludogame.Handler;

import java.awt.*;
import java.util.ArrayList;

public class SettingState extends State{

    //SETTINGS
    public static int FPS,BUTTON_SIZE,ULT_LOAD,DICE_ANIM_TIME;
    public static boolean FPS_COUNTER,DYNAMIC_BACKGROUND,SOUND;

	private final Slider sliderFps;
    private final Slider ultLoad;
	private final Button back;
    private final Button defaults;
    private final Button apply;
    private final Switch dynamicBackgroundSwitch;
    private final Switch fpsCounterSwitch;
    private final Switch soundSwitch;
    private final Slider diceAnimTime;

    private DynamicBackground dynamicBackground;

    public SettingState(Handler handler){
        super(handler);

        //default values
        FPS=60;                //ilość FPS
        BUTTON_SIZE=100;       //wartość procentowa
        ULT_LOAD=100;           //wartość procentowa
        FPS_COUNTER=false;
        DICE_ANIM_TIME=10;
        DYNAMIC_BACKGROUND=true;
        SOUND=true;

        //int[] szczebleFPS = {30,50,60,100,120,144};
        sliderFps=new Slider(handler, (handler.getFrameWidth()-Slider.SLIDER_WIDTH) /2, 220, 200, 30, 144, 60, "FPS");
        ultLoad=new Slider(handler, (handler.getFrameWidth()-Slider.SLIDER_WIDTH) /2, 280, 200, 0, 400, 100, "Ultimate ability load speed");
        diceAnimTime=new Slider(handler, (handler.getFrameWidth()-Slider.SLIDER_WIDTH) /2, 340, 200, 3, 30, 10, "Dice animation time");

        dynamicBackgroundSwitch=new Switch(handler, (handler.getFrameWidth()-Switch.SWITCH_WIDTH) /2,400,0.7,SettingState.DYNAMIC_BACKGROUND,"Dynamic background");
        fpsCounterSwitch=new Switch(handler, (handler.getFrameWidth()-Switch.SWITCH_WIDTH) /2,460,0.7,SettingState.FPS_COUNTER,"FPS Counter");
        soundSwitch=new Switch(handler,(handler.getFrameWidth()-Switch.SWITCH_WIDTH)/2,520,0.7,SettingState.SOUND,"Sound");

        defaults=new Button(handler, 140, handler.getFrameHeight()-130, 0.6, Assets.big_button_template,"DEFAULTS",34);
        back=new Button(handler, handler.getFrameWidth() -290, handler.getFrameHeight() -130,0.6,  Assets.medium_button_template,"BACK",34);
        apply=new Button(handler,handler.getFrameWidth()/2-30,handler.getFrameHeight()-130,0.6,Assets.medium_button_template,"APPLY",34);
    }

    @Override
    public void tick() {

        dynamicBackground.tick();
    	if(this.back.contains(handler.getMouseClickX(),handler.getMouseClickY())) {
            handler.resetMousePOS();
            setSettings();
    	    setState(handler.getGame().menuState);
        }

    	sliderFps.tick();
    	ultLoad.tick();
    	back.tick();

    	diceAnimTime.tick();
    	dynamicBackgroundSwitch.tick();
    	fpsCounterSwitch.tick();
    	soundSwitch.tick();
        apply.tick();

    	if(this.defaults.contains(handler.getMouseClickX(),handler.getMouseClickY())){
    	    handler.resetMousePOS();
    	    resetAll();}

    	else if(this.apply.contains(handler.getMouseClickX(),handler.getMouseClickY())){
    	    handler.resetMousePOS();
    	    setSettings();
        }

    	defaults.tick();


    }

    @Override
    public void render(Graphics g) {

    	g.setColor(Color.white);
        g.fillRect(0,0,handler.getFrameWidth(),handler.getFrameHeight());
        if(SettingState.DYNAMIC_BACKGROUND)
        dynamicBackground.render(g);

        sliderFps.render(g);
        ultLoad.render(g);
        back.render(g);

        defaults.render(g);
        diceAnimTime.render(g);
        dynamicBackgroundSwitch.render(g);
        fpsCounterSwitch.render(g);
        soundSwitch.render(g);
        apply.render(g);
    }

    public void setDynamicBackground(DynamicBackground dynamicBackground){
        this.dynamicBackground=dynamicBackground;
    }

    public void setSettings(){
        FPS=sliderFps.getValue();
        ULT_LOAD=ultLoad.getValue();
        DICE_ANIM_TIME=diceAnimTime.getValue();
        DYNAMIC_BACKGROUND=dynamicBackgroundSwitch.getValue();
        FPS_COUNTER=fpsCounterSwitch.getValue();
        SOUND=soundSwitch.getValue();
        //FPS_COUNTER
    }

    public void resetAll(){
        sliderFps.reset();
        ultLoad.reset();
        diceAnimTime.reset();
        dynamicBackgroundSwitch.reset();
        fpsCounterSwitch.reset();
        soundSwitch.reset();

        setSettings();
    }
}
