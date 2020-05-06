package ludogame;

import GFX.Assets;
import display.Display;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

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

    //grafiki
    private int value=0;
    private int tick=0;


    public LoadingScreen(Handler handler,String title,int width,int height){
        this.handler =handler;
        this.width=width;
        this.height=height;

        display=new Display(title,width,height);
        handler.setDisplay(display);


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

        if(handler.getGame()!=null)
            running=false;
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

        long timer=0;
        int ticks=0;

        while(running){
            now=System.nanoTime();
            delta+=(now-lastTime)/timePerTick;
            timer+=now-lastTime;
            lastTime=now;

            if(delta>=1) {
                tick();
                render();
                ticks++;
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

}