import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
public class Audio {
    public static void mainWindowMassage(){
        File file=new File("Welcometorccbankingsystem.wav");
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip=AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
}
