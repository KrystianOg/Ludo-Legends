package states;
import Entities.Board;
import Entities.Counters.Counter;
import Entities.Counters.Funi;
import Entities.Counters.Intan;
import Entities.Counters.Saph;
import Entities.HUD.Dice;
import Entities.Players.Player;
import Entities.Players.Person;
import Entities.ui.Tile;
import Entities.HUD.Timer;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;

public class GameState extends State{

    private final Player[] player;
    private Board board;
    private Dice dice;
    private Timer timer;

    public static final Color[] color=new Color[4];


    private static final int W1=92,H1=91;
    private static final int W2=166,H2=166;

    private int turnof;

    private int roll;

    public GameState(Handler handler){
        super(handler);

        handler.setGameState(this);
        this.player=new Player[4];
        setColors();
    }

    public void init(){

        board=new Board(handler,0,0,750,790);   //z�e liczby-zmienic
        dice=new Dice(handler,765,300);
        timer=new Timer(handler,765,300);

        turnof=(int)(Math.random()*4);
        System.out.println("MOVING: "+turnof);
        this.setRoll(6);
    }

    public void setPlayer(Player player){
        for(int i=0;i<4;i++){
            if(this.player[i]==null){
                this.player[i]=player;
                break;
            }
        }
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
    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.BLACK);
        timer.render(g);
        dice.render(g);

        board.render(g);
//
        player[0].render(g);
        player[1].render(g);
        player[2].render(g);
        player[3].render(g);
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

    public void setRolled(){
        dice.setRolled(true);
        this.dice.setTickcount();
    }
}