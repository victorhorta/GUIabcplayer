package musicRepresentation;

/**
 * The SoundUnit interface is implemented by the Rest and Chord class. A Measure
 * is composed of a List<SoundUnit>.
 * 
 * @author Eric
 * 
 */
public interface SoundUnit {
	/**
	 * @return durationMultiplier representing the length of the Chord or Rest
	 *         in comparison with the default length of the piece
	 */
	public double getDurationMultiplier();
}
