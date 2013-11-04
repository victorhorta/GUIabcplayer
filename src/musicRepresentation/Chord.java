package musicRepresentation;

import java.util.List;

/**
 * The Chord class implements the SoundUnit class. A Chord is composed of a list
 * of one or more Notes in the Chord. As per the new specifications, the
 * duration of a Chord is determined by the first Note in the Chord. All other
 * Notes in the Chord should have that same duration. A Chord also has a
 * syllable, represented as a String, attached to it.
 * 
 * @author Eric
 * 
 */
public class Chord implements SoundUnit {
	List<Note> listOfNotesInChord;
	String syllable;
	double chordDurationMultiplier;
	
	/**
	 * The constructor method of the Chord class. The chordDurationMultiplier is
	 * set to be the duration of the first note's durationMultiplier. All other
	 * Notes in the Chord are set to have this first note's durationMultiplier
	 * as well.
	 * 
	 * @param listOfNotesInChord
	 *            Must be the order in which the Notes appear in the Chord.
	 *            Cannot be empty.
	 */
    public Chord(List<Note> listOfNotesInChord) {
        this.listOfNotesInChord = listOfNotesInChord;
        this.chordDurationMultiplier = listOfNotesInChord.get(0).getDurationMultiplier();
        this.syllable = "";

        for (Note n : listOfNotesInChord) {
            n.setDurationMultiplier(chordDurationMultiplier);
        }
    }
	
	/**
	 * Mutator Method. This method is used in the Measure class to get the
	 * List<Note> from the Chord and apply the key signature.
	 * 
	 * @return listOfNotesInChord
	 */
	public List<Note> getListOfNotesInChord() {
		return listOfNotesInChord;
	}
	
	/**
	 * @return syllable attached with the Chord
	 */
	public String getSyllable() {
		return syllable;
	}
	
	/* (non-Javadoc)
	 * @see musicRepresentation.SoundUnit#getDurationMultiplier()
	 */
	@Override
	public double getDurationMultiplier() {
		return chordDurationMultiplier;
	}
	
	/**
	 * @param syllable the syllable to be sung on this Chord
	 */
	public void setSyllable(String syllable) {
		this.syllable = syllable;
	}
	
	/**
	 * Mutator Method. This method is used to correctly set the duration length
	 * of the Notes in a Chord. This method is used when the duration of Notes
	 * are affected by being inside of a Tuplet.
	 * 
	 * "In the case of a triplet, you should play each note or chord for 2/3 of
	 * the original duration. For a duplet, 3/2 of the original durations. And
	 * for a quadruplet, 3/4 of the original durations."
	 * 
	 * @param factor
	 *            the double to multiply each note.durationMultipler by. Should
	 *            be either 2/3, 3/2, or 3/4.
	 */
    public void multiplyDurationMultiplier(double factor) {
        for (Note n : listOfNotesInChord) {
            n.multiplyDurationMultiplier(factor);
        }
        chordDurationMultiplier = chordDurationMultiplier * factor;
    }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return false;
        Chord that = (Chord) obj;
        return this.syllable.equals(that.syllable)
                && this.chordDurationMultiplier == that.chordDurationMultiplier
                && this.listOfNotesInChord.equals(that.listOfNotesInChord);
    }
	
    @Override
    public String toString() {
        // the syllable is not currently printed in the toString() method
        String string = "[";
        for (Note n : listOfNotesInChord) {
            string += " " + n.toString();
        }
        string += "]";
        return string;
    }
}
