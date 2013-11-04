package musicRepresentation;

import grammar.ABCMusicLexer;
import grammar.ABCMusicListener;
import grammar.ABCMusicParser.Abc_headerContext;
import grammar.ABCMusicParser.Abc_lineContext;
import grammar.ABCMusicParser.Abc_musicContext;
import grammar.ABCMusicParser.Abc_tuneContext;
import grammar.ABCMusicParser.AccidentalContext;
import grammar.ABCMusicParser.BasenoteContext;
import grammar.ABCMusicParser.CommentContext;
import grammar.ABCMusicParser.ElementContext;
import grammar.ABCMusicParser.End_of_lineContext;
import grammar.ABCMusicParser.Field_composerContext;
import grammar.ABCMusicParser.Field_default_lengthContext;
import grammar.ABCMusicParser.Field_keyContext;
import grammar.ABCMusicParser.Field_meterContext;
import grammar.ABCMusicParser.Field_numberContext;
import grammar.ABCMusicParser.Field_tempoContext;
import grammar.ABCMusicParser.Field_textContext;
import grammar.ABCMusicParser.Field_titleContext;
import grammar.ABCMusicParser.Field_voiceContext;
import grammar.ABCMusicParser.KeyContext;
import grammar.ABCMusicParser.Key_accidentalContext;
import grammar.ABCMusicParser.KeynoteContext;
import grammar.ABCMusicParser.LineContext;
import grammar.ABCMusicParser.LyricContext;
import grammar.ABCMusicParser.Lyric_textContext;
import grammar.ABCMusicParser.Lyrical_elementContext;
import grammar.ABCMusicParser.MeterContext;
import grammar.ABCMusicParser.Mid_tune_fieldContext;
import grammar.ABCMusicParser.Mode_minorContext;
import grammar.ABCMusicParser.Multi_noteContext;
import grammar.ABCMusicParser.NoteContext;
import grammar.ABCMusicParser.Note_elementContext;
import grammar.ABCMusicParser.Note_lengthContext;
import grammar.ABCMusicParser.Note_length_strictContext;
import grammar.ABCMusicParser.Note_or_restContext;
import grammar.ABCMusicParser.OctaveContext;
import grammar.ABCMusicParser.Other_fieldsContext;
import grammar.ABCMusicParser.PitchContext;
import grammar.ABCMusicParser.RestContext;
import grammar.ABCMusicParser.TempoContext;
import grammar.ABCMusicParser.Tuplet_elementContext;
import grammar.ABCMusicParser.Valid_letterContext;
import grammar.ABCMusicParser.Valid_noteContext;
import grammar.ABCMusicParser.Valid_text_with_numberContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import sound.Pitch;

public class ABCMusicParseListener implements ABCMusicListener {
    private static final boolean DEBUGMODE = false;

    private static Stack<Object> stack = new Stack<Object>();
    private static ABCHeader header;

    public ABCHeader getHeader() {
        return header;
    }

    public Map<String, Voice> getVoiceMap() {
    	return (Map<String, Voice>) stack.pop();
    }

    @Override
    public void enterEveryRule(ParserRuleContext arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitEveryRule(ParserRuleContext arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visitErrorNode(ErrorNode arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visitTerminal(TerminalNode arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enterValid_text_with_number(Valid_text_with_numberContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitValid_text_with_number(Valid_text_with_numberContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enterNote_length_strict(Note_length_strictContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitNote_length_strict(Note_length_strictContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enterAbc_music(Abc_musicContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitAbc_music(Abc_musicContext ctx) {
        /*
         * When we exit an abc_music, we know that we have all the voices in the
         * stack, but in reverse order. We create a listOfVoices by prepending
         * each voice we get from the stack, stopping when there are no more
         * voices in the stack. We create an empty HashMap<String,Voice> to
         * store each voice, and we will iterate through the listOfVoices to
         * merge all voices with the same name together. For each voice in the
         * listOfVoices, we check if that voice is already in the map. If the
         * voice is not in the map yet, we define the current voice as the voice
         * in the Map. If we already have the voice instantiated in the map,
         * then we extend the voice in the map with the current voice (once it
         * is an extension of the voice). As said by Nils Molina (Piazza @502),
         * the number of lines per track don't need to match across voices. Also
         * as said by the instructor (Piazza @517) all lyrics will be played
         * simultaneously, so there is no "Main lyric" to be played.
         */
        Map<String, Voice> voiceMap = new HashMap<String, Voice>();

        Voice previousVoice = new Voice("", new ArrayList<Measure>());

        voiceMap.put("", previousVoice);

        // Build voiceList with voices in the Stack
        List<Voice> voiceList = new ArrayList<Voice>();
        while (stack.peek() instanceof Voice) {
            voiceList.add(0, (Voice) stack.pop());
        }

        Iterator<Voice> voiceIterator = voiceList.iterator();

        // For each voice in the list
        while (voiceIterator.hasNext()) {
            Voice current = voiceIterator.next();

            // If we don't know which voice it is, use the
            // previous voice to extend.
            if (current.getVoiceName().equals("")) {
                previousVoice.extend(current);
            } else {
                Voice originalVoice = voiceMap.get(current.getVoiceName());
                if (originalVoice == null) {
                    voiceMap.put(current.getVoiceName(), current);

                } else {
                    originalVoice.extend(current);
                }
                previousVoice = voiceMap.get(current.getVoiceName());
            }
        }
        
        // call Voice.fixRepeats() on each voice in the voiceMap.
        for (String key: voiceMap.keySet()) {
        	voiceMap.get(key).fixRepeats();
        }
        stack.push(voiceMap);
    }

    @Override
    public void enterOther_fields(Other_fieldsContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitOther_fields(Other_fieldsContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enterAbc_header(Abc_headerContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitAbc_header(Abc_headerContext ctx) {
        int x;
        String title = "";

        // When the field 'C' is omitted, any reasonable string will suffice
        // (e.g. "Unknown").
        String composer = "Unknown";

        int beatsPerMinute = -1; // Check later if modified
        // When the field 'M' is omitted, the default value for meter is 4/4.
        int beatsPerMeasure = 4; // Denominator of Meter
        double whatNoteGetsTheBeat = 1.0 / 4.0; // Divisor of Meter
        double defaultNoteLength = -1; // Check later if modified
        double bpmNoteLength = -1; // Check later if modified
        String key = "";

        while (!stack.isEmpty()) {
            // FIELD_TITLE
            String field = stack.pop().toString();

            // X:
            if (field.equals("X:")) {
                x = Integer.valueOf(stack.pop().toString());

                // Title
            } else if (field.equals("T:")) {
                title = stack.pop().toString();

                // Composer
            } else if (field.equals("C:")) {
                composer = stack.pop().toString();

                // Default Length
            } else if (field.equals("L:")) {
                String[] parsed = stack.pop().toString().split("/");
                double denominator = Double.valueOf(parsed[0]);
                double divisor = Double.valueOf(parsed[1]);
                defaultNoteLength = denominator / divisor;

                // Meter
            } else if (field.equals("M:")) {
                String meter = (String)stack.pop();
                
                if( meter.equals("C")) {
                    int denominator = 4;
                    int divisor = 4;
                    whatNoteGetsTheBeat = 1.0 / divisor;
                    beatsPerMeasure = denominator;
                }

                else if( meter.equals("C|")){
                    int denominator = 2;
                    int divisor = 4;
                    whatNoteGetsTheBeat = 1.0 / divisor;
                    beatsPerMeasure = denominator;
                } else {
                    String[] parsed = meter.toString().split("/");
                    int denominator = Integer.valueOf(parsed[0]);
                    int divisor = Integer.valueOf(parsed[1]);
                    whatNoteGetsTheBeat = 1.0 / divisor;
                    beatsPerMeasure = denominator;
                }

                // Tempo (BPM)
            } else if (field.equals("Q:")) {
                String tempo = (String) stack.pop();

                String[] parsed = tempo.split("=");

                beatsPerMinute = Integer.valueOf(parsed[1]);

                String[] note = parsed[0].split("/");
                double denominator = Double.valueOf(note[0]);
                double divisor = Double.valueOf(note[1]);

                bpmNoteLength = denominator / divisor;

                // Key
            } else if (field.equals("K:")) {
                key = stack.pop().toString();
            }
        }

        /*
         * If there is no 'L' field defined, a unit note length is set based on
         * the meter: if it is less than 0.75 the default unit note length is a
         * sixteenth note; if it is 0.75 or greater, it is an eighth note. For
         * example, 2/4 = 0.5, so, the default unit note length is a sixteenth
         * note, while for 4/4 = 1.0, or 6/8 = 0.75, or 3/4= 0.75, it is an
         * eighth note. Notice that if neither 'M' nor 'L' fields are present,
         * the default note length is an eighth.
         */
        if (defaultNoteLength < 0) {
            double meter = beatsPerMeasure * whatNoteGetsTheBeat;
            if (meter < 0.75) {
                defaultNoteLength = 1.0 / 16.0;
            } else {
                defaultNoteLength = 1.0 / 8.0;
            }
        }

        /*
         * When the field 'Q' is omitted, the default value is 100
         * default-length notes (given by field 'L') per minute. Notice,
         * however, that when 'Q' is specified, it includes a beat size, and the
         * tempo is not based on 'L'.
         */
        if (beatsPerMinute < 0 && bpmNoteLength < 0) {
            beatsPerMinute = 100;
            bpmNoteLength = defaultNoteLength;
        }

        int leastCommonTick = 1536; // 3*2^9 (Covers really short beats and also
                                    // triplets)
        int ticksPerBeat = leastCommonTick;

        ABCMusicParseListener.header = new ABCHeader(title, composer, beatsPerMinute,
                bpmNoteLength, ticksPerBeat, whatNoteGetsTheBeat, beatsPerMeasure,
                defaultNoteLength, key);
        
        stack.push(ABCMusicParseListener.header);
    }

    @Override
    public void enterMulti_note(Multi_noteContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitMulti_note(Multi_noteContext ctx) {
        // When we exit a multi_note, we will instantiate a Chord object that
        // contains the Notes. According to the new specifications, the first
        // note of a chord dictates the length of all other notes inside the
        // chord. This is going to be handled as follows: as the first element
        // to enter a Stack is the last to get out, the last Note to be popped
        // out will be the first Note in the Chord, which is responsible for
        // informing the whole Chord length. To do this, we will instantiate a
        // temporary list and pop the Notes from the stack in order to prepend
        // to this temporary list until we reach the SQ_BRACKET_OPEN token. At
        // this point, we instantiate a Chord object, passing in the List<Note>.
        // The Chord constructor will correctly set the length of the notes in
        // the Chord.

        // multi_note : SQ_BRACKET_OPEN note+ SQ_BRACKET_CLOSE;

        // instantiate a temporary Array to hold the Notes in the Chord
        List<Note> noteList = new ArrayList<Note>();

        // i is the number of notes in the Chord; subtract 2 in order to not
        // count the '[' and ']'
        for(int i = 0; i < ctx.getChildCount() - 2; i++) {
            Note n = (Note) stack.pop();
            noteList.add(n);
        }
        
        // Then, in order to fix the order of chord notes, we revert them.
        Collections.reverse(noteList);
        
        Chord c = new Chord(noteList);
        stack.push(c);
        if(DEBUGMODE)
            System.out.println("Multi-note Chord constructed");
    }

    @Override
    public void enterTempo(TempoContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitTempo(TempoContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enterLine(LineContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitLine(LineContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enterNote_element(Note_elementContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitNote_element(Note_elementContext ctx) {
        // When we exit a note_element, the top of the stack is either a Rest,
        // Note, or Chord. If it is a Chord or Rest, then we don't need to
        // change it. If it is a Note, we instantiate a single-note Chord object
        // and push that onto the stack. We now only have Chords and Rests!

        // note_element : note | multi_note;

        if (stack.peek() instanceof Note) {
            Note n = (Note) stack.pop();
            List<Note> noteList = new ArrayList<Note>();
            noteList.add(n);
            Chord c = new Chord(noteList);
            stack.push(c);
            if(DEBUGMODE)
                System.out.println("Single-note Chord Built");
        }
    }

    @Override
    public void enterBasenote(BasenoteContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitBasenote(BasenoteContext ctx) {
        // basenote : valid_note;
        // WE DON'T NEED TO DO ANYTHING HERE!!!!!!!
    }

    @Override
    public void enterNote_length(Note_lengthContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitNote_length(Note_lengthContext ctx) {
        // note_length : NUMBER? SLASH NUMBER? | NUMBER;
        double num = 1.;
        double den = 1.;

        if (ctx.getChildCount() == 1) {
            // we can have a number or a bar
            switch (ctx.start.getType()) {
            case ABCMusicLexer.NUMBER:
                num = Double.parseDouble(ctx.getChild(0).getText());
                ;
                break;
            case ABCMusicLexer.SLASH:
                den = 2.;
                break;
            }
        } else if (ctx.getChildCount() == 3) {
            // we must have a number, a bar, and a number
            num = Double.parseDouble(ctx.getChild(0).getText());
            den = Double.parseDouble(ctx.getChild(2).getText());
        } else {
            // we can only have slash-number or number-slash here
            if (ctx.getChild(0).getText().equals("/")) {
                den = Double.parseDouble(ctx.getChild(1).getText());
            } else {
                num = Double.parseDouble(ctx.getChild(0).getText());
            }
        }
        if(DEBUGMODE){
            System.out.println(num);
            System.out.println(den);
        }
        stack.push(num / den);
    }

    @Override
    public void enterValid_note(Valid_noteContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitValid_note(Valid_noteContext ctx) {
        // valid_note : A_THROUGH_G | LOWERCASE_B | CAPITAL_C;
        stack.push(new Character(ctx.start.getText().charAt(0)));
    }

    @Override
    public void enterAbc_tune(Abc_tuneContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitAbc_tune(Abc_tuneContext ctx) {
        /* At this point we only have the voiceMap in the list The user can get this information by calling the method getVoiceMap() */
    }

    @Override
    public void enterField_key(Field_keyContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitField_key(Field_keyContext ctx) {
        // field_key : FIELD_K SPACE* key end_of_line;
        stack.push(ctx.key().getText());
        stack.push(ctx.FIELD_K().getText());
    }

    @Override
    public void enterMeter(MeterContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitMeter(MeterContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enterAccidental(AccidentalContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitAccidental(AccidentalContext ctx) {
        // Notice that if we find an accidentalValue EQUALS, in order to
        // indicate to the exitpitch
        // method that this is being neutrally accented, we pass an accidental
        // value of 8!!!!!!

        // accidental : CARET | CARET CARET | UNDERSCORE | UNDERSCORE UNDERSCORE
        // | EQUALS ;
        int accidentalValue = 0;

        // Creating a new int according to the node we are visiting.
        if (ctx.getChildCount() == 1) {
            switch (ctx.start.getType()) {
            case ABCMusicLexer.CARET:
                accidentalValue = 1;
                break;
            case ABCMusicLexer.UNDERSCORE:
                accidentalValue = -1;
                break;
            case ABCMusicLexer.EQUALS:
                accidentalValue = 8;
                break;
            }
        } else if (ctx.CARET() != null) {
            accidentalValue = 2;
        } else if (ctx.UNDERSCORE() != null) {
            accidentalValue = -2;
        }
        stack.push(accidentalValue);
    }

    @Override
    public void enterTuplet_element(Tuplet_elementContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitTuplet_element(Tuplet_elementContext ctx) {
        // When we exit a Tuplet_element, we call ctx.getChildCount() in order
        // to determine whether the tuplet is a duplet, triplet, or quadruplet.
        // We subtract one from this count in order to determine the amount of
        // times we should pop a Chord from the stack. We create a temporary
        // list, prepending Chords as we pop. We call
        // chord.multiplyDurationMultiplier passing in the argument as follows:
        // In the case of a triplet, you should play Chord for 2/3 of the
        // original duration. For a duplet, 3/2 of the original durations. And
        // for a quadruplet, 3/4 of the original durations. We then push the
        // Chords back into the stack in the reverse order that we popped them
        // by extracting from the front of the list.

        // tuplet_element : TUPLET_BEGIN_2 note_element note_element |
        // TUPLET_BEGIN_3 note_element note_element note_element |
        // TUPLET_BEGIN_4 note_element note_element note_element note_element;

        List<Chord> chordList = new ArrayList<Chord>();

        switch (ctx.getChildCount() - 1) {
        // duplet
        case 2:
            for (int i = 0; i < 2; i++) {
                Chord c = (Chord) stack.pop();
                c.multiplyDurationMultiplier(3.0 / 2.0);
                chordList.add(0, c);
            }

            for (int j = 0; j < 2; j++) {
                Chord c2 = chordList.remove(0);
                stack.push(c2);
            }
            if(DEBUGMODE)
                System.out.println("Duplet constructed");
            break;
        // triplet
        case 3:
            for (int i = 0; i < 3; i++) {
                Chord c = (Chord) stack.pop();
                c.multiplyDurationMultiplier(2.0 / 3.0);
                chordList.add(0, c);
            }

            for (int j = 0; j < 3; j++) {
                Chord c2 = chordList.remove(0);
                stack.push(c2);
            }
            if(DEBUGMODE)
                System.out.println("Triplet constructed");
            break;
        // quadruplet
        case 4:
            for (int i = 0; i < 4; i++) {
                Chord c = (Chord) stack.pop();
                c.multiplyDurationMultiplier(3.0 / 4.0);
                chordList.add(0, c);
            }

            for (int j = 0; j < 4; j++) {
                Chord c2 = chordList.remove(0);
                stack.push(c2);
            }
            if(DEBUGMODE)
                System.out.println("Quadruplet constructed");
            break;
        }
    }

    @Override
    public void enterKey(KeyContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitKey(KeyContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enterNote(NoteContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitNote(NoteContext ctx) {
        // Case 1: A note has two children which means that it has a note_length
        // child. In this
        // case, we will pop the double (that represents the note
        // length) and an Object, that represents a Pitch or a period of rest.
        // If the object is a
        // Pitch, we instantiate a Note, and push it to the stack,
        // and if it is a period of rest, we instantiate a Rest object and push
        // it to the stack.
        // Case 2 (Rest): A note has one child which means that it does not have
        // a note_length
        // child. We follow procedure as above, instantiating a Note or
        // Rest object using the default value of one for durationMultiplier.
        switch (ctx.getChildCount()) {
        case 2:
            double noteLength = (double) stack.pop();
            if (stack.peek() instanceof Pitch) {
                Note n = new Note((Pitch) stack.pop(), noteLength);
                stack.add(n);
                return;
            } else if (stack.peek() instanceof Character) {
                stack.pop();
                Rest r = new Rest(noteLength);
                stack.add(r);
                return;
            }
            break;
        case 1:
            if (stack.peek() instanceof Pitch) {
                Note n = new Note((Pitch) stack.pop());
                stack.add(n);
                return;
            } else if (stack.peek() instanceof Character) {
                stack.pop();
                Rest r = new Rest();
                stack.add(r);
                return;
            }
            break;
        }
    }

    @Override
    public void enterValid_letter(Valid_letterContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitValid_letter(Valid_letterContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enterKeynote(KeynoteContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitKeynote(KeynoteContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enterField_tempo(Field_tempoContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitField_tempo(Field_tempoContext ctx) {
        /*
         * When the field 'Q' is omitted, the default value is 100
         * default-length notes (given by field 'L') per minute. Notice,
         * however, that when 'Q' is specified, it includes a beat size, and the
         * tempo is not based on 'L'.
         * 
         * field_tempo : FIELD_Q SPACE* tempo end_of_line;
         */
        // field_tempo : FIELD_Q SPACE* tempo end_of_line;
        stack.push(ctx.tempo().getText());
        stack.push(ctx.FIELD_Q().getText());
    }

    @Override
    public void enterElement(ElementContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitElement(ElementContext ctx) {
    // We call ctx.getChild(0).getText().
    // Case 1: If it is " " then we ignore it. This signals that it was a SPACE token.
    // Case 2: If it is "|", "||", "|]", ":|", then we call stack.peek(). There are two subcases:
    //              Subcase 2a: The barline is at the beginning barline of the abc_line if stack.peek() does not
    //              return a Chord or Rest object. Since this barline encodes no useful information, we ignore it.
    //              Subcase 2b: The barline is at the end of a measure if stack.peek() returns a Chord or Rest object.
    //              This means that every Chord and Rest not part of a Measure is part of this new Measure. We instantiate
    //              a temporary List<SoundUnit> and pop from the main stack if stack.peek() returns an instanceof Chord or Rest.
    //              We prepend Chords and Rests to the temporary list. We also check if the new measure has a beginningBarLine
    //              ("[|", "|:", "[1", "[2") by calling stack.peek(). If that returns a String object, that means that it is the
    //              beginningBarLine so we call stack.pop(). We instantiate a measure, passing in the temporary list, the endingBarLine,
    //              and the beginningBarLine if it exists. We push it to the stack.
    // Case 3: If it is "[|", "|:", "[1", "[2" we are at the beginning of a new section. We push the String to the stack.
    // Case 4: If it is not one of the above, then a Chord or Rest is on the stack. We're not at the end of a Measure, so we do nothing. 
           
    // element : note_element | tuplet_element | BARLINE | NTH_REPEAT | SPACE;
            String ctxText = ctx.getChild(0).getText();
            // Case 1: Do Nothing.
            // Case 2
            if (ctxText.equals("|")
                            || ctxText.equals("||")
                            || ctxText.equals("|]")
                            || ctxText.equals(":|")) {
                    // Subcase 2b: Barline is at the end of a measure
                    if (!stack.isEmpty() && stack.peek() instanceof Chord
                                    || stack.peek() instanceof Rest) {
                            List<SoundUnit> listOfSoundUnits = new ArrayList<SoundUnit>();
                            // pop from the stack as long as the top of the stack is a Chord or Rest
                            while (stack.peek() instanceof Chord
                                            || stack.peek() instanceof Rest) {
                                    SoundUnit newSoundUnit = (SoundUnit) stack.pop();
                                    listOfSoundUnits.add(0, newSoundUnit);
                            }
                            // Measure has a beginningBarLine
                            if (stack.peek() instanceof String) {
                                    String beginningBarLine = (String) stack.pop();
                                    Measure m = new Measure(header.getKeySignature(), listOfSoundUnits, beginningBarLine, ctxText);
                                    stack.push(m);
                            }
                           
                            // Measure does not have a beginningBarLine
                            else {
                                    Measure m = new Measure(header.getKeySignature(), listOfSoundUnits, ctxText);
                                    stack.push(m);
                            }
                    }
                   
                    // Subcase 2a: Do Nothing.
            }
           
            // Case 3
            if (ctxText.equals("[|")
                            || ctxText.equals("|:")
                            || ctxText.equals("[1")
                            || ctxText.equals("[2")) {
                    stack.push(ctxText);
            }
           
            // Case 4: Do nothing.         
    }

    @Override
    public void enterLyrical_element(Lyrical_elementContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitLyrical_element(Lyrical_elementContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enterMid_tune_field(Mid_tune_fieldContext ctx) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void exitMid_tune_field(Mid_tune_fieldContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enterEnd_of_line(End_of_lineContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitEnd_of_line(End_of_lineContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enterLyric_text(Lyric_textContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitLyric_text(Lyric_textContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enterAbc_line(Abc_lineContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitAbc_line(Abc_lineContext ctx) {
        /*
         * When we exit abc_line, there is a List<String>, representing the song
         * lyrics, and Measures. At this point, we pop the stack to get the
         * List<String>. We then pop the stack until it is no longer a Measure
         * on the top of the stack. We prepend these Measures to a
         * List<Measure>. We also peek the stack to see if it is a String (which
         * will be the Voice label e.g. V2). If there is no voice label, we
         * define an empty String as the voiceName (this will be handled later,
         * due to the fact that an empty string can also mean that this abc_line
         * is an extension of the previous abc_line). We will then run the
         * following algorithm:
         */
        // Get the list of syllables
        String voiceName = "";
        List<String> lyrics;
        if(stack.peek() instanceof ArrayList<?>){
            lyrics = (ArrayList<String>)stack.pop();
        } else {
            lyrics = new ArrayList<String>();
        }
        List<Measure> measureList = new ArrayList<Measure>();

        // Create the list of Measure
        while (stack.peek() instanceof Measure) {
            measureList.add(0, (Measure) stack.pop());
        }
        
        if (stack.peek() instanceof Valid_text_with_numberContext) {
            voiceName = (String) ((Valid_text_with_numberContext)stack.pop()).getText();
        }
        Iterator<Measure> measureIterator = measureList.iterator();

        // For each measure in list, and there are lyrics to apply
        while (measureIterator.hasNext() && !lyrics.isEmpty()) {
            Measure current = measureIterator.next();
            lyrics = current.applyLyrics(lyrics);
        }

        Voice voice = new Voice(voiceName, measureList);

        stack.push(voice);
    }

    @Override
    public void enterField_composer(Field_composerContext ctx) {
        // TODO Auto-generated method stub
    }

    @Override
    public void exitField_composer(Field_composerContext ctx) {
        // field_composer : FIELD_C SPACE* valid_text_with_number end_of_line;
        stack.push(ctx.valid_text_with_number().getText());
        stack.push(ctx.FIELD_C().getText());
    }

    @Override
    public void enterOctave(OctaveContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitOctave(OctaveContext ctx) {
        // TODO: TEST IF IT CREATES A LIST OF COMMA/APOSTROPHE IF WE HAVE ONLY
        // ONE OF THESE!
        // octave : APOSTROPHE+ | COMMA+;
        int octaveValue = 0;

        // Creating a new int according to the node we are visiting.
        if (!ctx.COMMA().isEmpty()) {
            octaveValue = (-1)*ctx.getChildCount();
        } else if (!ctx.APOSTROPHE().isEmpty()) {
            octaveValue = ctx.getChildCount();
        }
        stack.push(octaveValue);

    }

    @Override
    public void enterNote_or_rest(Note_or_restContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitNote_or_rest(Note_or_restContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enterLyric(LyricContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitLyric(LyricContext ctx) {
        //On this method, we are:
        // --------------------------------------------------------------
        //1st - removing the w: from the line
        //2nd - creating an Array of strings, using spaces as separators
        //3rd - converting that Array to an ArrayList.        
        List<String> mySyllablesInitial = new ArrayList<String>(Arrays.asList(ctx.getText().substring(2).split("\\s+")));
 
        // --------------------------------------------------------------
        //4th - replacing '\-' with '\\' and removing empty Strings from the List.
        // "A-ma-zi-ng gra\-ce!" becomes "A-ma-zi-ng","gra\\ce!"
        List<String> mySyllablesBeforeTreatingHyphens = new ArrayList<String>();
        for(String s : mySyllablesInitial)
            mySyllablesBeforeTreatingHyphens.add(s.replace("\\-", "\\\\"));
       
        mySyllablesBeforeTreatingHyphens.removeAll(Arrays.asList("", null));
       
        // --------------------------------------------------------------
        // 5th - we create a new List to receive hyphenized syllables.
        List<String> mySyllablesAfterTreatingHyphens = new ArrayList<String>();
       
        // here we are treating words splitted with hyphens:
        // "A-ma-zi-ng" becomes "A-","ma-","zi-","ng"
       
        for (String s : mySyllablesBeforeTreatingHyphens) {
            List<String> tempList = new ArrayList<String>();
            tempList = Arrays.asList(s.split("-"));
            Iterator<String> tempIterator2 = tempList.iterator();
            while (tempIterator2.hasNext()) {
                String tempString = tempIterator2.next();
                if (tempIterator2.hasNext()) {
                    mySyllablesAfterTreatingHyphens.add(tempString.concat("-"));
                } else {
                    mySyllablesAfterTreatingHyphens.add(tempString.concat(" "));
                }
            }
        }
       
        // we should separate _ * | into new strings
        List<String> separatingSpecialChars = new ArrayList<String>();
        for (String s : mySyllablesAfterTreatingHyphens) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '_') {
                    separatingSpecialChars.add(sb.toString());
                    separatingSpecialChars.add("_ ");
                    sb.setLength(0);
                } else if (s.charAt(i) == '*') {
                    separatingSpecialChars.add(sb.toString());
                    separatingSpecialChars.add("*");
                    sb.setLength(0);
                } else if (s.charAt(i) == '|') {
                    separatingSpecialChars.add(sb.toString());
                    separatingSpecialChars.add("|");
                    sb.setLength(0);
                } else {
                    sb.append(s.charAt(i));
                    if (i == s.length() - 1 && !sb.toString().equals(" "))
                        separatingSpecialChars.add(sb.toString());
                }
            }
        }
 
        separatingSpecialChars.removeAll(Arrays.asList("", null));
 
        // finally, we should convert ~ into spaces, and \\ into -
        List<String> endingList = new ArrayList<String>();
        for (String s : separatingSpecialChars)
            endingList.add(s.replace("~", " ").replace("\\\\", "-"));
        endingList.removeAll(Arrays.asList("", null));
        stack.push(endingList);
    }
    @Override
    public void enterField_default_length(Field_default_lengthContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitField_default_length(Field_default_lengthContext ctx) {
        /*
         * Definition in the project's website: If there is no 'L' field
         * defined, a unit note length is set based on the meter: if it is less
         * than 0.75 the default unit note length is a sixteenth note; if it is
         * 0.75 or greater, it is an eighth note. For example, 2/4 = 0.5, so,
         * the default unit note length is a sixteenth note, while for 4/4 =
         * 1.0, or 6/8 = 0.75, or 3/4= 0.75, it is an eighth note. Notice that
         * if neither 'M' nor 'L' fields are present, the default note length is
         * an eighth.
         * 
         * field_default_length : FIELD_L SPACE* note_length_strict end_of_line;
         */
        stack.push(ctx.note_length_strict().getText());
        stack.push(ctx.FIELD_L().getText());
    }

    @Override
    public void enterKey_accidental(Key_accidentalContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitKey_accidental(Key_accidentalContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enterMode_minor(Mode_minorContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitMode_minor(Mode_minorContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enterField_meter(Field_meterContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitField_meter(Field_meterContext ctx) {
        /*
         * When the field 'M' is omitted, the default value for meter is 4/4.
         * field_meter : FIELD_M SPACE* meter end_of_line;
         */
        stack.push(ctx.meter().getText());
        stack.push(ctx.FIELD_M().getText());
    }

    @Override
    public void enterField_number(Field_numberContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitField_number(Field_numberContext ctx) {
        // field_number : FIELD_X SPACE* NUMBER end_of_line
        stack.push(ctx.NUMBER().toString());
        stack.push(ctx.FIELD_X().toString());
    }

    @Override
    public void enterRest(RestContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitRest(RestContext ctx) {
        // rest : LOWERCASE_Z;
        if(DEBUGMODE)
            System.out.println('z');
        stack.push('z');
    }

    @Override
    public void enterComment(CommentContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitComment(CommentContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enterField_title(Field_titleContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitField_title(Field_titleContext ctx) {
        // field_title : FIELD_T SPACE* valid_text_with_number end_of_line;
        stack.push(ctx.valid_text_with_number().getText());
        stack.push(ctx.FIELD_T().toString());
    }

    @Override
    public void enterPitch(PitchContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitPitch(PitchContext ctx) {
        // Remember that if it hass a natural accent, the accidental value will
        // be 8.

        // pitch : accidental? basenote octave?;

        int octave = (ctx.octave() != null) ? (int) stack.pop() : 0;
        char c = (char) stack.pop();
        int accidental = (ctx.accidental() != null) ? (int) stack.pop() : 0;

        // checking if has an accidental (remember that at this point, the
        // neutral would be an 8)
        boolean hasAccidental = (accidental != 0);

        // if lowercase, we need to raise one octave!
        if (Character.isLowerCase(c))
            octave++;

        // reverting our "neutral-key-accidental-hack" by assigning an
        // accidental of 0 to the value.
        if (accidental == 8)
            accidental = 0;

        Pitch p = new Pitch(Character.toUpperCase(c), accidental, octave,
                hasAccidental);
        stack.push(p);
    }

    @Override
    public void enterField_voice(Field_voiceContext ctx) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void exitField_voice(Field_voiceContext ctx) {
        // field_voice : FIELD_V SPACE* valid_text_with_number end_of_line;
        stack.push(ctx.valid_text_with_number());
    }

	@Override
	public void enterField_text(Field_textContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitField_text(Field_textContext ctx) {
		// TODO Auto-generated method stub
		
	}
}