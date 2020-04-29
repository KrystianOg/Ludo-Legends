package Entities.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import Entities.Entity;
import GFX.Assets;
import GFX.Text;

import ludogame.Handler;

public class Slider extends Entity {

	Button reset;
	
	private final Rectangle hitbox;
    private boolean pressed;
    private float positionX;
	private final float frameWidth;
    private final float minValue;
	private final float maxValue;
	private final float defaultValue;
	private final float minMax;
    private float value;
    private int valueInt;
    private String valueString;
	private final String title;
	
	ArrayList<Integer> szczeble = new ArrayList<Integer>();
    
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

		reset=new Button(handler, (int)x*2+ frameWidth, y, (float)0.3, Assets.medium_button_template,Assets.reset_button);
	}
	public Slider(Handler handler, float x, float y, int width, int height, int frameWidth, float minValue, float maxValue, float defaultValue, String title, ArrayList<Integer> szczeble) {
		super(handler, x, y, width, height);
		
		this.szczeble=szczeble;
		this.hitbox=new Rectangle((int)x,(int)y, frameWidth, height);
		this.pressed=false;
		this.frameWidth=frameWidth;
		this.minValue=minValue;
		this.maxValue=maxValue;
		this.defaultValue=defaultValue;
		this.title=title;
		minMax=maxValue-minValue;
		for(int i=0;i<szczeble.size();i++)
			if (szczeble.get(i)==defaultValue)
				positionX=x+i*200/(szczeble.size()-1);
	    value=szczeble.get(((int)positionX-(int)x)*(szczeble.size()-1)/200);
	    valueInt=Math.round(value);
	    valueString = String.valueOf(valueInt);

		reset=new Button(handler, (int)x*2+ frameWidth, y, (float)0.3, Assets.medium_button_template,Assets.reset_button);
	}

	@Override
	public void tick() {
		
		if(this.hitbox.contains(handler.getHoverX(),handler.getHoverY()) && handler.getGame().getMousemanager().isLeftPressed()) {
            this.pressed=true;
            if (szczeble.isEmpty()){
            	positionX=handler.getHoverX();
            	value=(positionX-x)*minMax/200 + minValue;
            	}
            else {
            	if (handler.getHoverX()>=x && handler.getHoverX()<x+(200/(szczeble.size()-1))/2) {
            		positionX=x;
            		value=szczeble.get(0);
            	}
            	else
            		for(int i=1;i<szczeble.size()-1;i++) 
            			if(handler.getHoverX()>=x+(i-0.5)*200/(szczeble.size()-1) && handler.getHoverX()<x+(i+0.5)*200/(szczeble.size()-1)) {
            				positionX=x+i*200/(szczeble.size()-1);
            				value=szczeble.get(((int)positionX-(int)x)*szczeble.size()/200);
            			}	
            	if (handler.getHoverX()>=x+(szczeble.size()-1.5)*200/(szczeble.size()-1) && handler.getHoverX()<x+(szczeble.size()-1)*200/(szczeble.size()-1)) {
    				positionX=x+200;
    				value=szczeble.get(szczeble.size()-1);
            	}
            }
            valueInt=Math.round(value);
    	    valueString = String.valueOf(valueInt);
		}
        else
            this.pressed=false;
		if(this.reset.getHitbox().contains(handler.getMouseClickX(),handler.getMouseClickY())) {
			positionX=x+((defaultValue-minValue)/minMax *200);
			if (szczeble.isEmpty()) {
				value=(positionX-x)*minMax/200 + minValue;
			}
            else {
            	for(int i=0;i<szczeble.size();i++)
        			if (szczeble.get(i)==defaultValue) {
        				positionX=x+i*200/(szczeble.size()-1);
        			}
            	value=szczeble.get(((int)positionX-(int)x)*szczeble.size()/200);
            }
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

	public int getValueInt() {
		return valueInt;
	}

	public void reset() {
		if (szczeble.isEmpty()) {
		positionX=x+((defaultValue-minValue)/minMax *200);
	    value=(positionX-x)*minMax/200 + minValue;
		}
		else {
			for(int i=0;i<szczeble.size();i++)
    			if (szczeble.get(i)==defaultValue) {
    				positionX=x+i*200/(szczeble.size()-1);
    			}
        	value=szczeble.get(((int)positionX-(int)x)*szczeble.size()/200);
		}
	    valueInt=Math.round(value);
	    valueString = String.valueOf(valueInt);

	}
}
