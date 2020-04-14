package Entities;

import ludogame.Handler;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Button extends Entity {

    private BufferedImage[] button;
    private Rectangle hitbox;
    private boolean pressed;
    private boolean onhover;

    public Button(Handler handler, float x, float y, int width, int height,BufferedImage[] button){
        super(handler,x,y,width,height);

        this.hitbox=new Rectangle((int)x,(int)y,width,height);
        this.button=new BufferedImage[2];
        this.button=button;
        this.pressed=false;
        this.onhover=false;
        System.out.println("X: "+x+"Y: "+y);
    }

    @Override
    public void tick() {

        if(this.hitbox.contains(handler.getGame().getMousemanager().getHoverX(),handler.getGame().getMousemanager().getHoverY()))
            this.onhover=true;
        else
            this.onhover=false;
    }

    @Override
    public void render(Graphics g) {

        if(onhover)
            g.drawImage(button[1],(int)x,(int)y,null);
        else
            g.drawImage(button[0],(int)x,(int)y,null);
    }

    public Rectangle getHitbox(){
        return this.hitbox;
    }
}

