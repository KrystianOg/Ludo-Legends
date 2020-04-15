package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import GFX.Assets;
import GFX.Text;

import ludogame.Handler;

public class Slider extends Entity{

	private Rectangle hitbox;
    private boolean pressed;
    private float positionX, frameWidth;


    private float minValue, maxValue, defaultValue, minMax;
    private float value;
    private String valueString;
    
	public Slider(Handler handler, float x, float y, int width, int height, int frameWidth, float minValue, float maxValue, float defaultValue) {
		super(handler, x, y, width, height);
		
		this.hitbox=new Rectangle((int)x,(int)y, frameWidth, height);
		this.pressed=false;
		this.frameWidth=frameWidth;
		this.minValue=minValue;
		this.maxValue=maxValue;
		this.defaultValue=defaultValue;

		minMax=maxValue-minValue;
	    positionX=x+((defaultValue-minValue)/minMax *200);
	    value=(positionX-x)*minMax/200 + minValue;
	    valueString = String.valueOf(value);
	}

	@Override
	public void tick() {
		
		if(this.hitbox.contains(handler.getHoverX(),handler.getHoverY()) && handler.getGame().getMousemanager().isLeftPressed()) {
            this.pressed=true;
            positionX=handler.getHoverX();
            value=(positionX-x)*minMax/200 + minValue;
            valueString = String.valueOf(value);
            
		}
        else
            this.pressed=false;
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.slider_back, (int)x, (int)y+10, null);
		if(pressed)
			g.drawImage(Assets.slider_front,(int)handler.getGame().getMousemanager().getHoverX()-8,(int)y, width, height, null);
		else g.drawImage(Assets.slider_front,(int)positionX-8,(int)y, width, height, null);
			Text.drawString(g, valueString , (int)x+(int)frameWidth, (int)y+height/2, false, Color.WHITE, Assets.font28);
	}
}
