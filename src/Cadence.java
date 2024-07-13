import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Sets of chord changes
 */
public class Cadence {
    //int[][] blues = new int[][]{{0,0},{0,0},{0,0},{0,0},{3,0},{3,0},{0,0},{0,0},{4,0},{3,0},{0,0},{4,0}};
    //int[][] andalusian = new int[][]{{5,1},{4,0},{3,0},{2,0}};
    //int[][] coolio = new int[][]{{0,0},{4,0},{5,1},{2,1},{3,0},{0,0},{3,0},{4,0}};
    //int[][] mozart_clarinetquintet581 = new int[][]{{0,0},{0,0},{1,1},{4,0},{0,0},{4,0},{0,0},{4,0},{0,0},{0,0},{1,1},{4,0},{5,1},{3,0},{4,0},{0,0}};
    //int[][] wizardofoz_somewhereoverrainbow = new int[][]{{0,0},{0,0},{2,1},{0,0},{3,0},{3,0},{0,0},{0,0},{3,0},{3,1},{0,0},{5,1},{1,1},{4,0},{0,0}};
    //int[][] beatles_whenimsixtyfour = new int[][]{{0,0},{0,0},{0,0},{4,0},{4,0},{4,0},{4,0},{0,0}};
    //closed cadence, sense of finality and conclusion, the dominant can be extended to increase the tension
    //int[][] authentic_cadence = new int[][]{{4,0},{0,0}};
    //closed cadence, used as 'amen' in hymns, maybe it is an extension of the authentic cadence
    //int[][] plagal_cadence = new int[][]{{3,0},{0,0}};
    //anything that ends on the dominant is a half cadence
    //open cadences
    //int[][] half_cadenceone = new int[][]{{1,0},{4,0}};
    //int[][] half_cadencetwo = new int[][]{{0,0},{4,0}};
    //int[][] halfcadencethree = new int[][]{{3,0},{4,0}};
    //open cadence
    //int[][] deceptiveCadence = new int[][]{{4,0},{5,1}};
    //the 4th implies a desired return to the tonic
    //////////////////////////////////////////////////////
    //not inversions, the melody notes
    //The top notes (the top melody) have an important effect on the character of an authentic cadence (V – I):
    //The sense of finality is the strongest when the tonic note is on top the tonic chord (as we’ve seen in the examples above);
    //The sense of finality is weakened when the third of the tonic is on top the tonic chord;
    //And the sense of finality is even weaker when the fifth of the tonic is on the top the tonic chord.
    //Apart from the chords and the top melody, the musical effect of a cadence also depends on whether its final chord occurs on a strong beat or a weak beat.
    //In an authentic cadence (V – I):
    ////The sense of resolution is stronger if the final chord is on a stronger beat:
    //The sense of resolution is weaker if the final chord is on a weaker beat:
    //Here is an example from Bizet’s Farandole from L’ Arlessiene Suite 2. The 2 phrases here end on the tonic D but the second one is more final just because it ends on the strong beat.
    //And to add to our possibilities, even the inversions of the chords themselves have a significant impact on the effect of a cadence.
    //The sense of conclusion of the authentic cadence is strongest when both chords are in root position (with the roots of the chords in the bass).

    /**
     * Chord progressions
     */
    public int[][][] cadenceArray = new int[14][16][2];
    /**
     * Loads the hardcoded progressions
     */
    Cadence() {
        cadenceArray[13] = new int[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}, {4, 0}, {3, 0}, {1, 0}, {4, 0}};
        cadenceArray[12] = new int[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}, {3, 0}, {3, 0}, {0, 0}, {0, 0}, {4, 0}, {3, 0}, {0, 0}, {4, 0}};
        cadenceArray[11] = new int[][]{{0, 0}, {3, 0}, {0, 0}, {0, 0}, {3, 0}, {3, 0}, {0, 0}, {0, 0}, {4, 0}, {3, 0}, {0, 0}, {4, 0}};
        cadenceArray[10] = new int[][]{{5, 1}, {4, 0}, {3, 0}, {2, 0}};
        cadenceArray[9] = new int[][]{{0, 0}, {4, 0}, {5, 1}, {2, 1}, {3, 0}, {0, 0}, {3, 0}, {4, 0}};
        cadenceArray[8] = new int[][]{{0, 0}, {0, 0}, {1, 1}, {4, 0}, {0, 0}, {4, 0}, {0, 0}, {4, 0}, {0, 0}, {0, 0}, {1, 1}, {4, 0}, {5, 1}, {3, 0}, {4, 0}, {0, 0}};
        cadenceArray[7] = new int[][]{{0, 0}, {0, 0}, {2, 1}, {0, 0}, {3, 0}, {3, 0}, {0, 0}, {0, 0}, {3, 0}, {3, 1}, {0, 0}, {5, 1}, {1, 1}, {4, 0}, {0, 0}};
        cadenceArray[6] = new int[][]{{0, 0}, {0, 0}, {0, 0}, {4, 0}, {4, 0}, {4, 0}, {4, 0}, {0, 0}};
        //closed cadence, sense of finality and conclusion, the dominant can be extended to increase the tension
        cadenceArray[0] = new int[][]{{4, 0}, {0, 0}};
        //closed cadence, used as 'amen' in hymns, maybe it is an extension of the authentic cadence
        cadenceArray[1] = new int[][]{{3, 0}, {0, 0}};
        //anything that ends on the dominant is a half cadence
        //open cadences
        cadenceArray[2] = new int[][]{{1, 0}, {4, 0}};
        cadenceArray[3] = new int[][]{{0, 0}, {4, 0}};
        cadenceArray[4] = new int[][]{{3, 0}, {4, 0}};
        //open cadence
        cadenceArray[5] = new int[][]{{4, 0}, {5, 1}};
    }
}
