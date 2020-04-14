package states;
import Entities.Board;
import Entities.Counters.Counter;
import Entities.Counters.Funi;
import Entities.Counters.Intan;
import Entities.Counters.Saph;
import Entities.HUD.Dice;
import Entities.Players.Player;
import Entities.Players.Person;
import Entities.Tile;
import Entities.HUD.Timer;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;

public class GameState extends State{

    Player[] player=new Player[4];
    Board board;
    Dice dice;
    Timer timer;

    public static final Color[] color=new Color[4];

    private static final int W1=92,H1=91;
    private static final int W2=166,H2=166;

    private int turnof;

    private int roll;

    public GameState(Handler handler){
        super(handler);

        //counter=new Counter[4][4];
        board=new Board(handler,0,0,750,850);   //złe liczby-zmienic
        dice=new Dice(handler,765,300);
        timer=new Timer(handler,765,300);

        turnof=(int)(Math.random()*4);
        System.out.println("MOVING: "+turnof);
        this.setRoll(6);
        handler.setGameState(this);
        //wybieranie graczy

        setColors();
        setPlayers();
        setCounters();
    }

    private void setPlayers() {
        player[0] = new Person(handler, 1,51, Assets.counter[0]);
        player[1] = new Person(handler, 14,12,Assets.counter[1]);
        player[2] = new Person(handler, 27,25,Assets.counter[2]);
        player[3] = new Person(handler, 40,38,Assets.counter[3]);
    }

    private void setCounters(){
        //player 0
            Funi a1, a4;
            Saph a2, a3;

            a1=new Funi(handler,W1,H1);
            a2=new Saph(handler,W2,H1);
            a3=new Saph(handler,W1,H2);
            a4=new Funi(handler,W2,H2);

            player[3].setCounters(a1,a2,a3,a4);

        //player1
            Funi b1;
            Intan b2,b3;
            Saph b4;

            b1=new Funi(handler,450+W1,H1);
            b2=new Intan(handler,450+W2,H1);
            b3=new Intan(handler,450+W1,H2);
            b4=new Saph(handler,450+W2,H2);

            player[0].setCounters(b1,b2,b3,b4);

        //player2
            Funi c1, c3;
            Intan c2;
            Saph c4;

            c1= new Funi(handler,W1,450+H1);
            c2= new Intan(handler,W2,450+H1);
            c3= new Funi(handler,W1,450+H2);
            c4= new Saph(handler,W2,450+H2);

            player[2].setCounters(c1,c2,c3,c4);

       //player3
            Funi d1, d2;
            Saph  d4;
            Intan d3;

            d1= new Funi(handler,450+W1,450+H1);
            d2= new Funi(handler,450+W2,450+H1);
            d3= new Intan(handler,450+W1,450+H2);
            d4= new Saph(handler,450+W2,450+H2);

            player[1].setCounters(d1,d2,d3,d4);

    }

    private void setColors(){
                color[0]=new Color(255,214,0);
                color[1]=new Color(0,109,200);
                color[2]=new Color(201,0,1);
                color[3]=new Color(0,190,0);
    }

    public Player getPlayer(int i){
        return player[i];
    }


    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public int getTurnof() {
        return turnof;
    }

    public void setTurnof() {
        turnof++;
        if(turnof==4){
            this.turnof=0;
        }
    }

    @Override
    public void tick() {
        if(!dice.isRolled()) {
            dice.tick();
            timer.tick();
        }

        if(dice.isRolled())
        player[turnof].tick();
        player[turnof].counter[0].ultimateBar.tick();
        player[turnof].counter[1].ultimateBar.tick();
        player[turnof].counter[2].ultimateBar.tick();
        player[turnof].counter[3].ultimateBar.tick();


    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.BLACK);
        timer.render(g);
        dice.render(g);

        board.render(g);

        player[0].render(g);
        player[1].render(g);
        player[2].render(g);
        player[3].render(g);

        player[turnof].counter[0].ultimateBar.render(g);
        player[turnof].counter[1].ultimateBar.render(g);
        player[turnof].counter[2].ultimateBar.render(g);
        player[turnof].counter[3].ultimateBar.render(g);
    }

    public Tile getTile(int i){
        return board.getTile(i);
    }

    public void boardLogic(int posonmap){
        board.boardLogic(posonmap);
    }

    public void setCounter(Counter counter,int posonmap){
        board.setCounter(counter,posonmap);
    }

    public Dice getDice(){
        return this.dice;
    }

    public Timer getTimer(){
        return this.timer;
    }

}
