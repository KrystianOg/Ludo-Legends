package states;

import Entities.Counters.*;
import Entities.Button;


import GFX.Assets;
import ludogame.Handler;

import java.awt.*;

public class PrepState extends State {

    Counter[] counter;

    Entities.Button apply;

    public PrepState(Handler handler) {
        super(handler);

        counter=new Counter[8];
/*
        counter[0]=new Albali(Handler handler,(float));
        counter[1]=new Funi();
        counter[2]=new Intan();
        counter[3]=new Lich();
        counter[4]=new Polaris();
        counter[5]=new Samaya();
        counter[6]=new Saph();
        counter[7]=new Venator();
*/
        counter[0]=new Funi(handler,200,200);

        apply=new Button(handler,(float)((handler.getFrameWidth()-350)/2),300,350,90, Assets.apply_button);
    }

    @Override
    public void tick() {

        if(apply.getHitbox().contains(handler.getMouseClickX(),handler.getMouseClickY())){
            setState(handler.getGame().gamestate);
        }


        counter[0].tick();
        apply.tick();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(153,153,153));
        g.fillRect(0,0,handler.getFrameWidth(),handler.getFrameHeight());
        counter[0].renderPick(g);
        apply.render(g);
        //renderPick();
    }
}