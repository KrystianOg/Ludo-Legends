package GFX;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Assets {


    private static final int p_width=41,p_height=78,r_width=75,r_height=75, b_width=200, b_height=60;
    public static BufferedImage roll_1,roll_2,roll_3,roll_4,roll_5,roll_6;
    public static BufferedImage counter_y,counter_b,counter_r,counter_g;
    public static BufferedImage sword,cloak_b,cloak_f;
    public static BufferedImage ultimate_bar_b, ultimate_bar_g, ultimate_bar_r, ultimate_bar_y;

    public static BufferedImage map;


    public static void init(){
        SpriteSheet roll=new SpriteSheet(ImageLoader.loadImage("graphics/Dice/Roll.png"));
        SpriteSheet player=new SpriteSheet(ImageLoader.loadImage("graphics/Counters/Pionki.png"));
        SpriteSheet ultimate_bar=new SpriteSheet(ImageLoader.loadImage("graphics/Ultimate_Bar_Sheet.png"));

        map=ImageLoader.loadImage("graphics/Map.png");
        sword=ImageLoader.loadImage("graphics/Counters/Sword1.png");
        cloak_b=ImageLoader.loadImage("graphics/Counters/Cloak_Back.png");
        cloak_f=ImageLoader.loadImage("graphics/Counters/Cloak_Front.png");


        roll_1=roll.crop(0,0,r_width,r_height);
        roll_2=roll.crop(r_width,0,r_width,r_height);
        roll_3=roll.crop(r_width*2,0,r_width,r_height);
        roll_4=roll.crop(0,r_height,r_width,r_height);
        roll_5=roll.crop(r_width,r_height,r_width,r_height);
        roll_6=roll.crop(r_width*2,r_height,r_width,r_height);


        counter_g=player.crop(0,0,p_width,p_height);
        counter_y=player.crop(p_width,0,p_width,p_height);
        counter_r=player.crop(0,p_height,p_width,p_height);
        counter_b=player.crop(p_width,p_height,p_width,p_height);

        ultimate_bar_b=ultimate_bar.crop(0, 0, b_width, b_height);
        ultimate_bar_g=ultimate_bar.crop(b_width, 0, b_width, b_height);        
        ultimate_bar_r=ultimate_bar.crop(0, b_height, b_width, b_height);
        ultimate_bar_y=ultimate_bar.crop(b_width, b_height, b_width, b_height);
    }

}
