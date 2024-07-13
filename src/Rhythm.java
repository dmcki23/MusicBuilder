import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * some basic preprogrammed rhythms
 */
public class Rhythm {
    /**
     * Hardcoded rhythm, i love apple pie
     */
    int[] iloveapplepie = new int[]{1, 0, 1, 0, 2, 2, 2, 0};
    /**
     * Hardcoded rhythm, i love to play my drum
     */
    int[] ilovetoplayplaymydrum = new int[]{1, 0, 0, 1, 0, 1, 2, 2, 2, 0, 0, 0};
    /**
     * Hardcoded rhythm, mash potatoes
     */
    int[] mashpotatoes = new int[]{1, 0, 2, 2, 2, 0};
    /**
     * Hardcoded rhythm, hot chocolate
     */
    int[] hotchocolate = new int[]{1, 0, 2, 2};
    /**
     * Hardcoded rhythm, heartbeat
     */
    int[] heartbeat = new int[]{1, 1, 2, 0};
    //won't you come with me on vacation
    //alligators don't walk well on leashes
    /**
     * List of basic rhythms
     */
    List<List<Integer>> basicRhythmList = new ArrayList<>();
    /**
     * Initializes the lists from the hardcoded variables
     */
    Rhythm() {
        basicRhythmList.add(new ArrayList<Integer>(Arrays.asList(1, 0, 1, 0, 2, 2, 2, 0)));
        basicRhythmList.add(new ArrayList<Integer>(Arrays.asList(1, 0, 0, 1, 0, 1, 2, 2, 2, 0, 0, 0)));
        basicRhythmList.add(new ArrayList<Integer>(Arrays.asList(1, 0, 2, 2, 2, 0)));
        basicRhythmList.add(new ArrayList<Integer>(Arrays.asList(1, 0, 2, 2)));
        basicRhythmList.add(new ArrayList<Integer>(Arrays.asList(1, 1, 0, 0)));
        basicRhythmList.add(new ArrayList<Integer>(Arrays.asList(1, 1, 2, 0)));
    }
}
