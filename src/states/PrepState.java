package states;

import Entities.Counters.*;
import Entities.PositionOnMap;
import Entities.ui.Button;
import Entities.ui.TextField;
import Entities.ui.*;
import GFX.Assets;
import GFX.DynamicBackground;
import Players.Blank;
import Players.Bot;
import Players.Person;
import ludogame.Handler;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PrepState extends State {

    private static final int PLAYER_POSY=230;

    private final PositionOnMap[]  PLAYER_STARTING_POS={new PositionOnMap(1),new PositionOnMap(14),new PositionOnMap(27),new PositionOnMap(40)},
                                          PLAYER_ENDING_POS={new PositionOnMap(51),new PositionOnMap(12),new PositionOnMap(25),new PositionOnMap(38)};

    private final int[]  COUNTER_POS_X={166,166,92,92},
                                COUNTER_POS_Y={88,162,162,88},
                                BASE_POS_X={450,450,0,0},
                                BASE_POS_Y={0,450,450,0};

    //zoptymalizować
    private final Button apply;
    private Error nicknameLengthError;
    private Error nullPlayersError;
    //

    private PlayerPick[] playerPick;
    private List<Integer> playerI;
    private final TextField[] textField=new TextField[4];

    private int picking;
    private boolean typePick;
    private LegendPick[] legendPick;

    private final Pause pause;
    private final Info info;

    //error

    DynamicBackground dynamicBackground;

    public PrepState(Handler handler) {
        super(handler);

        apply=new Button(handler,(float)((handler.getFrameWidth()-350)/2),500,1, Assets.big_button_template,"APPLY",76);
        pause=new Pause(handler,handler.getFrameWidth()-100,30,Assets.pause_button);
        info=new Info(handler,handler.getFrameWidth()-100,110,Assets.info_button);
    }

    public void init(DynamicBackground dynamicBackground){

        this.dynamicBackground=dynamicBackground;

        typePick=true;

        picking =0;
        playerI=new LinkedList<>();

        legendPick=new LegendPick[4];
        for(int i=0;i<legendPick.length;i++)
        legendPick[i]=new LegendPick(handler,Assets.counter[i]);

        playerPick=new PlayerPick[4];
        for(int i=0;i<playerPick.length;i++)
        playerPick[i]=new PlayerPick(handler,handler.getFrameWidth()/2-195+i*100,PLAYER_POSY,Assets.tile[i],Assets.arrow[i]);

        for(int i=0;i<textField.length;i++)
            textField[i]=new TextField(handler,780,(handler.getFrameHeight()-60*4)/2+i*60-20,GameState.color[i],Person.defaultNickname[i]);

        nicknameLengthError=new Error(handler.getFrameWidth()/2,130,"Please choose a nickname at least 4 characters long.");
        nullPlayersError=new Error(handler.getFrameWidth()/2,130,"Please choose at least one player other than blank.");
    }

    @Override
    public void tick() {

        dynamicBackground.tick();

        if(!pause.getClicked() && !info.getClicked()) {
            if (typePick) {
                playerPick[0].tick();
                playerPick[1].tick();
                playerPick[2].tick();
                playerPick[3].tick();

                playerI.clear();
                for(int i=0;i<playerPick.length;i++){
                    if(playerPick[i].getCurrentPick()==1)
                        playerI.add(i);
                }
                nickNamePlaceTick();


                if (apply.contains(handler.getMouseClickX(), handler.getMouseClickY())) {
                    handler.resetMousePOS();
                    checkPick();

                }

            } else {
    	
            	legendPick[picking].tick();

                if (playerPick[picking].getCurrentPick() == 1 && legendPick[picking].getchoosen() == 4 && apply.contains(handler.getMouseClickX(), handler.getMouseClickY())) {
                    handler.resetMousePOS();
                    savePersonCounters();
                    picking++;
                } else if (playerPick[picking].getCurrentPick() == 0) {
                    //rng wybierania
                    setBotCounters();
                    picking++;
                } else if (playerPick[picking].getCurrentPick() == 2) {
                    picking++;
                }

                if (picking == 4) {
                	handler.getGameState().init();
                    resetVariables();
                    setState(handler.getGame().gameState);
                }
            	
            }
            apply.tick();


            errorsTick();


        }
        if (!typePick)
        	info.tick();

        pause.tick();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0,0,handler.getFrameWidth(),handler.getFrameHeight());

        dynamicBackground.render(g);

        apply.render(g);

        if(typePick){
            for(int i=0;i<4;i++)
                playerPick[i].render(g);
            nickNamePlaceRender(g);
        }
        else{
            legendPick[picking].render(g);
            info.render(g);
        }

        renderErrors(g);

        pause.render(g);

    }


    //nie wiem czy to najlepsze rozwiązanie
    //ale przynajmniej nie wymaga wywoływania jonstruktorów wszystkich klas
    private void savePersonCounters(){
        int i=0;
        int barPos=0;

            for(int j=0;j<8;j++) {
                if (legendPick[picking].getCounterTile(j).isChoosen()){
                    setCountertypes(j,barPos,i);
                    legendPick[picking].getCounterTile(j).setChoosen();
                    if(handler.getPlayer(picking).getCounter(i).hasUltBar())
                        barPos++;
                    i++;
                }
            }
        }

    private void setBotCounters(){
        Integer[] random={0,1,2,3,4,5,6,7};
        List<Integer> randomList= Arrays.asList(random);
        Collections.shuffle(randomList);
        randomList.toArray(random);

        int barPos=0;
        for(int i=0;i<4;i++){
            setCountertypes(random[i],barPos,i);
            if(handler.getPlayer(picking).getCounter(i).hasUltBar())
                barPos++;
        }
    }

    public void resetVariables(){
        this.legendPick=null;
        this.playerPick=null;
        typePick=true;
        picking=0;

    }

    private void setCountertypes(int pick,int barPos,int i){
        switch (pick) {
            case 0 : handler.getPlayer(picking).setCounter(new Albali(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking],barPos));break;
            case 1 : handler.getPlayer(picking).setCounter(new Funi(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking],barPos));break;
            case 2 : handler.getPlayer(picking).setCounter(new Intan(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking]));break;
            case 3 : handler.getPlayer(picking).setCounter(new Mira(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking],barPos));break;
            case 4 : handler.getPlayer(picking).setCounter(new Polaris(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking],barPos));break;
            case 5 : handler.getPlayer(picking).setCounter(new Samaya(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking]));break;
            case 6 : handler.getPlayer(picking).setCounter(new Saph(handler, BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking],barPos));break;
            case 7 : handler.getPlayer(picking).setCounter(new Venator(handler,BASE_POS_X[picking]+COUNTER_POS_X[i], BASE_POS_Y[picking]+COUNTER_POS_Y[i], Assets.counter[picking],barPos));break;
        }
    }

    private void nickNamePlaceTick(){
        for(int i=0;i<playerI.size();i++){
            textField[playerI.get(i)].setY((handler.getFrameHeight()-playerI.size()*60)/2+7+i*60-20);

            if(textField[playerI.get(i)].contains(handler.getMouseClickX(),handler.getMouseClickY())){
                handler.resetMousePOS();
                for (TextField field : textField) field.setClicked(false);
                textField[playerI.get(i)].setClicked(true);
            }

            textField[playerI.get(i)].tick();
        }
    }

    private void nickNamePlaceRender(Graphics g){
        if(playerI.size()>0)
            for (Integer integer : playerI) {
                textField[integer].render(g);
            }
    }

    private boolean properPick(){
        for(int i=0;i<4;i++){
            if(playerPick[i].getCurrentPick()==1){
                if(textField[i].getNickname().length()<4) {
                    nicknameLengthError.setOccured();
                    return false;
                }
            }
        }

        if(playerPick[0].getCurrentPick()==2&&playerPick[1].getCurrentPick()==2&&playerPick[2].getCurrentPick()==2&&playerPick[3].getCurrentPick()==2) {
            nullPlayersError.setOccured();
            return false;
        }
        return true;
    }

    private void checkPick(){
        if(properPick()){
            for (int i = 0; i < 4; i++) {
                switch (playerPick[i].getCurrentPick()) {
                    case 0:
                        handler.getGameState().setPlayer(new Bot(handler, PLAYER_STARTING_POS[i], PLAYER_ENDING_POS[i], Assets.counter[i]));
                        break;
                    case 1:
                        handler.getGameState().setPlayer(new Person(handler, PLAYER_STARTING_POS[i], PLAYER_ENDING_POS[i], Assets.counter[i],textField[i].getNickname()));
                        break;
                    case 2:
                        handler.getGameState().setPlayer(new Blank(handler, PLAYER_STARTING_POS[i], PLAYER_ENDING_POS[i], Assets.counter[i]));
                        break;
                }
            }
            typePick=false;
        }
    }

    private void errorsTick(){
        nicknameLengthError.tick();
        nullPlayersError.tick();
    }

    private void renderErrors(Graphics g){
        nicknameLengthError.render(g);

        if(nicknameLengthError.getOccured()){
            nullPlayersError.setX(nicknameLengthError.getX());
            nullPlayersError.setY(nicknameLengthError.getY());
        }
        nullPlayersError.render(g);
    }

}