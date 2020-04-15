package states;

import GFX.Assets;
import ludogame.Game;
import ludogame.Handler;

import java.awt.*;

public class SettingState extends State{


	Entities.Slider slider_fps, slider_size;
	Entities.Button back, reset_all;
	
    public SettingState(Handler handler){
        super(handler);
        
        slider_fps=new Entities.Slider(handler, 100, 100, 17, 29, 200, 24, 144, 60, "FPS");
        slider_size=new Entities.Slider(handler, 100, 300, 17, 29, 200, 24, 144, 60, "HUD size");
        back=new Entities.Button(handler, handler.getFrameWidth() -100, handler.getFrameHeight() -100, 90, 90, Assets.back_button);
        reset_all=new Entities.Button(handler, 100, 700, 90, 90, Assets.back_button);

    }


    @Override
    public void tick() {
    	if(this.back.getHitbox().contains(handler.getMouseClickX(),handler.getMouseClickY()))
            setState(handler.getGame().menustate);
    	slider_fps.tick();
    	slider_size.tick();
    	back.tick();
    	if(this.reset_all.getHitbox().contains(handler.getMouseClickX(),handler.getMouseClickY())) {
            slider_fps.reset();
            slider_size.reset();
    	}

    	reset_all.tick();
    }

    @Override
    public void render(Graphics g) {

    	g.setColor(new Color(153,153,153));
        g.fillRect(0,0,handler.getFrameWidth(),handler.getFrameHeight());
        slider_fps.render(g);
        slider_size.render(g);
        back.render(g);
        reset_all.render(g);

    }


	public Entities.Slider getSlider() {
		return slider_fps;
	}
    
}
