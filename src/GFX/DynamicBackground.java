package GFX;

import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DynamicBackground {


    private final int width;
    private final int height;

    private final BufferedImage[] dynamicBackground;

    private int ticks;      //dodac zeby nie renderowalo co klatke

    private int leftBezel,rightBezel;

    private final int[] cluePoint;
    private final int[] follow;
    private final int[] position;
    private final boolean[] right;

    //wiecej logiki dodac jakies rng

    public DynamicBackground(Handler handler, int height){
        this.dynamicBackground=Assets.dynamicBackground;
        this.height=height;
        this.width=height/dynamicBackground[0].getHeight()*dynamicBackground[0].getWidth();


        this.cluePoint=new int[2];
        this.cluePoint[0]=(70-this.width-handler.getFrameWidth());
        this.cluePoint[1]=-70;

        this.position=new int[2];
        this.position[0]=cluePoint[0]+1;
        this.position[1]=cluePoint[1]-1;

        this.follow=new int[2];

        this.right=new boolean[2];
        this.right[0]=true;
        this.right[1]=false;

    }

    public void tick(){
        moveBackgroundLogic(0);
        moveBackgroundLogic(1);
    }

    public void render(Graphics g){
        g.drawImage(dynamicBackground[0],position[0],-5,null);
        g.drawImage(dynamicBackground[1],position[1],-5,null);
    }

    private void moveBackgroundLogic(int i){

        if(position[i]==cluePoint[0]||position[i]==cluePoint[1]){
            changeDirection(i);
        }

        if(right[i])
            position[i]++;
        else
            position[i]--;
    }

    private void changeDirection(int i){
        right[i]= !right[i];
    }

}
