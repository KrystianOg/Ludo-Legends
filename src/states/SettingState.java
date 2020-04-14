package states;

import GFX.Assets;
import ludogame.Game;
import ludogame.Handler;

import java.awt.*;

public class SettingState extends State{


	Entities.Slider slider;
	
    public SettingState(Handler handler){
        super(handler);
        
        slider=new Entities.Slider(handler, 100, 100, 17, 29, 200);

    }


    @Override
    public void tick() {
    	slider.tick();
    }

    @Override
    public void render(Graphics g) {

    	g.setColor(new Color(153,153,153));
        g.fillRect(0,0,handler.getFrameWidth(),handler.getFrameHeight());
        slider.render(g);

    }
}
