import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
/**
 * does the logic of generating the random notes
 */
public class PartGenerator {
    Cadence cadenceobj = new Cadence();
    Scales scalesobj = new Scales();
    //chord chordobj = new Chord();
    Rhythm rhythmobj = new Rhythm();
    int cadenceLength;
    int rhythmLength;
    List<int[]> activeCadence;
    List<List<Integer>> activeRhythinBetween;

    List<Measure> mmeasureList = new ArrayList<>();
    List<Note> noteList = new ArrayList<>();
    int timebeats;
    int timetype;
    int chunkLength;
    int[][] outfirst;


    /**
     * Currently empty
     */
    PartGenerator() {
    }


    /**
     * Generation function that works and is used currently
     * @return the output in a list of Part s
     */
    public List<Part> generateBass() {
        List<Part> outPart = new ArrayList<>();
        Part currPart = new Part();
        timebeats = 6;
        timetype = 8;
        Random rand = new Random();
        Measure newMeasure = new Measure();
        chunkLength = 6;
        Note activeNote = new Note();
        Note tieNote = new Note();
        int[] divisions = new int[6];
        for (int m = 0; m < 4; m++) {
            newMeasure = new Measure(Integer.toString(m + 1), 2, 0, 6, 8, "F", 2);
            //newMeasure.staves = "2";
            newMeasure.clefLines = new String[]{"4", "2"};
            newMeasure.clefSigns = new String[]{"G", "F"};
            activeNote = new Note();
            activeNote.type = 1;
            activeNote.pitchOctave = 3;
            activeNote.pitchStep = 5;
            newMeasure.noteList.add(activeNote);
            for (int n = 1; n < 6; n++) {
                activeNote = new Note();
                activeNote.type = 1;
                activeNote.pitchOctave = 3;
                activeNote.pitchStep = 1;
                newMeasure.noteList.add(activeNote);
            }
            currPart.measureList.add(newMeasure);
        }
        int currentKeyFifths = 0;
        for (int m = 0; m < 512; m++) {
            newMeasure = new Measure(Integer.toString(m + 5), 2, 0, 6, 8, "F", 2);
            //newMeasure.staves = "2";
            newMeasure.clefLines = new String[]{"4", "2"};
            newMeasure.clefSigns = new String[]{"G", "F"};
            newMeasure.keyFifths = currentKeyFifths;
            if (m % 16 == 0 && m > 0){
                //newMeasure.keyFifths = rand.nextInt(-7,8);
                newMeasure.keyFifths = 4;
                currentKeyFifths = newMeasure.keyFifths;
            }
            int index = 0;
            while (index < 6) {
                activeNote = new Note();
                activeNote.restNote = false;
                activeNote.dot = false;
                tieNote = new Note();
                tieNote.restNote = false;
                tieNote.dot = false;
                int a = 0;
                if (index == 5) {
                    a = 1;
                } else {
                    a = rand.nextInt(1, 7 - index);
                }
                newMeasure.aList[index] = a;
                index += a;
                if (a == 1) {
                    activeNote.type = 1;
                }
                if (a == 8) {
                    activeNote.type = 4;
                }
                if (a == 2) {
                    activeNote.type = 2;
                }
                if (a == 4) {
                    activeNote.type = 3;
                }
                if (a == 3) {
                    activeNote.type = 2;
                    activeNote.dot = true;
                }
                if (a == 5) {
                    activeNote.type = 3;
                    tieNote.type = 1;
                }
                if (a == 6) {
                    activeNote.type = 3;
                    activeNote.dot = true;
                }
                //activeNote.duration = a;
                int restOrNot = rand.nextInt(0, 5);
                if (restOrNot == 1) {
                    activeNote.restNote = true;
                    if (a == 5) {
                        tieNote.restNote = true;
                    } else if (a == 3) {
                        tieNote.dot = false;
                        tieNote.restNote = true;
                        tieNote.type = 1;
                        activeNote.dot = false;
                        activeNote.restNote = true;
                        activeNote.type = 2;
                    } else if (a == 6) {
                        tieNote.restNote = true;
                        tieNote.dot = false;
                        tieNote.type = 2;
                        activeNote.dot = false;
                    }
                } else {
                    activeNote.restNote = false;
                    tieNote.restNote = false;
                    int newPitchAddress = rand.nextInt(3, 15);
                    if (newPitchAddress > 11 && newPitchAddress <= 18) {
                        activeNote.pitchOctave = 4;
                        tieNote.pitchOctave = 4;
                    } else if (newPitchAddress <= 4) {
                        activeNote.pitchOctave = 2;
                        tieNote.pitchOctave = 2;
                    } else if (newPitchAddress > 4 && newPitchAddress <= 11){
                        activeNote.pitchOctave = 3;
                        tieNote.pitchOctave = 3;
                    } else if (newPitchAddress > 18){
                        activeNote.pitchOctave = 5;
                        tieNote.pitchOctave = 5;
                    }
                    activeNote.pitchStep = newPitchAddress + 3 - 7 * (activeNote.pitchOctave - 2);
                    tieNote.pitchStep = activeNote.pitchStep;
                }
                newMeasure.noteList.add(activeNote);
                if (a == 5 || (a == 3 && activeNote.restNote) || (a == 6 && activeNote.restNote)) {
                    newMeasure.noteList.add(tieNote);
                }
            }
            currPart.measureList.add(newMeasure);
        }
        outPart.add(currPart);
        return outPart;
    }
    /**
     * Generates the music for a bass guitar
     * @param lower bottom left hand corner of fretboard to work with
     * @param upper upper right hand corner of fretboard to work with
     * @param key key to work with
     * @param random if false it is all the selected key, if true it modulates every so often
     * @param intype type of beat in time signature
     * @param inbeats number of beats in time signature
     * @param restChance chance of a note being a rest, 1/restChance
     * @param modFrequency how many measures between key modulation, if random is true
     * @return Part list
     */
    public List<Part> generateBass(int lower, int upper, int key, boolean random, int intype, int inbeats, int restChance, int modFrequency) {
        List<Part> outPart = new ArrayList<>();
        Part currPart = new Part();
        timebeats = inbeats;
        timetype = intype;
        Random rand = new Random();
        Measure newMeasure = new Measure();
        chunkLength = inbeats;
        Note activeNote = new Note();
        Note tieNote = new Note();
        int[] divisions = new int[6];
        for (int m = 0; m < 4; m++) {
            newMeasure = new Measure(Integer.toString(m + 1), 2, 0, inbeats, intype, "F", 4,2);
            newMeasure.staves = "2";
            newMeasure.clefLines = new String[]{"2", "4"};
            newMeasure.clefSigns = new String[]{"G", "F"};
            activeNote = new Note();
            activeNote.type = 1;
            activeNote.pitchOctave = 3;
            activeNote.pitchStep = 5;
            newMeasure.noteList.add(activeNote);
            for (int n = 1; n < inbeats; n++) {
                activeNote = new Note();
                activeNote.type = 1;
                activeNote.pitchOctave = 3;
                activeNote.pitchStep = 1;
                newMeasure.noteList.add(activeNote);
            }
            currPart.measureList.add(newMeasure);
        }
        int currentKeyFifths = key;
        for (int m = 0; m < 512; m++) {

            newMeasure = new Measure(Integer.toString(m + 5), 2, currentKeyFifths, inbeats, intype, "F", 4,2);
            newMeasure.staves = "2";
            newMeasure.clefLines = new String[]{"2", "4"};
            newMeasure.clefSigns = new String[]{"G", "F"};
            newMeasure.keyFifths = currentKeyFifths;
            if (m % modFrequency == 0 && m > 0 && random){
                newMeasure.keyFifths = rand.nextInt(-7,8);
                currentKeyFifths = newMeasure.keyFifths;
            }
            int index = 0;
            while (index < inbeats) {
                activeNote = new Note();
                activeNote.restNote = false;
                activeNote.dot = false;
                tieNote = new Note();
                tieNote.restNote = false;
                tieNote.dot = false;
                int a = 0;
                if (index == inbeats-1) {
                    a = 1;
                } else {
                    a = rand.nextInt(1, (inbeats+1) - index);
                }
                newMeasure.aList[index] = a;
                index += a;
                if (a == 1) {
                    activeNote.type = 1;
                }
                if (a == 8) {
                    activeNote.type = 4;
                }
                if (a == 2) {
                    activeNote.type = 2;
                }
                if (a == 4) {
                    activeNote.type = 3;
                }
                if (a == 3) {
                    activeNote.type = 2;
                    activeNote.dot = true;
                }
                if (a == 5) {
                    activeNote.type = 3;
                    tieNote.type = 1;
                }
                if (a == 6) {
                    activeNote.type = 3;
                    activeNote.dot = true;
                }
                //activeNote.duration = a;
                int restOrNot = rand.nextInt(0, restChance);
                if (restOrNot == 1) {
                    activeNote.restNote = true;
                    if (a == 5) {
                        tieNote.restNote = true;
                    } else if (a == 3) {
                        tieNote.dot = false;
                        tieNote.restNote = true;
                        tieNote.type = 1;
                        activeNote.dot = false;
                        activeNote.restNote = true;
                        activeNote.type = 2;
                    } else if (a == 6) {
                        tieNote.restNote = true;
                        tieNote.dot = false;
                        tieNote.type = 2;
                        activeNote.dot = false;
                    }
                } else {
                    activeNote.restNote = false;
                    tieNote.restNote = false;
                    int newPitchAddress = rand.nextInt(lower, upper);
                    if (newPitchAddress > 11 && newPitchAddress <= 18) {
                        activeNote.pitchOctave = 4;
                        tieNote.pitchOctave = 4;
                    } else if (newPitchAddress <= 4) {
                        activeNote.pitchOctave = 2;
                        tieNote.pitchOctave = 2;
                    } else if (newPitchAddress > 4 && newPitchAddress <= 11){
                        activeNote.pitchOctave = 3;
                        tieNote.pitchOctave = 3;
                    } else if (newPitchAddress > 18 && newPitchAddress <= 25){
                        activeNote.pitchOctave = 5;
                        tieNote.pitchOctave = 5;
                    } else if (newPitchAddress > 25){
                        activeNote.pitchOctave = 6;
                        tieNote.pitchOctave = 6;
                    } else if (newPitchAddress <= -3 ){
                        activeNote.pitchOctave = 1;
                        activeNote.pitchOctave = 1;
                    }
                    activeNote.pitchStep = ((((newPitchAddress + 2 - 7 * (activeNote.pitchOctave - 2))+21) % 8)+1);
                    activeNote.pitchStep = (((newPitchAddress+3+21)%7)+1);

                    tieNote.pitchStep = activeNote.pitchStep;
                }
                newMeasure.noteList.add(activeNote);
                if (a == 5 || (a == 3 && activeNote.restNote) || (a == 6 && activeNote.restNote)) {
                    newMeasure.noteList.add(tieNote);
                }
            }
            currPart.measureList.add(newMeasure);
        }
        outPart.add(currPart);
        return outPart;
    }
    /**
     * Generates the music for a bass guitar
     * @param lower bottom left hand corner of fretboard to work with
     * @param upper upper right hand corner of fretboard to work with
     * @param key key to work with
     * @param random if false it is all the selected key, if true it modulates every so often
     * @param intype type of beat in time signature
     * @param inbeats number of beats in time signature
     * @param restChance chance of a note being a rest, 1/restChance
     * @param modFrequency how many measures between key modulation, if random is true
     * @return Part list
     */
    public List<Part> generateBass(int lower, int upper, int key, boolean random, int intype, int inbeats, int restChance, int modFrequency, int clef) {
        List<Part> outPart = new ArrayList<>();
        String[] allClefLines = new String[]{"4","2","2","5"};
        String[] allClefStrings = new String[]{"G","F","F","C"};
        Part currPart = new Part();
        timebeats = inbeats;
        timetype = intype;
        Random rand = new Random();
        Measure newMeasure = new Measure();
        chunkLength = inbeats;
        Note activeNote = new Note();
        Note tieNote = new Note();
        int[] divisions = new int[6];
        for (int m = 0; m < 4; m++) {
            newMeasure = new Measure(Integer.toString(m + 1), 2, 0, inbeats, intype, allClefStrings[clef], Integer.parseInt(allClefLines[clef]),1);
            newMeasure.staves = "1";
            newMeasure.clefLines = Arrays.copyOfRange(allClefLines,clef,clef+1);;
            newMeasure.clefSigns = Arrays.copyOfRange(allClefStrings,clef,clef+1);
            activeNote = new Note();
            activeNote.type = 1;
            activeNote.pitchOctave = 3;
            activeNote.pitchStep = 5;
            newMeasure.noteList.add(activeNote);
            for (int n = 1; n < inbeats; n++) {
                activeNote = new Note();
                activeNote.type = 1;
                activeNote.pitchOctave = 3;
                activeNote.pitchStep = 1;
                newMeasure.noteList.add(activeNote);
            }
            currPart.measureList.add(newMeasure);
        }
        int currentKeyFifths = key;
        for (int m = 0; m < 512; m++) {

            newMeasure = new Measure(Integer.toString(m + 5), 2, currentKeyFifths, inbeats, intype, allClefStrings[clef], Integer.parseInt(allClefLines[clef]),1);
            newMeasure.staves = "1";
            newMeasure.clefSigns = new String[]{allClefStrings[clef]};
            newMeasure.clefLines = new String[]{allClefLines[clef]};
//            newMeasure.clefLines = Arrays.copyOfRange(allClefLines,clef,clef+1);;
//            newMeasure.clefSigns = Arrays.copyOfRange(allClefStrings,clef,clef+1);
            newMeasure.keyFifths = currentKeyFifths;
            if (m % modFrequency == 0 && m > 0 && random){
                newMeasure.keyFifths = rand.nextInt(-7,8);
                currentKeyFifths = newMeasure.keyFifths;
            }
            int index = 0;
            while (index < inbeats) {
                activeNote = new Note();
                activeNote.restNote = false;
                activeNote.dot = false;
                tieNote = new Note();
                tieNote.restNote = false;
                tieNote.dot = false;
                int a = 0;
                if (index == inbeats-1) {
                    a = 1;
                } else {
                    a = rand.nextInt(1, (inbeats+1) - index);
                }
                newMeasure.aList[index] = a;
                index += a;
                if (a == 1) {
                    activeNote.type = 1;
                }
                if (a == 8) {
                    activeNote.type = 4;
                }
                if (a == 2) {
                    activeNote.type = 2;
                }
                if (a == 4) {
                    activeNote.type = 3;
                }
                if (a == 3) {
                    activeNote.type = 2;
                    activeNote.dot = true;
                }
                if (a == 5) {
                    activeNote.type = 3;
                    tieNote.type = 1;
                }
                if (a == 6) {
                    activeNote.type = 3;
                    activeNote.dot = true;
                }
                //activeNote.duration = a;
                int restOrNot = rand.nextInt(0, restChance);
                if (restOrNot == 1) {
                    activeNote.restNote = true;
                    if (a == 5) {
                        tieNote.restNote = true;
                    } else if (a == 3) {
                        tieNote.dot = false;
                        tieNote.restNote = true;
                        tieNote.type = 1;
                        activeNote.dot = false;
                        activeNote.restNote = true;
                        activeNote.type = 2;
                    } else if (a == 6) {
                        tieNote.restNote = true;
                        tieNote.dot = false;
                        tieNote.type = 2;
                        activeNote.dot = false;
                    }
                } else {
                    activeNote.restNote = false;
                    tieNote.restNote = false;
                    int newPitchAddress = rand.nextInt(lower, upper);
                    if (newPitchAddress > 11 && newPitchAddress <= 18) {
                        activeNote.pitchOctave = 4;
                        tieNote.pitchOctave = 4;
                    } else if (newPitchAddress <= 4) {
                        activeNote.pitchOctave = 2;
                        tieNote.pitchOctave = 2;
                    } else if (newPitchAddress > 4 && newPitchAddress <= 11){
                        activeNote.pitchOctave = 3;
                        tieNote.pitchOctave = 3;
                    } else if (newPitchAddress > 18 && newPitchAddress <= 25){
                        activeNote.pitchOctave = 5;
                        tieNote.pitchOctave = 5;
                    } else if (newPitchAddress > 25){
                        activeNote.pitchOctave = 6;
                        tieNote.pitchOctave = 6;
                    } else if (newPitchAddress <= -3){
                        activeNote.pitchOctave = 1;
                        tieNote.pitchOctave = 1;

                    }
                    activeNote.pitchStep = (((newPitchAddress + 2 - 7 * (activeNote.pitchOctave - 2))+21)%8)+1;
                    activeNote.pitchStep = (((newPitchAddress+3+21)%7)+1);
                    tieNote.pitchStep = activeNote.pitchStep;
                }
                newMeasure.noteList.add(activeNote);
                if (a == 5 || (a == 3 && activeNote.restNote) || (a == 6 && activeNote.restNote)) {
                    newMeasure.noteList.add(tieNote);
                }
            }
            currPart.measureList.add(newMeasure);
        }
        outPart.add(currPart);
        return outPart;
    }
    /**
     * Generates the music for a bass guitar
     * @param lower bottom left hand corner of fretboard to work with
     * @param upper upper right hand corner of fretboard to work with
     * @param key key to work with
     * @param random if false it is all the selected key, if true it modulates every so often
     * @param intype type of beat in time signature
     * @param inbeats number of beats in time signature
     * @param restChance chance of a note being a rest, 1/restChance
     * @param modFrequency how many measures between key modulation, if random is true
     * @return Part list
     */
    public List<Part> generateBassFiveString(int lower, int upper, int key, boolean random, int intype, int inbeats, int restChance, int modFrequency) {
        List<Part> outPart = new ArrayList<>();
        Part currPart = new Part();
        timebeats = inbeats;
        timetype = intype;
        Random rand = new Random();
        Measure newMeasure = new Measure();
        chunkLength = inbeats;
        Note activeNote = new Note();
        Note tieNote = new Note();
        int[] divisions = new int[6];
        for (int m = 0; m < 4; m++) {
            newMeasure = new Measure(Integer.toString(m + 1), 2, 0, inbeats, intype, "F", 4,3);
            newMeasure.staves = "3";
            newMeasure.clefLines = new String[]{"2", "4","3"};
            newMeasure.clefSigns = new String[]{"G", "F","F"};
            activeNote = new Note();
            activeNote.type = 1;
            activeNote.pitchOctave = 3;
            activeNote.pitchStep = 5;
            newMeasure.noteList.add(activeNote);
            for (int n = 1; n < inbeats; n++) {
                activeNote = new Note();
                activeNote.type = 1;
                activeNote.pitchOctave = 3;
                activeNote.pitchStep = 1;
                newMeasure.noteList.add(activeNote);
            }
            currPart.measureList.add(newMeasure);
        }
        int currentKeyFifths = key;
        for (int m = 0; m < 512; m++) {

            newMeasure = new Measure(Integer.toString(m + 5), 2, currentKeyFifths, inbeats, intype, "F", 4,3);
            newMeasure.staves = "3";
            newMeasure.clefLines = new String[]{"2", "4","3"};
            newMeasure.clefSigns = new String[]{"G", "F","F"};
            newMeasure.keyFifths = currentKeyFifths;
            if (m % modFrequency == 0 && m > 0 && random){
                newMeasure.keyFifths = rand.nextInt(-7,8);
                currentKeyFifths = newMeasure.keyFifths;
            }
            int index = 0;
            while (index < inbeats) {
                activeNote = new Note();
                activeNote.restNote = false;
                activeNote.dot = false;
                tieNote = new Note();
                tieNote.restNote = false;
                tieNote.dot = false;
                int a = 0;
                if (index == inbeats-1) {
                    a = 1;
                } else {
                    a = rand.nextInt(1, (inbeats+1) - index);
                }
                newMeasure.aList[index] = a;
                index += a;
                if (a == 1) {
                    activeNote.type = 1;
                }
                if (a == 8) {
                    activeNote.type = 4;
                }
                if (a == 2) {
                    activeNote.type = 2;
                }
                if (a == 4) {
                    activeNote.type = 3;
                }
                if (a == 3) {
                    activeNote.type = 2;
                    activeNote.dot = true;
                }
                if (a == 5) {
                    activeNote.type = 3;
                    tieNote.type = 1;
                }
                if (a == 6) {
                    activeNote.type = 3;
                    activeNote.dot = true;
                }
                //activeNote.duration = a;
                int restOrNot = rand.nextInt(0, restChance);
                if (restOrNot == 1) {
                    activeNote.restNote = true;
                    if (a == 5) {
                        tieNote.restNote = true;
                    } else if (a == 3) {
                        tieNote.dot = false;
                        tieNote.restNote = true;
                        tieNote.type = 1;
                        activeNote.dot = false;
                        activeNote.restNote = true;
                        activeNote.type = 2;
                    } else if (a == 6) {
                        tieNote.restNote = true;
                        tieNote.dot = false;
                        tieNote.type = 2;
                        activeNote.dot = false;
                    }
                } else {
                    activeNote.restNote = false;
                    tieNote.restNote = false;
                    int newPitchAddress = rand.nextInt(lower, upper);
                    if (newPitchAddress > 11 && newPitchAddress <= 18) {
                        activeNote.pitchOctave = 4;
                        tieNote.pitchOctave = 4;
                    } else if (newPitchAddress <= 4) {
                        activeNote.pitchOctave = 2;
                        tieNote.pitchOctave = 2;
                    } else if (newPitchAddress > 4 && newPitchAddress <= 11){
                        activeNote.pitchOctave = 3;
                        tieNote.pitchOctave = 3;
                    } else if (newPitchAddress > 18 && newPitchAddress <= 25){
                        activeNote.pitchOctave = 5;
                        tieNote.pitchOctave = 5;
                    } else if (newPitchAddress > 25 ){
                        activeNote.pitchOctave = 6;
                        tieNote.pitchOctave = 6;
                    }
                    if (newPitchAddress <= -3 ){
                        activeNote.pitchOctave = 1;
                        tieNote.pitchOctave = 1;
                    }
                    if (newPitchAddress <= -10){
                        activeNote.pitchOctave = 0;
                        tieNote.pitchOctave = 0;
                    }
                    activeNote.pitchStep =((( (newPitchAddress + 3 - 7 * (activeNote.pitchOctave - 2))+21)) % 8)+1;
                    activeNote.pitchStep = (((newPitchAddress+3+21)%7)+1);

                    tieNote.pitchStep = activeNote.pitchStep;
                }
                newMeasure.noteList.add(activeNote);
                if (a == 5 || (a == 3 && activeNote.restNote) || (a == 6 && activeNote.restNote)) {
                    newMeasure.noteList.add(tieNote);
                }
            }
            currPart.measureList.add(newMeasure);
        }
        outPart.add(currPart);
        return outPart;
    }
//    /**
//     * Generates the music for a bass guitar
//     * @param lower bottom left hand corner of fretboard to work with
//     * @param upper upper right hand corner of fretboard to work with
//     * @param key key to work with
//     * @param random if false it is all the selected key, if true it modulates every so often
//     * @param intype type of beat in time signature
//     * @param inbeats number of beats in time signature
//     * @param restChance chance of a note being a rest, 1/restChance
//     * @param modFrequency how many measures between key modulation, if random is true
//     * @return Part list
//     */
//    public List<Part> generateBassFiveString(int lower, int upper, int key, boolean random, int intype, int inbeats, int restChance, int modFrequency, int[] staves) {
//        int numStaves = 0;
//        for (int spot = 0; spot < 4; spot++){
//            if (staves[spot] == 1){
//                numStaves++;
//            }
//        }
//        String[] allClefSigns = new String[]{"C","G","F","C"};
//        int[] allClefLines = new int[]{}
//        String[] clefStrings = new String[numStaves];
//        int[] clefLineNums = new int[numStaves];
//        List<Part> outPart = new ArrayList<>();
//        Part currPart = new Part();
//        timebeats = inbeats;
//        timetype = intype;
//        Random rand = new Random();
//        Measure newMeasure = new Measure();
//        chunkLength = inbeats;
//        Note activeNote = new Note();
//        Note tieNote = new Note();
//        int[] divisions = new int[6];
//        for (int m = 0; m < 4; m++) {
//            newMeasure = new Measure(Integer.toString(m + 1), 2, 0, inbeats, intype, "F", 4);
//            newMeasure.staves = "3";
//            newMeasure.clefLines = new String[]{"2", "4","5"};
//            newMeasure.clefSigns = new String[]{"G", "F","C"};
//            activeNote = new Note();
//            activeNote.type = 1;
//            activeNote.pitchOctave = 3;
//            activeNote.pitchStep = 5;
//            newMeasure.noteList.add(activeNote);
//            for (int n = 1; n < inbeats; n++) {
//                activeNote = new Note();
//                activeNote.type = 1;
//                activeNote.pitchOctave = 3;
//                activeNote.pitchStep = 1;
//                newMeasure.noteList.add(activeNote);
//            }
//            currPart.measureList.add(newMeasure);
//        }
//        int currentKeyFifths = key;
//        for (int m = 0; m < 512; m++) {
//
//            newMeasure = new Measure(Integer.toString(m + 5), 2, currentKeyFifths, inbeats, intype, "F", 2);
//            newMeasure.staves = "3";
//            newMeasure.clefLines = new String[]{"2", "4","5"};
//            newMeasure.clefSigns = new String[]{"G", "F","C"};
//            newMeasure.keyFifths = currentKeyFifths;
//            if (m % modFrequency == 0 && m > 0 && random){
//                newMeasure.keyFifths = rand.nextInt(-7,8);
//                currentKeyFifths = newMeasure.keyFifths;
//            }
//            int index = 0;
//            while (index < inbeats) {
//                activeNote = new Note();
//                activeNote.restNote = false;
//                activeNote.dot = false;
//                tieNote = new Note();
//                tieNote.restNote = false;
//                tieNote.dot = false;
//                int a = 0;
//                if (index == inbeats-1) {
//                    a = 1;
//                } else {
//                    a = rand.nextInt(1, (inbeats+1) - index);
//                }
//                newMeasure.aList[index] = a;
//                index += a;
//                if (a == 1) {
//                    activeNote.type = 1;
//                }
//                if (a == 8) {
//                    activeNote.type = 4;
//                }
//                if (a == 2) {
//                    activeNote.type = 2;
//                }
//                if (a == 4) {
//                    activeNote.type = 3;
//                }
//                if (a == 3) {
//                    activeNote.type = 2;
//                    activeNote.dot = true;
//                }
//                if (a == 5) {
//                    activeNote.type = 3;
//                    tieNote.type = 1;
//                }
//                if (a == 6) {
//                    activeNote.type = 3;
//                    activeNote.dot = true;
//                }
//                //activeNote.duration = a;
//                int restOrNot = rand.nextInt(0, restChance);
//                if (restOrNot == 1) {
//                    activeNote.restNote = true;
//                    if (a == 5) {
//                        tieNote.restNote = true;
//                    } else if (a == 3) {
//                        tieNote.dot = false;
//                        tieNote.restNote = true;
//                        tieNote.type = 1;
//                        activeNote.dot = false;
//                        activeNote.restNote = true;
//                        activeNote.type = 2;
//                    } else if (a == 6) {
//                        tieNote.restNote = true;
//                        tieNote.dot = false;
//                        tieNote.type = 2;
//                        activeNote.dot = false;
//                    }
//                } else {
//                    activeNote.restNote = false;
//                    tieNote.restNote = false;
//                    int newPitchAddress = rand.nextInt(lower, upper);
//                    if (newPitchAddress > 11 && newPitchAddress <= 18) {
//                        activeNote.pitchOctave = 4;
//                        tieNote.pitchOctave = 4;
//                    } else if (newPitchAddress <= 4) {
//                        activeNote.pitchOctave = 2;
//                        tieNote.pitchOctave = 2;
//                    } else if (newPitchAddress > 4 && newPitchAddress <= 11){
//                        activeNote.pitchOctave = 3;
//                        tieNote.pitchOctave = 3;
//                    } else if (newPitchAddress > 18){
//                        activeNote.pitchOctave = 5;
//                        tieNote.pitchOctave = 5;
//                    }
//                    if (newPitchAddress <= -3 ){
//                        activeNote.pitchOctave = 1;
//                        tieNote.pitchOctave = 1;
//                    }
//                    if (newPitchAddress <= -10){
//                        activeNote.pitchOctave = 0;
//                        tieNote.pitchOctave = 0;
//                    }
//                    activeNote.pitchStep = newPitchAddress + 3 - 7 * (activeNote.pitchOctave - 2);
//                    tieNote.pitchStep = activeNote.pitchStep;
//                }
//                newMeasure.noteList.add(activeNote);
//                if (a == 5 || (a == 3 && activeNote.restNote) || (a == 6 && activeNote.restNote)) {
//                    newMeasure.noteList.add(tieNote);
//                }
//            }
//            currPart.measureList.add(newMeasure);
//        }
//        outPart.add(currPart);
//        return outPart;
//    }
    /**
     * If this is not that semi-musical demo I put on Facebook last year, I'll have to check the archive again
     * @param multin number of something??
     * @return A Part list constructed
     */

    public List<Part> generateMultiple(int multin) {
        activeRhythinBetween = new ArrayList<>();
        Random rand = new Random();
        activeCadence = Arrays.asList(cadenceobj.cadenceArray[rand.nextInt(0, 14)]);
        cadenceLength = activeCadence.size();
        activeRhythinBetween.add(rhythmobj.basicRhythmList.get(rand.nextInt(0, 5)));
        rhythmLength = activeRhythinBetween.get(0).size();
        timebeats = rhythmLength;
        timetype = 8;
        Note newNote = new Note();
        mmeasureList = new ArrayList<Measure>();
        Measure newMeasure = new Measure();
        chunkLength = cadenceLength * timebeats * 4;
        outfirst = new int[chunkLength][2];
        int[][] outcadence = new int[chunkLength][2];
        int[] outrhythm = new int[chunkLength];
        int cli = 0;
        List<Part> outPartList = new ArrayList<Part>();
        List<Part> parts = new ArrayList<Part>();
        Part currPart = new Part();
        for (int min = 0; min < multin; min++) {
            noteList = new ArrayList<Note>();
            mmeasureList = new ArrayList<Measure>();
            parts.add(new Part());
            parts.get(min).partNumber = min + 1;
            currPart = new Part();
            for (int mi = 0; mi < cadenceLength; mi++) {
                for (int eighths = 0; eighths < timebeats; eighths++) {
                    cli = mi * timebeats + eighths;
                    outrhythm[cli] = activeRhythinBetween.get(0).get(eighths);
                    outcadence[cli] = activeCadence.get(mi);
                }
            }
            int[][][] notesOut = new int[chunkLength][3][3];
            for (int div = 0; div < chunkLength; div++) {
                notesOut[div][0] = scalesobj.majorScale[outcadence[div][0]];
                if (outcadence[div][1] == 0) {
                    notesOut[div][1] = scalesobj.majorScale[(outcadence[div][0] + 2) % 7];
                } else if (outcadence[div][1] == 1) {
                    notesOut[div][1] = scalesobj.nmBydeg[(outcadence[div][0] + 2) % 7];
                }
                notesOut[div][2] = scalesobj.majorScale[(4 + outcadence[div][0]) % 7];
                if (outrhythm[div] == 0) {
                    notesOut[div][0] = new int[]{99, 0, 0};
                    notesOut[div][1] = new int[]{99, 0, 0};
                    notesOut[div][2] = new int[]{99, 0, 0};
                }
            }
            int[][] activeNoteOut = new int[chunkLength][3];
            int[] activeDurationsOut = new int[chunkLength];
            for (int div = 0; div < chunkLength; div++) {
                if (outrhythm[div] == 1) {
                    if (notesOut[div][0][0] != 99) {
                        activeNoteOut[div] = notesOut[div][0];
                    }
                    if (notesOut[div][0][0] == 99) {
                        activeNoteOut[div] = new int[]{99, 0, 0};
                    }
                }
                if (outrhythm[div] == 0) {
                    activeNoteOut[div] = new int[]{99, 0, 0};
                }
                if (outrhythm[div] == 2) {
                    activeNoteOut[div] = notesOut[div][rand.nextInt(1, 3)];
                }
                int trailingZeros = 1;
                for (int index = div + 1; index < chunkLength; index++) {
                    if (outrhythm[index] == 0) {
                        trailingZeros++;
                    } else {
                        break;
                    }
                }
                if (outrhythm[div] != 0) {
                    activeDurationsOut[div] = trailingZeros;
                } else {
                    activeDurationsOut[div] = 1;
                }
            }

            for (int repeat = 0; repeat < 4; repeat++) {
                int clii = 0;
                for (int i = 0; i < cadenceLength; i++) {
                    newMeasure = new Measure();
                    newMeasure = new Measure(Integer.toString(i + 1), timebeats, 0, timebeats, 8, "F", 2);
                    for (int ni = 0; ni < timebeats; ni++) {
                        newNote = new Note();
                        clii = i * timebeats + ni;
                        newNote = new Note(activeNoteOut[clii][0], 4, 1, 3, activeNoteOut[clii][1]);
                        if (activeNoteOut[clii][0] == 99) {
                            //newNote.type = 0;
                            newNote.restNote = true;
                        }
                        noteList.add(newNote);
                    }
                    newMeasure.noteList = noteList;
                    mmeasureList.add(newMeasure);
                }
            }
            currPart.measureList = mmeasureList;
            //partList.get(0).measureList = measureList;
            //partList.get(0).partNumber = min;
            //groups.add(new ArrayList<Person>(Arrays.asList(new Person("John"))));
            //
            outPartList.add(currPart);
        }
        return outPartList;
    }
    /**
     * Generation function that works and is used currently
     * @return the output in a list of Part s
     */
    public List<Part> generatePianoWalk() {
        List<Part> outPart = new ArrayList<>();
        Part currPart = new Part();
        timebeats = 6;
        timetype = 8;
        Random rand = new Random();
        Measure newMeasure = new Measure();
        chunkLength = 6;
        Note activeNote = new Note();
        Note tieNote = new Note();
        int[] divisions = new int[6];
        for (int m = 0; m < 4; m++) {
            newMeasure = new Measure(Integer.toString(m + 1), 2, 0, 6, 8, "F", 2);
            newMeasure.staves = "2";
            newMeasure.clefLines = new String[]{"4", "2"};
            newMeasure.clefSigns = new String[]{"G", "F"};
            activeNote = new Note();
            activeNote.type = 1;
            activeNote.pitchOctave = 3;
            activeNote.pitchStep = 5;
            newMeasure.noteList.add(activeNote);
            for (int n = 1; n < 6; n++) {
                activeNote = new Note();
                activeNote.type = 1;
                activeNote.pitchOctave = 3;
                activeNote.pitchStep = 1;
                newMeasure.noteList.add(activeNote);
            }
            currPart.measureList.add(newMeasure);
        }
        int currentKeyFifths = 0;
        for (int m = 0; m < 512; m++) {
            newMeasure = new Measure(Integer.toString(m + 5), 2, 0, 6, 8, "F", 2);
            newMeasure.staves = "2";
            newMeasure.clefLines = new String[]{"4", "2"};
            newMeasure.clefSigns = new String[]{"G", "F"};
            newMeasure.keyFifths = currentKeyFifths;
            if (m % 16 == 0 && m > 0){
                //newMeasure.keyFifths = rand.nextInt(-7,8);
                newMeasure.keyFifths = 4;
                currentKeyFifths = newMeasure.keyFifths;
            }
            Note[][] notes = new Note[newMeasure.divisions][4];
            int octave = rand.nextInt(1,4);
            int inversion = rand.nextInt(1,4);
            int[] chordWeights = new int[]{0,1,1,1,2,2,3,3,4,4,4,5,5,5,6,6,7,7};
            int chord = rand.nextInt(1,chordWeights.length);
            int bottomStep = 0;
            if (inversion == 1){
                bottomStep = 1;
            }
            if (inversion == 2){
                bottomStep = 5;
            }
            if (inversion == 3){
                bottomStep = 3;
            }
            boolean constraintsSatisfied = false;
            while (!constraintsSatisfied){

            }
            int spot = 0;
            while (spot < 6){
                int timeStep = rand.nextInt(1,7-spot);

            }
            int index = 0;
            while (index < 6) {
                activeNote = new Note();
                activeNote.restNote = false;
                activeNote.dot = false;
                tieNote = new Note();
                tieNote.restNote = false;
                tieNote.dot = false;
                int a = 0;
                if (index == 5) {
                    a = 1;
                } else {
                    a = rand.nextInt(1, 7 - index);
                }
                newMeasure.aList[index] = a;
                index += a;
                if (a == 1) {
                    activeNote.type = 1;
                }
                if (a == 8) {
                    activeNote.type = 4;
                }
                if (a == 2) {
                    activeNote.type = 2;
                }
                if (a == 4) {
                    activeNote.type = 3;
                }
                if (a == 3) {
                    activeNote.type = 2;
                    activeNote.dot = true;
                }
                if (a == 5) {
                    activeNote.type = 3;
                    tieNote.type = 1;
                }
                if (a == 6) {
                    activeNote.type = 3;
                    activeNote.dot = true;
                }
                //activeNote.duration = a;
                int restOrNot = rand.nextInt(0, 5);
                if (restOrNot == 1) {
                    activeNote.restNote = true;
                    if (a == 5) {
                        tieNote.restNote = true;
                    } else if (a == 3) {
                        tieNote.dot = false;
                        tieNote.restNote = true;
                        tieNote.type = 1;
                        activeNote.dot = false;
                        activeNote.restNote = true;
                        activeNote.type = 2;
                    } else if (a == 6) {
                        tieNote.restNote = true;
                        tieNote.dot = false;
                        tieNote.type = 2;
                        activeNote.dot = false;
                    }
                } else {
                    activeNote.restNote = false;
                    tieNote.restNote = false;
                    int newPitchAddress = rand.nextInt(3, 15);
                    if (newPitchAddress > 11 && newPitchAddress <= 18) {
                        activeNote.pitchOctave = 4;
                        tieNote.pitchOctave = 4;
                    } else if (newPitchAddress <= 4) {
                        activeNote.pitchOctave = 2;
                        tieNote.pitchOctave = 2;
                    } else if (newPitchAddress > 4 && newPitchAddress <= 11){
                        activeNote.pitchOctave = 3;
                        tieNote.pitchOctave = 3;
                    } else if (newPitchAddress > 18){
                        activeNote.pitchOctave = 5;
                        tieNote.pitchOctave = 5;
                    }
                    activeNote.pitchStep = newPitchAddress + 3 - 7 * (activeNote.pitchOctave - 2);
                    tieNote.pitchStep = activeNote.pitchStep;
                }
                newMeasure.noteList.add(activeNote);
                if (a == 5 || (a == 3 && activeNote.restNote) || (a == 6 && activeNote.restNote)) {
                    newMeasure.noteList.add(tieNote);
                }
            }
            currPart.measureList.add(newMeasure);
        }
        outPart.add(currPart);
        return outPart;
    }
}
