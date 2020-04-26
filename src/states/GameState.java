package states;
import Entities.Board;
import Entities.Counters.Counter;
import Entities.HUD.Dice;
import Players.Player;
import Entities.ui.Tile;
import Entities.HUD.Timer;
import ludogame.Handler;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GameState extends State{

    private final Player[] player;
    private final List<Counter> resetingCounter=new LinkedList<>();
    private final List<Player> winnerTable=new LinkedList<>();

    private Board board;
    private Dice dice;
    private Timer timer;

    private int timesRolled=0;

    private final List<String> botNickname=new LinkedList<>();

    public static final Color[] color=new Color[4];

    private int turnOf;

    public GameState(Handler handler){
        super(handler);
        handler.setGameState(this);
        this.player=new Player[4];
        setBotNicknames();
    }

    public void init(){

        board=new Board(handler,0,0,750,790);   //z�e liczby-zmienic
        dice=new Dice(handler,765,300);
        timer=new Timer(handler,765,300);

        turnOf=(int)(Math.random()*4);
    }

    public void setPlayer(Player player){
        for(int i=0;i<4;i++){
            if(this.player[i]==null){
                this.player[i]=player;
                break;
            }
        }
    }
    
    public void setTurnof() {

        player[turnOf].resetList();

        turnOf++;
        if(turnOf==4){
            this.turnOf=0;
        }

        timesRolled++;

        System.out.println("BREAK "+timesRolled);

        dice.setRolled(false);
        timer.resetTimer();
        player[turnOf].resetRolls();
    }

    @Override
    public void tick() {
        player[turnOf].tick();

        resetCounters();
    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.BLACK);
        timer.render(g);
        dice.render(g);

        board.render(g);
        renderPlayers(g);

    }

    private void renderPlayers(Graphics g){
        for(int i=0;i<4;i++){
            player[i].render(g);

            if(i==turnOf)
                player[i].renderUltBar(g);
        }
    }

    private void setBotNicknames(){

        botNickname.add("Bot James");
        botNickname.add("Bot John");
        botNickname.add("Bot William");
        botNickname.add("Bot Timothy");
        botNickname.add("Bot Nicholas");
        botNickname.add("Bot Stephen");
        botNickname.add("Bot Nathan");

        botNickname.add("Bot Sarah");
        botNickname.add("Bot Nancy");
        botNickname.add("Bot Lisa");
        botNickname.add("Bot Sandra");
        botNickname.add("Bot Laura");
        botNickname.add("Bot Nicole");
        botNickname.add("Bot Lauren");

    }

    public String getBotNickname(){

        int i=(int)(Math.random()*botNickname.size());
        String nick=botNickname.get(i);
        Collections.swap(botNickname,i,botNickname.size()-1);
        botNickname.remove(botNickname.size()-1);

        return nick;
    }

    public Player getPlayer(int i){
        return player[i];
    }

    public int getTurnOf() {
        return turnOf;
    }

    public Dice getDice(){
        return this.dice;
    }

    public Timer getTimer(){
        return this.timer;
    }

    public Board getBoard(){
        return this.board;
    }

    private void resetCounters(){
        for(int i=0;i<resetingCounter.size();i++){
            if(resetingCounter.get(i).getReseting())
                resetingCounter.get(i).tick();
            else{
                resetingCounter.remove(i);
            }
        }

    }

    public void addToReset(Counter counter){
        resetingCounter.add(counter);
    }
}