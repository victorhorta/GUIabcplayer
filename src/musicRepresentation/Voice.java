package musicRepresentation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Voice class represents a Voice in an abc music file. Voice is composed of
 * List<Measure> and is identified by a voiceName. A Voice is in charge of
 * handling the repeats that occur within its measures.
 * 
 * @author Eric
 * 
 */
public class Voice {
    String voiceName;
    private List<Measure> listOfMeasures;

	/**
	 * @param voiceName
	 *            String representing the name (label) of the voice. Can be
	 *            empty.
	 * @param listOfMeasures
	 *            List containing measures that will belong to this voice (can
	 *            be empty).
	 */
    Voice(String voiceName, List<Measure> listOfMeasures) {
        this.voiceName = voiceName;
        this.listOfMeasures = listOfMeasures;
    }

	/**
	 * Gets the list of Measures from the Voice
	 * 
	 * @return a reference to the list of Measures
	 */
    List<Measure> getListOfMeasures() {
        return listOfMeasures;
    }
    
    String getVoiceName() {
    	return voiceName;
    }
    
	/**
	 * Extends this voice with thatVoice by concatenating this.listOfMeasures
	 * and thatVoice.listOfMeasures
	 * 
	 * @param thatVoice
	 *            the voice that we want to extend this voice Object with
	 */
    void extend(Voice thatVoice) {
    	this.listOfMeasures.addAll(thatVoice.getListOfMeasures());
    }
    
	/**
	 * Returns a new Voice object whose listOfMeasures has been corrected such
	 * that all repeats are handled correctly. 
	 * 
	 * Repeats are handled as follows:
	 * 
	 * Barlines "|" do not affect repeats. 
	 * Double Barlines "||" do not affect repeats.
	 * Begin Repeat "|:" marks the beginning of a repeated section.
	 * End Repeat ":|" makes the piece repeat from either a Begin Repeat, the
	 * 		beginning of the piece, or End Bold Double Bar Lines, whichever appears closest
	 * Begin Bold Double Barlines "[|" marks the beginning of the section.
	 * End Bold Double Barlines "|]" marks the beginning of the next section 
	 * 		(i.e repeats begin from the measure following the one ending with this marker)
	 * Alternate Ending 1 "[1" marks the first alternate ending of a repeated section
	 * Alternate Ending 2 "[2" marks the second alternate ending of a repeated section
	 * 
	 * @return the Voice Object with its listOfMeasures corrected such that
	 *         repeats are handled correctly
	 */
    Voice fixRepeats() {
        List<Measure> newListOfMeasures = new ArrayList<Measure>();
        int measureToRepeatFrom = 0; // repeats to the beginning of piece unless otherwise noted
        boolean firstAlternateEndingSeen = false;
        int firstAlternateEndingBegins = 0;
        
        int i = 0;
        for (Measure m: listOfMeasures) {
        	// handle "|:" and "[|" which mark the beginning of repeated sections
        	if (m.getBeginningBarLine().equals("|:")
        			|| m.getBeginningBarLine().equals("[|")) {
        		measureToRepeatFrom = i;
        	}
        	
        	// handle "[1" which marks the first alternate ending of a repeated section
        	if (m.getBeginningBarLine().equals("[1")) {
        		firstAlternateEndingBegins = i;
        		firstAlternateEndingSeen = true;
        	}
        	++i;
        	newListOfMeasures.add(m);
        	
        	// handle repeats
        	if (m.getEndingBarLine().equals(":|")) {
        		if (!firstAlternateEndingSeen) {
        			// Note: toIndex on subList(int, int) is exclusive
        			newListOfMeasures.addAll(listOfMeasures.subList(measureToRepeatFrom, i));
        	}
        		else {
        			newListOfMeasures.addAll(listOfMeasures.subList(measureToRepeatFrom, firstAlternateEndingBegins));
        			firstAlternateEndingSeen = false;
        		}
        	}
        	
        	// handle End Bold Double Barlines "|]"
        	if (m.getEndingBarLine().equals("|]")) {
        		measureToRepeatFrom = i;
        	}
        }
        
        this.setListOfMeausres(newListOfMeasures);
        return this;
    }
    
    /**
     * Sets the listOfMeasures to a the newListOfMeasures
     * 
     * @param newListOfMeasures
     * 			the List<Measure> that listOfMeasures should be set to
     */
    public void setListOfMeausres(List<Measure> newListOfMeasures) {
    	listOfMeasures = newListOfMeasures;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return false;
        Voice that = (Voice) obj;
        return this.voiceName == that.voiceName
        		&& this.getListOfMeasures().equals(that.getListOfMeasures());
    }
    
    @Override
    public String toString() {
        String s = "";
        Iterator<Measure> iterator = this.listOfMeasures.iterator();
        while(iterator.hasNext()) {
            Measure measure = iterator.next();
            s = s.concat(measure.toString()+" ");
        }
        return s;
    }
}
