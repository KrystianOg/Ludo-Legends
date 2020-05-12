package players;
import GFXandEffects.Assets;
import GFXandEffects.Text;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Person extends Player {

    public static final String[] defaultNickname={ "Player1", "Player2", "Player3", "Player4"};

    private int input;

    public Person(Handler handler, PositionOnMap startingPos, PositionOnMap endingPos, BufferedImage counterColor,String nickname) { //zmienic na getter
        super(handler,startingPos,endingPos,counterColor);
        this.nickname=nickname;
        this.isPlayer=true;
        input=-1;
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

        for (entities.counters.Counter value : counter){ value.tick(); }
    }

    @Override
    public void render(Graphics g) {

        if(counter[0]!=null)
            Text.drawString(g,this.nickname,counter[3].getBaseX()+60,counter[3].getBaseY()-15,true,Color.white, Assets.Ubuntu34);
    }

    private void moveLogic(){

        //timer dla ruchu
        handler.getTimer().tick();

        if(!handler.getDice().isRolled()) {
            handler.getDice().tick();
        }

        else{
            if(inBaseOrWon()&&handler.getRoll()!=6) {
                notSixLogic();
                handler.setTurnof();
            }
            else if(handler.getRoll()==6) {
                notSix.clear();
                chance.clear();

                input=getInput();

                if(input>=0) {
                    counter[input].setMoving(true);
                    lastRolls.add(handler.getRoll());
                }

            }
            else if(!isinbase&&handler.getRoll()<6){
                input=getInput();

                if(input>=0&&!counter[input].isInbase()) {
                    counter[input].setMoving(true);
                    lastRolls.add(handler.getRoll());
                }
            }
        }

    }

    private int getInput() {
        if(counter[0].isClicked()&&!counter[0].getWon())
            return 0;
        else if(counter[1].isClicked()&&!counter[1].getWon())
            return 1;
        else if(counter[2].isClicked()&&!counter[2].getWon())
            return 2;
        else if(counter[3].isClicked()&&!counter[3].getWon())
            return 3;
        else
            return -1;
    }

    private boolean inBaseOrWon(){
        for(int i=0;i<4;i++){
            if(!counter[i].isInbase()&&!counter[i].getWon())
                return false;
        }
        return true;
    }

}
