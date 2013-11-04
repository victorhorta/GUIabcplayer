package musicRepresentation;

/**
 * The Rest class implements SoundUnit along with the Chord class. A Rest
 * indicates a period of silence in a voice.
 * 
 * @author Eric
 * 
 */
public class Rest implements SoundUnit{
	double durationMultiplier;
	
	/**
	 * The first constructor of the Rest class. durationMultiplier defaults to
	 * 1.
	 */
	public Rest() {
		this.durationMultiplier = 1;
	}
	
	/**
	 * The second constructor of the Rest class.
	 * 
	 * @param durationMultiplier
	 *            the durationMultiplier the Rest should have
	 */
	public Rest(double durationMultiplier) {
		this.durationMultiplier = durationMultiplier;
	}
	
	/* (non-Javadoc)
	 * @see musicRepresentation.SoundUnit#getDurationMultiplier()
	 */
	@Override
	public double getDurationMultiplier() {
		return durationMultiplier;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return false;
        Rest that = (Rest) obj;
        return this.durationMultiplier == that.durationMultiplier;
    }
	
    @Override
    public String toString() {
        String s = "z" + this.durationMultiplier;
        return s;
    }
}
