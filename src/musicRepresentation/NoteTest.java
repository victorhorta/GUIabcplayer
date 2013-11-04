package musicRepresentation;

import static org.junit.Assert.*;

import org.junit.Test;

import sound.Pitch;

/**
 * This is the test suite for the Note class.
 * 
 * @author Eric
 *
 */
public class NoteTest {
	// Tests the single argument constructor of Note
	@Test
    public void testNote_OneArg() {
		new Note(new Pitch('C'));
	}
	
	// Tests the single argument constructor of Note
	@Test
    public void testNote_TwoArg() {
		new Note(new Pitch('C'), 2);
	}
	
	// Tests the get methods of the Note class
	@Test
    public void testGetMethods() {
		Note note = new Note(new Pitch('C'), 2);
		Pitch result1 = note.getPitch();
		assertTrue(new Pitch('C').equals(result1));
		
		double result2 = note.getDurationMultiplier();
		assertEquals(2, result2, 0.00001);
	}
	
	// Tests the multiplyDurationMultiplier method	
	@Test
	public void testMultiplyDurationMultiplier() {
		Note note = new Note(new Pitch('C'), 2);
		note.multiplyDurationMultiplier(5);
		double result1 = note.getDurationMultiplier();
		
		assertEquals(10, result1, 0.00001);
	}
	
	// Tests the multiplyDurationMultiplier method	
    @Test
    public void testSetDurationMultipler() {
        Note note = new Note(new Pitch('C'), 2);
        note.setDurationMultiplier(Double.MAX_VALUE);
        double result1 = note.getDurationMultiplier();

        assertEquals(Double.MAX_VALUE, result1, 0.00001);
    }
	
	// Tests the setPitch method	
	@Test
    public void testSetPitch() {
        Note note = new Note(new Pitch('C'), 2);
        Pitch newPitch = new Pitch('D');
        note.setPitch(newPitch);

        assertTrue(note.getPitch().equals(newPitch));
    }
}
