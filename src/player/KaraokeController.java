package player;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import musicRepresentation.ABCMusic;
import musicRepresentation.MidiNoteRepresentation;
import musicRepresentation.SequencerInformation;
import musicRepresentation.Syllable;
import sound.LyricListener;
import sound.SequencePlayer;

/**
 * A simple implementation of a karaoke. Uses MIDI format to play music, and
 * uses the terminal as the user interface. Joins information of abc files and
 * MIDI. Have control over the basic flow of a karaoke player. Immutable.
 * @author Nicholas M. Mizoguchi
 * 
 */
public class KaraokeController {

    private final String musicFolder;
    
    /**
     * Constructor
     * @param folder the path to the folder where the .abc files are located.
     */
    public KaraokeController(String folder) {
        musicFolder = folder;
    }
    
    /**
     * Starts the karaoke controller. Gives the option to the user to select musics to play.
     * Accepts only positive integers that are mapping an .abc file, and it stops running
     * if the users type an empty string (pressing enter).
     */
    public void run() {
      
        String files;
        File folder = new File(musicFolder);
        File[] listOfFiles = folder.listFiles(); 
       
        System.out.println("\nList of available files:\n");
        
        // List all files in musicFolder that has .abc extension
        for (int i = 0; i < listOfFiles.length; i++) 
        {
       
         if (listOfFiles[i].isFile()) {
             
             files = listOfFiles[i].getName();
             if (files.endsWith(".abc") || files.endsWith(".ABC")) {
                System.out.println(i+": "+files);
              }
           }
        }
        
        // Asks the user a file number to be played.
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String inputData = "";
        do {
            System.out.print("\n\nChoose a file number to play: ");
            
            // Reads an input from the user. If invalid, asks for a valid input again
            try {
                inputData = in.readLine();
                // We avoid non-numerical strings, and also strings representing integers that do not
                // match an index on the listOfFiles
                if (!inputData.equals("") 
                        && Pattern.matches("[0-9]+", inputData)
                        && 0 <= Integer.parseInt(inputData)
                        && Integer.parseInt(inputData) < listOfFiles.length) {
                    
                    play(musicFolder + listOfFiles[Integer.parseInt(inputData)].getName());
                }
            } catch (IOException e) {
                System.out.println("Invalid input!");
                continue;
            } catch (RuntimeException re) {
                // we can treat for exceptions here!
                System.err.println(re.getClass().getName() + ": " + re.getMessage());
            }

        // Empty input terminates the do-loop.
        } while (!inputData.equals(""));
        
        System.out.print("\nThank you for singing with us :)");
    }
    
    /**
     * Plays the input file using Java MIDI API and displays header information
     * to the standard output stream. It opens file, represent it in a playable
     * format, and then starts playing. This method use a default listener to
     * treat lyrics (print it to the terminal).
     * @param file The name of input abc file
     */
    public static void play(String file) {

        ABCMusic myTune;
        try {
            myTune = new ABCMusic(file);
        } catch (IOException e) {
            System.out.println("File " + file + " not found!");
            return;
        }

        List<SequencerInformation> sequencerTracks = myTune
                .constructSequencerInformation();

        // Create a new player, with 120 beats per minute, 2 ticks per beat
        // and a LyricListener that prints each lyric that it sees.
        LyricListener listener = new LyricListener() {
            private int counter = 0;
            private int lyricsPerLine = 18;

            public void processLyricEvent(String text) {
                counter = (counter + 1) % lyricsPerLine;
                if (counter == 0)
                    System.out.println("");
                System.out.print(text);
            }
        };

        SequencePlayer player;
        try {
            player = new SequencePlayer(myTune.getBeatsPerMinute(),
                    myTune.getTicksPerBeat(), listener);
        } catch (MidiUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        } catch (InvalidMidiDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }

        /*
         * Adding all midiNoteRepresentation as MidiNotes. For that, we need to
         * get each MidiNoteRepresentation from each voice, represented as an
         * element of SequencerInformation in sequencerTracks.
         */
        
        // Printing on Console: music title and composer
        System.out.println("Title: "+myTune.getTitle());
        System.out.println("Composer: "+myTune.getComposer());
        
        
        Iterator<SequencerInformation> seqIterator = sequencerTracks.iterator();
        
        while(seqIterator.hasNext()){
            SequencerInformation sequencerInfo = seqIterator.next();

            // Adding all MidiNoteRepresentations as MidiNotes
            for (MidiNoteRepresentation n : sequencerInfo.getMidiNotes())
                player.addNote(n.getPitch().toMidiNote(), n.getStartTick(),
                        n.getNumTicks());
        
            // Adding all Syllables
            for (Syllable s : sequencerInfo.getSyllables())
                player.addLyricEvent(s.getSyllable(), s.getStartTick());
        }
        
        // PLAY
        try {
            player.play();
        } catch (MidiUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
    }
}
