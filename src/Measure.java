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
    int stavesInt;
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
    String staves;
    /**
     * Bass and treble clef symbols
     */
    String[] clefSigns;
    /**
     * Bass and treble clef line numbers
     */
    String[] clefLines;
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
    Measure(String partIDin, int divisionsIn, int keyFifthsIn, int timeBeatsIn, int timeTypeIn, String clefSignIn, int clefLineIn, int stavesIn) {
        inBetween = new ArrayList<XMLtag>();
        XMLtaglist = new ArrayList<XMLtag>();
        noteList = new ArrayList<Note>();
        partID = partIDin;
        divisions = divisionsIn;
        keyFifths = keyFifthsIn;
        timeBeats = timeBeatsIn;
        timeType = timeTypeIn;
//        clefSign = clefSignIn;
//        clefLine = clefLineIn;
        stavesInt = stavesIn;
        staves = Integer.toString(stavesIn);
        System.out.println("Staves: " + staves + " " + partIDin);
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
//        clefSign = clefSignIn;
//        clefLine = clefLineIn;
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
        System.out.println("========================Staves: " + Integer.toString(stavesInt));
        if (stavesInt == 1) {

            XMLtag cleftag = new XMLtag("clef number = \"1\"", 3);
            cleftag.outname = "</clef>";
            XMLtag signtag = new XMLtag("sign", 4);
            signtag.text = clefSigns[0];
            cleftag.inBetween.add(signtag);
            XMLtag linetag = new XMLtag("line", 4);
            linetag.text = clefLines[0];
            cleftag.inBetween.add(linetag);
            attributesTag.inBetween.add(cleftag);
        } else         if (stavesInt== 2) {
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
        } else if (stavesInt==3) {
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

            cleftag = new XMLtag("clef number = \"3\"", 3);
            cleftag.outname = "</clef>";
            signtag = new XMLtag("sign", 4);
            signtag.text = clefSigns[2];
            cleftag.inBetween.add(signtag);
            linetag = new XMLtag("line", 4);
            linetag.text = clefLines[2];
            cleftag.inBetween.add(linetag);
//            XMLtag oct = new XMLtag("clef-octave-change",4);
//            oct.text = "-3";
//            cleftag.inBetween.add(oct);
            attributesTag.inBetween.add(cleftag);

        }

        for (int li = 0; li < noteList.size(); li++) {

            measurenumbertag.inBetween.addAll(noteList.get(li).outputNoteXML());
        }
        return XMLtaglist;
    }

}
