package Entities.ui;

import Entities.Entity;
import GFX.Assets;
import ludogame.Handler;
import states.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Pause extends Entity {

    private final BufferedImage[] button;
    private final Rectangle hitbox;
    private final Handler handler;
    private final Button menu;

    private final Color blackOp=new Color(0,0,0,190);

    private boolean hoover;
    private boolean clicked,menuClicked;

    public Pause(Handler handler, float x, float y, BufferedImage[] button) {
        super(handler,x, y, 70, 70);
        this.handler=handler;
        this.button=button;
        hitbox=new Rectangle((int)x,(int)y,width,height);
        menu=new Button(handler,(handler.getFrameWidth()-225)/2,400,1, Assets.medium_button_template,"MENU",58);
        clicked=false;
        menuClicked=false;
    }

    @Override
    public void tick() {

        //na ta chwile X wychodzi z programu

        hoover= this.hitbox.contains(handler.getHoverX(), handler.getHoverY());

        if(this.hitbox.contains(handler.getMouseClickX(),handler.getMouseClickY())) {
            handler.resetMousePOS();
            changeClicked();
        }

        if(clicked){
            menu.tick();
            if(menu.contains(handler.getMouseClickX(),handler.getMouseClickY())){
                handler.resetMousePOS();
                menuClicked=true;
                State.setState(handler.getGame().menuState);
                clicked=false;
                //handler.getGame().prepState.re

            }
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
            menu.render(g);
        }

        if(hoover)
            g.drawImage(button[2],(int)x,(int)y,null);
    }

    private void changeClicked(){
        this.clicked=!clicked;
    }

    public boolean getMenuClicked(){
        return this.menuClicked;
    }
    public void setMenuClicked(boolean clicked){
        this.menuClicked=clicked;
    }


    public boolean getClicked(){
        return this.clicked;
    }

}
