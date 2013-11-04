package sound;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * The test suite for the Pitch class. The Pitch class was changed in order to
 * distinguish between cases such as C and =C. The hasAccidental used to
 * construct a Pitch object is used to distinguish between the two cases.
 * 
 * @author Eric
 * 
 */
public class PitchTest {
	// Tests the constructor method of the Pitch class
	@Test
	public void testPitch() {
		new Pitch(7, 2, 0, true);
	}
	
	// Check that C and =C are NOT equal
	@Test
	public void testEquals_NaturalCAndC() {
		Pitch cNatural = new Pitch(7, 0, 1, true);
		Pitch c = new Pitch(7, 0, 1, false);
		assertFalse(cNatural.equals(c));
	}
	
	// Check that C and =C do not have the same hashCode
	@Test
	public void testHashCode_NaturalCAndC() {
		Pitch cNatural = new Pitch(7, 0, 1, true);
		Pitch c = new Pitch(7, 0, 1, false);
		assertFalse(cNatural.hashCode() == c.hashCode());
	}
}
