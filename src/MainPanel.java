import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
     * Low string notes, guitar bass guitar
     */
    String[] stringNotes = new String[]{"E","F","G","A","B","C","D","E"};
    /**
     * High string notes, bass guitar, guitar
     */
    String[] highStringNotes =new String[]{"G","A","B","C","D","E","F","G","A","B","C"};
    /**
     * Low string notes, 4 string bass guitar
     */
    String[] fifthStringNotes = new String[]{"B","C","D","E","F","G","A","B"};
    /**
     * High string notes on a guitar
     */
    String[] guitarHighNotes = new String[]{"E","F","G","A","B","C","D","E","F","G","A","B","C"};
    /**
     * Low string notes on violin
     */
    String[] majorGNotes = new String[]{"G","A","B","C","D","E","F","G"};
    /**
     * Low string notes on cello and viola
     */
    String[] violaCelloLow = new String[]{"C","D","E","F","G","A","B","C"};
    /**
     * High string notes on cello and viola
     */
    String[] violaCelloHigh = new String[]{"A","B","C","D","E","F","G","A","B","C","D"};
    /**
     * Not significant, used as a dummy variable to distinguish switch cases
     */
    int[] usedClefs;
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
     * Instrument selection box
     */
    JComboBox instument;
    /**
     * Swing rest frequency selection
     */
    JComboBox restFrequency;
    /**
     * How many measures in between key changes, if applicable
     */
    JComboBox keyChangeFrequency;

    /**
     * Whether to do drop D on the instrument, on non-guitar instruments, it's drop whatever the bottom string is
     */
    JCheckBox dropD;
    /**
     * Low range of notes in integer form relative to the low E on a bass guitar, one of the things that needs changing in a reboot
     */
    int low;
    /**
     * High range of notes in integer form, see low for more
     */
    int high;


    /**
     * GUI logic here
     */

    public MainPanel() {
        dropD = new JCheckBox();
        usedClefs = new int[4];
        instument = new JComboBox();
        instument.addItem("4 string bass guitar");
        instument.addItem("5 string bass guitar");
        instument.addItem("6 string guitar");

        instument.addItem("Cello");
        instument.addItem("Viola");
        instument.addItem("Violin");

        instument.setSelectedIndex(0);




        instument.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lowerBox.removeAllItems();
                upperBox.removeAllItems();
                int index = instument.getSelectedIndex();
                switch (index) {
                    case 0:
                        for (int spot = 0; spot < highStringNotes.length; spot++) {
                            upperBox.addItem(highStringNotes[spot]);
                        }
                        for (int spot = 0; spot < stringNotes.length; spot++) {
                            lowerBox.addItem(stringNotes[spot]);
                        }
                        usedClefs = new int[]{1, 1, 0};
                        low = 0;
                        high = 9;
                        break;


                    case 1:
                        for (int spot = 0; spot < highStringNotes.length; spot++) {
                            upperBox.addItem(highStringNotes[spot]);
                        }
                        for (int spot = 0; spot < fifthStringNotes.length; spot++) {
                            lowerBox.addItem(fifthStringNotes[spot]);
                        }
                        usedClefs = new int[]{1, 1, 0};
                        low = -3;
                        high = 9;
                        break;


                    case 2:
                        for (int spot = 0; spot < guitarHighNotes.length; spot++ ){
                            upperBox.addItem(guitarHighNotes[spot]);
                        }
                        for (int spot = 0; spot < stringNotes.length; spot++){
                            lowerBox.addItem(stringNotes[spot]);
                        }
                        //dummy code because otherwise the switch statement won't let me have 2 and 4 the exact same
                        usedClefs = new int[]{0,1,1,0};
                        low = 7;
                        high = 21;
                        break;
                    case 3:
                        for (int spot = 0; spot < violaCelloLow.length; spot++ ){
                            lowerBox.addItem(violaCelloLow[spot]);
                        }
                        for (int spot = 0; spot < violaCelloHigh.length; spot++){
                            upperBox.addItem(violaCelloHigh[spot]);
                        }
                        low = -2;
                        high = 11;
                        break;


//                        usedClefs = new int[]{0,1,1,1};
//                        low = 7;
//                        high = 21;
//                        break;
                    case 4:
                        for (int spot = 0; spot < violaCelloLow.length; spot++ ){
                            lowerBox.addItem(violaCelloLow[spot]);
                        }
                        for (int spot = 0; spot < violaCelloHigh.length; spot++){
                            upperBox.addItem(violaCelloHigh[spot]);
                        }
                        low = 5;
                        high = 14;
                        break;
                    case 5:
                        for (int spot = 0; spot < majorGNotes.length; spot++ ){
                            lowerBox.addItem(majorGNotes[spot]);
                        }
                        for (int spot = 0; spot < stringNotes.length; spot++){
                            upperBox.addItem(stringNotes[spot]);
                        }
                        low = 9;
                        high = 18;
                        break;
                    case 6:
                        for (int spot = 0; spot < highStringNotes.length; spot++) {
                            upperBox.addItem(highStringNotes[spot]);
                        }
                        for (int spot = 0; spot < fifthStringNotes.length; spot++) {
                            lowerBox.addItem(fifthStringNotes[spot]);
                        }
                        usedClefs = new int[]{1, 1, 1};
                        low = -3;
                        high = 9;
                        break;
                }
                upperBox.setSelectedIndex(upperBox.getItemCount()-1);
                lowerBox.setSelectedIndex(lowerBox.getItemCount()-1);

                }
        });

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
        refresh = new JButton("file: output.musicXML");
        for (int num = 1; num < 20; num++){
            numBeat.addItem(num);
        }
        typeBeat.addItem("whole");
        typeBeat.addItem("half");
        typeBeat.addItem("quarter");
        typeBeat.addItem("eighth");



        keyBox = new JComboBox();
        for (int numSharps = -7; numSharps < 8; numSharps++){
            keyBox.addItem(numSharps);
        }
        keyBox.addItem("Random");

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sheetOfMusic = new SheetOfMusic();
                int divisions = 2;
                int type = (int)Math.pow(2,typeBeat.getSelectedIndex());
                int num = numBeat.getSelectedIndex()+1;
                int lower = lowerBox.getSelectedIndex()+low;
                int upper = upperBox.getSelectedIndex()+high;
                int in = instument.getSelectedIndex();
                if (dropD.isSelected()){
                    lower -= 1;
                }

                int key =keyBox.getSelectedIndex()-7;
                int restChance = restFrequency.getSelectedIndex()+2;
                int modFreq = (int)keyChangeFrequency.getSelectedItem();
                boolean randomKeys = false;
                if (key == 8) {
                    randomKeys = true;
                    key = 0;
                }
                //sheetOfMusic.partList = sheetOfMusic.pg.generateBass();
//                if (instument.getSelectedIndex() == 0 || instument.getSelectedIndex() == 2 || instument.getSelectedIndex() == 4){
//                    sheetOfMusic.partList = sheetOfMusic.pg.generateBass(lower,upper,key,randomKeys,type,num,restChance, modFreq);
//                } else {
//                    sheetOfMusic.partList = sheetOfMusic.pg.generateBassFiveString(lower,upper,key,randomKeys,type,num,restChance, modFreq);
//                }
                switch (in){
                    case 0:
                        sheetOfMusic.partList = sheetOfMusic.pg.generateBass(lower,upper,key,randomKeys,type,num,restChance, modFreq);
                        break;

                    case 1:
                        sheetOfMusic.partList = sheetOfMusic.pg.generateBass(lower,upper,key,randomKeys,type,num,restChance, modFreq);
                        break;

                    case 2:
                        sheetOfMusic.partList = sheetOfMusic.pg.generateBass(lower,upper,key,randomKeys,type,num,restChance, modFreq);
                        break;
                    case 3:
                        sheetOfMusic.partList = sheetOfMusic.pg.generateBass(lower,upper,key,randomKeys,type,num,restChance, modFreq);
                        break;

                    case 4:
                        sheetOfMusic.partList = sheetOfMusic.pg.generateBass(lower,upper,key,randomKeys,type,num,restChance, modFreq,3);
                            break;
                    case 5:
                        sheetOfMusic.partList = sheetOfMusic.pg.generateBass(lower,upper,key,randomKeys,type,num,restChance, modFreq);
                        break;

                    case 6:
                        sheetOfMusic.partList = sheetOfMusic.pg.generateBassFiveString(lower, upper, key,randomKeys,type,num,restChance,modFreq);
                        break;
                }
                sheetOfMusic.outputXML();

            }
        });
        instument.setSelectedIndex(0);
        for (int spot = 0; spot < highStringNotes.length; spot++) {
            upperBox.addItem(highStringNotes[spot]);
        }
        for (int spot = 0; spot < stringNotes.length; spot++) {
            lowerBox.addItem(stringNotes[spot]);
        }
        usedClefs = new int[]{1, 1, 0};
        low = 0;
        high = 9;


        //lowerBox.setSelectedIndex(0);
        //upperBox.setSelectedIndex(6);
        typeBeat.setSelectedIndex(3);
        numBeat.setSelectedIndex(3);
        restFrequency.setSelectedIndex(4);
        keyBox.setSelectedIndex(15);
        keyChangeFrequency.setSelectedIndex(11);
        JFrame frame = new JFrame();
        frame.getContentPane().add(this);
        frame.setSize(700, 500);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(0,2));
        //this.add(new JLabel(" "));
        //this.add(new JLabel("Number of strings"));
        //this.add(numberStrings);
        this.add(new JLabel("Instrument"));
        this.add(instument);
        this.add(new JLabel("Drop D or bottom string minus a whole step"));
        this.add(dropD);
        this.add(new JLabel("Bottom left hand corner"));
        this.add(lowerBox);
        this.add(new JLabel("Upper right hand corner"));
        this.add(upperBox);
        this.add(new JLabel("Number of beats, top time signature number"));
        this.add(numBeat);
        this.add(new JLabel("Type of beat, bottom time signature number"));
        this.add(typeBeat);
        this.add(new JLabel("Key"));
        this.add(keyBox);
        this.add(new JLabel("Chance of a note being a rest note"));
        this.add(restFrequency);
        this.add(new JLabel("How many measures between key modulations"));
        this.add(keyChangeFrequency);

        this.add(new JLabel("In the directory this program is in"));
        this.add(refresh);
        frame.add(this);
        frame.pack();
        //frame.add(new JLabel());
        frame.setVisible(true);
        repaint();

    }

}
