package Players;

import Entities.Counters.Counter;
import GFX.Assets;
import GFX.Text;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Bot extends Player {

    private static final List<String> botNickname=new LinkedList<>();

    public Bot(Handler handler, PositionOnMap startingPos, PositionOnMap endingPos, BufferedImage counterColor) {
        super(handler,startingPos,endingPos,counterColor);
        this.isPlayer=false;
        this.nickname=getBotNickname();
    }

    @Override
    public void tick() {
        setInBase();

        if(counter[0].getWon()&&counter[1].getWon()&&counter[2].getWon()&&counter[3].getWon()) {
            won = true;
            resetFire();
            handler.getGameState().setPlayerData(getPlayerData());
            handler.setTurnof();
        }

        if(!counterIsMoving()) {
            if(resetFireWhileTurn==handler.getGameState().getRound())
                resetFire();

            moveLogic();
        }

        for (Entities.Counters.Counter value : counter) value.tick();

    }

    @Override
    public void render(Graphics g) {

        if(counter!=null) {
             Text.drawString(g, nickname, counter[3].getBaseX() + 60, counter[3].getBaseY() - 15, true, Color.white, Assets.Ubuntu34);
        }
    }

    private void moveLogic() {

        //1.wyjscie z bazy
        //2. bicie
        //3.ochrona
        //4.rng ruchu

        if(!clicked){
            handler.getDice().botRoll();
            clicked=true;
        }

        if(!handler.getDice().isRolled()) {
            handler.getDice().tick();
            handler.getTimer().tick();

        }
        else{

            autoPick();
            clicked=false;
        }

        useUltimateAbility();
    }

    public static int getInBaseInput(Counter[] counter) {

        List<Integer> tab=new LinkedList<>();

        for(int i=0;i<4;i++){
            if(counter[i].isInbase())
                tab.add(i);
        }

        if(tab.size()==0)
            return getOutsideBaseInput(counter);

        return tab.get((int) (Math.random() * tab.size()));
    }

    private void useUltimateAbility(){

        List<Counter> ultimate=new LinkedList<>();
        for(int i=0;i<counter.length;i++){
            if(counter[i].hasUltBar())
            if(!counter[i].isInbase()&&!counter[i].getWon()&&counter[i].getUltimateBar().isLoaded())
                ultimate.add(counter[i]);
        }

        if(ultimate.size()>0) {
            int i = (int) (Math.random() * ultimate.size());
            ultimate.get(i).useUltimateAbility();
            substractUltLoad(ultimate.get(i).getUltimateBar().getUltLoad());
            ultimate.get(i).getUltimateBar().setUltUsed();
        }
    }

    public static int getOutsideBaseInput(Counter[] counter){
        List <Integer> tab=new LinkedList<>();

        for(int i=0;i<4;i++){
            if(!counter[i].isInbase()&&!counter[i].getWon())
                tab.add(i);
        }

        if(tab.size()>0)
        return tab.get((int) (Math.random() * tab.size()));
        else
            return -1;
    }

    public static void setBotNicknames(){

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

    private static String getBotNickname(){

        int i=(int)(Math.random()*botNickname.size());
        String nick=botNickname.get(i);
        Collections.swap(botNickname,i,botNickname.size()-1);
        botNickname.remove(botNickname.size()-1);

        return nick;
    }
}
