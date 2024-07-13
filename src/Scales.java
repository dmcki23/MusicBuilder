/**
 * Basic scale and chord templates, coming back to it I don't know if this works properly
 */
public class Scales {
    /**
     * Major scale
     */

    int[][] majorScale = new int[][]{{0,0,0},{1,0,0},{2,0,0},{3,0,0},{4,0,0},{5,0,0},{6,0,0},{7,0,1}};
    /**
     * Natural minor
     */
    int[][] naturalMinor = new int[][]{{5,0,0},{6,0,0},{0,0,0},{1,0,0},{2,0,0},{3,0,0},{4,0,0},{5,0,1}};
    /**
     * Natural minor by degree
     */
    int[][] nmBydeg = new int[][]{{0,0,0},{1,0,0},{2,0,0},{3,-1,0},{4,0,0},{5,-1,0},{6,-1,0},{7,0,1}};
    /**
     * Harmonic minor
     */
    int[][] harmonicMinor = new int[][]{{0,0,0},{6,0,0},{7,0,0},{1,0,0},{2,0,0},{3,0,0},{4,0,0},{5,1,0},{6,0,1}};
    /**
     * Melodic minor
     */
    int[][] melodicMinor = new int[][]{{0,0,0},{6,0,0},{7,0,0},{1,0,0},{2,0,0},{3,0,0},{4,1,0},{5,1,0},{6,0,1}};
    /**
     * Harmonic major
     */
    int[][] harmonicMajor = new int[][]{{0,0,0},{1,0,0},{2,0,0},{3,0,0},{4,0,0},{5,0,0},{6,0,0},{7,-1,0},{8,0,0}};
    /**
     * Melodic major
     */
    int[][] melodicMajor = new int[][]{{0,0,0},{1,0,0},{2,0,0},{3,0,0},{4,0,0},{5,0,0},{6,-1,0},{7,-1,0},{8,0,0}};
    //harmonic major and minors are just linearly applied sharps and flats
    //a rhythm bitmasking a cadence, with inversions being a rhythm part
    //that one chart of the fractions that the notes' frequencies create can
    //be applied to rhythm. That and or a perfect pythagorean division block
    //intro verse chorus verse verse chorus bridge chorus outro
    //zero indexed scale degree analysis
    /**
     * Circle of fifths
     */
    int[][] cof = new int[][]{{0,0,0},{4,0,0},{1,0,0},{5,0,0},{2,0,0},{6,0,0},{3,1,0},{0,1,0},{4,1,0},{1,1,0},{5,1,0},{2,1,0},{6,1,0}};
    /**
     * Circle of fifths backwards
     */
    int[][] cofbackwards = new int[][]{{0,0,0},{3,0,0},{6,-1,0},{2,-1,0},{5,-1,0},{1,-1,0},{4,-1,0},{0,-1,0},{3,-1,0},{6,-1,0},{2,-1,0},{5,-1,0},{1,-1,0},{4,-1,0}};
    /**
     * Modes
     */
    int[][][][] modes = new int[12][9][9][3];
    /**
     * Major chord
     */
    int[][] majorChord = new int[][]{{0,0},{1,0},{3,0},{5,0},{7,0}};
    /**
     * MInor chord
     */

    int[][] minorChord = new int[][]{{0,0},{1,0},{3,-1},{5,0},{7,0}};
    /**
     * Major keys
     */

    int[][][] majorKeys = new int[12][9][3];
    /**
     * Natural minor keys
     */
    int[][][] naturalMinorKeys = new int[12][9][3];
    /**
     * Natural minor by degree
     */
    int[][][] nmkeyBydeg = new int[12][9][3];
    /**
     * Harmonic minors
     */

    int[][][][] harmonicMinorKeys = new int[12][8][9][3];
    /**
     * Harmonic majors
     */
    int[][][][] harmonicMajorKeys = new int[12][8][9][3];
    /**
     * Initializes the arrays
     */
    Scales(){
        int numSharps;
        int sharpIndex;
        for (int key = 0; key < 12; key++){
            for (int scaleDegree = 0; scaleDegree < 8; scaleDegree++){
                majorKeys[key][scaleDegree][0] = majorScale[scaleDegree][0];
                majorKeys[key][scaleDegree][1] = majorScale[scaleDegree][1];
                majorKeys[key][scaleDegree][2] = majorScale[scaleDegree][2];
            }
        }
        sharpIndex = 4;
        for (int key = 1; key < 7; key++){
            majorKeys[key][sharpIndex][1] = 1;
            sharpIndex = (sharpIndex + 5) % 7;

        }
        int flatIndex = 6;
        for (int key = 11; key >6; key--){
            majorKeys[key][flatIndex][1] = -1;
            flatIndex = (flatIndex + 4) % 7;
        }

        numSharps = 0 ;
        sharpIndex = 0;
        for (int key = 0; key < 12; key++){
            for (int scaleDegree = 0; scaleDegree < 8; scaleDegree++){
                naturalMinorKeys[key][scaleDegree][0] = naturalMinor[scaleDegree][0];
                naturalMinorKeys[key][scaleDegree][1] = naturalMinor[scaleDegree][1];
                naturalMinorKeys[key][scaleDegree][2] = naturalMinor[scaleDegree][2];
            }
        }
        sharpIndex = 5;
        for (int key = 1; key < 7; key++){
            naturalMinorKeys[key][sharpIndex][1] += 1;
            sharpIndex = (sharpIndex + 4) % 7;

        }
        flatIndex = 6;
        for (int key = 11; key >6; key--){
            naturalMinorKeys[key][flatIndex][1] -= 1;
            flatIndex = (flatIndex + 3) % 7;
        }
        for (int key = 0; key < 12; key++){
            for (int har = 0; har < 8; har++){
                for (int sd = 0; sd < 8; sd++) {


                    harmonicMajorKeys[key][har][sd][0] = majorKeys[key][sd][0];
                    harmonicMajorKeys[key][har][sd][1] = majorKeys[key][sd][1];
                    harmonicMajorKeys[key][har][sd][2] = majorKeys[key][sd][2];
                    harmonicMinorKeys[key][har][sd][0] = naturalMinorKeys[key][sd][0];
                    harmonicMinorKeys[key][har][sd][1] = naturalMinorKeys[key][sd][1];
                    harmonicMinorKeys[key][har][sd][2] = naturalMinorKeys[key][sd][2];
                }
            }
        }
        sharpIndex = 7;
        for (int key = 0; key < 12; key++){
            for (int har = 0; har < 7; har++) {
                for (int hari = 0; hari < har; hari++) {
                    harmonicMinorKeys[key][har][6-hari][1] += 1;

                }
            }
        }
        flatIndex = 5;
        for (int key = 0; key < 12; key++){
            for (int har = 0; har < 7; har++) {
                flatIndex = 6;
                for (int hari = 0; hari < har; hari++) {
                    harmonicMajorKeys[key][har][flatIndex][1] -= 1;
                    flatIndex = (flatIndex + 3) % 7;
                }

            }
        }
        for (int key = 0; key < 12; key++){
            for (int mode = 0; mode < 9; mode++){
                for (int sd = 0; sd < 8; sd++){
                    for (int tagi = 0; tagi < 3; tagi++){
                        modes[key][mode][sd][tagi] = majorKeys[key][sd][tagi];
                    }
                }

            }
        }
        for (int key = 0; key < 12; key++){
            for (int mode = 1; mode < 8; mode++){
                int[][] tempTable = new int[10][3];
                for (int sd = 1; sd < 8; sd++){
                    for (int tagi = 0; tagi < 3; tagi++){
                        tempTable[sd][tagi] = modes[key][mode][(sd+1)%8][tagi];
                    }
                }
                for (int sd = 1; sd < 9; sd++){
                    for (int tagi = 0; tagi < 3; tagi++){
                        modes[key][mode][sd][tagi] = tempTable[sd][tagi];
                    }
                }
            }
        }

    }


}
