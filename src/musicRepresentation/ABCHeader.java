package musicRepresentation;

/**
 * This class stores basic information about an .abc music. It's an immutable
 * class.
 * 
 * @author Nicholas M. Mizoguchi
 * 
 */
public class ABCHeader {
    private final String title;
    private final String composer;
    private final int beatsPerMinute;
    private final int ticksPerBeat;
    private final int beatsPerMeasure; // Denominator of Meter
    private final double whatNoteGetsTheBeat; // Divisor of Meter
    private final double defaultNoteLength;
    private final double bpmNoteLength;
    private final KeySignature keySignature;

    /**
     * Creates an ABCHeader object by informing all its attributes.
     * 
     * @param title
     * @param composer
     * @param beatsPerMinute
     * @param bpmNoteLength
     * @param ticksPerBeat
     * @param whatNoteGetsTheBeat
     * @param beatsPerMeasure
     * @param defaultNoteLength
     * @param key
     */
    public ABCHeader(String title, String composer, int beatsPerMinute,
            double bpmNoteLength, int ticksPerBeat, double whatNoteGetsTheBeat,
            int beatsPerMeasure, double defaultNoteLength, String key) {
        this.title = title;
        this.composer = composer;
        this.beatsPerMinute = beatsPerMinute;
        this.ticksPerBeat = ticksPerBeat;
        this.whatNoteGetsTheBeat = whatNoteGetsTheBeat;
        this.beatsPerMeasure = beatsPerMeasure;
        this.defaultNoteLength = defaultNoteLength;
        this.bpmNoteLength = bpmNoteLength;
        this.keySignature = new KeySignature(key);
    }

    /**
     * Gives the information of how many beats per minute of the music that
     * Header represents. Good to remember that the representation of bpm is in
     * the bpmNoteLength time.
     * 
     * @return beatsPerMinute which is always an integer.
     */
    public int getBeatsPerMinute() {
        return beatsPerMinute;
    }

    /**
     * Gives the information of what note is the beat default. For instance, if
     * the bpm is 60 and the bpmNoteLength is 1/4 we will play 1 quarter note
     * per second.
     * 
     * @return beatsPerMinute which is always an integer.
     */
    public double getBpmNoteLength() {
        return bpmNoteLength;
    }

    /**
     * Gives the information of how many ticks per beat of the music that Header
     * represents. The value of ticksPerBeat is defined such that the smallest
     * note played is still an integer number, in relation to number of ticks.
     * It covers really short beats and triplets (least common divisor).
     * 
     * @return beatsPerMinute which is always an integer.
     */
    public int getTicksPerBeat() {
        // ticksPerBeat is defined as 2^9*3 = 1536 ticks.
        return ticksPerBeat;
    }

    /**
     * Gives the Title of the piece of music ABCHeader represents
     * 
     * @return the title String itself.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gives the Composer of the piece of music ABCHeader represents
     * 
     * @return the composer String itself.
     */
    public String getComposer() {
        return composer;
    }

    /**
     * Gives the denominator of the Meter.
     * 
     * @return an integer that represents how many beats (of type NoteLength) we
     *         have per measure.
     */
    public int getBeatsPerMeasure() {
        return beatsPerMeasure;
    }

    /**
     * Gives the fraction that represents what note gets the time of a beat. For
     * instance, a quarter note is represented by 0.25
     * 
     * @return a double that represents the fraction.
     */
    public double getWhatNoteGetsTheBeat() {
        return whatNoteGetsTheBeat;
    }

    /**
     * Gives the default note length of the piece. It is represented as a
     * double. For instance, a quarter note is represented by 0.25
     * 
     * @return a double that represents the default note length.
     */
    public double getDefaultNoteLength() {
        return defaultNoteLength;
    }

    /**
     * Gives an object that represents the key signature.
     * 
     * @return KeySignature object that represents the signature of the piece.
     */
    public KeySignature getKeySignature() {
        // KeySignature is immutable. OK to give the pointer.
        return keySignature;
    }

    /**
     * Returns a string that represents the header (formated)
     */
    @Override
    public String toString() {
        String info;
        info = "Title: " + this.title + "\n";
        info = info.concat("Composer: " + this.composer + "\n");
        info = info.concat("Tempo: " + bpmNoteLength + "="
                + this.beatsPerMinute + "\n");
        info = info.concat("Default note(beat): " + this.defaultNoteLength
                + "\n");
        info = info.concat("Ticks per beat: " + this.ticksPerBeat + "\n");
        info = info.concat("Meter note: " + this.whatNoteGetsTheBeat + "\n");
        info = info.concat("Beats per Measure: " + this.beatsPerMeasure + "\n");
        info = info.concat("Key: " + this.keySignature.getKeyName() + "\n");
        return info;
    }
}
