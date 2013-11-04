package musicRepresentation;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import sound.Pitch;

public class ChordTest {
    // Tests the constructor of the Chord class when one Note is in the list
    @Test
    public void testChord_OneNote() {
        Note note = new Note(new Pitch('C'));
        List<Note> noteList = new ArrayList<Note>();
        noteList.add(note);
        new Chord(noteList);
    }
	
    // Tests the constructor of the Chord class when two Notes are in the list
    @Test
    public void testChord_TwoNote() {
        Note note1 = new Note(new Pitch('C'));
        Note note2 = new Note(new Pitch('D'));
        List<Note> noteList = new ArrayList<Note>();
        noteList.add(note1);
        noteList.add(note2);
        new Chord(noteList);
    }
	
	// Tests the get methods of the Chord class
    @Test
    public void testGetMethods() {
        Note note1 = new Note(new Pitch('C'), 2);
        Note note2 = new Note(new Pitch('D'), 4);
        List<Note> noteList = new ArrayList<Note>();
        noteList.add(note1);
        noteList.add(note2);
        Chord chord = new Chord(noteList);
        assertTrue(noteList.equals(chord.getListOfNotesInChord()));

        assertEquals("", chord.getSyllable());

        assertEquals(2, chord.getDurationMultiplier(), 0.000001);

        // check to see if the durationMultiplier of the second Note in the Chord was changed
        // properly
        assertEquals(2, chord.getListOfNotesInChord().get(1).getDurationMultiplier(), 0.00001);
    }
	
    // Tests the setSyllable method with an Empty String
    @Test
    public void testSetSyllable_EmptyString() {
        Note note1 = new Note(new Pitch('C'), 2);
        Note note2 = new Note(new Pitch('D'), 4);
        List<Note> noteList = new ArrayList<Note>();
        noteList.add(note1);
        noteList.add(note2);
        Chord chord = new Chord(noteList);
        chord.setSyllable("");
        assertEquals("", chord.getSyllable());
    }
	
    // Tests the setSyllable method with a non-empty String
    @Test
    public void testSetSyllable_NonEmptyString() {
        Note note1 = new Note(new Pitch('C'), 2);
        Note note2 = new Note(new Pitch('D'), 4);
        List<Note> noteList = new ArrayList<Note>();
        noteList.add(note1);
        noteList.add(note2);
        Chord chord = new Chord(noteList);
        chord.setSyllable("ma-");
        assertEquals("ma-", chord.getSyllable());
    }	
	
	// Tests the multiplyDurationMultiplier method
	@Test
    public void testMultiplyDurationMultiplier() {
        Note note1 = new Note(new Pitch('C'), 2);
        Note note2 = new Note(new Pitch('D'), 4);
        List<Note> noteList = new ArrayList<Note>();
        noteList.add(note1);
        noteList.add(note2);
        Chord chord = new Chord(noteList);
        chord.multiplyDurationMultiplier(2.0 / 3.0);

        // Check that the Chord has chordDurationMultiplier equal to 2.0/3.0*2
        assertEquals(chord.getDurationMultiplier(), 2.0 / 3.0 * 2, 0.0000001);
        // Check that the 1st Note in the Chord has a durationMultiplier equal to 2.0/3.0*2
        assertEquals(2.0 / 3.0 * 2, chord.getListOfNotesInChord().get(0).getDurationMultiplier(),
                0.00001);
        // Check that the 2nd Note in the Chord has a durationMultiplier equal to 2.0/3.0*2
        assertEquals(chord.getDurationMultiplier(), chord.getListOfNotesInChord().get(1)
                .getDurationMultiplier(), 0.00001);
	}	
}