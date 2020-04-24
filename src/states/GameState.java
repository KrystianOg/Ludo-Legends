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

    private final Player[] player;          //vector
    private Board board;
    private Dice dice;
    private Timer timer;

    public static final Color[] color=new Color[4];

    private int turnof;

    private int roll;

    public GameState(Handler handler){
        super(handler);

        handler.setGameState(this);
        this.player=new Player[4];
    }

    public void init(){

        board=new Board(handler,0,0,750,790);   //z�e liczby-zmienic
        dice=new Dice(handler,765,300);
        timer=new Timer(handler,765,300);

        turnof=(int)(Math.random()*4);
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
        player[turnof].tick();
    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.BLACK);
        timer.render(g);
        dice.render(g);

        board.render(g);
        renderPlayers(g);

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

    private void renderPlayers(Graphics g){
        for(int i=0;i<4;i++){
            player[i].render(g);
        }
    }

    public Board getBoard(){
        return this.board;
    }
}