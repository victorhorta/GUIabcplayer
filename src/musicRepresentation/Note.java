package musicRepresentation;

import sound.Pitch;

/**
 * The Note class is composed of a Pitch object and a durationMultiplier
 * (defaults to 1). Note is mutable. The information stored in Note will be used
 * to later generate PreMidiNotes.
 * 
 * @author Eric
 * 
 */
public class Note {
	Pitch pitch;
	double durationMultiplier;

	/**
	 * The first constructor method of Note, used when no durationMultiplier is
	 * given for the Note.
	 * 
	 * @param pitch
	 *            the pitch of the Note
	 */
	public Note(Pitch pitch) {
		this.pitch = pitch;
		this.durationMultiplier = 1;
	}

	
	/**
	 * The second constructor method of Note, used when a durationMultiplier is
	 * given for the Note.
	 * 
	 * @param pitch
	 *            the pitch of the Note
	 * @param durationMultiplier
	 *            the length the Note should be played for in comparison to the
	 *            default length of the piece. Must be > 0.
	 */
	public Note(Pitch pitch, double durationMultiplier) {
		this.pitch = pitch;
		this.durationMultiplier = durationMultiplier;
	}
	
	/**
	 * @return the pitch of the Note
	 */
	public Pitch getPitch() {
		return pitch;
	}

	/**
	 * @return the durationMultiplier of the Note.
	 */
	public double getDurationMultiplier() {
		return durationMultiplier;
	}
	
	/**
	 * Mutator Method. This method is used to correctly set the
	 * durationMultiplier of a tuplet. The only different thing about a tuplet
	 * is thats its constituent notes have different lengths.
	 * 
	 * @param factor
	 *            the factor to multiply durationMultiplier by. Must be > 0.
	 */
	public void multiplyDurationMultiplier(double factor) {
		durationMultiplier = durationMultiplier * factor;
	}

	/**
	 * Mutator Method. This method is used to set the durationMultiplier on a
	 * Note in the case when the Note is part of a multi-note chord. As per the
	 * new specifications, the length of all notes in a Chord is determined by
	 * the duration of its first note. This method is used to correct the
	 * durationMultiplier of the other Notes. Pre-condition:
	 * newDurationMultiplier > 0
	 * 
	 * @param newDurationMultiplier
	 *            the number durationMultiplier should be set to. Must be > 0.
	 */
	public void setDurationMultiplier(double newDurationMultiplier) {
		durationMultiplier = newDurationMultiplier;
	}
	
	
	/**
	 * Sets the value of pitch to newPitch. Used in the constructor method of
	 * the Measure class.
	 * 
	 * @param newPitch
	 *            the new pitch of the Note
	 */
	public void setPitch(Pitch newPitch) {
		pitch = newPitch;
	}
	
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return false;
        Note that = (Note) obj;
        return this.pitch.equals(that.pitch)
            && this.durationMultiplier == that.durationMultiplier;
    }
    
    @Override
    public String toString() {
    	String string = "" + pitch.toString() + durationMultiplier;
    	return string;
    }
}