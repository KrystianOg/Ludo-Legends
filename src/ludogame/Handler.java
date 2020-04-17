package ludogame;

import Entities.HUD.Dice;
import Entities.HUD.Timer;
import Entities.Players.Player;
import states.GameState;
import states.PrepState;
import states.SettingState;

public class Handler {

    private Game game;

    private GameState gameState;
    private PrepState prepState;
    private SettingState settingState;

    public GameState getGameState() {
        return gameState;
    }

    public PrepState getPrepState(){
        return prepState;
    }

    public void setPrepState(PrepState prepState){
        this.prepState=prepState;
    }

    public SettingState getSettingState() {
        return settingState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setSettingState(SettingState settingState) {
        this.settingState = settingState;
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

    public Timer getTimer(){
        return gameState.getTimer();
    }

    public int getFrameWidth(){
        return game.getFrameWidth();
    }

    public int getFrameHeight(){
        return game.getFrameHeight();
    }

    public int getHoverX(){
        return game.getMousemanager().getHoverX();
    }

    public int getHoverY(){
        return game.getMousemanager().getHoverY();
    }

    public int getMouseClickX(){
        return game.getMousemanager().getX();
    }

    public int getMouseClickY(){
        return game.getMousemanager().getY();
    }

    public void resetMousePOS(){
        game.getMousemanager().reset();
    }
}
