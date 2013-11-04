package musicRepresentation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import sound.Pitch;

/**
 * Test suite for SequencerInformation class.
 * 
 * @author Victor
 *
 */
public class SequencerInformationTest {
    
    /**
     * Testing the SequencerInformation constructor. 
     */
    @Test
    public void testSequencerInformation() {
        List<MidiNoteRepresentation> midiNotes = new ArrayList<MidiNoteRepresentation>();
        midiNotes.add(new MidiNoteRepresentation(new Pitch('A'), 1, 123));
        midiNotes.add(new MidiNoteRepresentation(new Pitch('A'), 1, 123));
        midiNotes.add(new MidiNoteRepresentation(new Pitch('A'), 1, 123));
        
        List<Syllable> syllables = new ArrayList<Syllable>();
        syllables.add(new Syllable("La-", 0));
        syllables.add(new Syllable("ra-", 10));
        syllables.add(new Syllable("riii", 20));
        syllables.add(new Syllable("_", 30));
        syllables.add(new Syllable("Oh~te'sting", 40));
        syllables.add(new Syllable("", 50));
        syllables.add(new Syllable("mess", 20));
        
        SequencerInformation seqInfo = new SequencerInformation("Voice name", midiNotes, syllables);
        assertEquals(true, seqInfo.equals(seqInfo));
    }
    
    /**
     * Testing getter methods.
     */
    @Test
    public void testGetters() {
        List<MidiNoteRepresentation> midiNotes = new ArrayList<MidiNoteRepresentation>();
        midiNotes.add(new MidiNoteRepresentation(new Pitch('A'), 1, 123));
        midiNotes.add(new MidiNoteRepresentation(new Pitch('A'), 1, 123));
        midiNotes.add(new MidiNoteRepresentation(new Pitch('A'), 1, 123));
        
        List<MidiNoteRepresentation> midiNotesParameter = new ArrayList<MidiNoteRepresentation>(midiNotes);
        
        List<Syllable> syllables = new ArrayList<Syllable>();
        syllables.add(new Syllable("La-", 0));
        syllables.add(new Syllable("ra-", 10));
        syllables.add(new Syllable("riii", 20));
        syllables.add(new Syllable("_", 30));
        syllables.add(new Syllable("Oh~te'sting", 40));
        syllables.add(new Syllable("", 50));
        syllables.add(new Syllable("mess", 20));
        
        List<Syllable> syllablesParameter = new ArrayList<Syllable>(syllables);
        
        SequencerInformation seqInfo = new SequencerInformation("Voice name", midiNotesParameter, syllablesParameter);
        
        assertArrayEquals(midiNotes.toArray(), seqInfo.getMidiNotes().toArray());
        assertArrayEquals(syllables.toArray(), seqInfo.getSyllables().toArray());
    }
}
