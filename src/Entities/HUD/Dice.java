package Entities.HUD;

import Entities.Entity;
import GFX.Assets;
import GFX.Text;
import ludogame.Handler;
import states.SettingState;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Dice extends Entity {

    public static final int DICE_WIDTH=75,
                            DICE_HEIGHT=75;

    private final Rectangle hitbox;

    private boolean rolled;
    private boolean clicked;
    private int roll,rollImg;
    private final int minus;

    private final List<Integer> type=new LinkedList<>();
    private final int dimensions;

    //animation
    private int tickcount;
    private static final int DICE_ANIM_TICKS= 31* SettingState.FPS/60;

    public Dice(Handler handler, int x, int y,int dimensions,int minus) {
        super(handler,x, y, DICE_WIDTH, DICE_HEIGHT);
        hitbox=new Rectangle(x,y,DICE_WIDTH,DICE_HEIGHT);
        rolled=false;
        clicked=false;
        tickcount=-1;
        this.minus=minus;
        this.dimensions=dimensions;
        roll=dimensions;
        rollImg=dimensions;

        for(int i=1;i<=dimensions;i++)
            type.add(i);
    }

    @Override
    public void tick() {

        if(this.hitbox.contains(handler.getMouseClickX(),handler.getMouseClickY())&&!rolled&&!clicked){
            handler.resetMousePOS();
            clicked=true;
            tickcount=0;
            handler.getLoadingScreen().setPlay("dice");
            setChanceRoll();
            handler.getPlayer().rollsMinusOne();
        }
        else if(tickcount>=0&&tickcount<DICE_ANIM_TICKS){
            tickcount++;
            if(tickcount%(4*SettingState.FPS/60)==0)
                rollImg= (int) (Math.random() * dimensions + 1)+minus;
        }
        else if(tickcount==DICE_ANIM_TICKS){
            rolled=true;
            clicked=false;
            handler.getTimer().resetTimer();
            tickcount=-1;

            rollImg=roll;

            if(roll==6)
                handler.getPlayer().rollsPlusOne();

            if(handler.getPlayer().isThird()&&roll==6){
                handler.getPlayer().setRollsLeft(1);
                handler.setTurnof();
            }
        }

    }

    @Override
    public void render(Graphics g) {

        if(rollImg>6)
            rollImg=6;

        if(rollImg>0)
            g.drawImage(Assets.rollimg[rollImg - 1], (int) x, (int) y, null);
        else
            g.drawImage(Assets.minusrollimg[-rollImg], (int) x, (int) y, null);

        if(!rolled)
            Text.drawString(g,"Roll",(int)x+37,(int)y+100,true,Color.black,Assets.Ubuntu34);
        else
            Text.drawString(g,"Pick",(int)x+37,(int)y+100,true,Color.BLACK,Assets.Ubuntu34);
    }

    public void addSpecial(int i){
       this.roll+=i;
    }

    public boolean isClicked(){
        return this.clicked;
    }

    public boolean isRolled() {
        return rolled;
    }

    public int getRoll(){
        return roll;
    }

    public void setRoll(int roll){
        this.roll=roll;
    }

    public int getTickCount(){
        return this.tickcount;
    }

    public void setRolled(boolean rolled){
        this.rolled=rolled;
    }

    public boolean getClicked(){
        return this.clicked;
    }

    public void botRoll(){
        clicked=true;
        tickcount=0;
        handler.getLoadingScreen().setPlay("dice");
        setChanceRoll();
        handler.getPlayer().rollsMinusOne();
    }

    private void setChanceRoll(){
       type.addAll(handler.getPlayer().getChance());
       int rand =(int)(Math.random()*type.size());

       roll=type.get(rand)+minus;
       if(type.size()>6)
       type.subList(6,type.size()).clear();
    }


}
