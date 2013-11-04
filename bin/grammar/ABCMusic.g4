/**
 * This file is the grammar file used by ANTLR.
 *
 * In order to compile this file, navigate to this directory
 * (<src/grammar>) and run the following command:
 *
 * java -jar ../../antlr.jar ABCMusic.g4
 */

grammar ABCMusic;

/*
 * This puts "package grammar;" at the top of the output Java files.
 * Do not change these lines unless you know what you're doing.
 */
@header {
package grammar;
}

/*
 * This adds code to the generated lexer and parser. This makes the lexer and
 * parser throw errors if they encounter invalid input. Do not change these
 * lines unless you know what you're doing.
 */
@members {
    // This method makes the lexer or parser stop running if it encounters
    // invalid input and throw a RuntimeException.
    public void reportErrorsAsExceptions() {
        removeErrorListeners();
        addErrorListener(new ExceptionThrowingErrorListener());
    }

    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e) {
            throw new RuntimeException(msg);
        }
    }
}

/*
 * These are the lexical rules. They define the tokens used by the lexer.
 */
TABS_CRETURNS : [\t\r]+ -> skip ;
LOWERCASE_B : 'b';
LOWERCASE_M : 'm';
LOWERCASE_Z : 'z';
CAPITAL_C : 'C';
A_THROUGH_G: [A-Ga-g];
OTHER_LETTER : [H-Zh-y!?] | '.';
TILDE: '~';
COMMENT_BEGIN: '%';
FIELD_X: 'X:';
FIELD_T: 'T:';
FIELD_C: 'C:';
FIELD_Q: 'Q:';
FIELD_K: 'K:';
FIELD_L: 'L:';
FIELD_M: 'M:';
FIELD_V: 'V:';
FIELD_W: 'w:';
NUMBER: [0-9]+;
NEWLINE: '\n';
SPACE: ' ';
SLASH : '/';
EQUALS : '=' ;
NTH_REPEAT : '[1' | '[2';
BARLINE : '|' | '||' | '[|' | '|]' | ':|' | '|:';
STAR: '*';
HYPHEN: '-';
HASHTAG: '#';
UNDERSCORE : '_';
COMMA : ',';
CARET: '^';
APOSTROPHE : '\'';
TUPLET_BEGIN_2 : '(2';
TUPLET_BEGIN_3 : '(3';
TUPLET_BEGIN_4 : '(4';
PARENTHESIS_OPEN: '(';
PARENTHESIS_CLOSE: ')';
SQ_BRACKET_OPEN: '[';
SQ_BRACKET_CLOSE: ']';
SLASH_HYPHEN : '\-';


/*
 * These are the parser rules. They define the structures used by the parser.
 *
 * You should make sure you have one rule that describes the entire input.
 * This is the "start rule". The start rule should end with the special
 * predefined token EOF so that it describes the entire input. Below, we've made
 * "line" the start rule.
 *
 * For more information, see
 * http://www.antlr.org/wiki/display/ANTLR4/Parser+Rules#ParserRules-StartRulesandEOF
 */
line : abc_tune EOF;
abc_tune : abc_header abc_music ;
abc_header : field_number comment* field_title other_fields* field_key ;

field_number : FIELD_X SPACE* NUMBER end_of_line;
field_title : FIELD_T SPACE* valid_text_with_number end_of_line;
other_fields : field_composer | field_default_length | field_meter | field_tempo | field_voice | comment;
field_composer : FIELD_C SPACE* valid_text_with_number end_of_line;
field_default_length : FIELD_L SPACE* note_length_strict end_of_line;
field_meter : FIELD_M SPACE* meter end_of_line;
field_tempo : FIELD_Q SPACE* tempo end_of_line;
field_voice : FIELD_V SPACE* valid_text_with_number end_of_line;
field_key : FIELD_K SPACE* key end_of_line;


key : keynote mode_minor?;
keynote : (A_THROUGH_G | CAPITAL_C )key_accidental?;
key_accidental : HASHTAG | LOWERCASE_B;
mode_minor : LOWERCASE_M;

meter : (CAPITAL_C BARLINE?)| note_length_strict;
tempo : note_length_strict EQUALS NUMBER;


abc_music : abc_line+;
abc_line : mid_tune_field? element+ NEWLINE (lyric NEWLINE)? | comment;
element : note_element | tuplet_element | BARLINE | NTH_REPEAT | SPACE; 

note_element : note | multi_note;

note : note_or_rest note_length?;
note_or_rest : pitch | rest;
pitch : accidental? basenote octave?;
octave : APOSTROPHE+ | COMMA+;
note_length : NUMBER? SLASH NUMBER? | NUMBER;
note_length_strict : NUMBER SLASH NUMBER;


basenote : valid_note;

rest : LOWERCASE_Z;

tuplet_element : TUPLET_BEGIN_2 note_element note_element | TUPLET_BEGIN_3 note_element note_element note_element | TUPLET_BEGIN_4 note_element note_element note_element note_element;

multi_note : SQ_BRACKET_OPEN note+ SQ_BRACKET_CLOSE;

mid_tune_field : field_voice;

comment : COMMENT_BEGIN valid_text_with_number? NEWLINE;
end_of_line : comment | NEWLINE;

lyric : FIELD_W lyrical_element*;
lyrical_element :  SPACE+ | HYPHEN | UNDERSCORE | STAR | TILDE | SLASH_HYPHEN | BARLINE | lyric_text;
lyric_text : (valid_letter | NUMBER | COMMA | APOSTROPHE | PARENTHESIS_OPEN | PARENTHESIS_CLOSE | field_text)+;

valid_text_with_number: (valid_letter | APOSTROPHE | COMMA | HYPHEN | UNDERSCORE |  PARENTHESIS_OPEN | PARENTHESIS_CLOSE | NUMBER | SPACE)+;

valid_letter : OTHER_LETTER | A_THROUGH_G | LOWERCASE_B | LOWERCASE_M | LOWERCASE_Z | CAPITAL_C ;

valid_note : A_THROUGH_G | LOWERCASE_B | CAPITAL_C;

accidental : CARET | CARET CARET | UNDERSCORE | UNDERSCORE UNDERSCORE | EQUALS ;

field_text: FIELD_X | FIELD_T | FIELD_C | FIELD_L | FIELD_M | FIELD_Q | FIELD_V | FIELD_K;