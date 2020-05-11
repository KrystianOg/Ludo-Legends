package Entities.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Entities.Entity;
import ludogame.Handler;

public class Info extends Entity {
    
	private final InfoTile[] counterTile = new InfoTile[8];
    private final BufferedImage[] button;
    private final Rectangle hitbox;
    private final Handler handler;

    private final Color blackOp=new Color(0,0,0,190);
    
    private int counterTileWidth = 485, counterTileHeight=175;
    
    private boolean hoover;
    private boolean clicked;

    public Info(Handler handler, float x, float y, BufferedImage[] button) {
        super(handler,x, y, 70, 70);
        this.handler=handler;
        this.button=button;
        hitbox=new Rectangle((int)x,(int)y,width,height);
        clicked=false;
 
    }
    


    @Override
    public void tick() {
        hoover= this.hitbox.contains(handler.getHoverX(), handler.getHoverY());

        if(this.hitbox.contains(handler.getMouseClickX(),handler.getMouseClickY())) {
            handler.resetMousePOS();
            changeClicked();
        }

        if(clicked){

            }
        if(handler.getMouseWheel()>12)
    		handler.setMouseWheel(12);
    	if(handler.getMouseWheel()<-12)
    		handler.setMouseWheel(-12);
        for(int i=0;i<4;i++)
        	counterTile[i]=new InfoTile(handler, 30, 30+i*(10+counterTileHeight)+handler.getMouseWheel()*15, counterTileWidth, counterTileHeight, i);
        for(int i=4;i<8;i++)
        	counterTile[i]=new InfoTile(handler, 60+counterTileWidth, 30+(i-4)*(10+counterTileHeight)+handler.getMouseWheel()*15, counterTileWidth, counterTileHeight, i);
        
        }

    @Override
    public void render(Graphics g) {

        if(!clicked)
            g.drawImage(button[0],(int)x,(int)y,null);
        else {
            g.setColor(blackOp);
            g.fillRect(0,0,handler.getFrameWidth(),handler.getFrameHeight());
            g.drawImage(button[1], (int) x, (int) y, null);
            for(int i=0;i<8;i++)
            	counterTile[i].render(g);

        }

        if(hoover)
            g.drawImage(button[2],(int)x,(int)y,null);
    }
    
    private void changeClicked(){
        this.clicked=!clicked;
    }

    public boolean getClicked(){
        return this.clicked;
    }

    public boolean contains(int MouseX,int MouseY){
        return this.hitbox.contains(MouseX,MouseY);
    }
}

