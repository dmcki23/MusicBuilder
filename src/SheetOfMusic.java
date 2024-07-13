import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * A sheet of music
 */
public class SheetOfMusic {
    /**
     * XML tags in between the opening and closing of this one
     */
    List<XMLtag> inBetween;
    List<XMLtag> XMLtaglist;
    /**
     * List of Part s
     */
    List<Part> partList;
    /**
     * All parts in XMLtag list form
     */
    List<XMLtag> partListTag;
    /**
     * Does the actual note generation
     */
    PartGenerator pg;
    /**
     * Returns this whole thing in XMLtag format
     * @param inXMLtaglist in XMLtag s
     * @param inSize ???
     * @param inlevel ???
     * @return The whole thing in XMLtag format
     */
    public List<XMLtag> returnTaglist(List<XMLtag> inXMLtaglist, int inSize, int inlevel) {
        String outstring = "";
        int all = 0;
        int indent = 0;
        String name = "";
        String text = "";
        String outname = "";
        List<XMLtag> outlist;
        for (int pn = 0; pn < inXMLtaglist.size(); pn++) {
            outstring = "";
            //indent = (inXMLtaglist.get(pn).indentation);
            indent = inlevel;
            name = inXMLtaglist.get(pn).name;
            text = inXMLtaglist.get(pn).text;
            outname = inXMLtaglist.get(pn).outname;
            for (int i = 0; i < indent; i++) {
                outstring += "    ";
            }
            outstring += name;
            try {
                if (text == "" && outname != "") {
                    fw.write((outstring + "\n").getBytes());
                    System.out.println(outstring);
                } else if (text != "") {
                    fw.write((outstring + text + outname + "\n").getBytes());
                    System.out.println(outstring + text + outname);
                } else if (text == "" && outname == "") {
                    fw.write(outstring.getBytes() );
                    System.out.println(outstring);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            outstring = "";
            //System.out.println(text);
            outstring = "";
            int next_size = inXMLtaglist.get(pn).inBetween.size();
            if (next_size == 0) {
                outstring = "";
                if (text == "") {
                    for (int i = 0; i < indent; i++) {
                        outstring += "    ";
                    }
                    outstring += outname;
                    try {
                        if (true) {
                            fw.write((outstring + "\n").getBytes());
                            System.out.println(outstring);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                outstring = "";
            } else {
                //for (int index = 0; index < next_size; index++) {
                returnTaglist(inXMLtaglist.get(pn).inBetween, next_size, inlevel + 1);
                //inXMLtaglist.addAll(outlist);
                //}
                // = returnTaglist(XMLtaglist,next_size,inlevel+1);
                //indent = inXMLtaglist.get(pn).indentation;
                indent = inlevel;
                outname = inXMLtaglist.get(pn).outname;
                for (int i = 0; i < indent; i++) {
                    outstring += "    ";
                }
                try {
                    if (text == "") {
                        fw.write((outstring + outname + "\n").getBytes());
                        System.out.println(outstring + outname);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                outstring = "";
            }
        }
        return inXMLtaglist;
    }
    /**
     * Does outputting to file
     */
    FileOutputStream fw;
    /**
     * File to write to
     */
    File f;
    /**
     * Initializes everything
     */
    SheetOfMusic() {
        XMLtaglist = new ArrayList<XMLtag>();
        partList = new ArrayList<Part>();
        inBetween = new ArrayList<XMLtag>();
        partListTag = new ArrayList<XMLtag>();
        pg = new PartGenerator();

        //System.out.println("here");
        //pg.generateBass();
        outputXML();

        //partList = pg.generateBass();
        //outputXML();
    }
    /**
     * Outputs the entire sheet of music that is in a giant list of XMLtags to console and file
     */
    public void outputXML() {
        f = new File("output.musicXML");
        try {
            fw = new FileOutputStream(f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XMLtaglist.clear();
        //XMLtag versiontag = new XMLtag("?XML version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?", 0);
        //versiontag.outname = "";
        //XMLtaglist.add(versiontag);
        //XMLtag doctypetag = new XMLtag("!DOCTYPE score-partwise PUBLIC\"-//Recordare//DTD MusicXML 4.0 Partwise//EN\" \"http://www.musicXML.org/dtds/partwise.dtd\"",0);
        //doctypetag.outname = "";
        //XMLtaglist.add(doctypetag);
        XMLtag idTag = new XMLtag("identification",0);
        XMLtag creatorTag = new XMLtag("creator type=\"programmer\"",0);
        creatorTag.text = "Daniel W McKinley";
        //idTag.inBetween.add((creatorTag));
        //XMLtaglist.add(idTag);
        XMLtag scoretag = new XMLtag("score-partwise", 0);
        scoretag.outname = "</score-partwise>";
        XMLtaglist.add(scoretag);

        XMLtag defaultsTag = new XMLtag("defaults",1);
        XMLtag layoutTag = new XMLtag("staff-layout",2);
        XMLtag staffDistanceTag = new XMLtag("staff-distance",3);
        staffDistanceTag.text = Double.toString(.16);
        //layoutTag.inBetween.add(staffDistanceTag);
        //defaultsTag.inBetween.add(layoutTag);
        //scoretag.inBetween.add(defaultsTag);




        XMLtag partltag = new XMLtag("part-list", 1);
        for (int li = 0; li < partList.size(); li++) {
            XMLtag scoreparttag = new XMLtag("score-part id=\"P" + Integer.toString(partList.get(li).partNumber) + "\"", 2);
            scoreparttag.outname = "</score-part>";
            XMLtag partnametag = new XMLtag("part-name", 3);
            partnametag.text = "Hello World";
            //scoreparttag.inBetween.add(partnametag);
            partltag.inBetween.add(scoreparttag);
        }
        //scoretag.inBetween.add(partltag);
        scoretag.inBetween.add(partltag);
        for (int li = 0; li < partList.size(); li++) {
            scoretag.inBetween.addAll(partList.get(li).outputXML());
        }
        returnTaglist(XMLtaglist, XMLtaglist.size(), 0);
        try {
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
