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

    public static void main(String[] args) throws IOException, SAXException {
        Transposition transposition = new Transposition(false, false);
        XWPFDocument readIn = FileHandler.readDocxFile(
                "C:\\Users\\csomo\\Documents\\NetBeansProjects\\AccordTranspositorFromPDF\\src\\Vágylak látni (H).docx");
        XWPFDocument newDoc = transposition.transposeChordsInDocx(readIn, 3);
        FileHandler.writeToDocx(newDoc,
                "C:\\Users\\csomo\\Desktop\\TestFolder\\proba.docx");
        Arrays.stream(Chord.getChordNames()).
                forEach(c -> {
                    System.out.println(c);
                });

    }
}
