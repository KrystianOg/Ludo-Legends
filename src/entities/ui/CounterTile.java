package entities.ui;
import entities.Entity;
import GFXandEffects.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CounterTile extends Entity {

    public static final int COUNTER_TILE_WIDTH=90,
                            COUNTER_TILE_HEIGHT=90;

    private static final int COUNTER_WIDTH=32,
                             COUNTER_HEIGHT=60;

    private final Rectangle hitbox;
    private boolean hover;
    private boolean clicked;

    private final int imgPosX;
    private final int imgPosY;

    private final BufferedImage[] img;

    CounterTile(Handler handler, float x, float y,int imgPosX,int imgPosY,BufferedImage color, BufferedImage img_f){
        super(handler,x,y,COUNTER_TILE_WIDTH,COUNTER_TILE_HEIGHT);

        this.hitbox=new Rectangle((int)x,(int)y,COUNTER_TILE_WIDTH,COUNTER_TILE_HEIGHT);
        this.hover=false;

        this.imgPosX=(int)(imgPosX*LegendPick.SCALING);
        this.imgPosY=(int)(imgPosY*LegendPick.SCALING);

        img=new BufferedImage[3];
        this.clicked=false;

        this.img[0]=color;
        this.img[1]=img_f;
        this.img[2]=null;
    }


    CounterTile(Handler handler, float x, float y,int imgPosX,int imgPosY,BufferedImage color, BufferedImage img_f,BufferedImage img_b){
        super(handler,x,y,COUNTER_TILE_WIDTH,COUNTER_TILE_HEIGHT);

        this.hitbox=new Rectangle((int)x,(int)y,COUNTER_TILE_WIDTH,COUNTER_TILE_HEIGHT);
        this.hover=false;

        this.imgPosX=(int)(imgPosX*LegendPick.SCALING);
        this.imgPosY=(int)(imgPosY*LegendPick.SCALING);

        img=new BufferedImage[3];
        this.clicked=false;

        this.img[0]=color;
        this.img[1]=img_f;
        this.img[2]=img_b;
    }

    @Override
    public void tick() {

        hover= this.hitbox.contains(handler.getHoverX(), handler.getHoverY());

        if(this.hitbox.contains(handler.getMouseClickX(),handler.getMouseClickY())){
            handler.resetMousePOS();
            changeClicked();
        }
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Assets.tile[5],(int)x,(int)y,null);

        if(img[2]!=null)
            g.drawImage(this.img[2],(int)x+29+imgPosX,(int)y+15+imgPosY,(int)(img[2].getWidth()*0.75),(int)(img[2].getHeight()*0.75),null);

        g.drawImage(this.img[0],(int)x+29,(int)y+15,COUNTER_WIDTH,COUNTER_HEIGHT,null);
        if(img[1]!=null)
        g.drawImage(this.img[1],(int)x+29+imgPosX,(int)y+15+imgPosY,(int)(img[1].getWidth()*0.75),(int)(img[1].getHeight()*0.75),null);

        if(hover)
            g.drawImage(Assets.tile[4],(int)x,(int)y,null);

        else if(clicked) {
            g.drawImage(Assets.tile[4], (int) x, (int) y, null);
            g.drawImage(Assets.tile[4], (int) x, (int) y, null);
        }
    }

    private void changeClicked(){
        clicked = !clicked;
    }

    public boolean isChoosen(){
        return this.clicked;
    }

    public void setChoosen(){
        this.clicked=false;
    }
}
