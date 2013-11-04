package player;

import java.io.IOException;

/**
 * Main entry point of your application.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        
       String abcFilesPath = "sample_abc/";
       KaraokeController karaoke = new KaraokeController(abcFilesPath);
       
       karaoke.run();
       
       return;
    }
}
