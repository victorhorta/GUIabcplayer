package sound;

import static org.junit.Assert.assertEquals;
import grammar.ABCMusicLexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.junit.Test;

/**
 * This is the test suite for Lexer.
 * Similar tests can be found at ABCMusicLexerTest.java, from the grammar package.
 */
public class LexerTest {

    @Test
    public void lexerTest_header() {
        // Header
        verifyLexer("X: 123", new String[] {"X:", " ", "123"});
        verifyLexer("T: 9th Symphony", new String[] {"T:", " ", "9", "t", "h", " ",
                                                    "S", "y", "m", "p", "h", "o", "n", "y"});
        verifyLexer("Q:1/4=180", new String[] {"Q:", "1", "/", "4", "=", "180"});
    }
    
    @Test
    public void lexerTest_notes() {
        // Single Note
        verifyLexer("C1/16",new String[] {"C", "1", "/", "16"});
        verifyLexer("a", new String[] {"a"});
        
        // Multiple Notes
        verifyLexer("GAb",new String[] {"G", "A", "b"});
        verifyLexer("A2 B C",new String[] {"A", "2", " ", "B", " ", "C"}); 
        
        // Duplets/Triplets/Quadriplets
        verifyLexer("(2GA/2",new String[] {"(2", "G", "A", "/", "2"});
        verifyLexer("(3GAb",new String[] {"(3", "G", "A", "b"});
        verifyLexer("(4GAga",new String[] {"(4", "G", "A", "g", "a"});
        
        // Octaves and Accidents ( ' , ^ ^^ _ __ )
        verifyLexer("'A _b ^c^^d__f,Ff=",new String[] {"'", "A", " ", "_", "b", " ", "^",
                                                   "c", "^", "^", "d", "_", "_", "f", ",", "F", "f", "="}); 
        verifyLexer("A B C",new String[] {"A", " ", "B", " ", "C"}); 
        
        // Chord
        verifyLexer("[AGc]", new String[] {"[","A", "G", "c", "]"});
    }
    
    @Test
    public void lexerTest_lyrics() {
        // Lyrics
        verifyLexer("w: *A-ma-zing, Grace",
                    new String[] {"w:", " ", "*", "A", "-", "m", "a", "-",
                                  "z", "i", "n", "g", ",", " ", "G", "r",
                                  "a", "c", "e"});
    }
    @Test
    public void lexerTest_voices() {
        // Voices
        verifyLexer("V: ABDF", new String[] {"V:"," ", "A", "B", "D", "F"});
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

    public String readFile(String path) {
        try {
            // Read file
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String stringLine = br.readLine();
            String buffer = "";

            while(stringLine != null){
                buffer = buffer.concat(stringLine.concat("\n"));
                stringLine = br.readLine();
            }
            
            br.close();

            return buffer;
        }
        catch (IOException e) {
            return e.toString();
        }
    }
}