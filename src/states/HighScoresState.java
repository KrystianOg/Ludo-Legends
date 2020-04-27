package states;

import GFX.DynamicBackground;
import ludogame.Handler;

import java.awt.*;

//tabela z wynikami
//obsługa plików, zapis, odczyt, według nazwy gracza która bedzie podawana
//sumuje zdobyte punkty / zbicia pionków, nie uwzględnia botów
//wchodzi się z menu tak jak do ustawień

//może eksportowanie plików na stronę internetową?

public class HighScoresState extends State{

    //private final Button back;


    public HighScoresState(Handler handler) {
        super(handler);

        //back=new Button(handler,);

    }

    public void init(DynamicBackground dynamicBackground){
        System.out.println("\tc:");
    }

    @Override
    public void tick() {
        //if(this.)




    }

    @Override
    public void render(Graphics g) {

    }


}
