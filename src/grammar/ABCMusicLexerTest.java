package grammar;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.junit.Test;


/**
 * Test suite for the ABCMusic lexer.
 * 
 *    .General tests:
 *       -Beginning of a comment token
 *       -Numbers
 *       -Special symbols: parenthesis, letters, capital c, new line
 *    .Header tests:
 *       -Fields
 *       -Correctly recognize tokens from fields, and differentiate them from their content
 *    .Music tests:
 *       -Beginning of a tuplet
 *       -Nth_repeats, barlines, slashes 
 *       -Commas, carets, pound, equals, apostrophe
 *       -Identify correctly tokens of different notes, chords and rests
 *    .Lyric tests:
 *       -Slash-hyphen, underscore, stars, hyphens
 *       -Correctly recognize all tokens with special meanings for lyrics
 * 
 * @author Victor
 *
 */
public class ABCMusicLexerTest {
    
    @Test
    public void testLexer_general() {
        verifyLexer("%", new String[] { "%" });
        verifyLexer("12345", new String[] { "12345" });
        verifyLexer("()\n", new String[] { "(", ")", "\n" });
        verifyLexer("ABCDEFG", new String[] { "A","B","C","D","E","F","G" });
        verifyLexer("abcdefg", new String[] { "a","b","c","d","e","f","g" });
    }

    @Test
    public void testLexer_header() {
        verifyLexer("X:T:C:Q:K:L:M:V:w:/", new String[] { "X:","T:","C:","Q:","K:","L:","M:","V:","w:","/" });
        verifyLexer("C:Mozart", new String[] { "C:","M","o","z","a","r","t" });
    }
    
    @Test
    public void testLexer_music() {
        verifyLexer("[1[2", new String[] { "[1","[2" });
        verifyLexer("|:|]|||[|:|[]", new String[] { "|:","|]","||","|","[|",":|","[","]" });
        verifyLexer(",^#='", new String[] { ",","^","#","=","'" });
    }
    
    @Test
    public void testLexer_restNoteChordAndMeasure() {
        verifyLexer("z", new String[] { "z" });
        verifyLexer("^d2", new String[] { "^","d","2" });
        verifyLexer("[CEG]", new String[] { "[","C","E","G","]" });
        verifyLexer("z d2 [CEG] d'^c'ba |", new String[] { "z"," ","d","2"," ","[","C","E","G","]"," ","d","'","^","c","'","b","a"," ","|" });
    }
    
    @Test
    public void testLexer_lyrics() {
        verifyLexer("\\--*_~", new String[] { "\\-","-","*","_","~" });
        verifyLexer("A-ma-zing_ ye~ah", new String[] { "A","-","m","a","-","z","i","n","g","_"," ","y","e","~","a","h" });
    }
    
    
    public void verifyLexer(String input, String[] expectedTokens) {
        CharStream stream = new ANTLRInputStream(input);
        ABCMusicLexer lexer = new ABCMusicLexer(stream);
        lexer.reportErrorsAsExceptions();
        List<? extends Token> actualTokens = lexer.getAllTokens();
        
        assertEquals(expectedTokens.length, actualTokens.size());
        
        for(int i = 0; i < actualTokens.size(); i++) {
            String actualToken = actualTokens.get(i).getText();
            String expectedToken = expectedTokens[i];
            assertEquals(actualToken, expectedToken);
        }
    }
}
