import java.util.ArrayList;
import java.util.List;
/**
 * A single note within a sheet of music
 */
public class Note {
    /**
     * True if part of a larger chord or going in the same time slot as another note, false if a single note at that spot
     */
    boolean chord;
    /**
     * Types of beats
     */
    String[] typeNames = new String[]{" ", "eighth", "quarter", "half", "whole"};
    /**
     * Note names
     */
    String[] pitchNames = new String[]{" ", "C", "D", "E", "F", "G", "A", "B"};
    /**
     * Nashville number of the pitch
     */
    int pitchStep;
    /**
     * Octave of the note
     */
    int pitchOctave;
    /**
     * Duration of the note
     */
    int duration;
    /**
     * Integer value of the type of note that points to typeNames[]
     */
    int type;
    /**
     * True if this is a dotted note, false if not
     */
    boolean dot;
    /**
     * Sharps or flats in integer form
     */
    int alter;
    /**
     * Some list of tags, but I can't remember every detail of the control structure right now
     */
    List<XMLtag> XMLtaglist;
    /**
     * True if this note is the beginning of the tie, else false
     */
    boolean tieStart;
    /**
     * True if this note is the end of a tie, else false
     */
    boolean tieEnd;
    /**
     * True if this note is a rest note
     */

    boolean restNote;
    /**
     * True if this note is part of a tied note
     */
    boolean tie;
    /**
     * A voice is a separate instrument, currently only one voice
     */
    String voice = "1";
    /**
     * Staff to put the note on
     */
    String staff = "1";
    /**
     * Creates a note with parameters
     * @param pitchStepIn Nashville number of the pitch
     * @param pitchOctaveIn octave of the pitch
     * @param durationIn note length in terms of the duration, discrete time steps of the note for the measure
     * @param typeIn type of note, eighth , quarter, half etc
     */
    Note(int pitchStepIn, int pitchOctaveIn, int durationIn, int typeIn) {
        XMLtaglist = new ArrayList<XMLtag>();
        pitchStep = pitchStepIn;
        pitchOctave = pitchOctaveIn;
        duration = durationIn;
        type = typeIn;
        alter = 0;
        restNote = false;
    }
    /**
     * Creates a note with parameters
     * @param pitchStepIn Nashville number of the pitch
     * @param pitchOctaveIn octave of the pitch
     * @param durationIn note length in terms of the duration, discrete time steps of the note for the measure
     * @param typeIn type of note, eighth quarter half etc
     */
    Note(int pitchStepIn, int pitchOctaveIn, int durationIn, int typeIn, int alterin) {
        XMLtaglist = new ArrayList<XMLtag>();
        pitchStep = pitchStepIn;
        pitchOctave = pitchOctaveIn;
        duration = durationIn;
        type = typeIn;
        alter = alterin;
        restNote = false;
    }
    /**
     * Initializaes the taglist
     */
    Note() {
        XMLtaglist = new ArrayList<XMLtag>();
    }
    /**
     * Outputs this note in musicXML
     * @return this note in XMLtag list form
     */
    public List<XMLtag> outputNoteXML() {
        XMLtag restTag = new XMLtag();
        XMLtaglist = new ArrayList<XMLtag>();
        //XMLtaglist.add(new XMLtag("note", 3));
        if (!restNote) {

            XMLtag noteTag = new XMLtag("note", 3);
            XMLtag chordTag = new XMLtag("chord/",4);
            chordTag.outname = "";
            if (chord){
                noteTag.inBetween.add(chordTag);
            }
            XMLtag pitchTag = new XMLtag("pitch", 4);
            XMLtag stepTag = new XMLtag("step", 5);
            stepTag.text = pitchNames[pitchStep];
            XMLtag alterTag = new XMLtag("alter", 5);
            alterTag.text = Integer.toString(alter);
            pitchTag.inBetween.add(stepTag);
            pitchTag.inBetween.add(alterTag);
            XMLtag octaveTag = new XMLtag("octave", 5);
            octaveTag.text = Integer.toString(pitchOctave);
            pitchTag.inBetween.add(octaveTag);
            noteTag.inBetween.add(pitchTag);
            XMLtag typeTag = new XMLtag("type", typeNames[type], 4);
            typeTag.text = typeNames[type];
            XMLtag dotTag = new XMLtag("dot", 4);
            XMLtag tieTag = new XMLtag();
            if (tieStart && tie) {
                tieTag = new XMLtag("tie type=\"start\" /", 4);
                tieTag.outname = "";
            } else if (!tieStart && tie) {
                tieTag = new XMLtag("tie type=\"stop\" /", 4);
                tieTag.outname = "";
            }
            XMLtag voiceTag = new XMLtag("voice",4);
            voiceTag.text = voice;

            //noteTag.inBetween.add(durationTag);
            if (tie) {
                //noteTag.inBetween.add(tieTag);
            }
            XMLtag staffTag = new XMLtag("staff",4);
            staffTag.text = staff;
            //noteTag.inBetween.add(staffTag);
            noteTag.inBetween.add(typeTag);
            if (dot) {
                noteTag.inBetween.add(dotTag);
            }
            //noteTag.inBetween.add(voiceTag);

            XMLtaglist.add(noteTag);
            return XMLtaglist;
        } else if (restNote) {
            XMLtag notetag = new XMLtag("note", 3);
            restTag = new XMLtag("rest", 3);
            //restTag.outname = "";
            notetag.inBetween.add(restTag);
            //notetag.inBetween.add(durationTag);
            XMLtag typeTag = new XMLtag("type", 3);
            typeTag.text = typeNames[type];
            notetag.inBetween.add(typeTag);
            XMLtag voiceTag = new XMLtag("voice",3);
            voiceTag.text = voice;
//            XMLtag dotTag = new XMLtag("dot",3);
//            if (dot){
//                XMLtaglist.get(0).inBetween.add(dotTag);
//            }
//            XMLtag tieTag = new XMLtag("tie",3);
//            if (tie){
//                XMLtaglist.get(0).inBetween.add(tieTag);
//            }
            //notetag.inBetween.add(voiceTag);
            XMLtag staffTag = new XMLtag("staff",4);
            staffTag.text = staff;
            notetag.inBetween.add(staffTag);
            XMLtaglist.add(notetag);
            //return XMLtaglist;
        }
        return XMLtaglist;
    }
}
