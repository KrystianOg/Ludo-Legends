package states;

import Entities.ui.Button;
import Entities.ui.Slider;
import Entities.ui.Switch;
import GFX.Assets;
import GFX.DynamicBackground;
import ludogame.Handler;

import java.awt.*;

public class SettingState extends State{

    //SETTINGS
    public static int FPS,BUTTON_SIZE,ULT_LOAD,DICE_ANIM_TIME;
    public static boolean FPS_COUNTER,DYNAMIC_BACKGROUND;
<<<<<<< Updated upstream

	private final Slider slider_fps;
    private final Slider button_size;
    private final Slider ultload;
	private final Button back;
    private final Button reset_all;
    private final Slider dice_anim_time;

    DynamicBackground dynamicBackground;//time in seconds
=======

	private final Slider sliderFps;
    private final Slider buttonSize;
    private final Slider ultLoad;
	private final Button back;
    private final Button defaults;
    private final Button apply;
    private final Switch dynamicBackgroundSwitch;
    private final Switch fpsCounterSwitch;
    private final Slider diceAnimTime;

    private DynamicBackground dynamicBackground;//time in seconds
>>>>>>> Stashed changes

    public SettingState(Handler handler){
        super(handler);

        //default values
        FPS=60;                //ilość FPS
        BUTTON_SIZE=100;       //wartość procentowa
        ULT_LOAD=100;           //wartość procentowa
        FPS_COUNTER=false;
        DICE_ANIM_TIME=10;
        DYNAMIC_BACKGROUND=true;
<<<<<<< Updated upstream

        slider_fps=new Slider(handler, 100, 100, 17, 29, 200, 25, 144, 60, "FPS");
        button_size=new Slider(handler, 100, 200, 17, 29, 200, 50, 150, 100, "HUD size");
        ultload=new Slider(handler, 100, 300, 17, 29, 200, 0, 400, 100, "Ultimate ability load speed");
        dice_anim_time=new Slider(handler, 100, 400, 17, 29, 200, 3, 30, 10, "Dice animation time");
        back=new Button(handler, handler.getFrameWidth() -100, handler.getFrameHeight() -100,(float)0.5,  Assets.big_button_template,Assets.defaults_button);
        reset_all=new Button(handler, 100, 700, (float)0.5, Assets.big_button_template,Assets.defaults_button);
=======
        
        //int[] szczebleFPS = {30,50,60,100,120,144};
        sliderFps=new Slider(handler, (handler.getFrameWidth()-Slider.SLIDER_WIDTH) /2, 100, 200, 30, 144, 60, "FPS");
        buttonSize=new Slider(handler, (handler.getFrameWidth()-Slider.SLIDER_WIDTH) /2, 160,  200, 50, 150, 100, "HUD size");
        ultLoad=new Slider(handler, (handler.getFrameWidth()-Slider.SLIDER_WIDTH) /2, 220, 200, 0, 400, 100, "Ultimate ability load speed");
        diceAnimTime=new Slider(handler, (handler.getFrameWidth()-Slider.SLIDER_WIDTH) /2, 280, 200, 3, 30, 10, "Dice animation time");

        dynamicBackgroundSwitch=new Switch(handler,(int)(handler.getFrameWidth()-Switch.SWITCH_WIDTH)/2,340,0.7,SettingState.DYNAMIC_BACKGROUND,"Dynamic background");
        fpsCounterSwitch=new Switch(handler,(int)(handler.getFrameWidth()-Switch.SWITCH_WIDTH)/2,400,0.7,SettingState.FPS_COUNTER,"FPS Counter");

        defaults=new Button(handler, 140, handler.getFrameHeight()-130, 0.6, Assets.big_button_template,"DEFAULTS",34);
        back=new Button(handler, handler.getFrameWidth() -290, handler.getFrameHeight() -130,0.6,  Assets.medium_button_template,"BACK",34);
        apply=new Button(handler,handler.getFrameWidth()/2-30,handler.getFrameHeight()-130,0.6,Assets.medium_button_template,"APPLY",34);
>>>>>>> Stashed changes
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
    	buttonSize.tick();
    	ultLoad.tick();
    	back.tick();
<<<<<<< Updated upstream
    	dice_anim_time.tick();
    	if(this.reset_all.contains(handler.getMouseClickX(),handler.getMouseClickY())) {
            slider_fps.reset();
            button_size.reset();
            ultload.reset();
            dice_anim_time.reset();
    	}

    	reset_all.tick();
=======
    	diceAnimTime.tick();
    	dynamicBackgroundSwitch.tick();
    	fpsCounterSwitch.tick();
        apply.tick();

    	if(this.defaults.contains(handler.getMouseClickX(),handler.getMouseClickY())){
    	    handler.resetMousePOS();
    	    resetAll();}

    	else if(this.apply.contains(handler.getMouseClickX(),handler.getMouseClickY())){
    	    handler.resetMousePOS();
    	    setSettings();
        }

    	defaults.tick();
>>>>>>> Stashed changes
    }

    @Override
    public void render(Graphics g) {

    	g.setColor(Color.white);
        g.fillRect(0,0,handler.getFrameWidth(),handler.getFrameHeight());
        dynamicBackground.render(g);

        sliderFps.render(g);
        buttonSize.render(g);
        ultLoad.render(g);
        back.render(g);
<<<<<<< Updated upstream
        reset_all.render(g);
        dice_anim_time.render(g);
=======
        defaults.render(g);
        diceAnimTime.render(g);
        dynamicBackgroundSwitch.render(g);
        fpsCounterSwitch.render(g);
        apply.render(g);

>>>>>>> Stashed changes
    }

    public void setDynamicBackground(DynamicBackground dynamicBackground){
        this.dynamicBackground=dynamicBackground;
    }

    public void setSettings(){
        FPS=sliderFps.getValue();
        BUTTON_SIZE=buttonSize.getValue();
        ULT_LOAD=ultLoad.getValue();
        DICE_ANIM_TIME=diceAnimTime.getValue();
        DYNAMIC_BACKGROUND=dynamicBackgroundSwitch.getValue();
        FPS_COUNTER=fpsCounterSwitch.getValue();
        //FPS_COUNTER
    }

    public void resetAll(){
        sliderFps.reset();
        buttonSize.reset();
        ultLoad.reset();
        diceAnimTime.reset();
        dynamicBackgroundSwitch.reset();
        fpsCounterSwitch.reset();

        setSettings();
    }
}
