package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener { //poprawić + usunac gettery i settery

    private int x,y;
    private boolean leftPressed, rightPressed;

    private int hoverx,hovery;
    
    private int wheel;

    public MouseManager (){
        this.x=-1;
        this.y=-1;
    }

    
    public void reset(){
        this.y=-1;
        this.x=-1;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            leftPressed = true;
        else if (e.getButton() == MouseEvent.BUTTON3)
        	rightPressed = true;

        if(x!=e.getPoint().x&&y!=e.getPoint().y) {

            this.x=e.getPoint().x;
            this.y=e.getPoint().y;
            }
    }

    public boolean isLeftPressed() {
    	return leftPressed;
    }
    
    public boolean isRightPressed() {
    	return rightPressed;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    	if(e.getButton() == MouseEvent.BUTTON1)
            leftPressed = false;
        else if (e.getButton() == MouseEvent.BUTTON3)
        	rightPressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


	@Override
	public void mouseDragged(MouseEvent e) {
		this.hoverx=e.getX();
        this.hovery=e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
        this.hoverx=e.getX();
        this.hovery=e.getY();
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		wheel+=e.getWheelRotation();

	}

    public int getHoverX(){
        return this.hoverx;
    }

    public int getHoverY(){
        return this.hovery;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

	public int getWheel() {
		return wheel;
	}

	public void setWheel(int wheel) {
		this.wheel = wheel;
	}

}
