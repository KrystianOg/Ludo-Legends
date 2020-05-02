package Entities.ui;

import Entities.Entity;
import GFX.Assets;
import GFX.Text;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Switch extends Entity {

    public static final int SWITCH_WIDTH=375;
    private final Rectangle hitbox;
    private boolean onhover;

    private BufferedImage[] template=Assets.onOff_button_template;
    private static BufferedImage[] text=Assets.switchB;
    private String txt;

    private boolean defaultValue;
    private double scale;
    private boolean value;

    public Switch(Handler handler, float x, float y,double scale,boolean defaultValue,String txt){
        super(handler,(int)x,(int)y,text[0].getWidth(),text[0].getHeight());

        this.txt=txt;
        hitbox=new Rectangle((int)x+300,(int)y,(int)(scale*width),(int)(scale*height));
        this.defaultValue=defaultValue;
        this.scale=scale;
        value=defaultValue;
        onhover=false;
    }

    @Override
    public void tick() {
        if(hitbox.contains(handler.getHoverX(),handler.getHoverY()))
            onhover=true;
        else
            onhover=false;

        if(hitbox.contains(handler.getMouseClickX(),handler.getMouseClickY())) {
            handler.resetMousePOS();
            value = !value;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(template[0],(int)x+300,(int)y,(int)(scale*template[0].getWidth()),(int)(scale*template[0].getHeight()),null);
        Text.drawString(g,txt,(int)x,(int)y+(int)(text[0].getHeight()/1.75),false,Color.BLACK,Assets.Ubuntu28);

        if(value==true)
            g.drawImage(text[1],(int)x+300,(int)y,(int)(scale*text[1].getWidth()),(int)(scale*text[1].getHeight()),null);
        else
            g.drawImage(text[0],(int)x+300,(int)y,(int)(scale*text[0].getWidth()),(int)(scale*text[0].getHeight()),null);

        if(onhover)
            g.drawImage(template[1],(int)x+300,(int)y,(int)(scale*template[1].getWidth()),(int)(scale*template[1].getHeight()),null);
    }

    public boolean contains(int MouseX,int MouseY){
        return this.hitbox.contains(MouseX,MouseY);
    }

    public boolean getValue(){
        return this.value;
    }

    public int getWidth(){
        return this.width;
    }

    public void reset(){
        this.value=defaultValue;
    }
}