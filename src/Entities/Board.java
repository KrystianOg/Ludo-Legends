package Entities;

import GFX.Assets;

import java.awt.*;

public class Board extends Entity {

    Tile[][] map=new Tile[9][];

    public Board(int x, int y, int width, int height) {
        super(x, y, width, height);

        setMap();

    }






    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.map,0,0,null);
    }

    private void setMap(){

        int x=50, y=50;

        map[0]=new Tile[52];

        map[0][0]=new Tile(x*8,y*1,2,true);
        map[0][1]=new Tile(x*8,y*2,2,false);
        map[0][2]=new Tile(x*8,y*3,2,true);
        map[0][3]=new Tile(x*8,y*4,2,true);
        map[0][4]=new Tile(x*8,y*5,2,true);
        map[0][5]=new Tile(x*8,y*6,5,true);
        map[0][6]=new Tile(x*9,y*7,1,true);
        map[0][7]=new Tile(x*10,y*7,1,true);
        map[0][8]=new Tile(x*11,y*7,1,true);
        map[0][9]=new Tile(x*12,y*7,1,false);
        map[0][10]=new Tile(x*13,y*7,1,true);
        map[0][11]=new Tile(x*14,y*7,2,true);
        map[0][12]=new Tile(x*14,y*8,2,true);
        map[0][13]=new Tile(x*14,y*9,3,true);
        map[0][14]=new Tile(x*13,y*9,3,false);
        map[0][15]=new Tile(x*12,y*9,3,true);
        map[0][16]=new Tile(x*11,y*9,3,true);
        map[0][17]=new Tile(x*10,y*9,3,true);
        map[0][18]=new Tile(x*9,y*9,6,true);
        map[0][19]=new Tile(x*8,y*10,2,true);
        map[0][20]=new Tile(x*8,y*11,2,true);
        map[0][21]=new Tile(x*8,y*12,2,true);
        map[0][22]=new Tile(x*8,y*13,2,false);
        map[0][23]=new Tile(x*8,y*14,2,true);
        map[0][24]=new Tile(x*8,y*15,3,true);
        map[0][25]=new Tile(x*7,y*15,3,true);
        map[0][26]=new Tile(x*6,y*15,0,true);
        map[0][27]=new Tile(x*6,y*14,0,false);
        map[0][28]=new Tile(x*6,y*13,0,true);
        map[0][29]=new Tile(x*6,y*12,0,true);
        map[0][30]=new Tile(x*6,y*11,0,true);
        map[0][31]=new Tile(x*6,y*10,7,true);
        map[0][32]=new Tile(x*5,y*9,3,true);
        map[0][33]=new Tile(x*4,y*9,3,true);
        map[0][34]=new Tile(x*3,y*9,3,true);
        map[0][35]=new Tile(x*2,y*9,3,true);
        map[0][36]=new Tile(x*1,y*9,3,false);
        map[0][37]=new Tile(x*0,y*9,0,true);
        map[0][38]=new Tile(x*0,y*8,0,true);
        map[0][39]=new Tile(x*0,y*7,1,true);
        map[0][40]=new Tile(x*1,y*7,1,true);
        map[0][41]=new Tile(x*2,y*7,1,false);
        map[0][42]=new Tile(x*3,y*7,1,true);
        map[0][43]=new Tile(x*4,y*7,1,true);
        map[0][44]=new Tile(x*5,y*7,4,true);
        map[0][45]=new Tile(x*6,y*6,0,true);
        map[0][46]=new Tile(x*6,y*5,0,true);
        map[0][47]=new Tile(x*6,y*4,0,true);
        map[0][48]=new Tile(x*6,y*3,0,true);
        map[0][49]=new Tile(x*6,y*2,0,false);
        map[0][50]=new Tile(x*6,y*1,1,true);
        map[0][51]=new Tile(x*6,y*1,1,true);

        map[1]=new Tile[5];
        map[1][0]=new Tile(x*7,y*2,2,true);
        map[1][1]=new Tile(x*7,y*3,2,true);
        map[1][2]=new Tile(x*7,y*3,2,true);
        map[1][3]=new Tile(x*7,y*4,2,true);
        map[1][4]=new Tile(x*7,y*5,2,true);

        map[2]=new Tile[5];
        map[2][0]=new Tile(x*13,y*8,3,true);
        map[2][1]=new Tile(x*12,y*8,3,true);
        map[2][2]=new Tile(x*11,y*8,3,true);
        map[2][3]=new Tile(x*10,y*8,3,true);
        map[2][4]=new Tile(x*9,y*8,3,true);

        map[3]=new Tile[5];
        map[3][0]=new Tile(x*7,y*14,0,true);
        map[3][1]=new Tile(x*7,y*13,0,true);
        map[3][2]=new Tile(x*7,y*12,0,true);
        map[3][3]=new Tile(x*7,y*11,0,true);
        map[3][4]=new Tile(x*7,y*10,0,true);

        map[4]=new Tile[5];
        map[4][0]=new Tile(x*1,y*8,1,true);
        map[4][1]=new Tile(x*2,y*8,1,true);
        map[4][2]=new Tile(x*3,y*8,1,true);
        map[4][3]=new Tile(x*4,y*8,1,true);
        map[4][4]=new Tile(x*5,y*8,1,true);

        map[5]=new Tile[4];
        map[5][0]=new Tile(400,50,true);
        map[5][1]=new Tile(400,50,true);
        map[5][2]=new Tile(400,50,true);
        map[5][3]=new Tile(400,50,true);

        map[6]=new Tile[4];
        map[6][0]=new Tile(400,50,true);
        map[6][1]=new Tile(400,50,true);
        map[6][2]=new Tile(400,50,true);
        map[6][3]=new Tile(400,50,true);

        map[7]=new Tile[4];
        map[7][0]=new Tile(400,50,true);
        map[7][1]=new Tile(400,50,true);
        map[7][2]=new Tile(400,50,true);
        map[7][3]=new Tile(400,50,true);

        map[8]=new Tile[4];
        map[8][0]=new Tile(400,50,true);
        map[8][1]=new Tile(400,50,true);
        map[8][2]=new Tile(400,50,true);
        map[8][3]=new Tile(400,50,true);
    }

}

