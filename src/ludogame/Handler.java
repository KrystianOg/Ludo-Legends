package ludogame;

import Entities.Counters.Counter;
import Entities.HUD.Dice;
import Entities.HUD.Timer;
import Players.Player;
import Players.PositionOnMap;
import Entities.ui.Tile;
import display.Display;
import input.KeyboardManager;
import states.*;

public class Handler {

    private  Game game;

    private GameState gameState;

    private LoadingScreen loadingScreen;
    
    private Display display;

    public Handler(){

    }

    public void setGame(Game game){
        this.game=game;
    }
    
    public void setLoadingScreen(LoadingScreen loadingScreen){
        this.loadingScreen=loadingScreen;
    }

    public Game getGame() {
        return game;
    }

    public GameState getGameState() {
        return (GameState)game.gameState;
    }

    public PrepState getPrepState(){
        return (PrepState)game.prepState;
    }
    
    public MenuState getMenuState(){
        return (MenuState)game.menuState;
    }

    public SettingState getSettingState() {
        return (SettingState)game.settingState;
    }

    public HighScoresState getHighScoresState(){
        return  (HighScoresState)game.highScoresState;
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

    public Timer getTimer(){
        return gameState.getTimer();
    }

    public Tile getTile(PositionOnMap pos){
        return gameState.getBoard().getTile(pos);
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getTurnOf(){
        return gameState.getTurnOf();
    }

    public void setTurnof(){
        gameState.setTurnof();
    }

    public int getRoll(){
        return gameState.getDice().getRoll();
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

    public int getMouseWheel() {
    	return game.getMousemanager().getWheel();
    }
    
    public void setMouseWheel(int wheel) {
		game.getMousemanager().setWheel(wheel);
	}
    
    public void resetMousePOS(){
        game.getMousemanager().reset();
    }

    public void setCounterOnTile(PositionOnMap pos, Counter counter){
        gameState.getBoard().setCounterOnTile(pos,counter);
    }

    public void removeCounterFromTile(PositionOnMap pos,Counter counter){
        gameState.getBoard().removeCounterFromTile(pos,counter);
    }

    public KeyboardManager getKeyboardManager(){
        return this.game.getKeyboardManager();
    }

    public void setDisplay(Display display){
        this.display=display;
    }

    public Display getDisplay(){
        return this.display;
    }
    
    public LoadingScreen getLoadingScreen(){
        return this.loadingScreen;
    }

}
