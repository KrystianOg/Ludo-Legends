package states;

import Entities.ui.Button;
import GFX.Assets;
import GFX.DynamicBackground;
import GFX.Text;
import Players.PlayerData;
import ludogame.DBConnect;
import ludogame.Handler;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;
import java.util.List;

import static states.GameState.color;


//tabela z wynikami
//obsługa plików, zapis, odczyt, według nazwy gracza która bedzie podawana
//sumuje zdobyte punkty / zbicia pionków, nie uwzględnia botów
//wchodzi się z menu tak jak do ustawień

//może eksportowanie plików na stronę internetową?

public class HighScoresState extends State{

    //private final Button back;
    private static final int LEADERBOARD_WIDTH=825,LEADERBOARD_HEIGHT=625;
    private static int FRAME_X,FRAME_Y;

    private final List<PlayerData> playerData;
    private DBConnect connect;
    private DynamicBackground dynamicBackground;
    private final Color onHover=new Color(0,0,0,40);
    private final Color grayOp=new Color(38,38,38,180);
    private final Color textColor=new Color(26,26,26,220);

    private final Rectangle scoreHitbox;
    private final Rectangle beatsHitbox;
    private final Rectangle winsHitbox;
    private boolean scoreHover,beatsHover,winsHover;

    private final Entities.ui.Button back;

    //DB settings
    private static final int LIMIT=10;

    public HighScoresState(Handler handler) {
        super(handler);

        playerData=new LinkedList<>();
        FRAME_X=(handler.getFrameWidth()-LEADERBOARD_WIDTH)/2;
        FRAME_Y=(handler.getFrameHeight()-LEADERBOARD_HEIGHT+40)/2+15;

        back=new Button(handler,handler.getFrameWidth()-150,40,(float)0.6,Assets.medium_button_template,"BACK",40);

        scoreHitbox=new Rectangle(FRAME_X+675,45,130,45);
        beatsHitbox=new Rectangle(FRAME_X+525,45,130,45);
        winsHitbox=new Rectangle(FRAME_X+375,45,130,45);

        scoreHover=false;
        beatsHover=false;
        winsHover=false;
    }

    public void init(DynamicBackground dynamicBackground){
        playerData.clear();
        this.dynamicBackground=dynamicBackground;
        connect=new DBConnect(handler); //<-highscores;
        
        if(connect.isConnected())
            connect.getData("wins", LIMIT,playerData);
    }

    @Override
    public void tick() {
        dynamicBackground.tick();
        back.tick();

        if(playerData.size()!=0) {
            if (scoreHitbox.contains(handler.getHoverX(), handler.getHoverY())) {
                scoreHover = true;
                if (scoreHitbox.contains(handler.getMouseClickX(), handler.getMouseClickY())) {
                    handler.resetMousePOS();
                    playerData.clear();
                    connect.getData("score", LIMIT, playerData);
                }
            } else
                scoreHover = false;

            if (beatsHitbox.contains(handler.getHoverX(), handler.getHoverY())) {
                beatsHover = true;
                if (beatsHitbox.contains(handler.getMouseClickX(), handler.getMouseClickY())) {
                    handler.resetMousePOS();
                    playerData.clear();
                    connect.getData("kills", LIMIT, playerData);
                }
            } else
                beatsHover = false;

            if (winsHitbox.contains(handler.getHoverX(), handler.getHoverY())) {
                winsHover = true;
                if (winsHitbox.contains(handler.getMouseClickX(), handler.getMouseClickY())) {
                    handler.resetMousePOS();
                    playerData.clear();
                    connect.getData("wins", LIMIT, playerData);
                }
            } else
                winsHover = false;
        }

        if(back.contains(handler.getMouseClickX(),handler.getMouseClickY())){
            handler.resetMousePOS();
            connect.close();
            setState(handler.getGame().menuState);
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2=(Graphics2D)g;

        dynamicBackground.render(g);

        if(playerData.size()!=0) {
            drawHead(g);
            fillRoundRec(g);
        }

        for(int i=0;i<playerData.size();i++){
            drawNextPlayer(playerData.get(i),i,g);
        }

        if(playerData.size()==0){
            Text.drawString(g,"Couldn't connect to database.",handler.getFrameWidth()/2,handler.getFrameHeight()/2,true,Color.red,Assets.Ubuntu40);
        }

        if(scoreHover) {
            g2.setPaint(onHover);
            g2.fill(new RoundRectangle2D.Double(FRAME_X + 675, 45, 130, 45, 10, 10));
        }else if(beatsHover)  {
            g2.setPaint(onHover);
            g2.fill(new RoundRectangle2D.Double(FRAME_X+525,45,130,45,10,10));
        }else if(winsHover){
            g2.setPaint(onHover);
            g2.fill(new RoundRectangle2D.Double(FRAME_X + 375, 45, 130, 45, 10, 10));
        }

        back.render(g);

    }

    private void drawHead(Graphics g){

        Graphics2D g2=(Graphics2D)g;
        g2.setPaint(grayOp);
        g2.fill(new RoundRectangle2D.Double(FRAME_X,30,LEADERBOARD_WIDTH,75,20,20));

        g2.setPaint(new Color(233,192,33));
        g2.fill(new RoundRectangle2D.Double(FRAME_X+20,45,45,45,10,10));
        g2.setPaint(Color.white);
        g2.fill(new RoundRectangle2D.Double(FRAME_X+85,45,270,45,10,10));
        g2.fill(new RoundRectangle2D.Double(FRAME_X+375,45,130,45,10,10));
        g2.fill(new RoundRectangle2D.Double(FRAME_X+525,45,130,45,10,10));
        g2.fill(new RoundRectangle2D.Double(FRAME_X+675,45,130,45,10,10));

        Text.drawString(g,"Nickname",FRAME_X+95,82,false,textColor,Assets.Ubuntu40);
        Text.drawString(g,"Wins",FRAME_X+385,82,false,textColor,Assets.Ubuntu40);
        Text.drawString(g,"Beats",FRAME_X+535,82,false,textColor,Assets.Ubuntu40);
        Text.drawString(g,"Score",FRAME_X+685,82,false,textColor,Assets.Ubuntu40);
    }

    private void fillRoundRec(Graphics g){
        Graphics2D g2=(Graphics2D)g;
        g2.setPaint(grayOp);

        if(playerData.size()>0)
        g2.fill(new RoundRectangle2D.Double(FRAME_X,FRAME_Y,LEADERBOARD_WIDTH,playerData.size()*60+30,20,20));
    }

    private void drawNextPlayer(PlayerData playerData,int i,Graphics g){
        Graphics2D g2=(Graphics2D)g;

        g2.setPaint(Color.white);
        g2.fill(new RoundRectangle2D.Double(FRAME_X+20,FRAME_Y+20+i*60,45,45,10,10));
        g2.fill(new RoundRectangle2D.Double(FRAME_X+85,FRAME_Y+20+i*60,270,45,10,10));
        g2.fill(new RoundRectangle2D.Double(FRAME_X+375,FRAME_Y+20+i*60,130,45,10,10));
        g2.fill(new RoundRectangle2D.Double(FRAME_X+525,FRAME_Y+20+i*60,130,45,10,10));
        g2.fill(new RoundRectangle2D.Double(FRAME_X+675,FRAME_Y+20+i*60,130,45,10,10));

        if(i>=0&&i<3){  //puchary
            g.drawImage(Assets.cup[i],FRAME_X+22,FRAME_Y+i*60+22,null);
        }
        else{           //numer miejsca
            Text.drawString(g,Integer.toString(i+1),FRAME_X+42,FRAME_Y+i*60+42,true,textColor,Assets.Ubuntu40);
        }

        Text.drawString(g,playerData.getNickname(),FRAME_X+95,FRAME_Y+i*60+54,false,textColor,Assets.Ubuntu34);
        Text.drawString(g,Integer.toString(playerData.getWins()),FRAME_X+385,FRAME_Y+i*60+54,false,textColor,Assets.Ubuntu34);
        Text.drawString(g,Integer.toString(playerData.getKills()),FRAME_X+535,FRAME_Y+i*60+54,false,textColor,Assets.Ubuntu34);
        Text.drawString(g,Integer.toString(playerData.getScore()),FRAME_X+685,FRAME_Y+i*60+54,false,textColor,Assets.Ubuntu34);

    }
}
