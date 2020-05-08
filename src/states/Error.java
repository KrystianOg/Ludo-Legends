package states;

import GFX.Assets;
import GFX.Text;

import java.awt.*;

    class Error {


        private boolean occured;
        private String errorText;
        private Color redOp;
        private int opacity;

        private int count;
        private int defx, defy,x,y;


        public Error(int x,int y, String errorText) {
            defx=x;
            defy=y;
            this.x=x;
            this.y = y;
            this.errorText = errorText;

            redOp=new Color(201,0,1,255);
            occured = false;
            count = 0;
            opacity = 255;
        }

        public void tick() {
            if (occured) {
                count++;
                if (count >= SettingState.FPS * 4) {
                    redOp = new Color(201, 0, 1, opacity -= 3);

                    if (opacity <= 2) {
                        opacity = 255;
                        count = 0;
                        redOp = new Color(201, 0, 1, opacity);
                        occured = false;
                        x=defx;
                        y=defy;
                    }
                }
            }
        }

        public void render(Graphics g) {
            if(occured)
            Text.drawString(g, errorText, x, y, true, redOp, Assets.Ubuntu40);
        }

        public void setOccured() {
            count = 0;
            opacity = 255;
            redOp = new Color(201, 0, 1, 255);
            occured = true;
        }

        public int getX(){
            return this.x;
        }

        public void setX(int x){
            this.x=x;
        }

        public int getY(){
            return this.y;
        }

        public void setY(int y){
            this.y=y+40;
        }

        public boolean getOccured(){
            return this.occured;
        }
    }
