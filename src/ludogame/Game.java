package ludogame;

import java.awt.*;
import java.awt.image.BufferStrategy;

import GFX.*;
import display.*;
import input.KeyboardManager;
import input.MouseManager;
import states.*;

import static states.GameState.color;

public class Game implements Runnable {

    private Display display;        //klasa wyswietlajaca obraz (+canvas)
    private final int width;
    private final int height;        //szer/wys okna
    private boolean running=false;  //odpowiada za wyjscie z gry
    private Thread thread;
    private DBConnect connect;

    //FPS
    private BufferStrategy bs;      //-info

    private Graphics g;             //grafika

    private final Handler handler;

    //States                        // odpowiada za dzialanie roznych

    public State gameState;        // funkcji gry (np. gra,menu,ustawienia)
    public State menuState;
    public State settingState;
    public State prepState;        //- wybor postaci przed gr�
    public State highScoresState;

    //Input
    private final MouseManager mouseManager;
    private final KeyboardManager keyboardManager;

    public Game(Handler handler,int width,int height){

        this.handler=handler;
        this.width=width;
        this.height=height;
        mouseManager = new MouseManager();
        keyboardManager=new KeyboardManager();
    }

    private void init() {
        display=handler.getDisplay();

        display.getFrame().addMouseListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        display.getFrame().addKeyListener(keyboardManager);

        connect=new DBConnect();

        Assets.init();
        //
        handler.setGame(this);
        //
        setColors();

        gameState=new GameState(handler);
        settingState=new SettingState(handler);
        prepState=new PrepState(handler);
        menuState=new MenuState(handler);
        highScoresState=new HighScoresState(handler);

        State.setState(menuState); //gamestate change

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

        double timePerTick= 1000000000/SettingState.FPS;   //in nanoseconds
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
                timePerTick=1000000000/SettingState.FPS;
            }


            if(SettingState.FPS_COUNTER)        //fps counter
            if(timer>=1000000000){
                System.out.println("FPS: "+ticks);
                ticks=0;
                timer=0;
            }

        }
        stop();
    }

    public MouseManager getMousemanager(){
        return mouseManager;
    }

    public KeyboardManager getKeyboardManager(){
        return keyboardManager;
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

    private void setColors(){
        color[0]=new Color(255,214,0);
        color[1]=new Color(0,109,200);
        color[2]=new Color(201,0,1);
        color[3]=new Color(0,190,0);
    }

    public int getFrameHeight(){
        return this.height;
    }

    public int getFrameWidth(){
        return this.width;
    }

    public DBConnect getDBConnect(){
        return this.connect;
    }

    public boolean isRunning(){
        return this.running;
    }

    public Display getDisplay(){
        return this.display;
    }

}