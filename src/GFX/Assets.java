package GFX;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int P_WIDTH=41,P_HEIGHT=78,
            R_WIDTH=75,R_HEIGHT =75;

    public static BufferedImage sword,cloak_b,cloak_f,shield;
    public static BufferedImage progressbar_b,progressbar_f;
    public static BufferedImage map;
    public static BufferedImage timerFrame;
    public static BufferedImage[] game_button =new BufferedImage[2],
                                  settings_button =new BufferedImage[2],
                                  apply_button=new BufferedImage[2],
                                  rollimg =new BufferedImage[6],
                                  counter =new BufferedImage[4];

    public static void init(){
        SpriteSheet roll=new SpriteSheet(ImageLoader.loadImage("graphics/Dice/Roll.png"));
        SpriteSheet player=new SpriteSheet(ImageLoader.loadImage("graphics/Counters/Pionki.png"));

        //mapa
        map=ImageLoader.loadImage("graphics/Map.png");

        //grafiki specjalne postaci
        sword=ImageLoader.loadImage("graphics/Counters/Sword1.png");
        cloak_b=ImageLoader.loadImage("graphics/Counters/Cloak_Back.png");
        cloak_f=ImageLoader.loadImage("graphics/Counters/Cloak_Front.png");
        shield=ImageLoader.loadImage("graphics/Counters/Shield.png");

        //progressbar
        progressbar_b=ImageLoader.loadImage("graphics/ProgressBar/Bar_Back.png");
        progressbar_f=ImageLoader.loadImage("graphics/ProgressBar/Bar_Front.png");

        //przyciski menu
        game_button[0]=ImageLoader.loadImage("graphics/Menu/game.png");
        game_button[1]=ImageLoader.loadImage("graphics/Menu/game_onhover.png");
        settings_button[0]=ImageLoader.loadImage("graphics/Menu/settings.png");
        settings_button[1]=ImageLoader.loadImage("graphics/Menu/settings_onhover.png");
        apply_button[0]=ImageLoader.loadImage("graphics/Menu/apply.png");
        apply_button[1]=ImageLoader.loadImage("graphics/Menu/apply_onhover.png");

        //kostka do gry
        rollimg[0]=roll.crop(0,0,R_WIDTH,R_HEIGHT);
        rollimg[1]=roll.crop(R_WIDTH,0,R_WIDTH,R_HEIGHT);
        rollimg[2]=roll.crop(R_WIDTH*2,0,R_WIDTH,R_HEIGHT);
        rollimg[3]=roll.crop(0,R_HEIGHT,R_WIDTH,R_HEIGHT);
        rollimg[4]=roll.crop(R_WIDTH,R_HEIGHT,R_WIDTH,R_HEIGHT);
        rollimg[5]=roll.crop(R_WIDTH*2,R_HEIGHT,R_WIDTH,R_HEIGHT);
        timerFrame=ImageLoader.loadImage("graphics/Dice/Timer.png");

        //kolory pionkow
        counter[0]=player.crop(0,0,P_WIDTH,P_HEIGHT);
        counter[1]=player.crop(P_WIDTH,0,P_WIDTH,P_HEIGHT);
        counter[2]=player.crop(0,P_HEIGHT,P_WIDTH,P_HEIGHT);
        counter[3]=player.crop(P_WIDTH,P_HEIGHT,P_WIDTH,P_HEIGHT);
    }

}
