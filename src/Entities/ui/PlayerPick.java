package Entities.ui;

import Entities.Entity;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerPick extends Entity {

    private static final int PLAYER_PICK_WIDTH=90,
                             PLAYER_PICK_HEIGHT=270,
                             ARROW_WIDTH=50,ARROW_HEIGHT=50;

    private final BufferedImage bColor;
    private final BufferedImage arrowUp;
    private final BufferedImage arrowDown;

    Rectangle arrowUpHitbox;
    Rectangle arrowDownHitbox;

    private boolean hooverUp,hooverDown;

    private int currentPick;


    public PlayerPick(Handler handler, float x, float y, BufferedImage bColor,BufferedImage arrow) {
        super(handler, x, y, PLAYER_PICK_WIDTH,PLAYER_PICK_HEIGHT);

        this.arrowDownHitbox=new Rectangle((int)x+20,(int)y+190,ARROW_WIDTH,ARROW_HEIGHT);
        this.arrowUpHitbox=new Rectangle((int)x+20,(int)y+30,ARROW_WIDTH,ARROW_HEIGHT);
        this.bColor=bColor;
        this.arrowDown=arrow;

        this.currentPick=1;
        this.hooverDown=false;
        this.hooverUp=false;

        this.arrowUp=Assets.rotate(arrow,180);         //obrót obrazka
    }

    @Override
    public void tick() {
        if(this.arrowDownHitbox.contains(handler.getHoverX(),handler.getHoverY())) {
            hooverDown = true;
            hooverUp=false;
        }
        else if(this.arrowUpHitbox.contains(handler.getHoverX(),handler.getHoverY())) {
            hooverUp = true;
            hooverDown=false;
        }
        else{
            hooverDown=false;
            hooverUp=false;
        }

        if(this.arrowUpHitbox.contains(handler.getMouseClickX(),handler.getMouseClickY())){
            handler.resetMousePOS();
            this.currentPick++;
            if(currentPick==3)
                currentPick=0;
        }
        else if(this.arrowDownHitbox.contains(handler.getMouseClickX(),handler.getMouseClickY())){
            handler.resetMousePOS();
            this.currentPick--;
            if(currentPick==-1)
                currentPick=2;
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(bColor,(int)x,(int)y+90,null);
        g.drawImage(Assets.player[currentPick],(int)x,(int)y+90,null);

        g.drawImage(arrowDown,arrowDownHitbox.x,arrowDownHitbox.y,null);
        g.drawImage(arrowUp,arrowUpHitbox.x,arrowUpHitbox.y,null);



        if(!hooverUp)
            g.drawImage(Assets.arrow[4],arrowUpHitbox.x,arrowUpHitbox.y,null);
        if(!hooverDown)
            g.drawImage(Assets.arrow[4],arrowDownHitbox.x,arrowDownHitbox.y,null);

    }


    public int getCurrentPick(){
        return this.currentPick;
    }
}
