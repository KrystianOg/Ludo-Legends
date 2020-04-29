package GFX;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Assets {

    private static final int P_WIDTH=41,P_HEIGHT=78,
                             R_WIDTH=75,R_HEIGHT =75,
                             ARROW_WIDTH=50,ARROW_HEIGHT=50,
                             TILE_WIDTH=90,TILE_HEIGHT=90;

   // public static Font
    public static Font Ubuntu28,Ubuntu40,Ubuntu34;

    public static BufferedImage logo;
    public static BufferedImage slider_front, slider_back;


    public static BufferedImage sword,cloak_b,cloak_f,shield,wand,medkit,icicle_f,icicle_b,
                                bow_rb,bow_lb,bow_rf,bow_lf,swan;

    public static BufferedImage progressbar_b,progressbar_f;

    public static BufferedImage map;
    public static BufferedImage timerFrame;

    public static BufferedImage[] big_button_template=new BufferedImage[2],
                                  medium_button_template=new BufferedImage[2];

    public static BufferedImage[] rollimg =new BufferedImage[6],
                                  counter =new BufferedImage[4],
                                  tile=new BufferedImage[6],
                                  player=new BufferedImage[3],
                                  arrow=new BufferedImage[5],
                                  dynamicBackground=new BufferedImage[2],
                                  pause_button=new BufferedImage[3];

    public static BufferedImage apply_button,
                                defaults_button,
                                settings_button,
                                back_button,
                                reset_button,
                                game_text,
                                ranking_text,
                                menu;

    public static BufferedImage[] cup=new BufferedImage[3];

//Loading Screen
    public static BufferedImage loadingCircle;
    public static BufferedImage[] loadingDot=new BufferedImage[4];

    public static void init(){
        //czcionka
    	Ubuntu28=FontLoader.loadFont("fonts/Ubuntu-R.ttf", 28);
        Ubuntu40=FontLoader.loadFont("fonts/Ubuntu-B.ttf",40);


        //mapa
        map=ImageLoader.loadImage("graphics/Map.png");

        //grafiki specjalne postaci
        sword=ImageLoader.loadImage("graphics/Counters/Sword1.png");
            //
        cloak_b=ImageLoader.loadImage("graphics/Counters/Cloak_Back.png");
        cloak_f=ImageLoader.loadImage("graphics/Counters/Cloak_Front.png");
            //
        shield=ImageLoader.loadImage("graphics/Counters/Shield.png");
            //
        wand=ImageLoader.loadImage("graphics/Counters/wand1.png");
            //
        medkit=ImageLoader.loadImage("graphics/Counters/medkit1.png");
            //
        icicle_f=ImageLoader.loadImage("graphics/Counters/icicle_front.png");
        icicle_b=ImageLoader.loadImage("graphics/Counters/icicle_back.png");
            //
        bow_lb=ImageLoader.loadImage("graphics/Counters/bow_lb.png");
        bow_lf=ImageLoader.loadImage("graphics/Counters/bow_lf.png");
        bow_rb=ImageLoader.loadImage("graphics/Counters/bow_rb.png");
        bow_rf=ImageLoader.loadImage("graphics/Counters/bow_rf.png");
            //
        swan=ImageLoader.loadImage("graphics/Counters/swan.png");


        //progressbar
        progressbar_b=ImageLoader.loadImage("graphics/ProgressBar/Bar_Back.png");   //dodać siatke
        progressbar_f=ImageLoader.loadImage("graphics/ProgressBar/Bar_Front.png");

        //przyciski menu
        SpriteSheet big_button_templates=new SpriteSheet(ImageLoader.loadImage("graphics/Menu/big_button_template_red.png"));
        big_button_template[0]=big_button_templates.crop(0,0,350,90);
        big_button_template[1]=big_button_templates.crop(0,90,350,90);

        game_text=ImageLoader.loadImage("graphics/Menu/game_w.png");         //dodac siatke
        settings_button=ImageLoader.loadImage("graphics/Menu/settings.png");
        apply_button=ImageLoader.loadImage("graphics/Menu/apply.png");
        defaults_button=ImageLoader.loadImage("graphics/Menu/defaults.png");
        ranking_text=ImageLoader.loadImage("graphics/Menu/ranking.png");

        //settings
        slider_front=ImageLoader.loadImage("graphics/Settings/slider_front.png");
        slider_back=ImageLoader.loadImage("graphics/Settings/slider_back.png");

        back_button=ImageLoader.loadImage("graphics/Menu/back.png");

        SpriteSheet medium_button_templates=new SpriteSheet(ImageLoader.loadImage("graphics/Settings/reset_button_template.png"));
        medium_button_template[0]=medium_button_templates.crop(0,0,225,90);
        medium_button_template[1]=medium_button_templates.crop(0,90,225,90);
        reset_button=ImageLoader.loadImage("graphics/Settings/reset.png");
        menu=ImageLoader.loadImage("graphics/Menu/menu.png");

        SpriteSheet pause_button_sheet=new SpriteSheet(ImageLoader.loadImage("graphics/PreparationState/pauseSheet.png"));
        pause_button[0]=pause_button_sheet.crop(0,0,70,70);
        pause_button[1]=pause_button_sheet.crop(0,70,70,70);
        pause_button[2]=pause_button_sheet.crop(70,0,70,70);

        //kostka do gry
        SpriteSheet roll=new SpriteSheet(ImageLoader.loadImage("graphics/Dice/Roll.png"));
        rollimg[0]=roll.crop(0,0,R_WIDTH,R_HEIGHT);
        rollimg[1]=roll.crop(R_WIDTH,0,R_WIDTH,R_HEIGHT);
        rollimg[2]=roll.crop(R_WIDTH*2,0,R_WIDTH,R_HEIGHT);
        rollimg[3]=roll.crop(0,R_HEIGHT,R_WIDTH,R_HEIGHT);
        rollimg[4]=roll.crop(R_WIDTH,R_HEIGHT,R_WIDTH,R_HEIGHT);
        rollimg[5]=roll.crop(R_WIDTH*2,R_HEIGHT,R_WIDTH,R_HEIGHT);
        timerFrame=ImageLoader.loadImage("graphics/Dice/Timer.png");


        //kolory pionkow
        SpriteSheet counters=new SpriteSheet(ImageLoader.loadImage("graphics/Counters/Pionki.png"));
        counter[0]=counters.crop(P_WIDTH,0,P_WIDTH,P_HEIGHT);
        counter[1]=counters.crop(P_WIDTH,P_HEIGHT,P_WIDTH,P_HEIGHT);
        counter[2]=counters.crop(0,P_HEIGHT,P_WIDTH,P_HEIGHT);
        counter[3]=counters.crop(0,0,P_WIDTH,P_HEIGHT);


        //kolory tła wyboru
        SpriteSheet tileSheet=new SpriteSheet(ImageLoader.loadImage("graphics/PreparationState/tileSheet.png"));
        tile[0]=tileSheet.crop(TILE_WIDTH,0,TILE_WIDTH,TILE_HEIGHT);
        tile[1]=tileSheet.crop(TILE_WIDTH,TILE_HEIGHT,TILE_WIDTH,TILE_HEIGHT);
        tile[2]=tileSheet.crop(0,TILE_HEIGHT,TILE_WIDTH,TILE_HEIGHT);
        tile[3]=tileSheet.crop(0,0,TILE_WIDTH,TILE_HEIGHT);
        tile[4]=tileSheet.crop(TILE_WIDTH*2,0,TILE_WIDTH,TILE_HEIGHT);
        tile[5]=tileSheet.crop(TILE_WIDTH*2,TILE_HEIGHT,TILE_WIDTH,TILE_HEIGHT);


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

        //puchary
        SpriteSheet smallCupsSheet=new SpriteSheet((ImageLoader.loadImage("graphics/LeaderBoard/cups_sheet.png")));
        cup[0]=smallCupsSheet.crop(0,0,40,40);
        cup[1]=smallCupsSheet.crop(40,0,40,40);
        cup[2]=smallCupsSheet.crop(0,40,40,40);

        //dynamic background
        dynamicBackground[0]=ImageLoader.loadImage("graphics/Menu/Background_3.png");
        dynamicBackground[1]=ImageLoader.loadImage("graphics/Menu/Background_4.png");
    }

    public static void initLoadingScreen(){
        //logo
        logo=ImageLoader.loadImage("graphics/Main_Logo2.png");
        loadingCircle=ImageLoader.loadImage("graphics/Menu/loading.png");

        Ubuntu34=FontLoader.loadFont("fonts/Ubuntu-B.ttf",34);

        SpriteSheet loadingDots=new SpriteSheet(ImageLoader.loadImage("graphics/Menu/loadingSheet.png"));
        loadingDot[0]=loadingDots.crop(20,0,20,20);
        loadingDot[1]=loadingDots.crop(20,20,20,20);
        loadingDot[2]=loadingDots.crop(0,20,20,20);
        loadingDot[3]=loadingDots.crop(0,0,20,20);
    }


    //metoda obrotu grafik
    public static BufferedImage rotate(BufferedImage bimg, double angle) { //obrót BufferedImage

        int w = bimg.getWidth();
        int h = bimg.getHeight();

        BufferedImage rotated = new BufferedImage(w, h, bimg.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.rotate(Math.toRadians(angle), w/2, h/2);
        graphic.drawImage(bimg, null, 0, 0);
        graphic.dispose();
        return rotated;
    }

    //metoda zmieniająca przezroczystość grafik     na wyjsciu gotowy obraz
    public static BufferedImage opacity(BufferedImage image,float opacity){       //np opacity 0.5

        return image;       //czy coś tam typu BufferedImage
    }


}
