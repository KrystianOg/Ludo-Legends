package Players;

import Entities.PositionOnMap;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;


public class Bot extends Player {

    private final String nickname;

    private int input=-1;
    private boolean clicked=false;

    public Bot(Handler handler, PositionOnMap startingPos, PositionOnMap endingPos, BufferedImage counterColor) {
        super(handler,startingPos,endingPos,counterColor);
        nickname=handler.getBotNickname();
        System.out.println(this.nickname);
    }

    @Override
    public void tick() {
        setInBase();

        if(!counterIsMoving())
            moveLogic();

        for (Entities.Counters.Counter value : counter) value.tick();

    }

    @Override
    public void render(Graphics g) {

        for (Entities.Counters.Counter value : counter) value.render(g);

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
            lastRolls.add(handler.getRoll());
            if(lastRolls.size()==3&&lastRolls.get(2)==6){
                handler.setTurnof();
                System.out.println("BREAK AFTER THREE SIXS");
            }
            else if (isinbase && handler.getRoll() != 6) {
                handler.setTurnof();
            }
            else if (handler.getRoll() == 6) {
                input = (int) (Math.random() * 4);

                if (input >= 0) {
                    if(counter[input].isInbase())
                        currentlyinbase--;

                    counter[input].setMoving();
                }
            } else if (!isinbase && handler.getRoll() < 6) {
                input = getOutsideBaseInput();

                if (input >= 0) {
                    if (!counter[input].isInbase()) {
                        counter[input].setMoving();
                    }
                }
            }

            clicked=false;
        }
    }

    private int getInBaseInput() {

        List<Integer> tab=new LinkedList<>();

        for(int i=0;i<4;i++){
            if(counter[i].isInbase())
                tab.add(i);
        }

        return tab.get((int) (Math.random() * tab.size()));
    }

    private int getOutsideBaseInput(){
        List <Integer> tab=new LinkedList<>();

        for(int i=0;i<4;i++){
            if(!counter[i].isInbase())
                tab.add(i);
        }

        return tab.get((int) (Math.random() * tab.size()));
    }


}
