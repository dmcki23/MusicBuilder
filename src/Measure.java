import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
/**
 * One bar of music
 */
public class Measure {
    /**
     * ID number of the part it's in
     */
    String partID;
    /**
     * Number of discrete time blocks in the measure, 3/4 time = 12
     */
    int divisions;
    /**
     * Key of measure
     */
    int keyFifths;
    /**
     * Number of beats, 3/4 = 3
     */
    int timeBeats;
    /**
     * Type of beat, 1 = eigth, 2= quarter, etc
     */
    int timeType;
    /**
     * Key signature variable
     */
    String clefSign;
    /**
     * Line the clef symbol goes on
     */
    int clefLine;
    /**
     * List of XML tags between the opening and closing of this one
     */
    List<XMLtag> inBetween;
    /**
     * Tempo, I don't think this changes anything even with it, maybe it works with some sheet music editors
     */

    int tempo = 30;
    /**
     *
     */
    List<XMLtag> XMLtaglist;
    /**
     * List of Note s
     */
    List<Note> noteList;
    /**
     * I think this holds the integer values of the notes in a measure before they're written in tag form
     */
    int[] aList = new int[6];
    /**
     * Number of staves in the measure
     */
    String staves = "1";
    /**
     * Bass and treble clef symbols
     */
    String[] clefSigns = new String[2];
    /**
     * Bass and treble clef line numbers
     */
    String[] clefLines = new String[2];
    /**
     * Initializes the taglist
     */
    Measure() {
        XMLtaglist = new ArrayList<XMLtag>();
    }
    /**
     * Create a measure with parameters
     * @param partIDin ID of the Part within the SheetOfMusic
     * @param divisionsIn number of discrete time steps in the measure
     * @param keyFifthsIn key signature
     * @param timeBeatsIn number of beats in the time signature
     * @param timeTypeIn type of beat in the time signature
     * @param clefSignIn clef symbol to use
     * @param clefLineIn line to put the clef symbol on
     */
    Measure(String partIDin, int divisionsIn, int keyFifthsIn, int timeBeatsIn, int timeTypeIn, String clefSignIn, int clefLineIn) {
        inBetween = new ArrayList<XMLtag>();
        XMLtaglist = new ArrayList<XMLtag>();
        noteList = new ArrayList<Note>();
        partID = partIDin;
        divisions = divisionsIn;
        keyFifths = keyFifthsIn;
        timeBeats = timeBeatsIn;
        timeType = timeTypeIn;
        clefSign = clefSignIn;
        clefLine = clefLineIn;
    }
    /**
     * Outputs the measure in musicXML format
     * @return this measure in list form
     */
    public List<XMLtag> outputMeasureXML() {
        //XMLtaglist.clear();
        XMLtaglist = new ArrayList<XMLtag>();
        XMLtag measurenumbertag = new XMLtag("measure number= \"" + partID + "\"", 1);
        measurenumbertag.outname = "</measure>";
        //measurenumbertag.outname = "</measure>";
        XMLtaglist.add(measurenumbertag);
        XMLtag soundTag = new XMLtag("sound tempo = \""+Integer.toString(tempo)+"\"",1);

        //XMLtaglist.add(soundTag);



        XMLtag attributesTag = new XMLtag("attributes", 2);
        XMLtag stavesTag = new XMLtag("staves", 3);
        stavesTag.text = staves;
        XMLtaglist.get(0).inBetween.add(attributesTag);
        XMLtag divisionstag = new XMLtag("divisions", 3);
        divisionstag.text = Integer.toString(divisions);
        //attributesTag.inBetween.add(divisionstag);
        XMLtag keytag = new XMLtag("key", 3);
        attributesTag.inBetween.add(keytag);
        XMLtag fifthstag = new XMLtag("fifths", 4);
        fifthstag.text = Integer.toString(keyFifths);
        keytag.inBetween.add(fifthstag);
        XMLtag timetag = new XMLtag("time", 3);
        attributesTag.inBetween.add(timetag);

        XMLtag timebeatstag = new XMLtag("beats", 4);
        timebeatstag.text = Integer.toString(timeBeats);
        timetag.inBetween.add(timebeatstag);
        XMLtag timebeattypetag = new XMLtag("beat-type", 4);
        timebeattypetag.text = Integer.toString(timeType);
        timetag.inBetween.add(timebeattypetag);
        attributesTag.inBetween.add(stavesTag);

        if (staves == "1") {
            XMLtag cleftag = new XMLtag("clef number = \"1\"", 3);
            XMLtag signtag = new XMLtag("sign", 4);
            signtag.text = clefSigns[0];
            cleftag.inBetween.add(signtag);
            XMLtag linetag = new XMLtag("line", 4);
            linetag.text = clefLines[0];
            cleftag.inBetween.add(linetag);
            attributesTag.inBetween.add(cleftag);
        }
        if (staves == "2") {
            XMLtag cleftag = new XMLtag("clef number = \"1\"", 3);
            cleftag.outname = "</clef>";
            XMLtag signtag = new XMLtag("sign", 4);
            signtag.text = clefSigns[0];
            cleftag.inBetween.add(signtag);
            XMLtag linetag = new XMLtag("line", 4);
            linetag.text = clefLines[0];
            cleftag.inBetween.add(linetag);
            attributesTag.inBetween.add(cleftag);
            cleftag = new XMLtag("clef number = \"2\"", 3);
            cleftag.outname = "</clef>";
            signtag = new XMLtag("sign", 4);
            signtag.text = clefSigns[1];
            cleftag.inBetween.add(signtag);
            linetag = new XMLtag("line", 4);
            linetag.text = clefLines[1];
            cleftag.inBetween.add(linetag);
            attributesTag.inBetween.add(cleftag);
        } else if (staves == "3") {
            XMLtag cleftag = new XMLtag("clef number = \"1\"", 3);
            cleftag.outname = "</clef>";
            XMLtag signtag = new XMLtag("sign", 4);
            signtag.text = clefSigns[0];
            cleftag.inBetween.add(signtag);
            XMLtag linetag = new XMLtag("line", 4);
            linetag.text = clefLines[0];
            cleftag.inBetween.add(linetag);
            attributesTag.inBetween.add(cleftag);
            cleftag = new XMLtag("clef number = \"2\"", 3);
            cleftag.outname = "</clef>";
            signtag = new XMLtag("sign", 4);
            signtag.text = clefSigns[1];
            cleftag.inBetween.add(signtag);
            linetag = new XMLtag("line", 4);
            linetag.text = clefLines[1];
            cleftag.inBetween.add(linetag);
            attributesTag.inBetween.add(cleftag);
            XMLtag clefChangeTag = new XMLtag("clef-octave-change",4);
            clefChangeTag.outname = "/clef-octave-change";
            clefChangeTag.text = "-1";
            cleftag.inBetween.add(clefChangeTag);
            attributesTag.inBetween.add(cleftag);
        }
//        XMLtag commentTag = new XMLtag("!-- " + Arrays.toString(aList) + "--", 4);
//        commentTag.outname = "";
//        measurenumbertag.inBetween.add(commentTag);
        for (int li = 0; li < noteList.size(); li++) {
            //XMLtaglist.get(0).inBetween.add(new XMLtag());
            //XMLtaglist.get(0).inBetween.get(li) = noteList.get(li).outputNoteXML());
            measurenumbertag.inBetween.addAll(noteList.get(li).outputNoteXML());
        }
        return XMLtaglist;
    }

}
