package GFX;

import ludogame.Handler;
import states.SettingState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DynamicBackground {


    private final int width;
    private final int height;

    private final BufferedImage[] dynamicBackground;

    private int ticks;      //dodac zeby nie renderowalo co klatke

    private double difference;

    private static int[] cluePoint;
    private final double[] position;
    private final boolean[] right;
    private boolean onOff = true;

    //wiecej logiki dodac jakies rng

    public DynamicBackground(Handler handler, int height){
        this.height=height;
        this.width=Assets.dynamicBackground[0].getWidth()*height/ Assets.dynamicBackground[0].getHeight();

        System.out.println(this.width+" "+ this.height);

        this.dynamicBackground=Assets.dynamicBackground;

        cluePoint=new int[2];
        cluePoint[0]=(200-this.width+handler.getFrameWidth());
        cluePoint[1]=-200;

        this.position=new double[2];
        this.position[0]=cluePoint[0]+1;
        this.position[1]=cluePoint[1]-1;

        this.ticks=0;

        this.right=new boolean[2];
        this.right[0]=true;
    }



    public void tick() {

        if (SettingState.DYNAMIC_BACKGROUND) {

            difference = (20 / (double) SettingState.FPS);

            moveBackgroundLogic(0);
            moveBackgroundLogic(1);
            ticks++;
            if (ticks == 2)
                ticks = 0;
        }

    }

    public void render(Graphics g){

            g.drawImage(dynamicBackground[0], (int)position[0], 0, width, height, null);
            g.drawImage(dynamicBackground[1], (int)position[1], 0, width, height, null);



    }

    private void moveBackgroundLogic(int i){
        if((int)position[i]==cluePoint[0]||(int)position[i]==cluePoint[1]){

            //follow

            changeDirection(i);
        }

        if (right[i])
            position[i]+=difference;
        else
            position[i]-=difference;

    }

    private void changeDirection(int i){
        right[i]= !right[i];
    }

	public boolean isOnOff() {
		return onOff;
	}

	public void setOnOff(boolean onOff) {
		this.onOff = onOff;
	}

}
