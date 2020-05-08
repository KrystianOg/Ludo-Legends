package Entities.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import Entities.Entity;
import Entities.Counters.Albali;
import Entities.Counters.Funi;
import Entities.Counters.Intan;
import Entities.Counters.Mira;
import Entities.Counters.Polaris;
import Entities.Counters.Samaya;
import Entities.Counters.Saph;
import Entities.Counters.Venator;
import GFX.Assets;
import GFX.Description;
import GFX.Text;
import ludogame.Handler;

public class InfoTile extends Entity{
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int counter;
	
    private final CounterTile[] counterTile;
    
    private final Color grayOp=new Color(38,38,38,180);

	public InfoTile(Handler handler, int x, int y, int width, int height, int counter) {
		super(handler, x, y, width, height);
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.counter=counter;

		
        counterTile=new CounterTile[8];
        counterTile[0]=new CounterTile(handler,x+10,y+10,Albali.CLOAK_POSX,Albali.CLOAK_POSY,Assets.counter[0],Assets.cloak_f,Assets.cloak_b);
        counterTile[1]=new CounterTile(handler,x+10,y+10, Funi.WAND_POSX,Funi.WAND_POSY, Assets.counter[0],Assets.wand);
        counterTile[2]=new CounterTile(handler,x+10,y+10, Intan.SHIELD_POSX,Intan.SHIELD_POSY,Assets.counter[0],Assets.shield);
        counterTile[3]=new CounterTile(handler,x+10,y+10,Mira.MEDKIT_POSX,Mira.MEDKIT_POSY,Assets.counter[0],Assets.medkit);
        counterTile[4]=new CounterTile(handler,x+10,y+10, Polaris.ARMORF_X,Polaris.ARMORF_Y,Assets.counter[0],Assets.armor_f[0]);
        counterTile[5]=new CounterTile(handler,x+10,y+10,Samaya.SWAN_X,Samaya.SWAN_Y,Assets.counter[0],Assets.swan);
        counterTile[6]=new CounterTile(handler,x+10,y+10,0, Saph.SWORD_POSY,Assets.counter[0],Assets.sword);
        counterTile[7]=new CounterTile(handler,x+10,y+10,Venator.BOWR_FX,Venator.BOWR_FY,Assets.counter[0],Assets.bow_rf);
	}

	@Override
	public void tick() {

		
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2=(Graphics2D)g;
		g2.setPaint(grayOp);
		g2.fill(new RoundRectangle2D.Double(x,y,width,height,20,20));
		counterTile[counter].render(g);

		Description.initTextCounterTile(counter);
		for(int i=0;i<9;i++) {          		
    		Text.drawString(g,Description.textCounterTile[i],x+110,y+22+i*14,false,Color.white,Assets.Ubuntu12);
    	}
        Text.drawString(g,"advantages:",x+70,y+148,false,Color.green,Assets.Ubuntu12);
        Text.drawString(g,"disadvantages:",x+210,y+148,false,Color.red,Assets.Ubuntu12);
		Text.drawString(g,Description.advantages,x+70,y+160,false,Color.green,Assets.Ubuntu12);
		Text.drawString(g,Description.disadvantages,x+180,y+160,false,Color.red,Assets.Ubuntu12);

	}

}
