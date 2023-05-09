package accordtranspositorfrompdf;

import accordtranspositorfrompdf.logic.FileHandler;
import accordtranspositorfrompdf.logic.Transposition;
import accordtranspositorfrompdf.logic.Util;
import java.io.IOException;
import java.util.Arrays;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;
import org.jfugue.theory.Scale;

import org.xml.sax.SAXException;

/**
 * @author --G--
 */
public class AccordTranspositorFromPDF {

    private static String aFölddel
            = "C:\\Users\\csomo\\Documents\\NetBeansProjects\\AccordTranspositorFromPDF\\src\\A Földdel összeér a Menny (Here as in Heaven).docx";
    private static String bizzalEnlelkem
            = "C:\\Users\\csomo\\Documents\\NetBeansProjects\\AccordTranspositorFromPDF\\src\\Bízzál én lelkem (hm).docx";
    private static String baratomLettel
            = "C:\\Users\\csomo\\Documents\\NetBeansProjects\\AccordTranspositorFromPDF\\src\\Barátom lettél (c).docx";
    private static String sosemFelejtemEl
            = "C:\\Users\\csomo\\Documents\\NetBeansProjects\\AccordTranspositorFromPDF\\src\\Sosem felejtem el_G.docx";
    private static String vagylakLatni
            = "C:\\Users\\csomo\\Documents\\NetBeansProjects\\AccordTranspositorFromPDF\\src\\Vágylak látni (H).docx";
    private static String aldalakMertJoVagy
            = "C:\\Users\\csomo\\Documents\\NetBeansProjects\\AccordTranspositorFromPDF\\src\\Áldalak mert jó vagy.docx";
    private static String aldomAzUrat
            = "C:\\Users\\csomo\\Documents\\NetBeansProjects\\AccordTranspositorFromPDF\\src\\Áldom az Urat.docx";
    private static String hazavar
            = "C:\\Users\\csomo\\Documents\\NetBeansProjects\\AccordTranspositorFromPDF\\src\\Haza vár.docx";
    private static String szuntelen
            = "C:\\Users\\csomo\\Documents\\NetBeansProjects\\AccordTranspositorFromPDF\\src\\Szüntelen.docx";

    public static void main(String[] args) throws IOException, SAXException {
        Transposition transposition = new Transposition(false, false);
        XWPFDocument readIn = FileHandler.readDocxFile(szuntelen
        );
        XWPFDocument newDoc = transposition.transposeChordsInDocx(readIn, 1);
        FileHandler.writeToDocx(newDoc,
                "C:\\Users\\csomo\\Desktop\\TestFolder\\proba.docx");

    }
}
