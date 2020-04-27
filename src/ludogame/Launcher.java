package ludogame;

import java.sql.SQLException;

public class Launcher {

    public static void main(String[] args) {

        Game game=new Game("Ludo Legends",980,790);

        game.start();
    }

}
