package ludogame;

import java.sql.SQLException;

public class Launcher {


    public static void main(String[] args) {


        Game game=new Game("Ludo Legends",980,790);
        DBConnect connect=new DBConnect();
        connect.getData("select * from players order by score desc limit 10");
        game.start();
    }
}
