package states;
import Entities.Board;
import Entities.Counters.Counter;
import Entities.HUD.Dice;
import Players.PositionOnMap;
import Entities.ui.Pause;
import GFX.Assets;
import Players.Bot;
import Players.Player;
import Entities.HUD.Timer;
import Players.PlayerData;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class GameState extends State{

    private final Player[] player;
    private final List<Counter> resetingCounter=new LinkedList<>();
    private final List<PlayerData> winnerTable=new LinkedList<>();
    private final List<Counter> renderOrder=new LinkedList<>();


    public static final Color[] color=new Color[4];
    private GameOverScreen gameOverScreen;
    private Board board;
    private Dice dice;
    private Timer timer;
    private final Pause pause;
    private boolean inGame;
    private boolean endGame;
    private int round=0;
    private int turnOf;

    public GameState(Handler handler){
        super(handler);
        handler.setGameState(this);
        inGame=false;
        endGame=false;
        this.player=new Player[4];
        Bot.setBotNicknames();
        pause=new Pause(handler,handler.getFrameWidth()-100,30, Assets.pause_button);
    }

    public void init(){

        inGame=true;
        board=new Board(handler,0,0,750,790);   //zle liczby-zmienic
        dice=new Dice(handler,790, 300,6,0);
        timer=new Timer(handler,790,300);
        turnOf=(int)(Math.random()*4);
        gameOverScreen = new GameOverScreen(handler);
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
            round++;
        }

        dice.setRolled(false);
        timer.resetTimer();
        player[turnOf].resetRolls();
    }

    @Override
    public void tick() {

        if(!pause.getClicked()) {
            if(!endGame) {
                if (!player[turnOf].getWon())
                    player[turnOf].tick();
                else
                    setTurnof();

                resetCounters();

                if(player[0].getWon()&&player[1].getWon()&&player[2].getWon()&&player[3].getWon()) {
                    endGame=true;
                    gameOverScreen.init(winnerTable);
                }

            }
            else{
                gameOverScreen.tick();

            }
        }
        if(!endGame) {
            pause.tick();
            handler.resetMousePOS();
        }

    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.BLACK);
        timer.render(g);
        dice.render(g);


        board.render(g);

        for (Player value : player) {
            value.renderFire(g);
        }

        renderPlayers(g);

        if(endGame)
            gameOverScreen.render(g);
        else
            pause.render(g);

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

    public int getRound(){
        return this.round;
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

    private void renderPlayers(Graphics g){

        for (Counter counter : renderOrder) {
            counter.render(g);
        }

        for (Player value : player) {
            value.renderInBaseCounters(g);
        }

        player[turnOf].renderUltBar(g);
    }

    public void setRenderOrder(){

        renderOrder.clear();
        if(handler.getTile(new PositionOnMap(51)).getCounterListLength()>0){
            for(int j=0;j<handler.getTile(new PositionOnMap(51)).getCounterListLength();j++){
                renderOrder.add( handler.getTile(new PositionOnMap(51)).getCounter(j));
            }
        }
        for(int i=0;i<13;i++){
            if(handler.getTile(new PositionOnMap(i)).getCounterListLength()>0){
                for(int j=0;j<handler.getTile(new PositionOnMap(i)).getCounterListLength();j++){
                    renderOrder.add(handler.getTile(new PositionOnMap(i)).getCounter(j));
                }
            }
            if(handler.getTile(new PositionOnMap(50-i)).getCounterListLength()>0){
                for(int j=0;j<handler.getTile(new PositionOnMap(50-i)).getCounterListLength();j++){
                    renderOrder.add(handler.getTile(new PositionOnMap(50-i)).getCounter(j));
                }
            }
        }

        for(int i=0;i<6;i++){
            if(handler.getTile(new PositionOnMap(1,i)).getCounterListLength()>0){
                for(int j=0;j<handler.getTile(new PositionOnMap(1,i)).getCounterListLength();j++){
                    renderOrder.add(handler.getTile(new PositionOnMap(1,i)).getCounter(j));
                }
            }
            if(handler.getTile(new PositionOnMap(2,i)).getCounterListLength()>0){
                for(int j=0;j<handler.getTile(new PositionOnMap(2,i)).getCounterListLength();j++){
                    renderOrder.add(handler.getTile(new PositionOnMap(2,i)).getCounter(j));
                }
            }
            if(handler.getTile(new PositionOnMap(4,i)).getCounterListLength()>0){
                for(int j=0;j<handler.getTile(new PositionOnMap(4,i)).getCounterListLength();j++){
                    renderOrder.add(handler.getTile(new PositionOnMap(4,i)).getCounter(j));
                }
            }
            if(handler.getTile(new PositionOnMap(3,5-i)).getCounterListLength()>0){
                for(int j=0;j<handler.getTile(new PositionOnMap(3,5-i)).getCounterListLength();j++){
                    renderOrder.add(handler.getTile(new PositionOnMap(3,5-i)).getCounter(j));
                }
            }

        }
        for(int i=13;i<25;i++){
            if(handler.getTile(new PositionOnMap(i)).getCounterListLength()>0){
                for(int j=0;j<handler.getTile(new PositionOnMap(i)).getCounterListLength();j++){
                    renderOrder.add(handler.getTile(new PositionOnMap(i)).getCounter(j));
                }
            }
            if(handler.getTile(new PositionOnMap(50-i)).getCounterListLength()>0){
                for(int j=0;j<handler.getTile(new PositionOnMap(50-i)).getCounterListLength();j++){
                    renderOrder.add(handler.getTile(new PositionOnMap(50-i)).getCounter(j));
                }
            }
        }
        if(handler.getTile(new PositionOnMap(25)).getCounterListLength()>0){
            for(int j=0;j<handler.getTile(new PositionOnMap(25)).getCounterListLength();j++){
                renderOrder.add( handler.getTile(new PositionOnMap(25)).getCounter(j));
            }
        }
    }

    public Player getPlayerByColor(BufferedImage counterColor){
        for(int i=0;i<4;i++){
            if(player[i].getCounter(0)!=null) {
                if (player[i].getCounter(0).getCounterColor() == counterColor)
                    return player[i];
            }
        }

        return null;
    }

    public boolean isInGame(){
        return this.inGame;
    }

    public void setPlayerData(PlayerData playerData){
        this.winnerTable.add(playerData);
    }

    public void clear(){
        endGame=false;
        inGame=false;
        resetingCounter.clear();
        winnerTable.clear();
        renderOrder.clear();

        for(int i=0;i<player.length;i++)
            player[i]=null;
        board=null;
        timer=null;
        dice=null;
        round=0;
    }


}