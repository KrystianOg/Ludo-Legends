package ludogame;

import java.awt.*;
import java.awt.image.BufferStrategy;

import GFX.*;
import display.*;
import input.MouseManager;
import states.*;

public class Game implements Runnable {

    private Display display;        //klasa wyswietlajaca obraz (+canvas)
    public int width,height;        //szer/wys okna
    public String title;            //nazwa "gry"
    private boolean running=false;  //odpowiada za wyjscie z gry
    private Thread thread;          //

    //FPS
    public static int FPS=60;


    private BufferStrategy bs;      //-info
    private Graphics g;             //grafika

    private Handler handler;

    //States                        // odpowiada za dzialanie roznych

    public State gamestate;        // funkcji gry (np. gra,menu,ustawienia)
    public State menustate;
    public State settingstate;
    public State prepstate;        //- wybor postaci przed gr�


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
        display.getFrame().addMouseMotionListener(mousemanager);
        display.getCanvas().addMouseMotionListener(mousemanager);
        Assets.init();

        handler=new Handler(this);


        gamestate=new GameState(handler);
        settingstate=new SettingState(handler);
        prepstate=new PrepState(handler);
        menustate=new MenuState(handler);
       


        State.setState(menustate); //gamestate change

    }

    private void tick(){

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

        double timePerTick= 1000000000/FPS;   //in nanoseconds
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


    public State getState(){
        return State.getState();
    }

    public int getFrameHeight(){
        return this.height;
    }

    public int getFrameWidth(){
        return this.width;
    }

}