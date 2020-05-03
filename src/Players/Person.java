package Players;
import Entities.PositionOnMap;
import GFX.Assets;
import GFX.Text;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Person extends Player {

    //Z BAZY DANYCH
    private PlayerData playerData;
    private final String nickname;

    public static final String[] defaultNickname={ "Player1", "Player2", "Player3", "Player4"};

    private int input;

    public Person(Handler handler, PositionOnMap startingPos, PositionOnMap endingPos, BufferedImage counterColor,String nickname) { //zmienic na getter
        super(handler,startingPos,endingPos,counterColor);
        this.nickname=nickname;
        input=-1;
    }

    @Override
    public void tick() {
        setInBase();

        if(!counterIsMoving())
            moveLogic();

        for (Entities.Counters.Counter value : counter){ value.tick(); }

    }

    @Override
    public void render(Graphics g) {
        if(counter!=null)
            Text.drawString(g,nickname,counter[3].getBaseX()+60,counter[3].getBaseY()-15,true,Color.white, Assets.Ubuntu34);
        //for (Entities.Counters.Counter value : counter) value.render(g);

    }

    private void moveLogic(){

        //z kolejnymi rzutami wieksza szansa na wyrzucenie 6

        //timer dla ruchu
        //if()


        if(!handler.getDice().isRolled()) {
            handler.getDice().tick();
            handler.getTimer().tick();
        }

        else{
            if(lastRolls.size()==3&&lastRolls.get(2)==6){
                handler.setTurnof();
                System.out.println("BREAK AFTER THREE SIXS");
            }
            else if(isinbase&&handler.getRoll()!=6) {
                notSixLogic();
                handler.setTurnof();
            }
            else if(handler.getRoll()==6) {
                notSix.clear();
                chance.clear();

                input=getInput();

                if(input>=0) {
                    counter[input].setMoving();
                    lastRolls.add(handler.getRoll());
                }

            }
            else if(!isinbase&&handler.getRoll()<6){
                input=getInput();

                if(input>=0&&!counter[input].isInbase()) {
                    counter[input].setMoving();
                    lastRolls.add(handler.getRoll());
                }
            }
        }

    }

    private int getInput() {
        if(counter[0].isClicked())
            return 0;
        else if(counter[1].isClicked())
            return 1;
        else if(counter[2].isClicked())
            return 2;
        else if(counter[3].isClicked())
            return 3;
        else
            return -1;
    }



}
