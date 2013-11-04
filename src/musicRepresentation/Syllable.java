package musicRepresentation;

/**
 * This class represents a syllable to be shown by the karaoke and also the time
 * when it occurs in the music that it belongs to.
 * 
 * @author Victor
 * 
 */
public class Syllable {
    private final int startTick;
    private final String syllable;

    /**
     * Creates an instance of Syllable
     * @param syllable the syllable itself
     * @param startTick the tick where it happens in the music
     */
    public Syllable(String syllable, int startTick) {
        this.startTick = startTick;
        this.syllable = syllable;
    }

    /**
     * @return The starting tick where the syllable should be shown.
     */
    public int getStartTick() {
        return this.startTick;
    }

    /**
     * @return The syllable to be shown.
     */
    public String getSyllable() {
        return this.syllable;
    }
}
