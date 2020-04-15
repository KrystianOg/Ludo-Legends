package Entities.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Entities.Entity;
import Entities.ui.Button;
import GFX.Assets;
import GFX.Text;

import ludogame.Handler;

public class Slider extends Entity {

	Button reset;
	
	private Rectangle hitbox;
    private boolean pressed;
    private float positionX, frameWidth;
    private float minValue, maxValue, defaultValue, minMax;
    private float value;
    private int valueInt;
    private String valueString, title;
    
	public Slider(Handler handler, float x, float y, int width, int height, int frameWidth, float minValue, float maxValue, float defaultValue, String title) {
		super(handler, x, y, width, height);
		
		this.hitbox=new Rectangle((int)x,(int)y, frameWidth, height);
		this.pressed=false;
		this.frameWidth=frameWidth;
		this.minValue=minValue;
		this.maxValue=maxValue;
		this.defaultValue=defaultValue;
		this.title=title;
		minMax=maxValue-minValue;
	    positionX=x+((defaultValue-minValue)/minMax *200);
	    value=(positionX-x)*minMax/200 + minValue;
	    valueInt=Math.round(value);
	    valueString = String.valueOf(valueInt);
	    
	    reset=new Button(handler, (int)x*2+(int)frameWidth, y, 100, 40, Assets.reset_button);
	}

	@Override
	public void tick() {
		
		if(this.hitbox.contains(handler.getHoverX(),handler.getHoverY()) && handler.getGame().getMousemanager().isLeftPressed()) {
            this.pressed=true;
            positionX=handler.getHoverX();
            value=(positionX-x)*minMax/200 + minValue;

            valueInt=Math.round(value);
    	    valueString = String.valueOf(valueInt);
            

		}
        else
            this.pressed=false;
		if(this.reset.getHitbox().contains(handler.getMouseClickX(),handler.getMouseClickY())) {
			positionX=x+((defaultValue-minValue)/minMax *200);
		    value=(positionX-x)*minMax/200 + minValue;
		    valueInt=Math.round(value);
		    valueString = String.valueOf(valueInt);
		}
        reset.tick();
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.slider_back, (int)x, (int)y+10, null);
		if(pressed)
			g.drawImage(Assets.slider_front,handler.getHoverX()-8,(int)y, width, height, null);
		else g.drawImage(Assets.slider_front,(int)positionX-8,(int)y, width, height, null);

		Text.drawString(g, valueString , (int)x+(int)frameWidth, (int)y+height/2, false, Color.BLACK, Assets.Ubuntu28);
		Text.drawString(g, title, (int)x-50, (int)y, false, Color.BLACK, Assets.Ubuntu28);
		reset.render(g);

	}

	public int getValueInt(){
		return valueInt;
	}
}
