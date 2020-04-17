package states;

import ludogame.Handler;

import java.awt.*;

//tabela z wynikami
//obsługa plików, zapis, odczyt, według nazwy gracza która bedzie podawana
//sumuje zdobyte punkty / zbicia pionków, nie uwzględnia botów
//wchodzi się z menu tak jak do ustawień

//może eksportowanie plików na stronę internetową?

public class HighScoresState extends State{
    public HighScoresState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
