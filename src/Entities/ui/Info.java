package Entities.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import Entities.Entity;
import Entities.Counters.Albali;
import Entities.Counters.Funi;
import Entities.Counters.Intan;
import Entities.Counters.Mira;
import Entities.Counters.Polaris;
import Entities.Counters.Samaya;
import Entities.Counters.Saph;
import Entities.Counters.Venator;
import GFX.Assets;
import GFX.Description;
import GFX.Text;
import ludogame.Handler;
import states.State;

public class Info extends Entity {
    
	private final InfoTile[] counterTile = new InfoTile[8];
    private final BufferedImage[] button;
    private final Rectangle hitbox;
    private final Handler handler;

    private final Color blackOp=new Color(0,0,0,190);
    
    private final int counterTileWidth = 485;
    private final int counterTileHeight=175;
    
    private boolean hoover;
    private boolean clicked;

    public Info(Handler handler, float x, float y, BufferedImage[] button) {
        super(handler,x, y, 70, 70);
        this.handler=handler;
        this.button=button;
        hitbox=new Rectangle((int)x,(int)y,width,height);
        clicked=false;
        
        for(int i=0;i<4;i++)
        	counterTile[i]=new InfoTile(handler, 30, 30+i*(10+counterTileHeight), counterTileWidth, counterTileHeight, i);
        for(int i=4;i<8;i++)
        	counterTile[i]=new InfoTile(handler, 30+counterTileWidth, 30+(i-4)*(10+counterTileHeight), counterTileWidth, counterTileHeight, i);
        
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

}

