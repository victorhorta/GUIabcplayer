package musicRepresentation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RestTest {
	// Tests the first constructor of the Rest class (no arguments)
	@Test
	public void testRest_ZeroArgs() {
		new Rest();
	}
	
	// Tests the second constructor of the Rest class (one argument)
	@Test
	public void testRest_OneArg() {
		new Rest(2);
	}
	
	// Tests the getDurationMultiplier method
	@Test
    public void testGetDurationMultiplier() {
        Rest rest = new Rest(2);
        assertEquals(rest.getDurationMultiplier(), 2, 0.000001);
    }
}