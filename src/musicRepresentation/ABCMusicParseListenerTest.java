package musicRepresentation;

import static org.junit.Assert.assertEquals;
import grammar.ABCMusicLexer;
import grammar.ABCMusicParser;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

/**
 * Test suite for the ABCMusic parser.
 * @author Victor and Nicholas.
 *
 */
public class ABCMusicParseListenerTest {
    private static Map<String, Voice> voiceMap;
    private static ABCHeader header;
    
    @Test
    public void testParser_headerFields(){
        String input = "X:1\n";
        input = input+"T:Alphabet Song\n";
        input = input+"C:Traditional Kid's Song\n";
        input = input+"M:4/4\n";
        input = input+"L:1/4\n";
        input = input+"Q:1/8=100\n";
        input = input+"K:D\n";
        input = input+"C C C C |\n";
        
        parse(input);  
        
        String expectedTitle ="Alphabet Song";
        String expectedComposer ="Traditional Kid's Song";
        int expectedBeatsPerMeasure = 4;
        int expectedBeatsPerMinute = 100;
        double expectedBpmNoteLength = 0.125;
        double expectedDefaultNoteLength = 0.25;
        String expectedKeyName = "D";
        int expectedTicksPerBeat = 1536;
        double expectedWhatNoteGetsTheBeat = 0.25;
        
        assertEquals(true, ABCMusicParseListenerTest.header.getTitle().equals(expectedTitle));
        assertEquals(true, ABCMusicParseListenerTest.header.getComposer().equals(expectedComposer));
        assertEquals(expectedBeatsPerMeasure, ABCMusicParseListenerTest.header.getBeatsPerMeasure());
        assertEquals(expectedBeatsPerMinute, ABCMusicParseListenerTest.header.getBeatsPerMinute());
        assertEquals(expectedBpmNoteLength, ABCMusicParseListenerTest.header.getBpmNoteLength(), 0.001);
        assertEquals(expectedDefaultNoteLength, ABCMusicParseListenerTest.header.getDefaultNoteLength(), 0.001);
        assertEquals(true, ABCMusicParseListenerTest.header.getKeySignature().getKeyName().equals(expectedKeyName));
        assertEquals(expectedTicksPerBeat, ABCMusicParseListenerTest.header.getTicksPerBeat());
        assertEquals(expectedWhatNoteGetsTheBeat, ABCMusicParseListenerTest.header.getWhatNoteGetsTheBeat(), 0.001);
    }
    
    @Test
    public void testParser_SoundUnits(){
        String input = "X:1\n";
        input = input+"T:Alphabet Song\n";
        input = input+"C:Traditional Kid's Song\n";
        input = input+"M:4/4\n";
        input = input+"V:Input\n";
        input = input+"L:1/4\n";
        input = input+"Q:1/8=100\n";
        input = input+"K:D\n";
        input = input+"V:Input\n";
        input = input+"C c/2 B3/4 c''2 | d,, [A/4B/2] [ab] z z/4 z2 |\n";
        
        
        parse(input);  
        
        Voice voice = voiceMap.get("Input");
        assertEquals(true, voice != null);
        List<Measure> measures = voice.getListOfMeasures();
        assertEquals("[ ^C1.0] [ ^c0.5] [ B0.75] [ ^c''2.0] |", measures.get(0).toString());
        assertEquals("[ D,1.0] [ A0.25 B0.25] [ a1.0 b1.0] z1.0 z0.25 z2.0 |", measures.get(1).toString());
    }
    
    @Test
    public void testParser_lyrics(){
        String input = "X:1\n";
        input = input+"T:Alphabet Song\n";
        input = input+"C:Traditional Kid's Song\n";
        input = input+"M:4/4\n";
        input = input+"V:input1\n";
        input = input+"V:input2\n";
        input = input+"L:1/4\n";
        input = input+"Q:1/8=100\n";
        input = input+"K:D\n";
        input = input+"V:input1\n";
        input = input+"c c c c | c c c c | c c c c | c c c c| c c c c | c c c c |\n";
        input = input+"w:a-ma--zing grace_ hot\\-dog | pay*4~(four) one two | three four\n";
        input = input+"V:input2\n";
        input = input+"C c/2 B3/4 c''2 | d,, [A/4B/2] [ab] z z/4 z2 |\n";        
 
        parse(input);  

        Voice voice = voiceMap.get("input2");
        assertEquals(true, voice != null);
        voice = voiceMap.get("input1");
        assertEquals(true, voice != null);
        List<Measure> measures = voice.getListOfMeasures();
        
        // First measure
        Iterator<SoundUnit> it = measures.get(0).getListOfSoundUnits().iterator();
        String lyrics = "";
        while(it.hasNext()){
            SoundUnit s = it.next();
            if(s instanceof Chord){
                lyrics = lyrics+((Chord)s).getSyllable()+"//";
            }
        }
        assertEquals("a-//ma-//-//zing //", lyrics);
        
     // Second measure
        it = measures.get(1).getListOfSoundUnits().iterator();
        lyrics = "";
        while(it.hasNext()){
            SoundUnit s = it.next();
            if(s instanceof Chord){
                lyrics = lyrics+((Chord)s).getSyllable()+"//";
            }
        }
        assertEquals("grace//_ //hot-dog ////", lyrics);
        
     // Third measure
        it = measures.get(2).getListOfSoundUnits().iterator();
        lyrics = "";
        while(it.hasNext()){
            SoundUnit s = it.next();
            if(s instanceof Chord){
                lyrics = lyrics+((Chord)s).getSyllable()+"//";
            }
        }
   assertEquals("pay////4 (four) //one //", lyrics);
        
   // Fourth measure
   it = measures.get(3).getListOfSoundUnits().iterator();
   lyrics = "";
   while(it.hasNext()){
       SoundUnit s = it.next();
       if(s instanceof Chord){
           lyrics = lyrics+((Chord)s).getSyllable()+"//";
       }
   }
   assertEquals("two ////////", lyrics);
   
   // Fifth measure
   it = measures.get(4).getListOfSoundUnits().iterator();
   lyrics = "";
   while(it.hasNext()){
       SoundUnit s = it.next();
       if(s instanceof Chord){
           lyrics = lyrics+((Chord)s).getSyllable()+"//";
       }
   }
   assertEquals("three //four //////", lyrics);  
}
    
    
    /**
     * Helper method. Modifies the static 'voiceMap' and 'header' fields of the test suite, without
     * needing to create a single abc file only for that. Must be called in order to mutate the
     * static values from the class, so that they can be compared with the expected values.
     * 
     * @param input
     *            String corresponding to the input to be tested. Every line-break inside the
     *            'input' String corresponds to a line-break of a hypothetical abc file.
     */
    public static void parse(String input) {
        // Create a stream of tokens using the lexer.
        CharStream stream = new ANTLRInputStream(input);
        ABCMusicLexer lexer = new ABCMusicLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);

        // Feed the tokens into the parser.
        ABCMusicParser parser = new ABCMusicParser(tokens);
        parser.reportErrorsAsExceptions();

        // Generate the parse tree using the starter rule.
        ParseTree tree;
        tree = parser.line(); // "line" is the starter rule.

        // Walk the tree with the listener.
        ParseTreeWalker walker = new ParseTreeWalker();
        ParseTreeListener listener = new ABCMusicParseListener();
        walker.walk(listener, tree);
        ABCMusicParseListenerTest.voiceMap = ((ABCMusicParseListener) listener).getVoiceMap();
        ABCMusicParseListenerTest.header = ((ABCMusicParseListener) listener).getHeader();
        return;
    }

}
