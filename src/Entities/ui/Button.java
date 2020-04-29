package Entities.ui;

import Entities.Entity;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button extends Entity {

    private final BufferedImage[] template;
    private final BufferedImage text;
    private final Rectangle hitbox;
    private boolean onhover;
    private int onOff, defaultOnOff;

    public Button(Handler handler, float x, float y,float scale,BufferedImage[] template,BufferedImage text){
        super(handler,x,y,(int)(text.getWidth()*scale),(int)(text.getHeight()*scale));

        this.hitbox=new Rectangle((int)x,(int)y,width,height);
        this.template= template;
        this.text=text;
        this.onhover=false;
    }

    public Button(Handler handler, float x, float y,BufferedImage[] template,BufferedImage text){
        super(handler,x,y,text.getWidth(),text.getHeight());

        this.hitbox=new Rectangle((int)x,(int)y,width,height);
        this.template= template;
        this.text=text;
        this.onhover=false;
    }
    
    public Button(Handler handler, float x, float y,float scale,BufferedImage[] template,BufferedImage text, int onOff){
    	super(handler,x,y,(int)(text.getWidth()*scale),(int)(text.getHeight()*scale));

        this.hitbox=new Rectangle((int)x,(int)y,width,height);
        this.template= template;
        this.text=text;
        this.onOff=onOff;
        this.defaultOnOff=onOff;

    }

    @Override
    public void tick() {
    if (onOff==0)
        this.onhover= this.hitbox.contains(handler.getGame().getMousemanager().getHoverX(), handler.getGame().getMousemanager().getHoverY());
    
    }

    @Override
    public void render(Graphics g) {
    	if(onOff==0) {
        g.drawImage(template[0],(int)x,(int)y,width,height,null);
        g.drawImage(text,(int)x,(int)y,width,height,null);

        if(onhover)
            g.drawImage(template[1],(int)x,(int)y, width, height,null);
    	}
    	else if (onOff==1) {
    		g.drawImage(template[0],(int)x,(int)y,width,height,null);
    		g.drawImage(text,(int)x,(int)y,width,height,null);
    	}
    	else if (onOff==2){
    		g.drawImage(template[1],(int)x,(int)y,width,height,null);
    		g.drawImage(text,(int)x,(int)y,width,height,null);
    	}

    }

    public Rectangle getHitbox(){
        return this.hitbox;
    }

    public boolean contains(int MouseX,int MouseY){
        return this.contains(MouseX,MouseY);
    }

    public void reset() {
    	onOff=defaultOnOff;
    	
    }

	public int getOnOff() {
		return onOff;
	}

	public void setOnOff(int onOff) {
		this.onOff = onOff;
	}
    
}

