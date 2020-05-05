package display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Display {

    private JFrame frame;

    private Canvas canvas;

    private final String title;
    private static int width,height;


    public Display(String title,int width,int height){
        this.title=title;
        Display.width =width;
        Display.height =height;

        createDisplay();
    }

    private void createDisplay(){
        frame=new JFrame(title);
        frame.setSize(width,height);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            Image img=ImageIO.read(getClass().getResourceAsStream("/graphics/Main_Logo.png"));
            frame.setIconImage(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //frame.setIconImage(Toolkit.getDefaultToolkit().getImage("/graphics/Main_Logo.png"));
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.white);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas=new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas(){
        return this.canvas;
    }

    public JFrame getFrame(){
        return frame;
    }

}
