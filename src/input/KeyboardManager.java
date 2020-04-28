package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardManager implements KeyListener {

    private char key;

    private boolean del=false;
    public KeyboardManager(){

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if((int)e.getKeyChar()==8)
            del=true;
        else{
            key = e.getKeyChar();
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    public String getChar(){
        if(key=='\0')
            return "";
        else
            return String.valueOf(key);
    }

    public boolean getDelete(){
        return del;
    }

    public void resetKey(){
        key='\0';
        del=false;
    }

    public static String deleteLastChar(String s){
        if(s==null||s.length()==0)
            return null;
        else
            return s.substring(0,s.length()-1);
    }

}
