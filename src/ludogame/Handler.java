package ludogame;

import Entities.HUD.Dice;
import Entities.Players.Player;
import input.MouseManager;

import states.GameState;

public class Handler {      //przekazuje zmienne do klas

    private Game game;

    private GameState gameState=null;
    //inne zmienne


    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Handler(Game game){
        this.game=game;
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Dice getDice(){
        return gameState.getDice();
    }

    //this gracz
    public Player getPlayer(){
        return gameState.getPlayer(getTurnOf());
    }

    //gracz i
    public Player getPlayer(int i){
        return gameState.getPlayer(i);
    }

    public int getTurnOf(){
        return gameState.getTurnof();
    }

    public void setTurnof(){
        gameState.setTurnof();
    }


    public int getRoll(){
        return gameState.getRoll();
    }

    public int getFrameWidth(){
        return this.getGame().getFrameWidth();
    }

    public int getFrameHeight(){
        return this.getGame().getFrameHeight();
    }


    public int getHoverX(){
        return this.getGame().getMousemanager().getHoverX();
    }

    public int getHoverY(){
        return this.getGame().getMousemanager().getHoverY();
    }

    public int getMouseClickX(){
        return this.getGame().getMousemanager().getX();
    }

    public int getMouseClickY(){
        return this.getGame().getMousemanager().getY();
    }

    public void resetMousePOS(){
        getGame().getMousemanager().reset();
    }

}
