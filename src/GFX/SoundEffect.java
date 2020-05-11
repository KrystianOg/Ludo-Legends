package GFX;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class SoundEffect {

    public static void play(String sound) {

        try {
            InputStream in=SoundEffect.class.getResourceAsStream("/sounds/"+sound+".wav");
            AudioStream as=new AudioStream(in);
            AudioPlayer.player.start(as);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
