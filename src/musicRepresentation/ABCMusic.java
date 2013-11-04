package musicRepresentation;

import grammar.ABCMusicLexer;
import grammar.ABCMusicParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * This class consists of a representation of an .abc file, in a way that is
 * possible to extract information to be played with some kind of sequencer. For
 * now it gives information to be converted to MIDI. It gather information from
 * an .abc file by parsing it. It is an immutable class.
 * 
 * @author Nicholas M. Mizoguchi
 * 
 */
public class ABCMusic {
    private final ABCHeader header;
    private final Map<String, Voice> voiceMap;

    /**
     * Creates a representation of an ABC file as an ABCMusic Object.
     * @param path the .abc file to be interpreted.
     * @throws IOException in case the path or the file is invalid.
     */
    public ABCMusic(String path) throws IOException {
        // Opens file in path and get all the chars in the file
        String input = readFile(path);

        // Runs the parser in the stream of bytes received * from the file in

        // Create a stream of tokens using the lexer.
        CharStream stream = new ANTLRInputStream(input);
        ABCMusicLexer lexer = new ABCMusicLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);

        // Feed the tokens into the parser.
        ABCMusicParser parser = new ABCMusicParser(tokens);
        parser.reportErrorsAsExceptions();

        // Generate the parse tree using the starter rule.
        ParseTree tree;
        tree = parser.line(); // "line" is the starter rule.

        // Walk the tree with the listener.
        ParseTreeWalker walker = new ParseTreeWalker();
        ParseTreeListener listener = new ABCMusicParseListener();
        walker.walk(listener, tree);

        // ABCMusicParseListener() has methods to get all information from the AST
        this.voiceMap = ((ABCMusicParseListener) listener).getVoiceMap();
        this.header = ((ABCMusicParseListener) listener).getHeader();
    }

    /**
     * Transform the representation of the .abc file into information to be
     * passed to a SequencePlayer. It goes through all voices, returning the
     * representation of each note as a MidiNoteRepresentation in the list.
     * 
     * @return an instance of SequencerInformation
     */
    public List<SequencerInformation> constructSequencerInformation() {
        /*
         * Get all notes and syllables from ABCMusic as MidiNoteRep and
         * Syllables.
         * 
         * Strategy: For each voice, iterate through all the notes keeping track
         * of the StartTick. StartTick and the duration of each note is
         * calculated from the meter and the note length, found in the ABCHeader
         */

        List<SequencerInformation> info = new ArrayList<SequencerInformation>();

        // Creates the lists of voices by getting them from the map
        List<Voice> voices = new ArrayList<Voice>(voiceMap.values());

        // Define parameters
        int startTick = 0;
        int ticksPerBeat = header.getTicksPerBeat();
        Iterator<Voice> voicesIterator = voices.iterator();

        /*
         * Iterates through all voices in the piece creating a
         * sequencerInformation for each voice, puting each one as an element in
         * the list
         */
        while (voicesIterator.hasNext()) {
            startTick = 0;
            Voice currentVoice = voicesIterator.next();

            // Creates lists to store information to create the
            // SequencerInformation
            List<MidiNoteRepresentation> midiNotes = new ArrayList<MidiNoteRepresentation>();
            List<Syllable> lyrics = new ArrayList<Syllable>();

            Iterator<Measure> measuresIterator = currentVoice
                    .getListOfMeasures().iterator();

            // Iterates through all measures in the voice
            while (measuresIterator.hasNext()) {

                Measure currentMeasure = measuresIterator.next();
                Iterator<SoundUnit> soundUnitsIterator = currentMeasure
                        .getListOfSoundUnits().iterator();

                // Iterate through each SoundUnit in the measure
                while (soundUnitsIterator.hasNext()) {
                    SoundUnit sound = soundUnitsIterator.next();
                    // Calculate the equivalent of the note duration in number
                    // of ticks
                    int numTicks = (int) (sound.getDurationMultiplier()
                            * (header.getDefaultNoteLength() / header
                                    .getBpmNoteLength()) * ticksPerBeat);

                    // If it is a chord
                    if (sound instanceof Chord) {
                        // Build the lyrics list first
                        lyrics.add(new Syllable(((Chord) sound).getSyllable(),
                                startTick));

                        // Creates an iterator to iterate through each note
                        Iterator<Note> chordIterator = ((Chord) sound)
                                .getListOfNotesInChord().iterator();

                        // Add each note in chord
                        while (chordIterator.hasNext()) {
                            Note note = chordIterator.next();
                            MidiNoteRepresentation midiNote = new MidiNoteRepresentation(
                                    note.getPitch(), startTick, numTicks);
                            midiNotes.add(midiNote);
                        }
                    }

                    // If it is a rest just update startTick
                    startTick += numTicks;
                }
            }
            info.add(new SequencerInformation(currentVoice.getVoiceName(),
                    midiNotes, lyrics));
        }

        return info;
    }

    /**
     * Gives the Title of the piece of music ABCHeader represents
     * 
     * @return the title String itself.
     */
    public String getTitle() {
        return header.getTitle();
    }

    /**
     * Gives the Composer of the piece of music ABCHeader represents
     * 
     * @return the composer String itself.
     */
    public String getComposer() {
        return header.getComposer();
    }

    public int getBeatsPerMinute() {
        return header.getBeatsPerMinute();
    }

    public int getTicksPerBeat() {
        return header.getTicksPerBeat();
    }

    /**
     * Opens a file in <code>path</code>, giving a String of the file.
     * 
     * @param path
     *            The path where the file to be read is located.
     * @return A string with all the bytes in the file located in
     *         <code>path</code>.
     * @throws IOException
     *             if file not found, or any other issue with the file.
     */
    private String readFile(String path) throws IOException {
        // Read file
        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String stringLine = br.readLine();
        String buffer = "";

        while (stringLine != null) {
            buffer = buffer.concat(stringLine.concat("\n"));
            stringLine = br.readLine();
        }
        
        br.close();

        return buffer;
    }

    /**
     * Returns as a String a representation of the object.
     * @return String
     */
    @Override
    public String toString() {
        String s = this.header.toString() + "\n";
        List<Voice> voiceList = new ArrayList<Voice>(voiceMap.values());
        Iterator<Voice> iterator = voiceList.iterator();

        while (iterator.hasNext()) {
            Voice voice = iterator.next();
            s = s.concat(voice.toString() + "\n");
        }

        return s;
    }
}
