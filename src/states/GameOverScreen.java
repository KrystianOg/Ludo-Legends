package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;
import java.util.List;
import Entities.ui.Button;
import GFX.Assets;
import GFX.Text;
import Players.PlayerData;
import ludogame.DBConnect;
import ludogame.Handler;

public class GameOverScreen {
    private static final int LEADERBOARD_WIDTH=715,LEADERBOARD_HEIGHT=625;
    private static int FRAME_X,FRAME_Y;

    private List<PlayerData> playerData;

    private final Handler handler;
    private final Button menu;
    private DBConnect connect;

    private final Color blackOp=new Color(0,0,0,190);
    private final Color onHover=new Color(0,0,0,40);
    private final Color grayOp=new Color(38,38,38,180);
    private final Color textColor=new Color(26,26,26,220);

    public GameOverScreen(Handler handler) {
        playerData=new LinkedList<>();
        FRAME_X=(handler.getFrameWidth()-LEADERBOARD_WIDTH)/2;
        FRAME_Y=(handler.getFrameHeight()-LEADERBOARD_HEIGHT+40)/2+15;
        
        this.handler=handler;
        menu=new Button(handler,(handler.getFrameWidth()-225)/2,400,1, Assets.medium_button_template,"MENU",58);
    }
     
    public void tick() {

        menu.tick();
        if(menu.contains(handler.getMouseClickX(),handler.getMouseClickY())){
            handler.resetMousePOS();
            handler.getGameState().clear();
            State.setState(handler.getGame().menuState);
            try {
                this.finalize();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
    
    public void init(List<PlayerData> playerData) {
    	this.playerData=playerData;

    	connect=handler.getLoadingScreen().getConnection();


    	if(connect.isConnected()) {
            for (int i = 0; i < playerData.size(); i++) {
                if (i == 0&&playerData.get(0).isPlayer())
                    playerData.get(i).setWins(1);
                else if(playerData.get(i).isPlayer())
                    playerData.get(i).setWins(0);

                connect.addResults(playerData.get(i));
            }
        }

    }
    
    public void render(Graphics g) {

        g.setColor(blackOp);
        g.fillRect(0,0,handler.getFrameWidth(),handler.getFrameHeight());
        menu.render(g);
            
        drawHead(g);
        fillRoundRec(g);

        for(int i=0;i<playerData.size();i++){
            drawNextPlayer(playerData.get(i),i,g);
        }
    }
    
    private void drawHead(Graphics g){

        Graphics2D g2=(Graphics2D)g;
        g2.setPaint(grayOp);
        g2.fill(new RoundRectangle2D.Double(FRAME_X,30,LEADERBOARD_WIDTH,75,20,20));

        g2.setPaint(new Color(233,192,33));
        g2.fill(new RoundRectangle2D.Double(FRAME_X+20,45,45,45,10,10));
        g2.setPaint(Color.white);
        g2.fill(new RoundRectangle2D.Double(FRAME_X+85,45,270,45,10,10));
        g2.fill(new RoundRectangle2D.Double(FRAME_X+375,45,150,45,10,10));
        g2.fill(new RoundRectangle2D.Double(FRAME_X+545,45,150,45,10,10));

        Text.drawString(g,"Nickname",FRAME_X+95,82,false,textColor,Assets.Ubuntu40);
        Text.drawString(g,"Score",FRAME_X+385,82,false,textColor,Assets.Ubuntu40);
        Text.drawString(g,"Beats",FRAME_X+555,82,false,textColor,Assets.Ubuntu40);
    }

    private void fillRoundRec(Graphics g){
        Graphics2D g2=(Graphics2D)g;
        g2.setPaint(grayOp);
        g2.fill(new RoundRectangle2D.Double(FRAME_X,FRAME_Y,LEADERBOARD_WIDTH,playerData.size()*60+30,20,20));
    }

    private void drawNextPlayer(PlayerData playerData,int i,Graphics g){
        Graphics2D g2=(Graphics2D)g;

        g2.setPaint(Color.white);
        g2.fill(new RoundRectangle2D.Double(FRAME_X+20,FRAME_Y+20+i*60,45,45,10,10));
        g2.fill(new RoundRectangle2D.Double(FRAME_X+85,FRAME_Y+20+i*60,270,45,10,10));
        g2.fill(new RoundRectangle2D.Double(FRAME_X+375,FRAME_Y+20+i*60,150,45,10,10));
        g2.fill(new RoundRectangle2D.Double(FRAME_X+545,FRAME_Y+20+i*60,150,45,10,10));

        if(i>=0&&i<3){  //puchary
            g.drawImage(Assets.cup[i],FRAME_X+22,FRAME_Y+i*60+22,null);
        }
        else{           //numer miejsca
            Text.drawString(g,Integer.toString(i+1),FRAME_X+42,FRAME_Y+i*60+42,true,textColor,Assets.Ubuntu40);
        }

        Text.drawString(g,playerData.getNickname(),FRAME_X+95,FRAME_Y+i*60+54,false,textColor,Assets.Ubuntu34);
        Text.drawString(g,Integer.toString(playerData.getScore()),FRAME_X+385,FRAME_Y+i*60+54,false,textColor,Assets.Ubuntu34);
        Text.drawString(g,Integer.toString(playerData.getKills()),FRAME_X+555,FRAME_Y+i*60+54,false,textColor,Assets.Ubuntu34);
    }
}
