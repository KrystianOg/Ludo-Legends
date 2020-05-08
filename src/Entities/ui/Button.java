package Entities.ui;

import Entities.Entity;
import GFX.Assets;
import GFX.Text;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Button extends Entity {

    private final BufferedImage[] template;
    private final String text;
    private final Rectangle hitbox;
    private boolean onhover;

    private Map<Integer,Font> font=new HashMap<Integer,Font>();

    private int fontSize;

    public Button(Handler handler, float x, float y,double scale,BufferedImage[] template,String text,int fontSize){
        super(handler,x,y,(int)(template[0].getWidth()*scale),(int)(template[0].getHeight()*scale));

        font.put(18,Assets.Ubuntu18);
        font.put(34,Assets.Ubuntu34);
        font.put(40,Assets.Ubuntu40);
        font.put(58,Assets.Ubuntu58);
        font.put(76,Assets.Ubuntu76);

        this.hitbox=new Rectangle((int)x,(int)y,width,height);
        this.template= template;
        this.text=text;
        this.onhover=false;
        this.fontSize=fontSize;
    }

    @Override
    public void tick() {

        this.onhover= this.hitbox.contains(handler.getHoverX(), handler.getHoverY());

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(template[0],(int)x,(int)y,width,height,null);
        Text.drawString(g,text,(int)(x+width/2),(int)(y+height/2),true,Color.white, font.get(fontSize));

        if(onhover)
            g.drawImage(template[1],(int)x,(int)y, width, height,null);


    }

    public boolean contains(int MouseX,int MouseY){
        return this.hitbox.contains(MouseX,MouseY);
    }

}

