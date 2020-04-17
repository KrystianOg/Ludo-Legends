package Entities.ui;

import Entities.Entity;
import GFX.Assets;
import ludogame.Handler;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Button extends Entity {

    private final BufferedImage[] template;
    private final BufferedImage text;
    private final Rectangle hitbox;
    private final boolean pressed;
    private boolean onhover;

    public Button(Handler handler, float x, float y, int width, int height,BufferedImage[] template,BufferedImage text){
        super(handler,x,y,width,height);

        this.hitbox=new Rectangle((int)x,(int)y,width,height);
        this.template= template;
        this.text=text;
        this.pressed=false;
        this.onhover=false;
    }

    @Override
    public void tick() {
        this.onhover= this.hitbox.contains(handler.getGame().getMousemanager().getHoverX(), handler.getGame().getMousemanager().getHoverY());
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(template[0],(int)x,(int)y,width,height,null);
        g.drawImage(text,(int)x,(int)y,width,height,null);

        if(onhover)
            g.drawImage(template[1],(int)x,(int)y, width, height,null);

    }

    public Rectangle getHitbox(){
        return this.hitbox;
    }
}

