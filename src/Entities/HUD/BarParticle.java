package Entities.HUD;

import Entities.Entity;
import GFX.Assets;
import ludogame.Handler;
import states.SettingState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BarParticle extends Entity {

    private static  final int WIDTH=16,HEIGHT=16;
    private static final int MAX_ANGLE=360;

    private final BufferedImage img= Assets.bar_loaded[(int)(Math.random()*2.5+0.5)];

    private double directionX,directionY;
    private int angle;

    private int ticks=0;
    private int nextChangeOn=(int)(SettingState.FPS*0.7);
    private final Rectangle rectangle;

    private double speed;

    BarParticle (Handler handler,float barX,float barY){
        super(handler,barX,barY,WIDTH,HEIGHT);

        rectangle=new Rectangle((int)barX,(int)barY,200,40);

        x=(int)(Math.random()*rectangle.width-10)+x;
        y=(int)(Math.random()*rectangle.height-10)+y;

        angle=(int)(Math.random()*MAX_ANGLE);
        directionX=Math.sin(angle)*speed;
        directionY=Math.cos(angle)*speed;
        speed=Math.random()*0.6+0.4;
    }

    @Override
    public void tick() {
        ticks++;
        if(ticks==nextChangeOn){
            angle=(int)(Math.random()*10)+angle-5;
            nextChangeOn=(int)((Math.random()*1.4+0.6)*SettingState.FPS);

            directionX=Math.sin(angle)*speed;
            directionY=Math.cos(angle)*speed;
            ticks=0;

        }

        x+=directionX;
        y+=directionY;

        if(x<=rectangle.x-5||x>=rectangle.x+rectangle.width)
            directionX=-directionX;
        if(y<=rectangle.y-5||y>=rectangle.y+rectangle.height)
            directionY=-directionY;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img,(int)x,(int)y,null);
    }
}
