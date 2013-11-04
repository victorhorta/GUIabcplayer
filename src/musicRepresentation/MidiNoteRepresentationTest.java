package musicRepresentation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sound.Pitch;

/**
 * MidiNoteRepresentation tests.
 * 
 * @author Victor
 *
 */
public class MidiNoteRepresentationTest {

    /**
     * Verifying get methods.
     */
    @Test
    public void test_getMethods() {
        int numTicks = 10000;
        int startTick = 0;
        Pitch pitch = new Pitch('A');

        MidiNoteRepresentation midiNote = new MidiNoteRepresentation(pitch, startTick, numTicks);

        assertEquals(numTicks, midiNote.getNumTicks());
        assertEquals(startTick, midiNote.getStartTick());
        assertEquals(true, pitch.equals(midiNote.getPitch()));

        numTicks = 0;
        startTick = 10000;
        pitch = new Pitch('A').octaveTranspose(3).accidentalTranspose(4);

        midiNote = new MidiNoteRepresentation(pitch, startTick, numTicks);

        assertEquals(numTicks, midiNote.getNumTicks());
        assertEquals(startTick, midiNote.getStartTick());
        assertEquals(true, pitch.equals(midiNote.getPitch()));      
    }
    
    /**
     * Verifies if the MidiNoteRepresentation doesn't mutates if we call the octaveTranspose method
     * on its pitch.
     */
    @Test
    public void testMidiNoteRepresentation_verifyingImmutability() {
        int numTicks = 1234;
        int startTick = 1;
        Pitch pitch = new Pitch('A');
    
        MidiNoteRepresentation midiNote =
                new MidiNoteRepresentation(pitch, startTick, numTicks);
        
        int tempA = midiNote.getNumTicks();
        tempA = 9999;
        
        // Trying to screw up with immutability...
        Pitch tempPitch = null;
        tempPitch = midiNote.getPitch();
        tempPitch.octaveTranspose(999);

        assertEquals(false, tempA == midiNote.getNumTicks());
        assertEquals(numTicks, midiNote.getNumTicks());
        assertEquals(startTick, midiNote.getStartTick());
        assertEquals(true, tempPitch.equals(midiNote.getPitch()));
    }

}
