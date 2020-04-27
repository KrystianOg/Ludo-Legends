package states;

import GFX.DynamicBackground;
import ludogame.DBConnect;
import ludogame.Handler;

import java.awt.*;

//tabela z wynikami
//obsługa plików, zapis, odczyt, według nazwy gracza która bedzie podawana
//sumuje zdobyte punkty / zbicia pionków, nie uwzględnia botów
//wchodzi się z menu tak jak do ustawień

//może eksportowanie plików na stronę internetową?

public class HighScoresState extends State{

    //private final Button back;
    DBConnect connect;

    public HighScoresState(Handler handler) {
        super(handler);

        //back=new Button(handler,);

    }

    public void init(DynamicBackground dynamicBackground){
        System.out.println("\tc:");

        connect=new DBConnect();    //<-highscores;
        connect.getData("score",8);
    }

    @Override
    public void tick() {
        //if(this.)

    //metody z bazy danych z komendami


    }

    @Override
    public void render(Graphics g) {

    }


}
