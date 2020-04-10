package ludogame;

import java.awt.*;
import java.awt.image.BufferStrategy;

import GFX.*;
import display.*;
import input.MouseManager;
import states.*;

public class Game implements Runnable {

    private Display display;
    public int width,height;
    public String title;
    private boolean running=false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //States
    private State gamestate;
    private State menustate;
    private State settingstate;
    private State prepstate;

    //Input
    private MouseManager mousemanager;


    public Game(String title,int width,int height){

        this.width=width;
        this.height=height;
        this.title=title;
        mousemanager = new MouseManager();

    }

    private void init() {
        display =new Display(title,width,height);
        display.getFrame().addMouseListener(mousemanager);
        display.getCanvas().addMouseListener(mousemanager);
        Assets.init();

        prepstate=new PrepState(this);
        gamestate=new GameState(this,prepstate);
        menustate=new MenuState(this);
        settingstate=new SettingState(this);
        State.setState(gamestate);

    }



    private void tick(){
        mousemanager.tick();

        if(State.getState()!=null)
            State.getState().tick();


    }

    private void render(){
        bs=display.getCanvas().getBufferStrategy();
        if(bs==null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g=bs.getDrawGraphics();
        //cls
        g.clearRect(0,0,width,height);
        //

        if(State.getState()!=null)
            State.getState().render(g);


        //
        bs.show();
        g.dispose();

    }

    @Override
    public void run() {
        init();

        int fps=60;
        double timePerTick= 1000000000/fps;   //in nanoseconds
        double delta=0;
        long now;
        long lastTime=System.nanoTime();

        while(running){
            now=System.nanoTime();
            delta+=(now-lastTime)/timePerTick;
            lastTime=now;


            if(delta>=1) {
                tick();
                render();
                delta--;
            }


        }

        stop();

    }

    public MouseManager getMousemanager(){
        return mousemanager;
    }

    public synchronized void start(){

        if(running)
            return;

        running=true;
        thread=new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running)
            return;

        running=false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}