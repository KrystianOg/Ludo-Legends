package Entities.HUD;

import java.awt.*;
import java.awt.image.BufferedImage;

import Entities.Counters.Counter;
import Entities.Entity;
import GFX.Assets;
import ludogame.Handler;
import states.GameState;
import states.SettingState;

public class UltimateBar extends Entity {

    public static final int ULTIMATEBAR_WIDTH=200, ULTIMATEBAR_HEIGHT=60;
    public static final double SCALE=0.6;

    private boolean loaded;
    private final int ULT_LOAD;

    private float loadPercentage;
    private boolean canBeLoaded;

    //kwadrat z postacią
    private boolean characterHover;

    private final BufferedImage counterColor;
    private BufferedImage[] counterImage;
    private int[] offSetX,offSetY;

    private final BarParticle[]barParticle=new BarParticle[30];
    private final Counter counter;

    private final Rectangle square;

    public UltimateBar(Handler handler, Counter counter, int ULT_LOAD, int barPos) {
        super(handler,(float)765, (float)55+barPos*60, ULTIMATEBAR_WIDTH, ULTIMATEBAR_HEIGHT);

        this.counterColor=counter.getCounterColor();
        this.counter=counter;
        loaded=false;
        canBeLoaded=true;

        if(SettingState.ULT_LOAD==0){
            loaded=true;
            loadPercentage=100;
            this.ULT_LOAD=0;
        }
        else {
            this.ULT_LOAD = ULT_LOAD * 100 / SettingState.ULT_LOAD;
        }
        characterHover=false;

        square=new Rectangle((int)x+Assets.progressbar_f.getWidth()+10+((50-(int)(Assets.counter[0].getWidth()*SCALE))/2),(int)y-5,50,50);

        for(int i=0;i<barParticle.length;i++){
            barParticle[i]=new BarParticle(handler,x,y);
        }
    }

    @Override
    public void tick() {

        characterHover= square.contains(handler.getHoverX(), handler.getHoverY());

        if(square.contains(handler.getMouseClickX(),handler.getMouseClickY())){
            handler.resetMousePOS();

            if(loaded&&!counter.isInbase()&&canBeLoaded) {
                counter.useUltimateAbility();
                handler.getPlayer().substractUltLoad(ULT_LOAD);
            }
        }

        if(handler.getPlayer().getUltLoad()<ULT_LOAD) {
            this.loadPercentage = (handler.getPlayer().getUltLoad() /  (float)ULT_LOAD*100);
        }
        else{
            this.loadPercentage=(float)100;
            this.loaded=true;
        }

        if(loaded)
            loadAnimationTick();
    }

    @Override
    public void render(Graphics g) {
        if(characterHover) {
            if(loaded&&!counter.isInbase()&&!counter.getWon())
                g.setColor(new Color(0, 190, 0, 110));
            else
                g.setColor(new Color(201,0,1,110));

            g.fillRect(square.x + 2, square.y + 2, square.width - 4, square.height - 4);
        }

        g.drawImage(Assets.progressbar_b,(int)x,(int)y,null);
        g.setColor(GameState.color[handler.getTurnOf()]);
        g.fillRect((int)x+7,(int)y+7,(int)(186*loadPercentage/100),26);

        if(loaded)
            loadAnimationRender(g);

        g.drawImage(Assets.progressbar_f,(int)x,(int)y,null);

        g.drawImage(Assets.square,square.x,square.y,null);
        renderSquare(g,square.x+(50-(int)(Assets.counter[0].getWidth()*SCALE))/2,square.y-6);

    }

    private void loadAnimationTick(){
        for(int i=0;i<barParticle.length;i++){
            barParticle[i].tick();
        }
    }

    private void loadAnimationRender(Graphics g){
        for(int i=0;i<barParticle.length;i++){
            barParticle[i].render(g);
        }
    }

    private void renderSquare(Graphics g,int x,int y){

        if(counterImage!=null&&counterImage.length==2)
            g.drawImage(counterImage[1],x+(int)(offSetX[1]*SCALE),y+(int)(offSetY[1]*SCALE),(int)(counterImage[1].getWidth()*SCALE),(int)(counterImage[1].getHeight()*SCALE),null);

        g.drawImage(counterColor,x,y,(int)(counterColor.getWidth()*SCALE),(int)(counterColor.getHeight()*SCALE),null);

        if(counterImage!=null)
            g.drawImage(counterImage[0],x+(int)(offSetX[0]*SCALE),y+(int)(offSetY[0]*SCALE),(int)(counterImage[0].getWidth()*SCALE),(int)(counterImage[0].getHeight()*SCALE),null);
    }

    public void loadCounterImage(BufferedImage imgf,int offSetX,int offSetY){
        counterImage=new BufferedImage[1];
        this.offSetX=new int[1];
        this.offSetY=new int[1];

        counterImage[0]=imgf;
        this.offSetX[0]=offSetX;
        this.offSetY[0]=offSetY;
    }

    public void loadCounterImages(BufferedImage imgf,BufferedImage imgb,int offSetX1,int offSetY1,int offSetX2,int offSetY2){
        counterImage=new BufferedImage[2];
        offSetX=new int[2];
        offSetY=new int[2];

        counterImage[0]=imgf;
        this.offSetX[0]=offSetX1;
        this.offSetY[0]=offSetY1;

        counterImage[1]=imgb;
        this.offSetX[1]=offSetX1;
        this.offSetY[1]=offSetY1;
    }

    public boolean isLoaded(){
        return this.loaded;
    }

    public void setCanBeLoaded(boolean canBeLoaded){
        this.canBeLoaded=canBeLoaded;
    }

    public int getUltLoad(){
        return this.ULT_LOAD;
    }

    public void setUltUsed(){
        loaded=false;
    }
}