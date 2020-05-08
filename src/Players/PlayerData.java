package Players;

public class PlayerData {

    private int playerId;
    private String nickname;
    private int score;
    private int kills;
    private int wins;
    private boolean player;

    public PlayerData(){

    }

    public PlayerData(String nickname, int score, int kills, int wins){
    	this.nickname=nickname;
    	this.score=score;
    	this.kills=kills;
    	this.wins=wins;
    }

    public PlayerData(String nickname, int score, int kills,boolean player){
        this.player=player;
        this.nickname=nickname;
        this.score=score;
        this.kills=kills;
        this.wins=0;
    }
    
    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getKills() {
        return kills;
    }

    public boolean isPlayer(){
        return this.player;
    }

}