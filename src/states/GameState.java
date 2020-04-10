package states;
import Entities.Board;
import Entities.Counters.Counter;
import Entities.Counters.Funi;
import Entities.Counters.Saph;
import Entities.Player;
import Entities.Players.Person;
import ludogame.Game;
import java.awt.*;

public class GameState extends State{

    //Counter[][] counter;
    Player[] player=new Player[4];
    Board board;

    private static final int W1=92,H1=91;
    private static final int W2=166,H2=166;

    State prep;
    public GameState(Game game,State prepstate){
        super(game);


        prep=prepstate;


        //counter=new Counter[4][4];
        board=new Board(0,0,750,750);
        //wybieranie graczy

        setPlayers();
        setCounters();










    }

    private void setPlayers(){
        for(int i=0;i<4;i++)
            player[i]=new Person(game,i*13+1);
    }

    private void setCounters(){
        //player 0
            Funi a1, a4;
            Saph a2, a3;

            a1=new Funi(game,W1,H1);
            a2=new Saph(game,W2,H1);
            a3=new Saph(game,W1,H2);
            a4=new Funi(game,W2,H2);

            player[0].setCounters(a1,a2,a3,a4);
        //player1
        Funi b1, b3;
        Saph b2, b4;

        b1=new Funi(game,450+W1,H1);
        b2=new Saph(game,450+W2,H1);
        b3=new Funi(game,450+W1,H2);
        b4=new Saph(game,450+W2,H2);

        player[1].setCounters(b1,b2,b3,b4);
        //player2
        Funi c1, c2,c3;
        Saph c4;

        c1= new Funi(game,W1,450+H1);
        c2= new Funi(game,W2,450+H1);
        c3= new Funi(game,W1,450+H2);
        c4= new Saph(game,W2,450+H2);

        player[2].setCounters(c1,c2,c3,c4);
       //player3
        Funi d1, d2;
        Saph d3, d4;

        d1= new Funi(game,450+W1,450+H1);
        d2= new Funi(game,450+W2,450+H1);
        d3= new Saph(game,450+W1,450+H2);
        d4= new Saph(game,450+W2,450+H2);

        player[3].setCounters(d1,d2,d3,d4);

    }


    @Override
    public void tick() {

            player[0].tick();
            player[1].tick();
            player[2].tick();
            player[3].tick();

    }

    @Override
    public void render(Graphics g) {

        board.render(g);
        player[0].render(g);
        player[1].render(g);
        player[2].render(g);
        player[3].render(g);

    }

}
