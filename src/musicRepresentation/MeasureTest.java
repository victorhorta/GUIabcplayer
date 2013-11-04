package musicRepresentation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import sound.Pitch;

/**
 * This is the test suite for the Measure class.
 * 
 * @author Victor
 * 
 */
public class MeasureTest {
     
    /**
     * Constructor test strategy:
     * - keySignature
     *      major, minor, enharmonic [http://hymns.reactor-core.org/keysignatures.html]
     * - chordsAndRestsInMeasure
     *      size 1, size >1
     *      containing Rests and/or Chords
     * - endingBarLine
     *      length 1, length 2
     * 
     */
    @SuppressWarnings("serial")
    @Test
    public void testMeasure_majorKeySignature_hasSingleBar_hasRestInTheEnd(){
        // Initializing guineaPig objects -----------------------------
        
        // These are the pitches that we will offer as input: C, Cb, C, C=, C.
        // We will pass the Dmajor key (C#,F#).
        // We expect to have as an output: C#, Cb, Cb, C=, C=.
        String keyValue = "D";
        String barline = "|";
        String expectedBarlineOutput = "|";
        
        Pitch pitch1in = new Pitch('C',  0, 0, false);  // C
        Pitch pitch2in = new Pitch('C', -1, 0, true);   // Cb
        Pitch pitch3in = new Pitch('C',  0, 0, false);  // C
        Pitch pitch4in = new Pitch('C',  0, 0, true);   // C=
        Pitch pitch5in = new Pitch('C',  0, 0, false);  // C
        
        Pitch pitch1out = new Pitch('C',  1, 0, false); // C#
        Pitch pitch2out = new Pitch('C', -1, 0, true);  // Cb
        Pitch pitch3out = new Pitch('C', -1, 0, true);  // Cb
        Pitch pitch4out = new Pitch('C',  0, 0, true);  // C=
        Pitch pitch5out = new Pitch('C',  0, 0, true);  // C=
        
        // These are final in order to enable the inline block statement     
        final Note note1in = new Note(pitch1in);
        final Note note2in = new Note(pitch2in, 1.1);
        final Note note3in = new Note(pitch3in);
        final Note note4in = new Note(pitch4in, 2.2);
        final Note note5in = new Note(pitch5in);
        final Rest rest6in = new Rest(0.123);
        
        final Note note1out = new Note(pitch1out);
        final Note note2out = new Note(pitch2out, 1.1);
        final Note note3out = new Note(pitch3out);
        final Note note4out = new Note(pitch4out, 2.2);
        final Note note5out = new Note(pitch5out);
        final Rest rest6out = new Rest(0.123);
        
        // Inline declaration
        Chord chord1in = new Chord(new ArrayList<Note>(){{add(note1in);}});
        Chord chord2in = new Chord(new ArrayList<Note>(){{add(note2in);}});
        Chord chord3in = new Chord(new ArrayList<Note>(){{add(note3in);}});
        Chord chord4in = new Chord(new ArrayList<Note>(){{add(note4in);}});
        Chord chord5in = new Chord(new ArrayList<Note>(){{add(note5in);}});
        
        Chord chord1out = new Chord(new ArrayList<Note>(){{add(note1out);}});
        Chord chord2out = new Chord(new ArrayList<Note>(){{add(note2out);}});
        Chord chord3out = new Chord(new ArrayList<Note>(){{add(note3out);}});
        Chord chord4out = new Chord(new ArrayList<Note>(){{add(note4out);}});
        Chord chord5out = new Chord(new ArrayList<Note>(){{add(note5out);}});
        
        List<SoundUnit> guineaPigSoundUnits = new ArrayList<SoundUnit>();
        guineaPigSoundUnits.add(chord1in);
        guineaPigSoundUnits.add(chord2in);
        guineaPigSoundUnits.add(chord3in);
        guineaPigSoundUnits.add(chord4in);
        guineaPigSoundUnits.add(chord5in);
        guineaPigSoundUnits.add(rest6in);
        
        List<SoundUnit> expectedOutputSoundUnits = new ArrayList<SoundUnit>();
        expectedOutputSoundUnits.add(chord1out);
        expectedOutputSoundUnits.add(chord2out);
        expectedOutputSoundUnits.add(chord3out);
        expectedOutputSoundUnits.add(chord4out);
        expectedOutputSoundUnits.add(chord5out);
        expectedOutputSoundUnits.add(rest6out);
        
        KeySignature guineaPigKey = new KeySignature(keyValue);
        
        Measure guineaPigMeasure = new Measure(guineaPigKey, guineaPigSoundUnits, barline);
        
        // Checking chords individually
        assertEquals(guineaPigMeasure.getListOfSoundUnits().get(0).equals(chord1out), true);
        assertEquals(guineaPigMeasure.getListOfSoundUnits().get(1).equals(chord2out), true);
        assertEquals(guineaPigMeasure.getListOfSoundUnits().get(2).equals(chord3out), true);
        assertEquals(guineaPigMeasure.getListOfSoundUnits().get(3).equals(chord4out), true);
        assertEquals(guineaPigMeasure.getListOfSoundUnits().get(4).equals(chord5out), true);
        assertEquals(guineaPigMeasure.getListOfSoundUnits().get(5).equals(rest6out), true);
        
        // Checking the whole list
        assertEquals(guineaPigMeasure.getListOfSoundUnits().equals(expectedOutputSoundUnits), true);
        
        // Checking the barline
        assertEquals(guineaPigMeasure.getEndingBarLine().equals(expectedBarlineOutput), true);
    }
    
    @SuppressWarnings("serial")
    @Test
    public void testMeasure_minorKeySignature_hasDoubledBar_hasRestInTheMiddle(){
        // Initializing guineaPig objects -----------------------------
        
        // These are the pitches that we will offer as input: A, A#, A, A=, A.
        // We will pass the Cminor key (Ab,Bb,Eb).
        // We expect to have as an output: Ab, A#, A#, A=, A=.
        String keyValue = "Cm";
        String barline = ":|";
        String expectedBarlineOutput = ":|";
        
        Pitch pitch1in = new Pitch('A',  0, 0, false);  // A
        Pitch pitch2in = new Pitch('A',  1, 0, true);   // A#
        Pitch pitch3in = new Pitch('A',  0, 0, false);  // A
        Pitch pitch5in = new Pitch('A',  0, 0, true);   // A=
        Pitch pitch6in = new Pitch('A',  0, 0, false);  // A
        
        Pitch pitch1out = new Pitch('A', -1, 0, false); // Ab
        Pitch pitch2out = new Pitch('A',  1, 0, true);  // A#
        Pitch pitch3out = new Pitch('A',  1, 0, true);  // A#
        Pitch pitch5out = new Pitch('A',  0, 0, true);  // A=
        Pitch pitch6out = new Pitch('A',  0, 0, true);  // A=
        
        // These are final in order to enable the inline block statement     
        final Note note1in = new Note(pitch1in);
        final Note note2in = new Note(pitch2in);
        final Note note3in = new Note(pitch3in);
        final Rest rest4in = new Rest(0.123);
        final Note note5in = new Note(pitch5in);
        final Note note6in = new Note(pitch6in, 1.1);
        
        final Note note1out = new Note(pitch1out);
        final Note note2out = new Note(pitch2out);
        final Note note3out = new Note(pitch3out);
        final Rest rest4out = new Rest(0.123);
        final Note note5out = new Note(pitch5out);
        final Note note6out = new Note(pitch6out, 1.1);
        
        // Inline declaration
        Chord chord1in = new Chord(new ArrayList<Note>(){{add(note1in);}});
        Chord chord2in = new Chord(new ArrayList<Note>(){{add(note2in);}});
        Chord chord3in = new Chord(new ArrayList<Note>(){{add(note3in);}});
        Chord chord5in = new Chord(new ArrayList<Note>(){{add(note5in);}});
        Chord chord6in = new Chord(new ArrayList<Note>(){{add(note6in);}});
        
        Chord chord1out = new Chord(new ArrayList<Note>(){{add(note1out);}});
        Chord chord2out = new Chord(new ArrayList<Note>(){{add(note2out);}});
        Chord chord3out = new Chord(new ArrayList<Note>(){{add(note3out);}});
        Chord chord5out = new Chord(new ArrayList<Note>(){{add(note5out);}});
        Chord chord6out = new Chord(new ArrayList<Note>(){{add(note6out);}});
        
        List<SoundUnit> guineaPigSoundUnits = new ArrayList<SoundUnit>();
        guineaPigSoundUnits.add(chord1in);
        guineaPigSoundUnits.add(chord2in);
        guineaPigSoundUnits.add(chord3in);
        guineaPigSoundUnits.add(rest4in);
        guineaPigSoundUnits.add(chord5in);
        guineaPigSoundUnits.add(chord6in);
        
        List<SoundUnit> expectedOutputSoundUnits = new ArrayList<SoundUnit>();
        expectedOutputSoundUnits.add(chord1out);
        expectedOutputSoundUnits.add(chord2out);
        expectedOutputSoundUnits.add(chord3out);
        expectedOutputSoundUnits.add(rest4out);
        expectedOutputSoundUnits.add(chord5out);
        expectedOutputSoundUnits.add(chord6out);
        
        KeySignature guineaPigKey = new KeySignature(keyValue);
        
        Measure guineaPigMeasure = new Measure(guineaPigKey, guineaPigSoundUnits, barline);
        
        assertEquals(guineaPigMeasure.getListOfSoundUnits().get(0).equals(chord1out), true);
        assertEquals(guineaPigMeasure.getListOfSoundUnits().get(1).equals(chord2out), true);
        assertEquals(guineaPigMeasure.getListOfSoundUnits().get(2).equals(chord3out), true);
        assertEquals(guineaPigMeasure.getListOfSoundUnits().get(3).equals(rest4out), true);
        assertEquals(guineaPigMeasure.getListOfSoundUnits().get(4).equals(chord5out), true);
        assertEquals(guineaPigMeasure.getListOfSoundUnits().get(5).equals(chord6out), true);
        
        // Checking the whole list
        assertEquals(guineaPigMeasure.getListOfSoundUnits().equals(expectedOutputSoundUnits), true);
        
        // Checking the barline
        assertEquals(guineaPigMeasure.getEndingBarLine().equals(expectedBarlineOutput), true);
    }
    
    @SuppressWarnings("serial")
    @Test
    public void testMeasure_enharmonicKeySignature(){
        // Initializing guineaPig objects -----------------------------
        
        // These are the pitches that we will offer as input: C, Cb, C, C=, C.
        // We will pass the Bminor key (C#,F#) - it is a Dmajor enharmonic!
        // We expect to have as an output: C#, Cb, Cb, C=, C=.
        String keyValue = "Bm";
        String barline = "|";
        
        Pitch pitch1in = new Pitch('C',  0, 0, false); // C
        Pitch pitch2in = new Pitch('C', -1, 0, true);  // Cb
        Pitch pitch3in = new Pitch('C',  0, 0, false); // C
        Pitch pitch4in = new Pitch('C',  0, 0, true);  // C=
        Pitch pitch5in = new Pitch('C',  0, 0, false); // C
        
        Pitch pitch1out = new Pitch('C',  1, 0, false); // C#
        Pitch pitch2out = new Pitch('C', -1, 0, true);  // Cb
        Pitch pitch3out = new Pitch('C', -1, 0, true);  // Cb
        Pitch pitch4out = new Pitch('C',  0, 0, true);  // C=
        Pitch pitch5out = new Pitch('C',  0, 0, true);  // C=
        
        // These are final in order to enable the inline block statement     
        final Note note1in = new Note(pitch1in);
        final Note note2in = new Note(pitch2in);
        final Note note3in = new Note(pitch3in);
        final Note note4in = new Note(pitch4in);
        final Note note5in = new Note(pitch5in);
        
        final Note note1out = new Note(pitch1out);
        final Note note2out = new Note(pitch2out);
        final Note note3out = new Note(pitch3out);
        final Note note4out = new Note(pitch4out);
        final Note note5out = new Note(pitch5out);
        
        // Inline declaration
        Chord chord1in = new Chord(new ArrayList<Note>(){{add(note1in);}});
        Chord chord2in = new Chord(new ArrayList<Note>(){{add(note2in);}});
        Chord chord3in = new Chord(new ArrayList<Note>(){{add(note3in);}});
        Chord chord4in = new Chord(new ArrayList<Note>(){{add(note4in);}});
        Chord chord5in = new Chord(new ArrayList<Note>(){{add(note5in);}});
        
        Chord chord1out = new Chord(new ArrayList<Note>(){{add(note1out);}});
        Chord chord2out = new Chord(new ArrayList<Note>(){{add(note2out);}});
        Chord chord3out = new Chord(new ArrayList<Note>(){{add(note3out);}});
        Chord chord4out = new Chord(new ArrayList<Note>(){{add(note4out);}});
        Chord chord5out = new Chord(new ArrayList<Note>(){{add(note5out);}});
        
        List<SoundUnit> guineaPigSoundUnits = new ArrayList<SoundUnit>();
        guineaPigSoundUnits.add(chord1in);
        guineaPigSoundUnits.add(chord2in);
        guineaPigSoundUnits.add(chord3in);
        guineaPigSoundUnits.add(chord4in);
        guineaPigSoundUnits.add(chord5in);
        
        List<SoundUnit> expectedOutputSoundUnits = new ArrayList<SoundUnit>();
        expectedOutputSoundUnits.add(chord1out);
        expectedOutputSoundUnits.add(chord2out);
        expectedOutputSoundUnits.add(chord3out);
        expectedOutputSoundUnits.add(chord4out);
        expectedOutputSoundUnits.add(chord5out);
        
        KeySignature guineaPigKey = new KeySignature(keyValue);
        
        Measure guineaPigMeasure = new Measure(guineaPigKey, guineaPigSoundUnits, barline);
        
        assertEquals(guineaPigMeasure.getListOfSoundUnits().get(0).equals(chord1out), true);
        assertEquals(guineaPigMeasure.getListOfSoundUnits().get(1).equals(chord2out), true);
        assertEquals(guineaPigMeasure.getListOfSoundUnits().get(2).equals(chord3out), true);
        assertEquals(guineaPigMeasure.getListOfSoundUnits().get(3).equals(chord4out), true);
        assertEquals(guineaPigMeasure.getListOfSoundUnits().get(4).equals(chord5out), true);
        
        assertEquals(guineaPigMeasure.getListOfSoundUnits().equals(expectedOutputSoundUnits), true);
    }
    
    
    /**
     * applyLyrics test strategy.
     * 
     * For this test section, consider "valid strings" as all nonempty strings, except the vertical bar line.
     * - originalListOfStrings
     *      containing less valid strings than the list of sound units and ending with a bar line,
     *      containing less valid strings than the list of sound units and not ending with a bar line,
     *      containing the same number of valid strings as the list of sound units and ending with a bar line,
     *      containing the same number of valid strings as the list of sound units and not ending with a bar line,
     *      containing a number of valid strings greater than the list of sound units and ending with a bar line,
     *      containing a number of valid strings greater than the list of sound units and not ending with a bar line.
     */
    @SuppressWarnings("serial")
    @Test
    public void testApplyLyrics_lessValidStrings_noBarLine() {
        // In this case, we expect the method to return an empty array.
        String[] expectedValues = new String[0];
        
        // Initializing guineaPig objects -----------------------------
        List<String> originalListOfStrings = new ArrayList<String>();
        originalListOfStrings.add("A-");
        originalListOfStrings.add("ma-");
        originalListOfStrings.add("zing");
        
        Pitch pitchAmajor = new Pitch('A');
        Pitch pitchBsharp = new Pitch('B').transpose(1);
        Pitch pitchChigh = new Pitch('C').transpose(Pitch.OCTAVE);
        Pitch pitchDlow = new Pitch('D').transpose(-Pitch.OCTAVE);
        
        // These are final in order to enable the inline block statement     
        final Note noteA = new Note(pitchAmajor);
        final Note noteB = new Note(pitchBsharp);
        final Note noteC = new Note(pitchChigh);
        final Note noteD = new Note(pitchDlow);
        
        // Inline declaration
        Chord chordA = new Chord(new ArrayList<Note>(){{add(noteA);}});
        Chord chordB = new Chord(new ArrayList<Note>(){{add(noteB);}});
        Chord chordC = new Chord(new ArrayList<Note>(){{add(noteC);}});
        Chord chordD = new Chord(new ArrayList<Note>(){{add(noteD);}});
        
        List<SoundUnit> guineaPigSoundUnits = new ArrayList<SoundUnit>();
        guineaPigSoundUnits.add(chordA);
        guineaPigSoundUnits.add(chordB);
        guineaPigSoundUnits.add(chordC);
        guineaPigSoundUnits.add(chordD);
        
        
        KeySignature guineaPigKey = new KeySignature("C");
        
        Measure guineaPigMeasure = new Measure(guineaPigKey, guineaPigSoundUnits, "|");

        assertArrayEquals(expectedValues, guineaPigMeasure.applyLyrics(originalListOfStrings).toArray());
    }

    @SuppressWarnings("serial")
    @Test
    public void testApplyLyrics_lessValidStrings_endingBarLine() {
        // In this case, we expect the method to return an empty array.
        String[] expectedValues = new String[0];
        
        // Initializing guineaPig objects -----------------------------
        List<String> originalListOfStrings = new ArrayList<String>();
        originalListOfStrings.add("A-");
        originalListOfStrings.add("ma-");
        originalListOfStrings.add("zing");
        originalListOfStrings.add("|");
        
        Pitch pitchAmajor = new Pitch('A');
        Pitch pitchBsharp = new Pitch('B').transpose(1);
        Pitch pitchChigh = new Pitch('C').transpose(Pitch.OCTAVE);
        Pitch pitchDlow = new Pitch('D').transpose(-Pitch.OCTAVE);
        
        // These are final in order to enable the inline block statement     
        final Note noteA = new Note(pitchAmajor);
        final Note noteB = new Note(pitchBsharp, 0.5);
        final Note noteC = new Note(pitchChigh, 1.2);
        final Note noteD = new Note(pitchDlow);
        
        // Inline declaration
        Chord chordA = new Chord(new ArrayList<Note>(){{add(noteA);}});
        Chord chordB = new Chord(new ArrayList<Note>(){{add(noteB);}});
        Chord chordC = new Chord(new ArrayList<Note>(){{add(noteC);}});
        Chord chordD = new Chord(new ArrayList<Note>(){{add(noteD);}});
        
        List<SoundUnit> guineaPigSoundUnits = new ArrayList<SoundUnit>();
        guineaPigSoundUnits.add(chordA);
        guineaPigSoundUnits.add(chordB);
        guineaPigSoundUnits.add(chordC);
        guineaPigSoundUnits.add(chordD);
        
        
        KeySignature guineaPigKey = new KeySignature("C");
        
        Measure guineaPigMeasure = new Measure(guineaPigKey, guineaPigSoundUnits, "|");

        assertArrayEquals(expectedValues, guineaPigMeasure.applyLyrics(originalListOfStrings).toArray());
    }

    @SuppressWarnings("serial")
    @Test
    public void testApplyLyrics_sameNumberOfValidStrings_noBarLine() {
        // In this case, we expect the method to return an empty array.
        String[] expectedValues = new String[0];
        
        // Initializing guineaPig objects -----------------------------
        List<String> originalListOfStrings = new ArrayList<String>();
        originalListOfStrings.add("An");
        originalListOfStrings.add("a-");
        originalListOfStrings.add("ma-");
        originalListOfStrings.add("zing");
        
        Pitch pitchAmajor = new Pitch('A');
        Pitch pitchBsharp = new Pitch('B').transpose(1);
        Pitch pitchChigh = new Pitch('C').transpose(Pitch.OCTAVE);
        Pitch pitchDlow = new Pitch('D').transpose(-Pitch.OCTAVE);
        
        // These are final in order to enable the inline block statement     
        final Note noteA = new Note(pitchAmajor);
        final Note noteB = new Note(pitchBsharp);
        final Note noteC = new Note(pitchChigh);
        final Note noteD = new Note(pitchDlow);
        
        // Inline declaration
        Chord chordA = new Chord(new ArrayList<Note>(){{add(noteA);}});
        Chord chordB = new Chord(new ArrayList<Note>(){{add(noteB);}});
        Chord chordC = new Chord(new ArrayList<Note>(){{add(noteC);}});
        Chord chordD = new Chord(new ArrayList<Note>(){{add(noteD);}});
        
        List<SoundUnit> guineaPigSoundUnits = new ArrayList<SoundUnit>();
        guineaPigSoundUnits.add(chordA);
        guineaPigSoundUnits.add(chordB);
        guineaPigSoundUnits.add(chordC);
        guineaPigSoundUnits.add(chordD);
        
        
        KeySignature guineaPigKey = new KeySignature("C");
        
        Measure guineaPigMeasure = new Measure(guineaPigKey, guineaPigSoundUnits, "|");

        assertArrayEquals(expectedValues, guineaPigMeasure.applyLyrics(originalListOfStrings).toArray());
    }

    @SuppressWarnings("serial")
    @Test
    public void testApplyLyrics_sameNumberOfValidStrings_endingBarLine() {
        // In this case, we expect the method to return an empty array.
        String[] expectedValues = new String[0];
        
        // Initializing guineaPig objects -----------------------------
        List<String> originalListOfStrings = new ArrayList<String>();
        originalListOfStrings.add("An");
        originalListOfStrings.add("a-");
        originalListOfStrings.add("ma-");
        originalListOfStrings.add("zing");
        originalListOfStrings.add("|");
        
        Pitch pitchAmajor = new Pitch('A');
        Pitch pitchBsharp = new Pitch('B').transpose(1);
        Pitch pitchChigh = new Pitch('C').transpose(Pitch.OCTAVE);
        Pitch pitchDlow = new Pitch('D').transpose(-Pitch.OCTAVE);
        
        // These are final in order to enable the inline block statement     
        final Note noteA = new Note(pitchAmajor);
        final Note noteB = new Note(pitchBsharp);
        final Note noteC = new Note(pitchChigh);
        final Note noteD = new Note(pitchDlow);
        
        // Inline declaration
        Chord chordA = new Chord(new ArrayList<Note>(){{add(noteA);}});
        Chord chordB = new Chord(new ArrayList<Note>(){{add(noteB);}});
        Chord chordC = new Chord(new ArrayList<Note>(){{add(noteC);}});
        Chord chordD = new Chord(new ArrayList<Note>(){{add(noteD);}});
        
        List<SoundUnit> guineaPigSoundUnits = new ArrayList<SoundUnit>();
        guineaPigSoundUnits.add(chordA);
        guineaPigSoundUnits.add(chordB);
        guineaPigSoundUnits.add(chordC);
        guineaPigSoundUnits.add(chordD);
        
        
        KeySignature guineaPigKey = new KeySignature("C");
        
        Measure guineaPigMeasure = new Measure(guineaPigKey, guineaPigSoundUnits, "|");

        assertArrayEquals(expectedValues, guineaPigMeasure.applyLyrics(originalListOfStrings).toArray());
    }

    @SuppressWarnings("serial")
    @Test
    public void testApplyLyrics_moreValidStrings_onBarLine() {
        // In this case, we expect the method to return an array consisting of the ending strings.
        String[] expectedValues = new String[]{"a-","ma-","zing","thing"};
        
        // Initializing guineaPig objects -----------------------------
        List<String> originalListOfStrings = new ArrayList<String>();
        originalListOfStrings.add("That");
        originalListOfStrings.add("is");
        originalListOfStrings.add("such");
        originalListOfStrings.add("an");
        originalListOfStrings.add("a-");
        originalListOfStrings.add("ma-");
        originalListOfStrings.add("zing");
        originalListOfStrings.add("thing");
        
        Pitch pitchAmajor = new Pitch('A');
        Pitch pitchBsharp = new Pitch('B').transpose(1);
        Pitch pitchChigh = new Pitch('C').transpose(Pitch.OCTAVE);
        Pitch pitchDlow = new Pitch('D').transpose(-Pitch.OCTAVE);
        
        // These are final in order to enable the inline block statement     
        final Note noteA = new Note(pitchAmajor);
        final Note noteB = new Note(pitchBsharp);
        final Note noteC = new Note(pitchChigh);
        final Note noteD = new Note(pitchDlow);
        
        // Inline declaration
        Chord chordA = new Chord(new ArrayList<Note>(){{add(noteA);}});
        Chord chordB = new Chord(new ArrayList<Note>(){{add(noteB);}});
        Chord chordC = new Chord(new ArrayList<Note>(){{add(noteC);}});
        Chord chordD = new Chord(new ArrayList<Note>(){{add(noteD);}});
        
        List<SoundUnit> guineaPigSoundUnits = new ArrayList<SoundUnit>();
        guineaPigSoundUnits.add(chordA);
        guineaPigSoundUnits.add(chordB);
        guineaPigSoundUnits.add(chordC);
        guineaPigSoundUnits.add(chordD);
        
        
        KeySignature guineaPigKey = new KeySignature("C");
        
        Measure guineaPigMeasure = new Measure(guineaPigKey, guineaPigSoundUnits, "|");

        assertArrayEquals(expectedValues, guineaPigMeasure.applyLyrics(originalListOfStrings).toArray());
    }

    @SuppressWarnings("serial")
    @Test
    public void testApplyLyrics_moreValidStrings_endingBarLine() {
        // In this case, we expect the method to return an array consisting of the ending strings.
        String[] expectedValues = new String[]{"a-","ma-","zing","thing", "|"};
        
        // Initializing guineaPig objects -----------------------------
        List<String> originalListOfStrings = new ArrayList<String>();
        originalListOfStrings.add("That");
        originalListOfStrings.add("is");
        originalListOfStrings.add("such");
        originalListOfStrings.add("an");
        originalListOfStrings.add("a-");
        originalListOfStrings.add("ma-");
        originalListOfStrings.add("zing");
        originalListOfStrings.add("thing");
        originalListOfStrings.add("|");
        
        Pitch pitchAmajor = new Pitch('A');
        Pitch pitchBsharp = new Pitch('B').transpose(1);
        Pitch pitchChigh = new Pitch('C').transpose(Pitch.OCTAVE);
        Pitch pitchDlow = new Pitch('D').transpose(-Pitch.OCTAVE);
        
        // These are final in order to enable the inline block statement     
        final Note noteA = new Note(pitchAmajor);
        final Note noteB = new Note(pitchBsharp);
        final Note noteC = new Note(pitchChigh);
        final Note noteD = new Note(pitchDlow);
        
        // Inline declaration
        Chord chordA = new Chord(new ArrayList<Note>(){{add(noteA);}});
        Chord chordB = new Chord(new ArrayList<Note>(){{add(noteB);}});
        Chord chordC = new Chord(new ArrayList<Note>(){{add(noteC);}});
        Chord chordD = new Chord(new ArrayList<Note>(){{add(noteD);}});
        
        List<SoundUnit> guineaPigSoundUnits = new ArrayList<SoundUnit>();
        guineaPigSoundUnits.add(chordA);
        guineaPigSoundUnits.add(chordB);
        guineaPigSoundUnits.add(chordC);
        guineaPigSoundUnits.add(chordD);
        
        
        KeySignature guineaPigKey = new KeySignature("C");
        
        Measure guineaPigMeasure = new Measure(guineaPigKey, guineaPigSoundUnits, "|");

        assertArrayEquals(expectedValues, guineaPigMeasure.applyLyrics(originalListOfStrings).toArray());
    }

    
    
    
    /**
     * 'Get' methods test: getEndingBarLine, getListOfSoundUnits.
     * 
     */
    @SuppressWarnings("serial")
    @Test
    public void testGetters() {
        
        String barline = "||";
        String expectedBarLine = "||";
        
        // These don't make any difference to our final result.
        Pitch pitchAmajor = new Pitch('A');
        Pitch pitchBsharp = new Pitch('B').transpose(1);
        Pitch pitchChigh = new Pitch('C').transpose(Pitch.OCTAVE);
        Pitch pitchDlow = new Pitch('D').transpose(-Pitch.OCTAVE);
        
        // These are final in order to enable the inline block statement     
        final Note noteA = new Note(pitchAmajor);
        final Note noteB = new Note(pitchBsharp);
        final Note noteC = new Note(pitchChigh);
        final Note noteD = new Note(pitchDlow);
        
        // Inline declaration
        Chord chordA = new Chord(new ArrayList<Note>(){{add(noteA);}});
        Chord chordB = new Chord(new ArrayList<Note>(){{add(noteB);}});
        Chord chordC = new Chord(new ArrayList<Note>(){{add(noteC);}});
        Chord chordD = new Chord(new ArrayList<Note>(){{add(noteD);}});
        
        List<SoundUnit> guineaPigSoundUnits = new ArrayList<SoundUnit>();
        guineaPigSoundUnits.add(chordA);
        guineaPigSoundUnits.add(chordB);
        guineaPigSoundUnits.add(chordC);
        guineaPigSoundUnits.add(chordD);
        
        List<SoundUnit> expectedSoundUnits = new ArrayList<SoundUnit>(guineaPigSoundUnits);
        
        KeySignature guineaPigKey = new KeySignature("C");
        
        Measure guineaPigMeasure = new Measure(guineaPigKey, guineaPigSoundUnits, barline);
        
        assertEquals(guineaPigMeasure.getEndingBarLine().equals(expectedBarLine), true);
        assertEquals(guineaPigMeasure.getListOfSoundUnits().equals(expectedSoundUnits), true);
    }
    
}