package musicRepresentation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sound.Pitch;

/**
 * Represents a Measure. Measure is mutable.
 * 
 * @author Victor
 * 
 */
public class Measure {
    private List<SoundUnit> listOfSoundUnits;
    private String beginningBarLine;
    private String endingBarLine;    

    /**
	 * Creates a new Measure and applies the key signature to its chords.
	 * 
	 * @param keySignature
	 *            the corresponding key signature for the measure
	 * @param chordsAndRestsInMeasure
	 *            a list of the sound units (chords and rests) that compose the
	 *            measure. Must be non-empty.
	 * @param endingBarLine
	 *            a String representing the ending bar line. Requires a valid
	 *            bar line (can be "|", "||", "|]", ":|").
	 */
    Measure(KeySignature keySignature, List<SoundUnit> chordsAndRestsInMeasure, String endingBarLine) {
        // Assigning the endingBarLine.
        this.endingBarLine = endingBarLine;
        this.beginningBarLine = "";
        
        // Firstly, we need a copy of Pitch map from keySignature.
        Map<Pitch, Pitch> measureSignature = keySignature.getPitchMap();

        this.listOfSoundUnits = new ArrayList<SoundUnit>(chordsAndRestsInMeasure);
        
        // Secondly, we need to iterate over all SoundUnits of the measure
        for (SoundUnit sUnit : listOfSoundUnits) {
            
            // If we are dealing with a Chord instance, we need to work on it (not on a Rest!)
            if (sUnit instanceof Chord) {
                
                // Then, we iterate over all notes inside our Chord.
                for (Note note : ((Chord) sUnit).getListOfNotesInChord()) {
                    
                    if (note.getPitch().isHasAccidental()) {
                        // If the actual note being iterated has an Accidental, we need to update
                        // the measureSignature map!
                        measureSignature.put(new Pitch(note.getPitch().getValue(), 0, note
                                .getPitch().getOctave(), false), note.getPitch());
                        // By putting the key onto the Map, we override the previous value, updating
                        // the measure temp map.
                    } else {
                        // Otherwise, we need to update the note from the measure, applying the
                        // keySignature value if it is being mapped there.
                        if (measureSignature.containsKey(note.getPitch())) {
                            note.setPitch(measureSignature.get(note.getPitch()));
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Creates a new Measure and applies the key signature to its chords.
     * 
     * @param keySignature
     *            the corresponding key signature for the measure
     * @param chordsAndRestsInMeasure
     *            a list of the sound units (chords and rests) that compose the
     *            measure. Must be non-empty.
     * @param beginningBarLine
     *            a String representing the beginning bar line. Should be "[|",
     *            "|:", "[1", or "[2". A [1 can also appear midway through a
     *            measure. In that case, the [1 is still stored as a
     *            beginningBarLine but beginFirstAlternateEnding is set to a
     *            nonzero int.
     * @param endingBarLine
     *            a String representing the ending bar line. Requires a valid
     *            bar line (can be "|", "||", "|]", ":|").
     */
    Measure(KeySignature keySignature, List<SoundUnit> chordsAndRestsInMeasure, String beginningBarLine, String endingBarLine) {
        this(keySignature, chordsAndRestsInMeasure, endingBarLine);
        this.beginningBarLine = beginningBarLine;
    }

    /**
     * Returns the list of sound units from this measure.
     * 
     * @return the listOfSoundUnits
     */
    List<SoundUnit> getListOfSoundUnits() {
        return listOfSoundUnits;
    }
    
    /**
     * Returns a String corresponding to the beginning bar line of the measure.
     * 
     * @return the beginning bar line of the measure.
     */
    String getBeginningBarLine() {
    	return beginningBarLine;
    }

    /**
     * Returns the list of sound units from this measure.
     * 
     * @return the listOfSoundUnits
     */
    String getEndingBarLine() {
        return endingBarLine;
    }
    
    /**
     * Sets the endingBarLine to 
     * 
     * @param newEndingBarLine
     */
    void setEndingBarLine(String newEndingBarLine) {
    	endingBarLine = newEndingBarLine;
    }

    /**
     * Takes a List<String> and matches it to the correspondent Chord. If it encounters a "|" then
     * it makes the rest of the Chords in the measure have the "*" for its syllable.
     * Measure.applyLyrics() returns the input List<String> without the Strings that weren't
     * matched.
     * 
     * @param originalListOfStrings
     * @return a list of Strings excluding the Strings that weren't matched.
     */
    List<String> applyLyrics(List<String> originalListOfStrings) {

        List<String> lyrics = originalListOfStrings;

        Iterator<SoundUnit> soundUnitIterator = listOfSoundUnits.iterator();
        Iterator<String> syllablesIterator = lyrics.iterator();

        // newStart represents from the position of the syllable that was not appended 
        // to a Chord yet.

        int newStart = 0;

        while (soundUnitIterator.hasNext()) {
            // Iterates over listOfSoundUnits
            SoundUnit current = (SoundUnit) soundUnitIterator.next();

            // Apply the lyric only to chords
            if (current instanceof Chord) {

                // If there is a syllable to insert
                if (syllablesIterator.hasNext()) {
                    // Get the next syllable to apply
                    String syllable = (String) syllablesIterator.next();
                    newStart++;

                    
                    // If a barline appears, stops. The other syllables will be put in the next
                    // measure

                    if (syllable.equals("|")) {
                        break;

                    // Substitute stars with empty strings
                    } else if (syllable.equals("*")) {
                        syllable = "";
                    }

                    // Put the syllable in the chord
                    ((Chord) current).setSyllable(syllable);
                }
            }
        }
        
        // If the next string is a |, then skip it
        if (syllablesIterator.hasNext()) {
            if (syllablesIterator.next().equals("|")) {
                newStart++;
            }
        }

        if (newStart < originalListOfStrings.size()) {
            return originalListOfStrings.subList(newStart, originalListOfStrings.size());
        }

        else {
            return new ArrayList<String>();
        }
    }

    @Override
    public String toString() {
        StringBuilder measureAsText = new StringBuilder();
        if (!beginningBarLine.isEmpty()) {
            measureAsText.append(beginningBarLine + " ");
        }
        for (SoundUnit su : listOfSoundUnits)
            measureAsText.append(su.toString() + " ");
        measureAsText.append(endingBarLine);
        return measureAsText.toString();
    }
}
