package states;
import Entities.Board;
import Entities.Counters.Funi;
import Entities.Counters.Saph;
import Entities.Dice;
import Entities.Players.Player;
import Entities.Players.Person;
import GFX.Assets;
import ludogame.Game;
import ludogame.Handler;

import java.awt.*;

public class GameState extends State{

    //Counter[][] counter;
    Player[] player=new Player[4];
    Board board;
    Dice dice;

    private static final int W1=92,H1=91;
    private static final int W2=166,H2=166;

    private int turnof;
    private int roll;


    State prepState;

    public GameState(Handler handler, State prepstate){
        super(handler);


        prepState=prepstate;


        //counter=new Counter[4][4];
        board=new Board(handler,0,0,750,750);   //złe liczby-zmienic
        dice=new Dice(handler,770,50,this);
        this.setRoll(6);
        //wybieranie graczy

        setPlayers();
        setCounters();
    }

    private void setPlayers() {

        player[0] = new Person(handler, 1, Assets.counter[0]);
        player[1] = new Person(handler, 14,Assets.counter[1]);
        player[2] = new Person(handler, 27,Assets.counter[2]);
        player[3] = new Person(handler, 40,Assets.counter[3]);


    }
    private void setCounters(){
        //player 0
            Funi a1, a4;
            Saph a2, a3;

            a1=new Funi(handler,W1,H1,0);
            a2=new Saph(handler,W2,H1,0);
            a3=new Saph(handler,W1,H2,0);
            a4=new Funi(handler,W2,H2,0);

            player[0].setCounters(a1,a2,a3,a4);
        //player1
        Funi b1, b3;
        Saph b2, b4;

        b1=new Funi(handler,450+W1,H1,1);
        b2=new Saph(handler,450+W2,H1,1);
        b3=new Funi(handler,450+W1,H2,1);
        b4=new Saph(handler,450+W2,H2,1);

        player[1].setCounters(b1,b2,b3,b4);
        //player2
        Funi c1, c2,c3;
        Saph c4;

        c1= new Funi(handler,W1,450+H1,2);
        c2= new Funi(handler,W2,450+H1,2);
        c3= new Funi(handler,W1,450+H2,2);
        c4= new Saph(handler,W2,450+H2,2);

        player[2].setCounters(c1,c2,c3,c4);
       //player3
        Funi d1, d2;
        Saph d3, d4;

        d1= new Funi(handler,450+W1,450+H1,3);
        d2= new Funi(handler,450+W2,450+H1,3);
        d3= new Saph(handler,450+W1,450+H2,3);
        d4= new Saph(handler,450+W2,450+H2,3);

        player[3].setCounters(d1,d2,d3,d4);

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

    public void setTurnof(int turnof) {
        this.turnof = turnof;
    }

    @Override
    public void tick() {

            dice.tick();
            player[0].tick();
            player[1].tick();
            player[2].tick();
            player[3].tick();

    }

    @Override
    public void render(Graphics g) {

        dice.render(g);
        board.render(g);
        player[0].render(g);
        player[1].render(g);
        player[2].render(g);
        player[3].render(g);

    }

}
