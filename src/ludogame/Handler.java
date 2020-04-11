package ludogame;

public class Handler {      //przekazuje zmienne do klas

    private Game game;
    //inne zmienne


    public Handler(Game game){
        this.game=game;
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
