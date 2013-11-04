package musicRepresentation;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test suite for Syllable class.
 * @author Victor
 *
 */
public class SyllableTest {
    @Test
    public void testSyllable() {
        final int startTick = 1234;
        final String syllableContent = "Oh'yeah~lo";

        Syllable syllable = new Syllable(syllableContent, startTick);

        assertEquals(syllableContent, syllable.getSyllable());
        assertEquals(startTick, syllable.getStartTick());
    }

    @Test
    public void testSyllable_emptyString() {
        final int startTick = 1234;
        final String syllableContent = "";

        Syllable syllable = new Syllable(syllableContent, startTick);

        assertEquals(syllableContent, syllable.getSyllable());
        assertEquals(startTick, syllable.getStartTick());
    }
}
