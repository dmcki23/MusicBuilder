import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Sub-section of a sheet of music
 */
public class Part {
    /**
     * List of XML tags in between this one's opening and closing
     */
    List<XMLtag> inBetween;
    /**
     * This entire part in XMLtag list form
     */
    List<XMLtag> XMLtaglist;
    /**
     * Part number, not zero-indexed so must be >= 1
     */
    int partNumber;
    /**
     * List of measures in the part
     */
    List<Measure> measureList;
    /**
     * Part name
     */
    String partName = "untitled";
    /**
     * Initializes the lists
     */
    Part(){
        measureList = new ArrayList<Measure>();
        XMLtaglist = new ArrayList<XMLtag>();
        inBetween = new ArrayList<XMLtag>();
    }
    /**
     * Outputs the part in musicXML format
     * @return this entire part in a list of XMLtags
     */
    public List<XMLtag> outputXML(){

        XMLtaglist.clear();
        XMLtag partid = new XMLtag("part id=\"P" + Integer.toString(partNumber) + "\"",0);
        partid.outname = "</part>";
        XMLtaglist.add(partid);
        for (int li = 0; li < measureList.size(); li++){

            XMLtaglist.get(0).inBetween.addAll(measureList.get(li).outputMeasureXML());

        }
        return XMLtaglist;
    }

}
