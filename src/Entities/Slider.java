package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import GFX.Assets;
import ludogame.Handler;

public class Slider extends Entity{

	private Rectangle hitbox;
	private int frameWidth;
    private boolean pressed;
    private boolean onhover;
    private float positionX;
    
	public Slider(Handler handler, float x, float y, int width, int height, int frameWidth) {
		super(handler, x, y, width, height);
		
		this.hitbox=new Rectangle((int)x,(int)y, frameWidth, height);
		this.pressed=false;
	    this.onhover=false;
	    positionX=x;
	    frameWidth=this.frameWidth;
	}

	@Override
	public void tick() {
		
		if(this.hitbox.contains(handler.getGame().getMousemanager().getHoverX(),handler.getGame().getMousemanager().getHoverY()) && handler.getGame().getMousemanager().isLeftPressed()) {
            this.pressed=true;
            positionX=handler.getGame().getMousemanager().getHoverX();
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
		
	}

}
