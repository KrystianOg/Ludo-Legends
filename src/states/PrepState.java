package states;

import Entities.Counters.*;
import Entities.Players.Blank;
import Entities.Players.Bot;
import Entities.Players.Person;
import Entities.ui.Button;


import Entities.ui.PlayerPick;
import Entities.Players.Player;
import GFX.Assets;
import ludogame.Game;
import ludogame.Handler;

import java.awt.*;

public class PrepState extends State {

    private static final int PLAYER_POSY=150;

    Counter[] counter;
    Button apply;
    Player[] player;

    PlayerPick[] playerPick;

    private int picking=0;
    private boolean typePick;

    public PrepState(Handler handler) {
        super(handler);

        counter=new Counter[8];
        player=new Player[4];
        this.typePick=true;

        playerPick=new PlayerPick[4];

        playerPick[0]=new PlayerPick(handler,handler.getFrameWidth()/2-195,PLAYER_POSY,Assets.tile[0],Assets.arrow[0]);
        playerPick[1]=new PlayerPick(handler,handler.getFrameWidth()/2-95,PLAYER_POSY,Assets.tile[1],Assets.arrow[1]);
        playerPick[2]=new PlayerPick(handler,handler.getFrameWidth()/2+5,PLAYER_POSY,Assets.tile[2],Assets.arrow[2]);
        playerPick[3]=new PlayerPick(handler,handler.getFrameWidth()/2+105,PLAYER_POSY,Assets.tile[3],Assets.arrow[3]);

        apply=new Button(handler,(float)((handler.getFrameWidth()-350)/2),500,350,90, Assets.apply_button);
    }

    @Override
    public void tick() {

        if(typePick){
            playerPick[0].tick();
            playerPick[1].tick();
            playerPick[2].tick();
            playerPick[3].tick();

            if(apply.getHitbox().contains(handler.getMouseClickX(),handler.getMouseClickY())){
                handler.resetMousePOS();
                typePick=false;

                for(int i=0;i<4;i++){
                    switch(playerPick[i].getCurrentPick()){
                        case 0:
                            //handler.getGameState().setPlayers(new Bot());
                            break;
                        case 1:
                            //player[i]=new Person();
                            break;
                        case 2:
                            //player[i]=new Blank();
                            break;
                    }
                }
                //handler.getGameState().setPlayers(player[]);
            }

        }

        else if(!typePick) {

            if (apply.getHitbox().contains(handler.getMouseClickX(), handler.getMouseClickY())) {
                //System.out.println("Pick: " + picking);
            }


        }

        apply.tick();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Game.MENU_GRAY);
        g.fillRect(0,0,handler.getFrameWidth(),handler.getFrameHeight());
        apply.render(g);

        if(typePick){
            playerPick[0].render(g);
            playerPick[1].render(g);
            playerPick[2].render(g);
            playerPick[3].render(g);
        }
        else{

        }

    }

    private void setPlayer(Player player) {
        for(int i=0;i<4;i++){
            if(this.player[i]==null)
                this.player[i]=player;

        }
    }

    public Player[] getPlayers(){
        return this.player;
    }

}