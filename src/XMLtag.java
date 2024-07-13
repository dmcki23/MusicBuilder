import java.util.ArrayList;
import java.util.List;
/**
 * XML tag class
 * @author Daniel W McKinley
 */

public class XMLtag {
    /**
     * opening tag's text
     */
    public String name;
    /**
     * closing tag's text
     */
    public String outname;
    /**
     * list of tags between opening and closing tag
     */
    public List<XMLtag> inBetween;
    /**
     * text in between the opening and closing tags
     * usually used to define MusicXML parameters
     */
    public String text;
    /**
     * how many opening tags deep this tag is
     */
    public int indentation;
    /**
     * I don't know what this does, possibly redundant
     */
    public int type;
    /**
     * constructs a tag with given parameters
     * @param nameIn
     * @param ind
     */
    XMLtag(String nameIn, int ind){
        name = "<" + nameIn + ">";
        outname = "</" + nameIn + ">";
        inBetween = new ArrayList<XMLtag>();
        indentation = ind;
        text = new String();
        text = "";

    }
    /**
     * constructs a tag with given parameters
     * @param nameIn
     * @param inBetweenIn
     * @param ind
     */
    XMLtag(String nameIn, String inBetweenIn, int ind){
        name = "<" + nameIn + ">";
        outname = "</" + nameIn + ">";
        text = inBetweenIn;
        inBetween = new ArrayList<XMLtag>();
        indentation = ind;
        text = new String();
        text = "";
    }
    /**
     * constructs an empty tag with default parameters
     */
    XMLtag(){
        inBetween = new ArrayList<XMLtag>();
        indentation = 0;
        text = new String();
        text = "";


    }

}
