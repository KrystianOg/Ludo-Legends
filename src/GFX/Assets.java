package GFX;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {

    public static final int P_WIDTH=41,P_HEIGHT=78,
                             R_WIDTH=75,R_HEIGHT =75,
                             ARROW_WIDTH=50,ARROW_HEIGHT=50,
                             TILE_WIDTH=90,TILE_HEIGHT=90;

    public static Font Ubuntu28;

    public static BufferedImage logo;
    public static BufferedImage slider_front, slider_back;


    public static BufferedImage sword,cloak_b,cloak_f,shield;
    public static BufferedImage progressbar_b,progressbar_f;

    public static BufferedImage map;
    public static BufferedImage timerFrame;

    public static BufferedImage[] game_button =new BufferedImage[2],
                                  settings_button =new BufferedImage[2],
                                  apply_button=new BufferedImage[2],
                                  rollimg =new BufferedImage[6],
                                  counter =new BufferedImage[4],
                                  back_button =new BufferedImage[2],
                                  reset_button =new BufferedImage[2],
                                  tile=new BufferedImage[5],
                                  player=new BufferedImage[3],
                                  arrow=new BufferedImage[5];

    public static void init(){
    	Ubuntu28=FontLoader.loadfont("fonts/Ubuntu-R.ttf", 28);
        SpriteSheet roll=new SpriteSheet(ImageLoader.loadImage("graphics/Dice/Roll.png"));
        SpriteSheet counters=new SpriteSheet(ImageLoader.loadImage("graphics/Counters/Pionki.png"));

        //mapa
        map=ImageLoader.loadImage("graphics/Map.png");


        //logo
        logo=ImageLoader.loadImage("graphics/Maybe_Main_Logo.png");
        

        //grafiki specjalne postaci
        sword=ImageLoader.loadImage("graphics/Counters/Sword1.png");
        cloak_b=ImageLoader.loadImage("graphics/Counters/Cloak_Back.png");
        cloak_f=ImageLoader.loadImage("graphics/Counters/Cloak_Front.png");
        shield=ImageLoader.loadImage("graphics/Counters/Shield.png");


        //progressbar
        progressbar_b=ImageLoader.loadImage("graphics/ProgressBar/Bar_Back.png");   //dodać siatke
        progressbar_f=ImageLoader.loadImage("graphics/ProgressBar/Bar_Front.png");

        //przyciski menu
        game_button[0]=ImageLoader.loadImage("graphics/Menu/game.png");         //dodac siatke
        game_button[1]=ImageLoader.loadImage("graphics/Menu/game_onhover.png");
        settings_button[0]=ImageLoader.loadImage("graphics/Menu/settings.png");
        settings_button[1]=ImageLoader.loadImage("graphics/Menu/settings_onhover.png");
        apply_button[0]=ImageLoader.loadImage("graphics/Menu/apply.png");
        apply_button[1]=ImageLoader.loadImage("graphics/Menu/apply_onhover.png");

        //settings
        slider_front=ImageLoader.loadImage("graphics/Settings/slider_front.png");
        slider_back=ImageLoader.loadImage("graphics/Settings/slider_back.png");

        back_button[0]=ImageLoader.loadImage("graphics/Settings/back_button.png");
        back_button[1]=ImageLoader.loadImage("graphics/Settings/back_button_onhover.png");

        reset_button[0]=ImageLoader.loadImage("graphics/Settings/reset.png");
        reset_button[1]=ImageLoader.loadImage("graphics/Settings/reset_onhover.png");
        
        //kostka do gry
        rollimg[0]=roll.crop(0,0,R_WIDTH,R_HEIGHT);
        rollimg[1]=roll.crop(R_WIDTH,0,R_WIDTH,R_HEIGHT);
        rollimg[2]=roll.crop(R_WIDTH*2,0,R_WIDTH,R_HEIGHT);
        rollimg[3]=roll.crop(0,R_HEIGHT,R_WIDTH,R_HEIGHT);
        rollimg[4]=roll.crop(R_WIDTH,R_HEIGHT,R_WIDTH,R_HEIGHT);
        rollimg[5]=roll.crop(R_WIDTH*2,R_HEIGHT,R_WIDTH,R_HEIGHT);
        timerFrame=ImageLoader.loadImage("graphics/Dice/Timer.png");

        //kolory pionkow
        counter[0]=counters.crop(0,0,P_WIDTH,P_HEIGHT);
        counter[1]=counters.crop(P_WIDTH,0,P_WIDTH,P_HEIGHT);
        counter[2]=counters.crop(0,P_HEIGHT,P_WIDTH,P_HEIGHT);
        counter[3]=counters.crop(P_WIDTH,P_HEIGHT,P_WIDTH,P_HEIGHT);

        //kolory tła wyboru
        SpriteSheet tileSheet=new SpriteSheet(ImageLoader.loadImage("graphics/PreparationState/tileSheet.png"));
        tile[0]=tileSheet.crop(TILE_WIDTH,0,TILE_WIDTH,TILE_HEIGHT);
        tile[1]=tileSheet.crop(TILE_WIDTH,TILE_HEIGHT,TILE_WIDTH,TILE_HEIGHT);
        tile[2]=tileSheet.crop(0,TILE_HEIGHT,TILE_WIDTH,TILE_HEIGHT);
        tile[3]=tileSheet.crop(0,0,TILE_WIDTH,TILE_HEIGHT);
        tile[4]=tileSheet.crop(TILE_WIDTH*2,0,TILE_WIDTH,TILE_HEIGHT);

        //strzałki przy wyborze
        SpriteSheet arrowSheet=new SpriteSheet(ImageLoader.loadImage("graphics/PreparationState/arrowSheet.png"));
        arrow[0]=arrowSheet.crop(ARROW_WIDTH,0,ARROW_WIDTH,ARROW_HEIGHT);
        arrow[1]=arrowSheet.crop(ARROW_WIDTH,ARROW_HEIGHT,ARROW_WIDTH,ARROW_HEIGHT);
        arrow[2]=arrowSheet.crop(0,ARROW_HEIGHT,ARROW_WIDTH,ARROW_HEIGHT);
        arrow[3]=arrowSheet.crop(0,0,ARROW_WIDTH,ARROW_HEIGHT);
        arrow[4]=arrowSheet.crop(ARROW_WIDTH*2,0,ARROW_WIDTH,ARROW_HEIGHT);

        //typy graczy
        SpriteSheet playerSheet=new SpriteSheet(ImageLoader.loadImage("graphics/PreparationState/playerSheet.png"));
        player[0]=playerSheet.crop(0,0,TILE_WIDTH,TILE_HEIGHT);
        player[1]=playerSheet.crop(0,TILE_HEIGHT,TILE_WIDTH,TILE_HEIGHT);
        player[2]=playerSheet.crop(TILE_WIDTH,0,TILE_WIDTH,TILE_HEIGHT);
    }

}
