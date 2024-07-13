import java.util.Arrays;
public class Converters {
    int[][][] degreeToButton = new int[8][2][8];
    int[] keyOctaves = new int[88];
    int[][][] semitoneKeyboard;
    int[][] tones = new int[][]{{1, 0}, {1, 1}, {2, 0}, {2, 1}, {3, 0}, {4, 0}, {4, 1}, {5, 0}, {5, 1}, {6, 0}, {6, 1}, {7, 0}};
    int[][] flatTones = new int[][]{{1, 0}, {2, 1}, {2, 0}, {3, 1}, {3, 0}, {4, 0}, {5, 1}, {5, 0}, {6, 1}, {6, 0}, {7, 1}, {7, 0}};
    int[][] circleFifths = new int[][]{{0,5,2,6,3,7,4},{0,4,7,3,6,2,5}};
    public Converters() {
        semitoneKeyboard = new int[2][88][2];

        semitoneKeyboard[0][0][0] = 6;
        semitoneKeyboard[0][0][1] = 0;
        semitoneKeyboard[0][1][0] = 6;
        semitoneKeyboard[0][1][1]= 1;
        semitoneKeyboard[0][2][0] = 7;
        semitoneKeyboard[0][2][1] = 0;
        semitoneKeyboard[1][0][0] = 6;
        semitoneKeyboard[1][0][1] = 0;
        semitoneKeyboard[1][1][0] = 7;
        semitoneKeyboard[1][1][1]= 1;
        semitoneKeyboard[1][2][0] = 7;
        semitoneKeyboard[1][2][1] = 0;
        decimalLoop:
        for (int oct = 0; oct < 7; oct++) {
            for (int tone = 0; tone < 12; tone++) {
                if (3 + (12*oct) + tone == 88) break decimalLoop;
                for (int spot = 0; spot < 2; spot++) {
                    semitoneKeyboard[0][3 + (12 * oct) + tone][spot] = tones[tone][spot];
                    semitoneKeyboard[1][3 + (12 * oct) + tone][spot] = flatTones[tone][spot];
                }
            }
        }
        for (int oct = 0; oct < 5; oct++) {
            for (int spot = 0; spot < 12; spot++) {
                keyOctaves[12 * oct + spot + 3] = oct + 1;
            }
        }
        keyOctaves[0] = 0;
        keyOctaves[1] = 0;
        keyOctaves[2] = 0;
    }


    public int[][] applyKey(int numAlters, int direction) {
        degreeToButton = new int[8][2][7];
        for (int row = 0; row < 8; row++){
            for (int column = 0; column < 2; column++){
                for (int octave = 0; octave < 7; octave++){
                    degreeToButton[row][column][octave] = -1;
                }
            }
        }
        int[][] out = new int[88][2];
        for (int spot = 0; spot < 88; spot++) {
            if (direction == 1) {
                int in = semitoneKeyboard[0][spot][0];
                int sharps = semitoneKeyboard[0][spot][1];
                for (int sspot = 0; sspot < 12; sspot++) {
                    if (tones[sspot][0] == in && tones[sspot][1] == sharps) {
                        out[spot][0] = tones[(sspot + numAlters * 5) % 12][0];
                        out[spot][1] = tones[(sspot + numAlters * 5) % 12][1];
                    }
                }
            } else {
                int in = semitoneKeyboard[0][spot][0];
                int sharps = semitoneKeyboard[0][spot][1];
                for (int sspot = 0; sspot < 12; sspot++) {
                    if (tones[sspot][0] == in && tones[sspot][1] == sharps) {
                        out[spot][0] = tones[(sspot + numAlters * 7) % 12][0];
                        out[spot][1] = tones[(sspot + numAlters * 7) % 12][1];
                    }
                }
            }
        }
        for (int spot = 0; spot < 88; spot++){
            degreeToButton[out[spot][0]][out[spot][1]][keyOctaves[spot]] = spot;
        }
        return out;
    }
    public void test(){
        System.out.println("As sharps");
        for (int spot = 0; spot < 88; spot++){
            System.out.println("Spot: " + spot + " " + semitoneKeyboard[0][spot][0] + " " + semitoneKeyboard[0][spot][1]);
        }
        System.out.println("As flats");
        for (int spot = 0; spot < 88; spot++){
            System.out.println("Spot: " + spot + " " + semitoneKeyboard[1][spot][0] + " " + semitoneKeyboard[1][spot][1]);
        }
        int[][][][] modifieds = new int[8][2][88][2];
        for (int direction = 0; direction < 2; direction++){
            for (int numSharpFlats = 0; numSharpFlats < 1; numSharpFlats++){
                if (direction == 0){
                    System.out.println("Sharps: " + numSharpFlats + " key " + circleFifths[direction][numSharpFlats]);
                }
                modifieds[numSharpFlats][direction] = applyKey(numSharpFlats, direction);
                for (int spot = 0; spot < 88; spot++){
                    System.out.print("Spot: " + spot + " " + modifieds[numSharpFlats][1][spot][0] + " " + modifieds[numSharpFlats][1][spot][1] + " ");
                    if (spot % 12 == 0 && spot != 0){
                        System.out.print("\n");
                    }
                }
                System.out.print("\n\n");
                for (int octave = 0; octave < 6; octave++){
                    for (int degree = 1; degree < 8; degree++){
                        for (int spot = 0; spot < 2; spot++){
                            if (degreeToButton[degree][spot][octave] == -1) continue;
                            System.out.print(degreeToButton[degree][spot][octave] + " ");
                        }
                        System.out.print("\t");
                    }
                    System.out.print("\n");
                }

            }
        }

    }
}
