import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * beginnings of a GUI for this program
 */
public class MainPanel extends JPanel{
    /**
     * Lower left hand corner of fretboard to work with
     */
    JComboBox lowerBox;
    /**
     * Upper right hand corner of fretboard to work with
     */
    JComboBox upperBox;
    /**
     * Type of beat, lower number in time signature
     */
    JComboBox typeBeat;
    /**
     * Number of beats, upper number in time signature
     */
    JComboBox numBeat;
    /**
     * Swing button that starts the generation with the parameters selected
     */
    JButton refresh;
    /**
     * The program logic
     */
    SheetOfMusic sheetOfMusic;
    /**
     * Swing selection box for the key
     */
    JComboBox keyBox;
    /**
     * Swing rest frequency selection
     */
    JComboBox restFrequency;
    JComboBox keyChangeFrequency;
    /**
     * GUI logic here
     */

    public MainPanel() {
        keyChangeFrequency = new JComboBox();
        for (int power = 1; power < 32; power++){
            keyChangeFrequency.addItem(power);
        }
        restFrequency = new JComboBox();
        restFrequency.addItem("50%");
        restFrequency.addItem("33%");
        restFrequency.addItem("20%");
        restFrequency.addItem("17%");
        restFrequency.addItem("14%");
        sheetOfMusic = new SheetOfMusic();
        lowerBox = new JComboBox();
        upperBox = new JComboBox();
        typeBeat = new JComboBox();
        numBeat = new JComboBox();
        refresh = new JButton("Refresh");
        for (int num = 1; num < 20; num++){
            numBeat.addItem(num);
        }
        typeBeat.addItem("whole");
        typeBeat.addItem("half");
        typeBeat.addItem("quarter");
        typeBeat.addItem("eighth");
        String[] stringNotes = new String[]{"E","F","G","A","B","C","D"};
        String[] highStringNotes =new String[]{"A","B","C","D","E","F","G","A"};

        for (int spot = 0; spot < 7; spot++){
            lowerBox.addItem(stringNotes[spot]);
            upperBox.addItem(highStringNotes[spot]);
        }
        keyBox = new JComboBox();
        for (int numSharps = -7; numSharps < 8; numSharps++){
            keyBox.addItem(numSharps);
        }
        keyBox.addItem("Random");

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int divisions = 2;
                int type = (int)Math.pow(2,typeBeat.getSelectedIndex());
                int num = numBeat.getSelectedIndex()+1;
                int lower = lowerBox.getSelectedIndex();
                int upper = upperBox.getSelectedIndex()+10;
                int key =keyBox.getSelectedIndex()-7;
                int restChance = restFrequency.getSelectedIndex()+2;
                int modFreq = (int)keyChangeFrequency.getSelectedItem();
                boolean randomKeys = false;
                if (key == 8) {
                    randomKeys = true;
                    key = 0;
                }
                //sheetOfMusic.partList = sheetOfMusic.pg.generateBass();
                sheetOfMusic.partList = sheetOfMusic.pg.generateBass(lower,upper,key,randomKeys,type,num,restChance, modFreq);
                sheetOfMusic.outputXML();

            }
        });



        lowerBox.setSelectedIndex(0);
        upperBox.setSelectedIndex(6);
        typeBeat.setSelectedIndex(3);
        numBeat.setSelectedIndex(3);
        restFrequency.setSelectedIndex(4);
        keyBox.setSelectedIndex(15);
        keyChangeFrequency.setSelectedIndex(11);
        JFrame frame = new JFrame();
        frame.getContentPane().add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(20,2));
        frame.setSize(500, 500);
        frame.add(new JLabel("Bottom left hand corner"));
        frame.add(lowerBox);
        frame.add(new JLabel("Upper right hand corner"));
        frame.add(upperBox);
        frame.add(new JLabel("Number of beats, top time signature number"));
        frame.add(numBeat);
        frame.add(new JLabel("Type of beat, bottom time signature number"));
        frame.add(typeBeat);
        frame.add(new JLabel("Key"));
        frame.add(keyBox);
        frame.add(new JLabel("Chance of a note being a rest note"));
        frame.add(restFrequency);
        frame.add(new JLabel("How many measures between key modulations"));
        frame.add(keyChangeFrequency);
        frame.add(new JLabel("Outputs to output.musicXML in the directory this program is in"));
        frame.add(refresh);
        frame.setVisible(true);
        repaint();

    }

}
