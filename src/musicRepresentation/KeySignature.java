package musicRepresentation;

import java.util.HashMap;
import java.util.Map;

import sound.Pitch;

/**
 * Represents accidentals as a Map in a key signature.
 * It is an immutable class.
 * @author Nicholas M. Mizoguchi
 *
 */
public class KeySignature {
    private final String keyName;
    private final Map<Pitch,Pitch> pitchMap;
    
    /**
     * Creates a new KeySignature object with the keyName.
     * keyName defines the mapping of pitchMap. Considered
     * valid key signatures are the ones in the circle of
     * fifths (also enharmonics). If the key is invalid
     * an IllegalArgument exception is raised.
     * @param keyName a valid key signature for the music.
     */
    public KeySignature(String keyName) {
        this.keyName = keyName;
        Map<Pitch,Pitch> map = new HashMap<Pitch,Pitch>();
        int numSupportedOctaves = 5;
        
        Pitch accidentalPitch;

        /***************************
         * For Sharp accidentals
         ***************************/
        // C Major / A minor -> There is no need to change the mapping for these keys
        if( keyName.equals("C") || keyName.equals("Am")) {
            this.pitchMap = map;
            return;
        }
        
        // ** G Major / E minor -> F#
        // Create next accidental to be put in the map
        accidentalPitch = new Pitch('F');
        // Map the octaves, up to 5 octaves up and down
        for(int i = -numSupportedOctaves; i < numSupportedOctaves; i++){
            map.put(accidentalPitch.octaveTranspose(i),
                        accidentalPitch.octaveTranspose(i).accidentalTranspose(1));
        }
        if( keyName.equals("G") || keyName.equals("Em")) {
            this.pitchMap = map;
            return;
        }

        // ** D Major / B minor -> F#, C#
        // Create next accidental to be put in the map
        accidentalPitch = new Pitch('C');
        // Map the octaves, up to 5 octaves up and down
        for(int i = -numSupportedOctaves; i < numSupportedOctaves; i++){
            map.put(accidentalPitch.octaveTranspose(i),
                        accidentalPitch.octaveTranspose(i).accidentalTranspose(1));
        }
        if( keyName.equals("D") || keyName.equals("Bm")) {
            this.pitchMap = map;
            return;
        }
        
        // ** A Major / F# minor -> F#, C#, G#
        // Create next accidental to be put in the map
        accidentalPitch = new Pitch('G');
        // Map the octaves, up to 5 octaves up and down
        for(int i = -numSupportedOctaves; i < numSupportedOctaves; i++){
            map.put(accidentalPitch.octaveTranspose(i),
                        accidentalPitch.octaveTranspose(i).accidentalTranspose(1));
        }
        if( keyName.equals("A") || keyName.equals("F#m")) {
            this.pitchMap = map;
            return;
        }
        
        // ** E Major / C# minor -> F#, C#, G#, D#
        // Create next accidental to be put in the map
        accidentalPitch = new Pitch('D');
        // Map the octaves, up to 5 octaves up and down
        for(int i = -numSupportedOctaves; i < numSupportedOctaves; i++){
            map.put(accidentalPitch.octaveTranspose(i),
                        accidentalPitch.octaveTranspose(i).accidentalTranspose(1));
        }
        if( keyName.equals("E") || keyName.equals("C#m")) {
            this.pitchMap = map;
            return;
        }
        
        
        // ** B Major / G# minor -> F#, C#, G#, D#, A#
        // Create next accidental to be put in the map
        accidentalPitch = new Pitch('A');
        // Map the octaves, up to 5 octaves up and down
        for(int i = -numSupportedOctaves; i < numSupportedOctaves; i++){
            map.put(accidentalPitch.octaveTranspose(i),
                        accidentalPitch.octaveTranspose(i).accidentalTranspose(1));
        }
        if( keyName.equals("B") || keyName.equals("G#m")) {
            this.pitchMap = map;
             return;
        }
        
        
        //** F# Major / D# minor -> F#, C#, G#, D#, A#, E#
        // Create next accidental to be put in the map
        accidentalPitch = new Pitch('E');
        // Map the octaves, up to 5 octaves up and down
        for(int i = -numSupportedOctaves; i < numSupportedOctaves; i++){
            map.put(accidentalPitch.octaveTranspose(i),
                        accidentalPitch.octaveTranspose(i).accidentalTranspose(1));
        }
        if(keyName.equals("F#") || keyName.equals("D#m")) {
            this.pitchMap = map;
            return;
        }
        
        
        //** C# Major / A# minor -> F#, C#, G#, D#, A#, E#, B#
        // Create next accidental to be put in the map
        accidentalPitch = new Pitch('B');
        // Map the octaves, up to 5 octaves up and down
        for(int i = -numSupportedOctaves; i < numSupportedOctaves; i++){
            map.put(accidentalPitch.octaveTranspose(i),
                        accidentalPitch.octaveTranspose(i).accidentalTranspose(1));
        }
        if( keyName.equals("C#") || keyName.equals("A#m")) {
            this.pitchMap = map;
            return;
        }
        
        /***************************
         * For Flat accidentals
         ***************************/
        map = new HashMap<Pitch,Pitch>();
 
        // F Major / D minor -> Bb
        // Create next accidental to be put in the map
        accidentalPitch = new Pitch('B');
        // Map the octaves, up to 5 octaves up and down
        for(int i = -numSupportedOctaves; i < numSupportedOctaves; i++){
            map.put(accidentalPitch.octaveTranspose(i),
                        accidentalPitch.octaveTranspose(i).accidentalTranspose(-1));
        }
        if( keyName.equals("F") || keyName.equals("Dm")) {
            this.pitchMap = map;
            return;
        }
        
        
        // Bb Major / G minor -> Bb, Eb
        // Create next accidental to be put in the map
        accidentalPitch = new Pitch('E');
        // Map the octaves, up to 5 octaves up and down
        for(int i = -numSupportedOctaves; i < numSupportedOctaves; i++){
            map.put(accidentalPitch.octaveTranspose(i),
                        accidentalPitch.octaveTranspose(i).accidentalTranspose(-1));
        }
        if( keyName.equals("Bb") || keyName.equals("Gm")) {
            this.pitchMap = map;
            return;
        }
        
        
        // Eb Major / C minor -> Bb, Eb, Ab
        // Create next accidental to be put in the map
        accidentalPitch = new Pitch('A');
        // Map the octaves, up to 5 octaves up and down
        for(int i = -numSupportedOctaves; i < numSupportedOctaves; i++){
            map.put(accidentalPitch.octaveTranspose(i),
                        accidentalPitch.octaveTranspose(i).accidentalTranspose(-1));
        }
        if( keyName.equals("Eb") || keyName.equals("Cm")) {
            this.pitchMap = map;
            return;
        }
        
        
        // Ab Major / F minor -> Bb, Eb, Ab, Db
        // Create next accidental to be put in the map
        accidentalPitch = new Pitch('D');
        // Map the octaves, up to 5 octaves up and down
        for(int i = -numSupportedOctaves; i < numSupportedOctaves; i++){
            map.put(accidentalPitch.octaveTranspose(i),
                        accidentalPitch.octaveTranspose(i).accidentalTranspose(-1));
        }
        if( keyName.equals("Ab") || keyName.equals("Fm")) {
            this.pitchMap = map;
            return;
        }
        
        
        // Db Major / Bb minor -> Bb, Eb, Ab, Db, Gb
        // Create next accidental to be put in the map
        accidentalPitch = new Pitch('G');
        // Map the octaves, up to 5 octaves up and down
        for(int i = -numSupportedOctaves; i < numSupportedOctaves; i++){
            map.put(accidentalPitch.octaveTranspose(i),
                        accidentalPitch.octaveTranspose(i).accidentalTranspose(-1));
        }
        if( keyName.equals("Db") || keyName.equals("Bbm")) {
            this.pitchMap = map;
            return;
        }
        

        // Gb Major / Eb minor -> Bb, Eb, Ab, Db, Gb, Cb
        // Create next accidental to be put in the map
        accidentalPitch = new Pitch('C');
        // Map the octaves, up to 5 octaves up and down
        for(int i = -numSupportedOctaves; i < numSupportedOctaves; i++){
            map.put(accidentalPitch.octaveTranspose(i),
                        accidentalPitch.octaveTranspose(i).accidentalTranspose(-1));
        }
        if( keyName.equals("Gb") || keyName.equals("Ebm")) {
            this.pitchMap = map;
            return;
        }
        
        
        // Cb Major / Ab minor -> Bb, Eb, Ab, Db, Gb, Cb, Fb
        // Create next accidental to be put in the map
        accidentalPitch = new Pitch('F');
        // Map the octaves, up to 5 octaves up and down
        for(int i = -numSupportedOctaves; i < numSupportedOctaves; i++){
            map.put(accidentalPitch.octaveTranspose(i),
                        accidentalPitch.octaveTranspose(i).accidentalTranspose(-1));
        }
        if( keyName.equals("Cb") || keyName.equals("Abm")) {
            this.pitchMap = map;
            return;
        }
        
        // Otherwise invalid key
        throw new IllegalArgumentException();
    }
    
    /**
     * Gives a Map that matches each pitch correctly to its shift, that is
     * represented by an integer. It represents up to 5 octaves for each key.
     * @return Copy of pitchMap
     */
    public Map<Pitch,Pitch> getPitchMap() {
        Map<Pitch,Pitch> newMap = new HashMap<Pitch,Pitch>();
        newMap.putAll(this.pitchMap);
        return newMap;
    }
    
    public String getKeyName() {
        return this.keyName;
    }
}