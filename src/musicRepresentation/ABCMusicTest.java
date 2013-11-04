package musicRepresentation;

import java.io.IOException;

import org.junit.Test;

/**
 * Test suite for the ABCMusic class.
 * 
 * Here, we will test if the ABCMusic class is correctly handling well-formed abc files, as well as
 * detecting semantic errors in malformed abc files. More specific tests (such as the checking the
 * correctness of the parser) are being handled on the ABCMusicParseListenerTest class. Lyrics tests
 * are being handled at the ABCMusicParseListenerTest classes and also at the the MeasureTest .
 * 
 * 
 * Test strategy:
 * 
 * .Malformed files
 *  - Missing required header fields
 *  - Wrong header info given 
 *  - Invalid notes as inputs 
 *  - Malformed repetition (without corresponding barlines) 
 *  - Voice and lyrics on non-standard positions
 * 
 * .Well-formed files 
 *  - Accepts optional header fields 
 *  - Accepts multiple voices 
 *  - Accepts repeats
 * 
 * 
 * @author Victor and Nicholas
 * 
 */
public class ABCMusicTest {

    @Test(expected=RuntimeException.class)
    public void testABCMusic_missingHeaderField() throws IOException {
        ABCMusic music = new ABCMusic("sample_abc/test_bad1.abc");
    }
    
    @Test(expected=RuntimeException.class)
    public void testABCMusic_wrongHeaderInfo() throws IOException {
        ABCMusic music = new ABCMusic("sample_abc/test_bad2.abc");
    }
    
    @Test(expected=RuntimeException.class)
    public void testABCMusic_InvalidNotes() throws IOException {
        ABCMusic music = new ABCMusic("sample_abc/test_bad3.abc");
    }
    
    @Test(expected=RuntimeException.class)
    public void testABCMusic_malformedRepetition() throws IOException {
        ABCMusic music = new ABCMusic("sample_abc/test_bad4.abc");
    }
    
    @Test(expected=RuntimeException.class)
    public void testABCMusic_nonStandardPositions() throws IOException {
        ABCMusic music = new ABCMusic("sample_abc/test_bad5.abc");
    }
    
    @Test
    public void testABCMusic_acceptOptionalHeaderFields() throws IOException {
        ABCMusic music = new ABCMusic("sample_abc/payphone.abc");
    }
    
    @Test
    public void testABCMusic_acceptMultipleVoices() throws IOException {
        ABCMusic music = new ABCMusic("sample_abc/summer_nights.abc");
    }
    
    @Test
    public void testABCMusic_acceptRepeats() throws IOException {
        ABCMusic music = new ABCMusic("sample_abc/street_fighter_intro.abc");
    }
    
    @Test
    public void testABCMusic_complexMusic() throws IOException {
        ABCMusic music = new ABCMusic("sample_abc/fur_elise.abc");
    }
}
