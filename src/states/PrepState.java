package states;

import Entities.Counters.*;
import Entities.Players.Blank;
import Entities.Players.Bot;
import Entities.Players.Person;
import Entities.ui.Button;
import Entities.ui.LegendPick;
import Entities.ui.PlayerPick;
import GFX.Assets;
import GFX.DynamicBackground;
import ludogame.Handler;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PrepState extends State {

    private static final int PLAYER_POSY=230,PLAYER_SHIFT=450;

    private static final int[]  PLAYER_STARTING_POS={1,14,27,40},
                                PLAYER_ENDING_POS={51,12,25,38};

    private static final int[]  COUNTER_POS_X={166,166,92,92},
                                COUNTER_POS_Y={88,162,162,88},
                                BASE_POS_X={450,450,0,0},
                                BASE_POS_Y={0,450,450,0};


    //zoptymalizować
    Button apply;

    PlayerPick[] playerPick;

    private int picking;
    private boolean typePick;
    private LegendPick[] legendPick;

    DynamicBackground dynamicBackground;

    public PrepState(Handler handler) {
        super(handler);

        handler.setPrepState(this);

        apply=new Button(handler,(float)((handler.getFrameWidth()-350)/2),500,350,90, Assets.big_button_template,Assets.apply_button);
    }

    public void init(DynamicBackground dynamicBackground){

        this.dynamicBackground=dynamicBackground;

        typePick=true;
        picking =0;

        legendPick=new LegendPick[4];

        legendPick[0]=new LegendPick(handler,Assets.counter[0]);
        legendPick[1]=new LegendPick(handler,Assets.counter[1]);
        legendPick[2]=new LegendPick(handler,Assets.counter[2]);
        legendPick[3]=new LegendPick(handler,Assets.counter[3]);

        playerPick=new PlayerPick[4];

        playerPick[0]=new PlayerPick(handler,handler.getFrameWidth()/2-195,PLAYER_POSY,Assets.tile[0],Assets.arrow[0]);
        playerPick[1]=new PlayerPick(handler,handler.getFrameWidth()/2-95,PLAYER_POSY,Assets.tile[1],Assets.arrow[1]);
        playerPick[2]=new PlayerPick(handler,handler.getFrameWidth()/2+5,PLAYER_POSY,Assets.tile[2],Assets.arrow[2]);
        playerPick[3]=new PlayerPick(handler,handler.getFrameWidth()/2+105,PLAYER_POSY,Assets.tile[3],Assets.arrow[3]);

    }

    @Override
    public void tick() {

        dynamicBackground.tick();

        if(typePick){
            playerPick[0].tick();
            playerPick[1].tick();
            playerPick[2].tick();
            playerPick[3].tick();

            if(apply.getHitbox().contains(handler.getMouseClickX(),handler.getMouseClickY())){
                handler.resetMousePOS();
                typePick=false;

                for(int i=0;i<4;i++){
                    switch (playerPick[i].getCurrentPick()) {
                        case 0 -> handler.getGameState().setPlayer(new Bot(handler, PLAYER_STARTING_POS[i], PLAYER_ENDING_POS[i], Assets.counter[i]));
                        case 1 -> handler.getGameState().setPlayer(new Person(handler, PLAYER_STARTING_POS[i], PLAYER_ENDING_POS[i], Assets.counter[i]));
                        case 2 -> handler.getGameState().setPlayer(new Blank(handler, PLAYER_STARTING_POS[i], PLAYER_ENDING_POS[i], Assets.counter[i]));
                    }
                }
            }

        }

        else {

            legendPick[picking].tick();

            if (playerPick[picking].getCurrentPick()==1&&legendPick[picking].getchoosen()==4&&apply.getHitbox().contains(handler.getMouseClickX(), handler.getMouseClickY())) {
                handler.resetMousePOS();
                savePersonCounters();
                picking++;
            }
            else if(playerPick[picking].getCurrentPick()==0){
                //rng wybierania
                setBotCounters();
                picking++;
            }
            else if(playerPick[picking].getCurrentPick()==2) {
                handler.getPlayer(picking).setCounters(null);
                picking++;
            }

            if(picking==4){
                handler.getGameState().init();
                resetVariables();
                setState(handler.getGame().gamestate);
            }
        }

        apply.tick();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0,0,handler.getFrameWidth(),handler.getFrameHeight());
        if(SettingState.DYNAMIC_BACKGROUND)
        dynamicBackground.render(g);

        apply.render(g);

        if(typePick){
            for(int i=0;i<4;i++)
                playerPick[i].render(g);
        }
        else{
            legendPick[picking].render(g);
        }

    }


    //nie wiem czy to najlepsze rozwiązanie
    //ale przynajmniej nie wymaga wywoływania jonstruktorów wszystkich klas
    private void savePersonCounters(){

        //zobaczyc czy da sie zrobic jak w setstate

        int i=0;

            for(int j=0;j<8;j++) {
                if (legendPick[picking].getCounterTile(j).isChoosen()){
                    switch (j) {
                        case 0 -> handler.getGameState().getPlayer(picking).setCounter(new Albali(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking]));
                        case 1 -> handler.getGameState().getPlayer(picking).setCounter(new Funi(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking]));
                        case 2 -> handler.getGameState().getPlayer(picking).setCounter(new Intan(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking]));
                        case 3 -> handler.getGameState().getPlayer(picking).setCounter(new Mira(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking]));
                        case 4 -> handler.getGameState().getPlayer(picking).setCounter(new Polaris(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking]));
                        case 5 -> handler.getGameState().getPlayer(picking).setCounter(new Samaya(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking]));
                        case 6 -> handler.getGameState().getPlayer(picking).setCounter(new Saph(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking]));
                        case 7 -> handler.getGameState().getPlayer(picking).setCounter(new Venator(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking]));
                    }
                    legendPick[picking].getCounterTile(j).setChoosen();
                    i++;
                }
            }
        }

    private void setBotCounters(){
        //RNG
        Integer[] random={0,1,2,3,4,5,6,7};
        List<Integer> randomList= Arrays.asList(random);
        Collections.shuffle(randomList);
        randomList.toArray(random);

        //

        for(int i=0;i<4;i++){
            switch (random[i]) {
                case 0 -> handler.getPlayer(picking).setCounter(new Albali(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking]));
                case 1 -> handler.getPlayer(picking).setCounter(new Funi(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking]));
                case 2 -> handler.getPlayer(picking).setCounter(new Intan(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking]));
                case 3 -> handler.getPlayer(picking).setCounter(new Mira(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking]));
                case 4 -> handler.getPlayer(picking).setCounter(new Polaris(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking]));
                case 5 -> handler.getPlayer(picking).setCounter(new Samaya(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking]));
                case 6 -> handler.getPlayer(picking).setCounter(new Saph(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking]));
                case 7 -> handler.getPlayer(picking).setCounter(new Venator(handler,BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking]));
            }
        }
    }

    private void resetVariables(){
        this.legendPick=null;
        this.playerPick=null;
        typePick=true;
        picking=0;
    }

}