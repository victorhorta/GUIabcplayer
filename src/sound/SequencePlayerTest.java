package sound;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

/*
 * Task 2 of the Warmup Exercises. This class is a collection of JUnit tests that play 
 * the three warmup pieces using the sequencer, similar to the main method in the 
 * SequencePlayer class.
 * 
 * @category no_didit
 */
public class SequencePlayerTest {
    @Test
    public void sequencePlayer_testSample1() {
        SequencePlayer player;
        try {	

            /* Create a new player, with 140 beats per minute, 4 ticks per beat
             * and a LyricListener that prints each lyric that it sees.
        	 * Note that there are no lyrics in piece 1 but we must still construct
        	 * a listener in order to pass it to a new SequencePlayer object.
        	 * ticks per beat should be determined by looking at greatest denominator
        	 * and the tuplets in the piece */
            LyricListener listener = new LyricListener() {
                public void processLyricEvent(String text) {
                    System.out.println(text);
                }
            };
            player = new SequencePlayer(140, 12, listener);
            int beat = 12;
            
            // Measure 1
            player.addNote(new Pitch('C').toMidiNote(), 0, beat);
            player.addNote(new Pitch('C').toMidiNote(), 12, beat);
            player.addNote(new Pitch('C').toMidiNote(), 24, beat*3/4);
            player.addNote(new Pitch('D').toMidiNote(), 33, beat*1/4);
            player.addNote(new Pitch('E').toMidiNote(), 36, beat);
            
            // Measure 2
            player.addNote(new Pitch('E').toMidiNote(), 48, beat*3/4);
            player.addNote(new Pitch('D').toMidiNote(), 57, beat*1/4);
            player.addNote(new Pitch('E').toMidiNote(), 60, beat*3/4);
            player.addNote(new Pitch('F').toMidiNote(), 69, beat*1/4);
            player.addNote(new Pitch('G').toMidiNote(), 72, beat*2);
            
            // Measure 3
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 96, beat*1/3);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 100, beat*1/3);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 104, beat*1/3);
            player.addNote(new Pitch('G').toMidiNote(), 108, beat*1/3);
            player.addNote(new Pitch('G').toMidiNote(), 112, beat*1/3);
            player.addNote(new Pitch('G').toMidiNote(), 116, beat*1/3);
            player.addNote(new Pitch('E').toMidiNote(), 120, beat*1/3);
            player.addNote(new Pitch('E').toMidiNote(), 124, beat*1/3);
            player.addNote(new Pitch('E').toMidiNote(), 128, beat*1/3);
            player.addNote(new Pitch('C').toMidiNote(), 132, beat*1/3);
            player.addNote(new Pitch('C').toMidiNote(), 136, beat*1/3);
            player.addNote(new Pitch('C').toMidiNote(), 140, beat*1/3);
            
            // Measure 4
            player.addNote(new Pitch('G').toMidiNote(), 144, beat*3/4);
            player.addNote(new Pitch('F').toMidiNote(), 153, beat*1/4);
            player.addNote(new Pitch('E').toMidiNote(), 156, beat*3/4);
            player.addNote(new Pitch('D').toMidiNote(), 165, beat*1/4);
            player.addNote(new Pitch('C').toMidiNote(), 168, beat*2);


            System.out.println(player);

            // play!
            player.play();

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void sequencePlayer_testSample2() {
        SequencePlayer player;
        try {
            /* Create a new player, with 200 beats per minute, 12 ticks per beat.
             * Note that ticks per beat should be determined by looking at greatest
             * denominator and the tuplets in the piece */
            LyricListener listener = new LyricListener() {
                public void processLyricEvent(String text) {
                    System.out.println(text);
                }
            };
            
            player = new SequencePlayer(200, 12, listener);
            
            int tickPerBeat = 12;
            int currentTick = 0;
            
            /* Measure 1
             * [^F/2e/2] [F/2e/2] z/2 [F/2e/2] z/2 [F/2c/2] [Fe] | */
            player.addNote(new Pitch('F').transpose(1).toMidiNote(), currentTick, tickPerBeat/2);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), currentTick, tickPerBeat/2);
            currentTick += tickPerBeat/2;
            
            player.addNote(new Pitch('F').transpose(1).toMidiNote(), currentTick, tickPerBeat/2);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), currentTick, tickPerBeat/2);
            currentTick += tickPerBeat/2;
            
            currentTick += tickPerBeat/2; // rest
            
            player.addNote(new Pitch('F').transpose(1).toMidiNote(), currentTick, tickPerBeat/2);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), currentTick, tickPerBeat/2);
            currentTick += tickPerBeat/2;
            
            currentTick += tickPerBeat/2; // rest
            
            player.addNote(new Pitch('F').transpose(1).toMidiNote(), currentTick, tickPerBeat/2);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), currentTick, tickPerBeat/2);
            currentTick += tickPerBeat/2;
            
            player.addNote(new Pitch('F').transpose(1).toMidiNote(), currentTick, tickPerBeat);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), currentTick, tickPerBeat);
            currentTick += tickPerBeat;
            
            /* Measure 2
             * [GBg] z G z | */
            player.addNote(new Pitch('G').toMidiNote(), currentTick, tickPerBeat);
            player.addNote(new Pitch('B').toMidiNote(), currentTick, tickPerBeat);
            player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), currentTick, tickPerBeat);
            currentTick += tickPerBeat;
            
            currentTick += tickPerBeat; // rest
            
            player.addNote(new Pitch('G').toMidiNote(), currentTick, tickPerBeat);
            currentTick += tickPerBeat;
            
            currentTick += tickPerBeat; // rest
            
            /* Measure 3
             * c3/2 G/2 z E | */
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), currentTick, tickPerBeat*3/2);
            currentTick += tickPerBeat*3/2;
            
            player.addNote(new Pitch('G').toMidiNote(), currentTick, tickPerBeat/2);
            currentTick += tickPerBeat/2;
            
            currentTick += tickPerBeat; // rest
            
            player.addNote(new Pitch('E').toMidiNote(), currentTick, tickPerBeat);
            currentTick += tickPerBeat;
            
            /* Measure 4
             * E/2 A B _B/2 A | */
            player.addNote(new Pitch('E').toMidiNote(), currentTick, tickPerBeat/2);
            currentTick += tickPerBeat/2;
            
            player.addNote(new Pitch('A').toMidiNote(), currentTick, tickPerBeat);
            currentTick += tickPerBeat;
            
            player.addNote(new Pitch('B').toMidiNote(), currentTick, tickPerBeat);
            currentTick += tickPerBeat;
            
            player.addNote(new Pitch('B').transpose(-1).toMidiNote(), currentTick, tickPerBeat/2);
            currentTick += tickPerBeat/2;
            
            player.addNote(new Pitch('A').toMidiNote(), currentTick, tickPerBeat);
            currentTick += tickPerBeat;
            
            /* Measure 5
             * (Geg a f/2 g/2 | */
            player.addNote(new Pitch('G').toMidiNote(), currentTick, tickPerBeat*2/3);
            currentTick += tickPerBeat*2/3;
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), currentTick, tickPerBeat*2/3);
            currentTick += tickPerBeat*2/3;
            player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), currentTick, tickPerBeat*2/3);
            currentTick += tickPerBeat*2/3;
            
            player.addNote(new Pitch('A').transpose(Pitch.OCTAVE).toMidiNote(), currentTick, tickPerBeat);
            currentTick += tickPerBeat;
            
            player.addNote(new Pitch('F').transpose(Pitch.OCTAVE).toMidiNote(), currentTick, tickPerBeat/2);
            currentTick += tickPerBeat/2;
            
            player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), currentTick, tickPerBeat/2);
            currentTick += tickPerBeat/2;
            
            /* Measure 6
             * z/2 e c/2 d/2 B3/4 z3/4 || */
            currentTick += tickPerBeat/2;
            
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), currentTick, tickPerBeat);
            currentTick += tickPerBeat;
            
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), currentTick, tickPerBeat/2);
            currentTick += tickPerBeat/2;
            
            player.addNote(new Pitch('D').transpose(Pitch.OCTAVE).toMidiNote(), currentTick, tickPerBeat/2);
            currentTick += tickPerBeat/2;
            
            player.addNote(new Pitch('B').toMidiNote(), currentTick, tickPerBeat*3/4);
            currentTick += tickPerBeat*3/4;
            
            currentTick += tickPerBeat*3/4; // rest
            
            // play!
            player.play();

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void sequencePlayer_testSample3() {
    	SequencePlayer player;
        try {

            /* Create a new player, with 100 beats per minute, 1 ticks per beat
             * and a LyricListener that prints each lyric that it sees. */
            LyricListener listener = new LyricListener() {
                public void processLyricEvent(String text) {
                    System.out.println(text);
                }
            };
            player = new SequencePlayer(100, 1, listener);
            
            int ticksPerBeat = 1;

            // Measure 1
            player.addLyricEvent("A-", 4);
            player.addNote(new Pitch('D').toMidiNote(), 4, ticksPerBeat*2);
            
            // Measure 2
            player.addLyricEvent("ma-", 6);
            player.addNote(new Pitch('G').toMidiNote(), 6, ticksPerBeat*4);
            player.addLyricEvent("zing", 10);
            player.addNote(new Pitch('B').toMidiNote(), 10, ticksPerBeat*1);
            player.addNote(new Pitch('G').toMidiNote(), 11, ticksPerBeat*1);
            
            // Measure 3
            player.addLyricEvent("grace!", 12);
            player.addNote(new Pitch('B').toMidiNote(), 12, ticksPerBeat*4);
            player.addLyricEvent("How", 16);
            player.addNote(new Pitch('A').toMidiNote(), 16, ticksPerBeat*2);
            
            
            
            // Measure 4
            player.addLyricEvent("sweet", 18);
            player.addNote(new Pitch('G').toMidiNote(), 18, ticksPerBeat*4);
            player.addLyricEvent("the", 22);
            player.addNote(new Pitch('E').toMidiNote(), 22, ticksPerBeat*2);
            
            // Measure 5
            player.addLyricEvent("sound", 24);
            player.addNote(new Pitch('D').toMidiNote(), 24, ticksPerBeat*4);
            player.addLyricEvent("That", 28);
            player.addNote(new Pitch('D').toMidiNote(), 28, ticksPerBeat*2);
            
            // Measure 6
            player.addLyricEvent("saved", 30);
            player.addNote(new Pitch('G').toMidiNote(), 30, ticksPerBeat*4);
            player.addLyricEvent("a", 34);
            player.addNote(new Pitch('B').toMidiNote(), 34, ticksPerBeat);
            player.addNote(new Pitch('G').toMidiNote(), 35, ticksPerBeat);
            
            // Measure 7
            player.addLyricEvent("wretch", 36);
            player.addNote(new Pitch('B').toMidiNote(), 36, ticksPerBeat*4);
            player.addLyricEvent("like", 40);
            player.addNote(new Pitch('A').toMidiNote(), 40, ticksPerBeat*2);
            
            // Measure 8
            player.addLyricEvent("me.", 42);
            player.addNote(new Pitch('D').transpose(Pitch.OCTAVE).toMidiNote(), 42, ticksPerBeat*6);
            
            // Measure 9
            player.addNote(new Pitch('D').transpose(Pitch.OCTAVE).toMidiNote(), 48, ticksPerBeat*2);

            System.out.println(player);

            // play!
            player.play();

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
}
