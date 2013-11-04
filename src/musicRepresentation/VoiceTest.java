package musicRepresentation;

import java.util.*;

import static org.junit.Assert.*;

import org.junit.Test;

import sound.Pitch;

public class VoiceTest {
	// Tests the constructor method of the Voice class
	@Test
	public void testVoice_NonEmptyArgs() {
		// instantiate a Note
		Note note1 = new Note(new Pitch('C'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord = new ArrayList<Note>();
		listOfNotesInChord.add(note1);
		Chord chord1 = new Chord(listOfNotesInChord);
		// instantiate a Measure
		KeySignature keySignature = new KeySignature("Em"); // F# only note affected
		List<SoundUnit> chordsAndRestsInMeasure = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure.add(chord1);
		String endingBarLine = "|";
		Measure measure1 = new Measure(keySignature, chordsAndRestsInMeasure, endingBarLine);
		// instantiate a Voice
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		listOfMeasures.add(measure1);
		new Voice("voice1", listOfMeasures);
	}
	
	// Tests the constructor method of the Voice class with an empty voiceName
	@Test
	public void testVoice_EmptyVoiceName() {
		// instantiate a Note
		Note note1 = new Note(new Pitch('C'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord = new ArrayList<Note>();
		listOfNotesInChord.add(note1);
		Chord chord1 = new Chord(listOfNotesInChord);
		// instantiate a Measure
		KeySignature keySignature = new KeySignature("Em"); // F# only note affected
		List<SoundUnit> chordsAndRestsInMeasure = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure.add(chord1);
		String endingBarLine = "|";
		Measure measure1 = new Measure(keySignature, chordsAndRestsInMeasure, endingBarLine);
		// instantiate a Voice
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		listOfMeasures.add(measure1);
		new Voice("", listOfMeasures);
	}
	
	// Tests the constructor method of the Voice class with an empty listOfMeasures
	@Test
	public void testVoice_EmptyListOfMeasures() {
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		new Voice("", listOfMeasures);
	}
	
	// Tests the getListOfMeasures method with a nonempty listOfMeasures
	@Test
	public void testGetListOfMeasures_NonEmptyListOfMeasures() {
		// instantiate a Note
		Note note1 = new Note(new Pitch('C'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord = new ArrayList<Note>();
		listOfNotesInChord.add(note1);
		Chord chord1 = new Chord(listOfNotesInChord);
		// instantiate a Measure
		KeySignature keySignature = new KeySignature("Em"); // F# only note affected
		List<SoundUnit> chordsAndRestsInMeasure = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure.add(chord1);
		String endingBarLine = "|";
		Measure measure1 = new Measure(keySignature, chordsAndRestsInMeasure, endingBarLine);
		// instantiate a Voice
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		listOfMeasures.add(measure1);
		Voice v1 = new Voice("voice1", listOfMeasures);
		assertTrue(v1.getListOfMeasures().equals(listOfMeasures));
	}
	
	// Tests the getListOfMeasures method with an empty listOfMeasures
	@Test
	public void testGetListOfMeasures_EmptyListOfMeasures() {
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		Voice v1 = new Voice("", listOfMeasures);
		assertTrue(v1.getListOfMeasures().equals(listOfMeasures));
	}
	
	// Tests the getVoiceName method
	@Test
	public void testGetVoiceName_EmptyVoiceName() {
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		Voice v1 = new Voice("voice1", listOfMeasures);
		assertEquals(v1.getVoiceName(), "voice1");
	}
	
	// Tests the extend method of the Voice class with two Voices with nonempty listOfMeasures
	@Test
	public void testExtend_NonEmptyListOfMeasures() {
		// Instantiate the first voice
		// instantiate a Note
		Note note1 = new Note(new Pitch('C'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord = new ArrayList<Note>();
		listOfNotesInChord.add(note1);
		Chord chord1 = new Chord(listOfNotesInChord);
		// instantiate a Measure
		KeySignature keySignature = new KeySignature("Em"); // F# only note affected
		List<SoundUnit> chordsAndRestsInMeasure = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure.add(chord1);
		String endingBarLine = "|";
		Measure measure1 = new Measure(keySignature, chordsAndRestsInMeasure, endingBarLine);
		// instantiate a Voice
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		listOfMeasures.add(measure1);
		Voice voice1 = new Voice("voice1", listOfMeasures);
		
		// Instantiate a voice to extend the first voice
		// instantiate a Note
		Note note2 = new Note(new Pitch('C'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord2 = new ArrayList<Note>();
		listOfNotesInChord2.add(note2);
		Chord chord2 = new Chord(listOfNotesInChord2);
		// instantiate a Measure
		KeySignature keySignature2 = new KeySignature("Em"); // F# only note affected
		List<SoundUnit> chordsAndRestsInMeasure2 = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure2.add(chord2);
		String endingBarLine2 = "|";
		Measure measure2 = new Measure(keySignature2, chordsAndRestsInMeasure2, endingBarLine2);
		// instantiate a Voice
		List<Measure> listOfMeasures2 = new ArrayList<Measure>();
		listOfMeasures2.add(measure2);
		Voice extendedVoice1 = new Voice("voice1", listOfMeasures2);
		
		List<Measure> result = new ArrayList<Measure>();
		result.add(measure1);
		result.add(measure2);
		voice1.extend(extendedVoice1);
		assertTrue(voice1.getListOfMeasures().equals(result));
	}
	
	// Tests the extend method of the Voice class with two Voices with large listOfMeasures
	@Test
	public void testExtend_LargeListOfMeasures() {
		// Instantiate the first voice
		// instantiate a Note
		Note note1 = new Note(new Pitch('C'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord = new ArrayList<Note>();
		listOfNotesInChord.add(note1);
		Chord chord1 = new Chord(listOfNotesInChord);
		// instantiate a Measure
		KeySignature keySignature = new KeySignature("Em"); // F# only note affected
		List<SoundUnit> chordsAndRestsInMeasure = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure.add(chord1);
		String endingBarLine = "|";
		Measure measure1 = new Measure(keySignature, chordsAndRestsInMeasure, endingBarLine);
		// instantiate a 2nd Measure
		Measure measure2 = new Measure(keySignature, chordsAndRestsInMeasure, endingBarLine);
		// instantiate a Voice
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		listOfMeasures.add(measure1);
		listOfMeasures.add(measure2);
		Voice voice1 = new Voice("voice1", listOfMeasures);
		
		// Instantiate a voice to extend the first voice
		// instantiate a Note
		Note note2 = new Note(new Pitch('C'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord2 = new ArrayList<Note>();
		listOfNotesInChord2.add(note2);
		Chord chord2 = new Chord(listOfNotesInChord2);
		// instantiate a 3rd Measure
		KeySignature keySignature2 = new KeySignature("Em"); // F# only note affected
		List<SoundUnit> chordsAndRestsInMeasure2 = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure2.add(chord2);
		String endingBarLine2 = "|";
		Measure measure3 = new Measure(keySignature2, chordsAndRestsInMeasure2, endingBarLine2);
		// instantiate a 4th Measure
		Measure measure4 = new Measure(keySignature2, chordsAndRestsInMeasure, endingBarLine2);
		// instantiate a Voice
		List<Measure> listOfMeasures2 = new ArrayList<Measure>();
		listOfMeasures2.add(measure3);
		listOfMeasures2.add(measure4);
		Voice extendedVoice1 = new Voice("voice1", listOfMeasures2);
		
		List<Measure> result = new ArrayList<Measure>();
		result.add(measure1);
		result.add(measure2);
		result.add(measure3);
		result.add(measure4);
		voice1.extend(extendedVoice1);
		assertTrue(voice1.getListOfMeasures().equals(result));
	}
	
	// Tests the equals method of the Voice class on two Voices that should be
	// equal
	@Test
	public void testEquals_SameVoiceNameSameListOfMeasures() {
		// instantiate a Note
		Note note1 = new Note(new Pitch('C'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord = new ArrayList<Note>();
		listOfNotesInChord.add(note1);
		Chord chord1 = new Chord(listOfNotesInChord);
		// instantiate a Measure
		KeySignature keySignature = new KeySignature("Em"); // F# only note affected
		List<SoundUnit> chordsAndRestsInMeasure = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure.add(chord1);
		String endingBarLine = "|";
		Measure measure1 = new Measure(keySignature, chordsAndRestsInMeasure, endingBarLine);
		// instantiate a Voice
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		listOfMeasures.add(measure1);
		Voice voice1 = new Voice("voice1", listOfMeasures);
		Voice voice2 = new Voice("voice1", listOfMeasures);
		
		assertTrue(voice1.equals(voice2));
	}
	
	// Tests the equals method of the Voice class on two Voices that have the
	// same listOfMeasures but different voiceNames
	@Test
	public void testEquals_DifferentVoiceNameSameListOfMeasures() {
		// instantiate a Note
		Note note1 = new Note(new Pitch('C'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord = new ArrayList<Note>();
		listOfNotesInChord.add(note1);
		Chord chord1 = new Chord(listOfNotesInChord);
		// instantiate a Measure
		KeySignature keySignature = new KeySignature("Em"); // F# only note affected
		List<SoundUnit> chordsAndRestsInMeasure = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure.add(chord1);
		String endingBarLine = "|";
		Measure measure1 = new Measure(keySignature, chordsAndRestsInMeasure, endingBarLine);
		// instantiate a Voice
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		listOfMeasures.add(measure1);
		Voice voice1 = new Voice("voice1", listOfMeasures);
		Voice voice2 = new Voice("voice2", listOfMeasures);
		
		assertFalse(voice1.equals(voice2));
	}
	
	// Tests the equals method of the Voice class on two Voices that have same
	// voiceNames but different listOfMeasures
	@Test
	public void testEquals_SameVoiceNameDifferentListOfMeasures() {
		// instantiate a Note
		Note note1 = new Note(new Pitch('C'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord = new ArrayList<Note>();
		listOfNotesInChord.add(note1);
		Chord chord1 = new Chord(listOfNotesInChord);
		// instantiate a Measure
		KeySignature keySignature = new KeySignature("Em"); // F# only note affected
		List<SoundUnit> chordsAndRestsInMeasure = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure.add(chord1);
		String endingBarLine = "|";
		Measure measure1 = new Measure(keySignature, chordsAndRestsInMeasure, endingBarLine);
		// instantiate a Voice
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		listOfMeasures.add(measure1);
		Voice voice1 = new Voice("voice1", listOfMeasures);
		
		List<Measure> listOfMeasures2 = new ArrayList<Measure>();
		listOfMeasures2.add(measure1);
		listOfMeasures2.add(measure1);
		Voice voice2 = new Voice("voice1", listOfMeasures2);
		
		assertFalse(voice1.equals(voice2));
	}
	
	/**
	 * The I/O space of Voice.fixRepeats() was partitioned as follows:
	 * 		- No repeats
	 * 
	 * 		- One repeat 
	 * 				( Measure 1 | Measure 2 :|  BECOMES Measure 1 | Measure 2 | Measure 1 | Measure 2 | )
	 * 
	 * 		- One repeat with beginning repeat
	 * 				( Measure 1 |: Measure 2 | Measure 3 :|  BECOMES
	 * 				  Measure 1 | Measure 2 | Measure 3 | Measure 2 | Measure 3 | )
	 * 
	 * 		- One repeat with double bar (double bar should be ignored)
	 * 				( Measure 1 || Measure 2 :|  BECOMES  Measure 1 | Measure 2 | Measure 1 | Measure 2 | )
	 * 
	 * 		- One repeat with bold double bar line |]
	 * 				( Measure 1 |] Measure 2 :|  BECOMES Measure 1 | Measure 2 | Measure 2 )
	 * 
	 * 		- One repeat with begin bold double barline [|
	 * 				( Measure 1 |[| Measure 2 | Measure 3 :|  BECOMES
	 * 				  Measure 1 | Measure 2 | Measure 3 | Measure 2 | Measure 3 | )
	 * 
	 * 		- Two repeats
	 * 				( Measure 1  :|  Measure 2 ||: Measure 3 :|  BECOMES
	 * 				  Measure 1 | Measure 1 | Measure 2 | Measure 3 | Measure 3 | )
	 * 
	 * 		- Alternate endings
	 * 				( Measure 1 |[1 Measure 2 :|[2 Measure 3 | BECOMES
	 * 				  Measure 1 | Measure 2 | Measure 1 | Measure 3 | )
	 * 
	 * 		- Alternate endings with double bar line |]
	 * 				( Measure 1 ]| Measure 2 |[1 Measure 3 :|[2 Measure 4 | BECOMES
	 * 				  Measure 1 | Measure 2 | Measure 3 | Measure 2 | Measure 4 | )
	 * 		
	 */
	
	// Tests the fixRepeats method on a Voice containing no repeats (i.e. no
	// changes necessary)
	@Test
	public void testFixRepeats_NoRepeats() {
		// Instantiate the first voice
		// instantiate a Note
		Note note1 = new Note(new Pitch('C'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord = new ArrayList<Note>();
		listOfNotesInChord.add(note1);
		Chord chord1 = new Chord(listOfNotesInChord);
		// instantiate a Measure
		KeySignature keySignature = new KeySignature("Em"); // F# only note affected
		List<SoundUnit> chordsAndRestsInMeasure = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure.add(chord1);
		String endingBarLine = "|";
		Measure measure1 = new Measure(keySignature, chordsAndRestsInMeasure, endingBarLine);
		// instantiate a Voice
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		listOfMeasures.add(measure1);
		Voice voice = new Voice("voice", listOfMeasures);
		Voice voiceCorrected = voice.fixRepeats();
		
		assertTrue(voice.equals(voiceCorrected));
	}
	
	// Tests the fixRepeats method on a Voice containing one repeated section
	// Measure1 | Measure 2 :|  BECOMES  Measure1 | Measure 2 | Measure1 | Measure2 |
	@Test
	public void testFixRepeats_OneRepeat() {
		// instantiate a Note
		Note note1 = new Note(new Pitch('C'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord = new ArrayList<Note>();
		listOfNotesInChord.add(note1);
		Chord chord1 = new Chord(listOfNotesInChord);
		// instantiate a Measure
		KeySignature keySignature = new KeySignature("Em"); // F# only note affected
		List<SoundUnit> chordsAndRestsInMeasure = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure.add(chord1);
		String endingBarLine = "|";
		Measure measure1 = new Measure(keySignature, chordsAndRestsInMeasure, endingBarLine);
		
		// instantiate a 2nd Measure with a repeat barline
		Note note2 = new Note(new Pitch('D'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord2 = new ArrayList<Note>();
		listOfNotesInChord2.add(note2);
		Chord chord2 = new Chord(listOfNotesInChord2);
		// instantiate a Measure
		List<SoundUnit> chordsAndRestsInMeasure2 = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure2.add(chord2);
		String endingBarLine2 = ":|";
		Measure measure2 = new Measure(keySignature, chordsAndRestsInMeasure2, endingBarLine2);
		
		// instantiate a Voice
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		listOfMeasures.add(measure1);
		listOfMeasures.add(measure2);
		Voice voice = new Voice("voice", listOfMeasures);
		Voice voiceCorrected = voice.fixRepeats();
		
		List<Measure> result = new ArrayList<Measure>();
		// the List<Measure> result is measure1, measure2, measure1, measure2
		result.add(measure1);
		result.add(measure2);
		result.add(measure1);
		result.add(measure2);
		assertTrue(voiceCorrected.getListOfMeasures().equals(result));
	}
	
	// Tests the fixRepeats method on a Voice containing one repeat with
	// beginning repeat
	// ( Measure 1 ||: Measure 2 | Measure 3 :| BECOMES
	// Measure 1 | Measure 2 | Measure 3 | Measure 2 | Measure 3 | )
	
	@Test
	public void testFixRepeats_OneRepeatWithBeginningRepeat() {
		// instantiate a Note
		Note note1 = new Note(new Pitch('C'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord = new ArrayList<Note>();
		listOfNotesInChord.add(note1);
		Chord chord1 = new Chord(listOfNotesInChord);
		// instantiate a Measure
		KeySignature keySignature = new KeySignature("Em"); // F# only note affected
		List<SoundUnit> chordsAndRestsInMeasure = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure.add(chord1);
		String endingBarLine = "|";
		Measure measure1 = new Measure(keySignature, chordsAndRestsInMeasure, endingBarLine);
		
		// instantiate a Note
		Note note2 = new Note(new Pitch('E'), 4);
		// instantiate a Chord
		List<Note> listOfNotesInChord2 = new ArrayList<Note>();
		listOfNotesInChord2.add(note2);
		Chord chord2 = new Chord(listOfNotesInChord2);
		// instantiate a Measure with a repeat barline at the beginning, and a repeat barline at the end
		List<SoundUnit> chordsAndRestsInMeasure2 = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure2.add(chord2);
		String beginningBarLine2 = "|:";
		String endingBarLine2 = "|";
		Measure measure2 = new Measure(keySignature, chordsAndRestsInMeasure2, beginningBarLine2, endingBarLine2);
		
		// instantiate a 3rd Measure with a repeat barline
		Note note3 = new Note(new Pitch('D'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord3 = new ArrayList<Note>();
		listOfNotesInChord3.add(note3);
		Chord chord3 = new Chord(listOfNotesInChord3);
		// instantiate a Measure
		List<SoundUnit> chordsAndRestsInMeasure3 = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure3.add(chord3);
		String endingBarLine3 = ":|";
		Measure measure3 = new Measure(keySignature, chordsAndRestsInMeasure3, endingBarLine3);
		
		// instantiate a Voice
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		listOfMeasures.add(measure1);
		listOfMeasures.add(measure2);
		listOfMeasures.add(measure3);
		Voice voice = new Voice("voice", listOfMeasures);
		Voice voiceCorrected = voice.fixRepeats();
		
		List<Measure> result = new ArrayList<Measure>();
		// the List<Measure> result is measure1, measure2, measure3, measure2, measure3
		result.add(measure1);
		result.add(measure2);
		result.add(measure3);
		result.add(measure2);
		result.add(measure3);
		assertTrue(voiceCorrected.getListOfMeasures().equals(result));
	}
	// Tests the fixRepeats method on a Voice containing One repeat with double bar 
	// (double bar should be ignored)
	// ( Measure 1 || Measure 2 :|  BECOMES  
	//   Measure 1 | Measure 2 | Measure 1 | Measure 2 | )
	
	@Test
	public void testFixRepeats_OneRepeatWithDoubleBar() {
		// instantiate a Note
		Note note1 = new Note(new Pitch('C'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord = new ArrayList<Note>();
		listOfNotesInChord.add(note1);
		Chord chord1 = new Chord(listOfNotesInChord);
		// instantiate a Measure wwith double bar
		KeySignature keySignature = new KeySignature("Em"); // F# only note affected
		List<SoundUnit> chordsAndRestsInMeasure = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure.add(chord1);
		String endingBarLine = "||";
		Measure measure1 = new Measure(keySignature, chordsAndRestsInMeasure, endingBarLine);
		
		// instantiate a 2nd Measure with a repeat barline
		Note note2 = new Note(new Pitch('D'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord2 = new ArrayList<Note>();
		listOfNotesInChord2.add(note2);
		Chord chord2 = new Chord(listOfNotesInChord2);
		// instantiate a Measure
		List<SoundUnit> chordsAndRestsInMeasure2 = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure2.add(chord2);
		String endingBarLine2 = ":|";
		Measure measure2 = new Measure(keySignature, chordsAndRestsInMeasure2, endingBarLine2);
		
		// instantiate a Voice
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		listOfMeasures.add(measure1);
		listOfMeasures.add(measure2);
		Voice voice = new Voice("voice", listOfMeasures);
		Voice voiceCorrected = voice.fixRepeats();
		
		List<Measure> result = new ArrayList<Measure>();
		// the List<Measure> result is measure1, measure2, measure1, measure2
		result.add(measure1);
		result.add(measure2);
		result.add(measure1);
		result.add(measure2);
		assertTrue(voiceCorrected.getListOfMeasures().equals(result));
	}
	
	// Tests the fixRepeats method on a Voice containing One repeat with bold double bar line |]
	// ( Measure 1 |] Measure 2 :|  BECOMES Measure 1 | Measure 2 | Measure 2 )
	@Test
	public void testFixRepeats_OneRepeatWithEndBoldDoubleBarLine() {
		// instantiate a Note
		Note note1 = new Note(new Pitch('C'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord = new ArrayList<Note>();
		listOfNotesInChord.add(note1);
		Chord chord1 = new Chord(listOfNotesInChord);
		// instantiate a Measure with a bold double bar line |]
		KeySignature keySignature = new KeySignature("Em"); // F# only note affected
		List<SoundUnit> chordsAndRestsInMeasure = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure.add(chord1);
		String endingBarLine = "|]";
		Measure measure1 = new Measure(keySignature, chordsAndRestsInMeasure, endingBarLine);
		
		// instantiate a 2nd Measure with a repeat barline
		Note note2 = new Note(new Pitch('D'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord2 = new ArrayList<Note>();
		listOfNotesInChord2.add(note2);
		Chord chord2 = new Chord(listOfNotesInChord2);
		// instantiate a Measure
		List<SoundUnit> chordsAndRestsInMeasure2 = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure2.add(chord2);
		String endingBarLine2 = ":|";
		Measure measure2 = new Measure(keySignature, chordsAndRestsInMeasure2, endingBarLine2);
		
		// instantiate a Voice
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		listOfMeasures.add(measure1);
		listOfMeasures.add(measure2);
		Voice voice = new Voice("voice", listOfMeasures);
		Voice voiceCorrected = voice.fixRepeats();
		
		List<Measure> result = new ArrayList<Measure>();
		// the List<Measure> result is measure1, measure2, measure2
		result.add(measure1);
		result.add(measure2);
		result.add(measure2);
		assertTrue(voiceCorrected.getListOfMeasures().equals(result));
	}
	
	// Tests the fixRepeats method on a Voice containing One repeat with begin bold double barline [|
	// 				( Measure 1 |[| Measure 2 | Measure 3 :|  BECOMES
	// 				  Measure 1 | Measure 2 | Measure 3 | Measure 2 | Measure 3 | )
	@Test
	public void testFixRepeats_OneRepeatWithBeginBoldDoubleBarLine() {
		// instantiate a Note
		Note note1 = new Note(new Pitch('C'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord = new ArrayList<Note>();
		listOfNotesInChord.add(note1);
		Chord chord1 = new Chord(listOfNotesInChord);
		// instantiate a Measure
		KeySignature keySignature = new KeySignature("Em"); // F# only note affected
		List<SoundUnit> chordsAndRestsInMeasure = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure.add(chord1);
		String endingBarLine = "|";
		Measure measure1 = new Measure(keySignature, chordsAndRestsInMeasure, endingBarLine);
		
		// instantiate a Note
		Note note2 = new Note(new Pitch('E'), 4);
		// instantiate a Chord
		List<Note> listOfNotesInChord2 = new ArrayList<Note>();
		listOfNotesInChord2.add(note2);
		Chord chord2 = new Chord(listOfNotesInChord2);
		// instantiate a Measure with a bold double bar line at the beginning
		List<SoundUnit> chordsAndRestsInMeasure2 = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure2.add(chord2);
		String beginningBarLine2 = "[|";
		String endingBarLine2 = "|";
		Measure measure2 = new Measure(keySignature, chordsAndRestsInMeasure2, beginningBarLine2, endingBarLine2);
		
		// instantiate a 3rd Measure with a barline at the beginning of the measure, and a repeat barline ending the measure
		Note note3 = new Note(new Pitch('D'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord3 = new ArrayList<Note>();
		listOfNotesInChord3.add(note3);
		Chord chord3 = new Chord(listOfNotesInChord3);
		// instantiate a Measure
		List<SoundUnit> chordsAndRestsInMeasure3 = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure3.add(chord3);
		String endingBarLine3 = ":|";
		Measure measure3 = new Measure(keySignature, chordsAndRestsInMeasure3, endingBarLine3);
		
		// instantiate a Voice
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		listOfMeasures.add(measure1);
		listOfMeasures.add(measure2);
		listOfMeasures.add(measure3);
		Voice voice = new Voice("voice", listOfMeasures);
		Voice voiceCorrected = voice.fixRepeats();
		
		List<Measure> result = new ArrayList<Measure>();
		// the List<Measure> result is measure1, measure2, measure3, measure2, measure3
		result.add(measure1);
		result.add(measure2);
		result.add(measure3);
		result.add(measure2);
		result.add(measure3);
		assertTrue(voiceCorrected.getListOfMeasures().equals(result));
	}
		
	
	// Tests the fixRepeats method on a Voice containing two repeats
	// ( Measure 1  :|  Measure 2 ||: Measure 3 :|  BECOMES
	//	 Measure 1 | Measure 1 | Measure 2 | Measure 3 | Measure 3 | )
	@Test
	public void testFixRepeats_TwoRepeats() {
		// instantiate a Note
		Note note1 = new Note(new Pitch('C'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord = new ArrayList<Note>();
		listOfNotesInChord.add(note1);
		Chord chord1 = new Chord(listOfNotesInChord);
		// instantiate a Measure
		KeySignature keySignature = new KeySignature("Em"); // F# only note affected
		List<SoundUnit> chordsAndRestsInMeasure = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure.add(chord1);
		String endingBarLine = ":|";
		Measure measure1 = new Measure(keySignature, chordsAndRestsInMeasure, endingBarLine);
		
		// instantiate a Note
		Note note2 = new Note(new Pitch('E'), 4);
		// instantiate a Chord
		List<Note> listOfNotesInChord2 = new ArrayList<Note>();
		listOfNotesInChord2.add(note2);
		Chord chord2 = new Chord(listOfNotesInChord2);
		// instantiate a Measure
		List<SoundUnit> chordsAndRestsInMeasure2 = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure2.add(chord2);
		String endingBarLine2 = "|";
		Measure measure2 = new Measure(keySignature, chordsAndRestsInMeasure2, endingBarLine2);
		
		// instantiate a 3rd Measure with a repeat barline beginning the measure, and a repeat barline ending the measure
		Note note3 = new Note(new Pitch('D'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord3 = new ArrayList<Note>();
		listOfNotesInChord3.add(note3);
		Chord chord3 = new Chord(listOfNotesInChord3);
		// instantiate a Measure
		List<SoundUnit> chordsAndRestsInMeasure3 = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure3.add(chord3);
		String beginningBarLine3 = "|:";
		String endingBarLine3 = ":|";
		Measure measure3 = new Measure(keySignature, chordsAndRestsInMeasure3, beginningBarLine3, endingBarLine3);
		
		// instantiate a Voice
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		listOfMeasures.add(measure1);
		listOfMeasures.add(measure2);
		listOfMeasures.add(measure3);
		Voice voice = new Voice("voice", listOfMeasures);
		Voice voiceCorrected = voice.fixRepeats();
		
		List<Measure> result = new ArrayList<Measure>();
		// the List<Measure> result is measure1, measure1, measure2, measure3, measure3
		result.add(measure1);
		result.add(measure1);
		result.add(measure2);
		result.add(measure3);
		result.add(measure3);
		assertTrue(voiceCorrected.getListOfMeasures().equals(result));
	}
	// Tests the fixRepeats method on a Voice containing alternate endings
	// ( Measure 1 |[1 Measure 2 :|[2 Measure 3 | BECOMES
	//   Measure 1 | Measure 2 | Measure 1 | Measure 3 | )
	@Test
	public void testFixRepeats_AlternateEndings() {
		// instantiate a Note
		Note note1 = new Note(new Pitch('C'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord = new ArrayList<Note>();
		listOfNotesInChord.add(note1);
		Chord chord1 = new Chord(listOfNotesInChord);
		// instantiate a Measure
		KeySignature keySignature = new KeySignature("Em"); // F# only note affected
		List<SoundUnit> chordsAndRestsInMeasure = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure.add(chord1);
		String endingBarLine = "|";
		Measure measure1 = new Measure(keySignature, chordsAndRestsInMeasure, endingBarLine);
		
		// instantiate a Note
		Note note2 = new Note(new Pitch('E'), 4);
		// instantiate a Chord
		List<Note> listOfNotesInChord2 = new ArrayList<Note>();
		listOfNotesInChord2.add(note2);
		Chord chord2 = new Chord(listOfNotesInChord2);
		// instantiate a Measure with an alternate ending 1 at the beginning and a repeat bar line at the end
		List<SoundUnit> chordsAndRestsInMeasure2 = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure2.add(chord2);
		String beginningBarLine2 = "[1";
		String endingBarLine2 = ":|";
		Measure measure2 = new Measure(keySignature, chordsAndRestsInMeasure2, beginningBarLine2, endingBarLine2);
		
		// instantiate a 3rd Measure with an alternate ending 2 at the beginning and a bar line at the end
		Note note3 = new Note(new Pitch('D'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord3 = new ArrayList<Note>();
		listOfNotesInChord3.add(note3);
		Chord chord3 = new Chord(listOfNotesInChord3);
		// instantiate a Measure
		List<SoundUnit> chordsAndRestsInMeasure3 = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure3.add(chord3);
		String beginningBarLine3 = "[2";
		String endingBarLine3 = "|";
		Measure measure3 = new Measure(keySignature, chordsAndRestsInMeasure3, beginningBarLine3, endingBarLine3);
		
		// instantiate a Voice
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		listOfMeasures.add(measure1);
		listOfMeasures.add(measure2);
		listOfMeasures.add(measure3);
		Voice voice = new Voice("voice", listOfMeasures);
		Voice voiceCorrected = voice.fixRepeats();
		
		List<Measure> result = new ArrayList<Measure>();
		// the List<Measure> result is measure1, measure2, measure1, measure3
		result.add(measure1);
		result.add(measure2);
		result.add(measure1);
		result.add(measure3);
		assertTrue(voiceCorrected.getListOfMeasures().equals(result));
	}
	
	// Tests the fixRepeats method on a Voice containing alternate endings with double bar line |]
	// ( Measure 1 ]| Measure 2 |[1 Measure 3 :|[2 Measure 4 | BECOMES
	//	 Measure 1 | Measure 2 | Measure 3 | Measure 2 | Measure 4 | )
	@Test
	public void testFixRepeats_AlternateEndingsWithBoldDoubleBarLine() {
		// instantiate a Note
		Note note1 = new Note(new Pitch('C'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord = new ArrayList<Note>();
		listOfNotesInChord.add(note1);
		Chord chord1 = new Chord(listOfNotesInChord);
		// instantiate a Measure with a bold double bar line ]| ending
		KeySignature keySignature = new KeySignature("Em"); // F# only note affected
		List<SoundUnit> chordsAndRestsInMeasure = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure.add(chord1);
		String endingBarLine = "|]";
		Measure measure1 = new Measure(keySignature, chordsAndRestsInMeasure, endingBarLine);
		
		// instantiate a Note
		Note note2 = new Note(new Pitch('E'), 4);
		// instantiate a Chord
		List<Note> listOfNotesInChord2 = new ArrayList<Note>();
		listOfNotesInChord2.add(note2);
		Chord chord2 = new Chord(listOfNotesInChord2);
		// instantiate a Measure with barline ending
		List<SoundUnit> chordsAndRestsInMeasure2 = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure2.add(chord2);
		String endingBarLine2 = "|";
		Measure measure2 = new Measure(keySignature, chordsAndRestsInMeasure2, endingBarLine2);
		
		// instantiate a 3rd Measure with an alternate ending 1 at the beginning and a repeat barline at the end
		Note note3 = new Note(new Pitch('D'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord3 = new ArrayList<Note>();
		listOfNotesInChord3.add(note3);
		Chord chord3 = new Chord(listOfNotesInChord3);
		// instantiate a Measure
		List<SoundUnit> chordsAndRestsInMeasure3 = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure3.add(chord3);
		String beginningBarLine3 = "[1";
		String endingBarLine3 = ":|";
		Measure measure3 = new Measure(keySignature, chordsAndRestsInMeasure3, beginningBarLine3, endingBarLine3);
		
		// instantiate a 4th Measure with an alternate ending 2 at the beginning and a barline at the end
		Note note4 = new Note(new Pitch('G'), 2);
		// instantiate a Chord
		List<Note> listOfNotesInChord4 = new ArrayList<Note>();
		listOfNotesInChord4.add(note4);
		Chord chord4 = new Chord(listOfNotesInChord4);
		// instantiate a Measure
		List<SoundUnit> chordsAndRestsInMeasure4 = new ArrayList<SoundUnit>();
		chordsAndRestsInMeasure4.add(chord4);
		String beginningBarLine4 = "[2";
		String endingBarLine4 = "|";
		Measure measure4 = new Measure(keySignature, chordsAndRestsInMeasure4, beginningBarLine4, endingBarLine4);
		
		// instantiate a Voice
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		listOfMeasures.add(measure1);
		listOfMeasures.add(measure2);
		listOfMeasures.add(measure3);
		listOfMeasures.add(measure4);
		Voice voice = new Voice("voice", listOfMeasures);
		Voice voiceCorrected = voice.fixRepeats();
		System.out.println(voiceCorrected.getListOfMeasures());
		
		List<Measure> result = new ArrayList<Measure>();
		// the List<Measure> result is measure1, measure2, measure3, measure2, measure4
		result.add(measure1);
		result.add(measure2);
		result.add(measure3);
		result.add(measure2);
		result.add(measure4);
		System.out.print(result.toString());
		assertTrue(voiceCorrected.getListOfMeasures().equals(result));
	}
}