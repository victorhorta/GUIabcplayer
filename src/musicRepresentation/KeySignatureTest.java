package musicRepresentation;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import sound.Pitch;

public class KeySignatureTest {

    @Test
    public void testGetPitchMap_SharpKeySignatures() {
        /* The strategy for this test batch is to build a map
         * using Pitches that are affected by a key signature.
         * Since tests are ordered by number of sharps, and the
         * sharps are the same for the next key signature, in
         * addition to another accidental, we reuse the hash
         * to test all key signatures involving sharps
         */
        
        Map<Pitch,Pitch> map = new HashMap<Pitch,Pitch>();
        KeySignature key;
        
        // C Major / A minor
        key = new KeySignature("C");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));

        // G Major / E minor -> F#
        map.put( new Pitch('F'), new Pitch('F').accidentalTranspose(1));
        key = new KeySignature("G");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));
        key = new KeySignature("Em");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));
        
        // D Major / B minor -> F#, C#
        map.put( new Pitch('C'), new Pitch('C').accidentalTranspose(1));
        key = new KeySignature("D");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));
        key = new KeySignature("Bm");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));

        // A Major / F# minor -> F#, C#, G#
        map.put( new Pitch('G'), new Pitch('G').accidentalTranspose(1));
        key = new KeySignature("A");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));
        key = new KeySignature("F#m");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));

        // E Major / C# minor -> F#, C#, G#, D#
        map.put( new Pitch('D'), new Pitch('D').accidentalTranspose(1));
        key = new KeySignature("E");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));
        key = new KeySignature("C#m");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));

        // B Major / G# minor -> F#, C#, G#, D#, A#
        map.put( new Pitch('A'), new Pitch('A').accidentalTranspose(1));
        key = new KeySignature("B");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));
        key = new KeySignature("G#m");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));

        // F# Major / D# minor -> F#, C#, G#, D#, A#, E#
        map.put( new Pitch('E'), new Pitch('E').accidentalTranspose(1));
        key = new KeySignature("F#");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));
        key = new KeySignature("D#m");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));

        // C# Major / A# minor -> F#, C#, G#, D#, A#, E#, B#
        map.put( new Pitch('B'), new Pitch('B').accidentalTranspose(1));
        key = new KeySignature("C#");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));
        key = new KeySignature("A#m");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));
    }
    
    @Test
    public void testGetPitchMap_FlatKeySignatures() {
        /* The strategy for this test batch is to build a map
         * using Pitches that are affected by a key signature.
         * Since tests are ordered by number of flats, and the
         * sharps are the same for the next key signature, in
         * addition to another accidental, we reuse the hash
         * to test all key signatures involving flats.
         */

        Map<Pitch,Pitch> map = new HashMap<Pitch,Pitch>();
        KeySignature key;

        // F Major / D minor -> Bb
        map.put( new Pitch('B'), new Pitch('B').accidentalTranspose(-1));
        key = new KeySignature("F");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));
        key = new KeySignature("Dm");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));

        // Bb Major / G minor -> Bb, Eb
        map.put( new Pitch('E'), new Pitch('E').accidentalTranspose(-1));
        key = new KeySignature("Bb");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));
        key = new KeySignature("Gm");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));

        // Eb Major / C minor -> Bb, Eb, Ab
        map.put( new Pitch('A'), new Pitch('A').accidentalTranspose(-1));
        key = new KeySignature("Eb");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));
        key = new KeySignature("Cm");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));

        // Ab Major / F minor -> Bb, Eb, Ab, Db
        map.put( new Pitch('D'), new Pitch('D').accidentalTranspose(-1));
        key = new KeySignature("Ab");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));
        key = new KeySignature("Fm");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));

        // Db Major / Bb minor -> Bb, Eb, Ab, Db, Gb
        map.put( new Pitch('G'), new Pitch('G').accidentalTranspose(-1));
        key = new KeySignature("Db");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));
        key = new KeySignature("Bbm");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));

        // Gb Major / Eb minor -> Bb, Eb, Ab, Db, Gb, Cb
        map.put( new Pitch('C'), new Pitch('C').accidentalTranspose(-1));
        key = new KeySignature("Gb");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));
        key = new KeySignature("Ebm");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));

        // Cb Major / Ab minor -> Bb, Eb, Ab, Db, Gb, Cb, Fb
        map.put( new Pitch('F'), new Pitch('F').accidentalTranspose(-1));
        key = new KeySignature("Cb");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));
        key = new KeySignature("Abm");
        assertEquals(true, key.getPitchMap().entrySet().containsAll(map.entrySet()));
    }
    
    @Test
    public void testGetPitchMap_WrongKeyMapping() {
        
        Map<Pitch,Pitch> map = new HashMap<Pitch,Pitch>();
        KeySignature key;
        
        // Put a mapping that should not be in it.
        key = new KeySignature("C");
        map.put( new Pitch('B'), new Pitch('B').accidentalTranspose(1));
        
        // Check if the mappings are not equals anymore
        assertEquals(false, key.getPitchMap().entrySet().containsAll(map.entrySet()));
    }
    
    @Test
    public void testGetPitchMap_immutability() {
        KeySignature key = new KeySignature("G");
        Map<Pitch, Pitch> map = key.getPitchMap();
        
        // Checks equality for copies
        assertEquals(true, map.equals(key.getPitchMap()));
        
        // Change map
        map.put(new Pitch('B').octaveTranspose(20), new Pitch('C'));
        
        // Verify if they are different maps
        assertEquals(false, map.equals(key.getPitchMap()));
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testKeySignature_invalidInputs() {
        new KeySignature("");
        new KeySignature("a");
        new KeySignature("am");
        new KeySignature("C flat");
        new KeySignature("C sharp");
        new KeySignature("G major");
    }
}
