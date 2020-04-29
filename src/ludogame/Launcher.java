package ludogame;

public class Launcher {

    public static void main(String[] args) {

        Handler handler=new Handler();
        LoadingScreen loadingScreen= new LoadingScreen(handler,"Ludo Legends",1120,790);
        Game game=new Game(handler,1120,790);
        loadingScreen.start();
        game.start();
    }

}
