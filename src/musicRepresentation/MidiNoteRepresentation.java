package musicRepresentation;

import sound.Pitch;

/**
 * Represents a note that can be interpreted as a MIDI note.
 * @author Nicholas M. Mizoguchi
 * 
 */
public class MidiNoteRepresentation {
    private final Pitch pitch;
    private final int startTick;
    private final int numTicks;

    /**
     * Creates a new instance of MidiNoteRepresentation.
     * 
     * @param pitch
     *            a valid Pitch.
     * @param startTick
     *            a non-negative integer.
     * @param numTicks
     *            a non-negative integer.
     */
    public MidiNoteRepresentation(Pitch pitch, int startTick, int numTicks) {
        this.pitch = pitch;
        this.startTick = startTick;
        this.numTicks = numTicks;
    }

    /**
     * @return Pitch of the midi note that the object represents.
     */
    public Pitch getPitch() {
        return pitch;
    }

    /**
     * @return integer that represents which tick of the music the midi should
     *         be played.
     */
    public int getStartTick() {
        return startTick;
    }

    /**
     * @return integer that represents for how many ticks the midi should be
     *         played.
     */
    public int getNumTicks() {
        return numTicks;
    }

    /**
     * Return a string that represents the object.
     */
    @Override
    public String toString() {
        return this.pitch.toString();
    }
}
