package ludogame;

import GFX.Assets;
import GFX.SoundEffect;
import display.Display;
import states.SettingState;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class LoadingScreen implements Runnable{
        //info
    private final int width;
    private final int height;
    private final Handler handler;
        //
    private boolean running=false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;
    private final Display display;
    private final boolean initConnection;

    private DBConnect connect;
    //sounds
    private boolean play=false;
    private String path;


    //grafiki
    private int value=0;
    private int tick=0;

    private boolean doRender;

    public LoadingScreen(Handler handler,String title,int width,int height){
        this.handler =handler;
        this.width=width;
        this.height=height;

        handler.setLoadingScreen(this);
        doRender=true;
        display=new Display(title,width,height);
        handler.setDisplay(display);
        initConnection=false;
    }

    private void init(){
            Assets.initLoadingScreen();
    }

    private void tick(){
        tick++;
        if(tick%10==0) {
            value++;
            if(value==4)
                value=0;
        }

        if(play&&SettingState.SOUND){
            SoundEffect.play(path);
            play=false;
        }

        connectionSupporter();

        if(initConnection)
            if(connect==null||!connect.isConnected())
                connect=new DBConnect(handler);
    }

    private void render(){
        bs=display.getCanvas().getBufferStrategy();
        if(bs==null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g=bs.getDrawGraphics();

        g.clearRect(0,0,width,height);
        //
            renderLoadingScreen(g);

        //
        bs.show();
        g.dispose();
    }

    private void renderLoadingScreen(Graphics g){

        g.setColor(Color.white);
        g.fillRect(0,0,width,height);

        double scale=0.25;

        g.drawImage(Assets.logo,(width-(int)(Assets.logo.getWidth()*scale))/2,200,(int)(Assets.logo.getWidth()*scale),(int)(Assets.logo.getHeight()*scale),null);
        g.drawImage(Assets.loadingDot[value],(width-Assets.loadingDot[value].getWidth())/2-30+value*20,400,null);

    }

    @Override
    public void run() {
        init();

        double timePerTick= 1000000000/60;   //in nanoseconds
        double delta=0;
        long now;
        long lastTime=System.nanoTime();



        while(running){
            now=System.nanoTime();
            delta+=(now-lastTime)/timePerTick;

            lastTime=now;

            if(delta>=1) {
                tick();
                if(doRender)
                    render();
                delta--;
            }
        }
        stop();
    }

    public synchronized  void start(){
        if(running)
            return;

        running=true;
        thread=new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running)
            return;

        running =false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setRender(boolean render){
        this.doRender=render;
    }

    public boolean getRender(){
        return this.doRender;
    }

    public DBConnect getConnection(){
        return this.connect;
    }

    public void setConnection(DBConnect connect){
        this.connect=connect;
    }

    private void connectionSupporter(){
        if(tick==120*SettingState.FPS){
            tick=0;
            connect.getUser("");
        }
    }

    public void setPlay(String path){
            this.path = path;
            this.play = true;

    }
}
