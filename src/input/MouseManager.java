package input;

import java.awt.event.*;

public class MouseManager implements MouseListener, MouseMotionListener,MouseWheelListener{ //poprawić + usunac gettery i settery

    private int x,y;
    private boolean leftPressed, rightPressed;

    private int hoverx,hovery;

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

    /**
     * Invoked when the mouse wheel is rotated.
     *
     * @param e
     * @see MouseWheelEvent
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        System.out.println("scrollowanko");

            if (e.getWheelRotation() < 0) {
                System.out.println("Rotated Up... " + e.getWheelRotation());
            } else {
                System.out.println("Rotated Down... " + e.getWheelRotation());
            }

    }


}
