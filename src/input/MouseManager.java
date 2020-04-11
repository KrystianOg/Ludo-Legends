package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseManager implements MouseListener {

    private int x,y;
    private boolean loop;

    public MouseManager (){
        this.x=-1;
        this.y=-1;
    }

    public void tick(){
        System.out.println("X: "+getX()+"\t\tY: "+getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(x!=e.getPoint().x&&y!=e.getPoint().y) {
            setX(e.getPoint().x);
            setY(e.getPoint().y);
        }
    }

    public int getX() {
        return x;
    }

    public void setLoop(boolean set){
        this.loop=set;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void reset(){
        this.y=-1;
        this.x=-1;

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }



    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
