package Entities.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Entities.Entity;
import GFX.Assets;
import GFX.Text;

import ludogame.Handler;

public class Slider extends Entity {

	Button reset;

	public static final int SLIDER_WIDTH=700;

	private static final int WIDTH=17;
	private static final int HEIGHT=29;

	private final Rectangle hitbox;
    private boolean pressed;
    private float positionX;
	private final float frameWidth;
    private final float minValue;
	private final float maxValue;
	private final float defaultValue;
	private final float minMax;
    private float value;
	private final String title;
	private int[] szczeble;

    
	public Slider(Handler handler, float x, float y, int frameWidth, float minValue, float maxValue, float defaultValue, String title) {
		super(handler, x, y, WIDTH, HEIGHT);
		
		this.hitbox=new Rectangle((int)x+300,(int)y, frameWidth, height);
		this.pressed=false;
		this.frameWidth=frameWidth;
		this.minValue=minValue;
		this.maxValue=maxValue;
		this.defaultValue=defaultValue;
		this.title=title;
		minMax=maxValue-minValue;

	    positionX=x+300+((defaultValue-minValue)/minMax *200);
	    value=defaultValue;

		reset=new Button(handler, (int)x*2+ frameWidth+170, y, (float)0.3, Assets.medium_button_template,"RESET",18);
	}
	public Slider(Handler handler, float x, float y, int frameWidth, float minValue, float maxValue, float defaultValue, String title, int[] szczeble) {
		super(handler, x, y, WIDTH, HEIGHT);
		
		this.szczeble=szczeble;
		this.hitbox=new Rectangle((int)x+300,(int)y, frameWidth, height);
		this.pressed=false;
		this.frameWidth=frameWidth;
		this.minValue=minValue;
		this.maxValue=maxValue+1;
		this.defaultValue=defaultValue;
		this.title=title;
		minMax=maxValue-minValue;
		for(int i=0;i<szczeble.length;i++)
			if (szczeble[i]==defaultValue)
				positionX=x+i*200/(szczeble[i]-1);
	    value=defaultValue;

		reset=new Button(handler, (int)x*2+ frameWidth+170, y, (float)0.3, Assets.medium_button_template,"RESET",18);

	}

	@Override
	public void tick() {
		
		if(this.hitbox.contains(handler.getHoverX(),handler.getHoverY()) && handler.getGame().getMousemanager().isLeftPressed()) {
            this.pressed=true;

            if (szczeble==null){
            	positionX=handler.getHoverX();
            	value=(positionX-x-300)*minMax/200 + minValue;
            	}
            else {
            	if (handler.getHoverX()>=x && handler.getHoverX()<x+(200/(szczeble.length-1))/2) {
            		positionX=x;
            		value=szczeble[0];
            	}
            	else
            		for(int i=1;i<szczeble.length-1;i++)
            			if(handler.getHoverX()>=x+(i-0.5)*200/(szczeble.length-1) && handler.getHoverX()<x+(i+0.5)*200/(szczeble.length-1)) {
            				positionX=x+i*200/(szczeble.length-1);
            				value=szczeble[((int)positionX-(int)x)*szczeble.length/200];
            			}	
            	if (handler.getHoverX()>=x+(szczeble.length-1.5)*200/(szczeble.length-1) && handler.getHoverX()<x+(szczeble.length-1)*200/(szczeble.length-1)) {
    				positionX=x+200;
    				value=szczeble[szczeble.length-1];
            	}
            }
		}
        else
            this.pressed=false;
		if(this.reset.contains(handler.getMouseClickX(),handler.getMouseClickY())) {
			reset();

		}
        reset.tick();
		
	}

	@Override
	public void render(Graphics g) {

		g.drawImage(Assets.slider_back, (int)x+300, (int)y+10, null);
		if(pressed)
			g.drawImage(Assets.slider_front,handler.getHoverX()-8,(int)y, width, height, null);
		else g.drawImage(Assets.slider_front,(int)positionX-8,(int)y, width, height, null);

		Text.drawString(g, Integer.toString(Math.round(value)) , (int)x+340+(int)frameWidth, (int)y+height/2, true, Color.BLACK, Assets.Ubuntu34);
		Text.drawString(g, title, (int)x+120, (int)y+10, true, Color.BLACK, Assets.Ubuntu28);
		reset.render(g);

	}

	public int getValue() {
		return (int)value;
	}

	public void reset() {

		if (szczeble==null) {
		positionX=x+300+((defaultValue-minValue)/minMax *200);
	    value=defaultValue;
		}
		else {
			for(int i=0;i<szczeble.length;i++)
    			if (szczeble[i]==defaultValue) {
    				positionX=x+300+i*200/(szczeble.length-1);
    			}
        	value=szczeble[((int)positionX-(int)x)*szczeble.length/200];
		}

	}
}
