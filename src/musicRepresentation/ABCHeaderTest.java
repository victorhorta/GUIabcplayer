package musicRepresentation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sound.Pitch;

public class ABCHeaderTest {

    @Test
    public void testABCHeader_immutability() {
        /* Checks immutability.
         * Checks all mutators (it doesn't have).
         * Verify attributes that might allow mutability.
         */
        ABCHeader header = new ABCHeader("Title", "Composer", 200,0.25,12,0.5,0,0.25,"G");
        ABCHeader originalHeader = header;
        
        // Only object that might break invariant is KeySignature.
        // Other returned attributes are primitive, which are immutable.
        KeySignature key = header.getKeySignature();
        key.getPitchMap().put(new Pitch('B').octaveTranspose(Integer.MAX_VALUE),
                                new Pitch('B').octaveTranspose(Integer.MAX_VALUE));
        
        /* After modifying key signature, maybe it has changed.
         * If ABCHeader is immutable, they should be equal even
         * after modifying things.
         */
        assertEquals(originalHeader.equals(header), true);
    }
    
    public void testABCHeader_getMethods() {
        // Checks getter methods.
        ABCHeader header = new ABCHeader("Gravity", "John Meyer",0,0,0,0.0,0,0.0, "A");
        assertEquals("Gravity", header.getComposer());
        assertEquals("John Meyer", header.getTitle());
        assertEquals(new KeySignature("A").equals(header.getKeySignature()), true);
        assertEquals(0, header.getBeatsPerMinute());
        assertEquals(0, header.getTicksPerBeat());
        assertEquals(0.0, header.getDefaultNoteLength(), 0.01);
        assertEquals(0.0, header.getWhatNoteGetsTheBeat(), 0.01);
        
        // Max Values, spaces  ' and empty strings.
        header = new ABCHeader("Don't stop this train", "", 0, 0, 0, 0, 0, 0, "C#");
        assertEquals("Don't Stop this train", header.getComposer());
        assertEquals("John Meyer", header.getTitle());
        assertEquals(new KeySignature("C#").equals(header.getKeySignature()), true);
        assertEquals(Integer.MAX_VALUE, header.getBeatsPerMinute());
        assertEquals(Integer.MAX_VALUE, header.getTicksPerBeat());
        assertEquals(Double.MAX_VALUE, header.getDefaultNoteLength(), 0.01);
        assertEquals(Double.MAX_VALUE, header.getWhatNoteGetsTheBeat(), 0.01);
    }
    

}
