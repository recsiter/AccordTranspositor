package accordtranspositorfrompdf.logic;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.hwpf.converter.WordToFoUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;

/**
 * @author G
 */
public class Transposition {

    private final boolean ISFLATKEYFROM;
    private final boolean ISFLATKEYTO;

    public Transposition(boolean ISFLATKEYFROM, boolean ISFLATKEYTO) {
        this.ISFLATKEYFROM = ISFLATKEYFROM;
        this.ISFLATKEYTO = ISFLATKEYTO;
    }

    public boolean isISFLATKEYFROM() {
        return ISFLATKEYFROM;
    }

    public boolean isISFLATKEYTO() {
        return ISFLATKEYTO;
    }

    public static final String[] FLATKEY
            = {"F", "Hb", "Eb", "Ab", "Db", "d", "g", "c", "f", "ab", "db"};

    private static String chordRegex
            = "(C|C#|Db|D#|Eb|E|F|F#|Gb|G#|Ab|A#|Bb|B)";

//// Szöveg beolvasása byte tömbbe
//    public static void transposition(byte[] docxData, int transpositionDistence) {
//        String text = new String(docxData, StandardCharsets.UTF_8);
//
//// Az akkordok transzponálása
//    }
    public static boolean isFlatKey(String accord) {
        return Arrays.asList(FLATKEY).
                contains(accord);
    }

    public XWPFDocument transposeChordsInDocx(XWPFDocument doc, int semitones) {
        TransposeAccord transposeAccord;
//        if (ISFLATKEYFROM && ISFLATKEYTO) {
//            transposeAccord = new TransposeFlatToFlat();
//        } 
        if (!ISFLATKEYFROM && !ISFLATKEYTO) {
            transposeAccord = new TransposeSharpToSharp();
        } else if (ISFLATKEYFROM && !ISFLATKEYTO) {
            transposeAccord = new TransposeFlatToSharp();
        } else if (!ISFLATKEYFROM && ISFLATKEYTO) {
            transposeAccord = new TransposeSharpToFlat();
        } else {
            throw new IllegalArgumentException(
                    "Rossz típusok a transposition osztályban");
        }
        correctNoteNames(doc, Util.CORRECTNOTES);
        Pattern pattern = Pattern.compile(chordRegex);
        for (XWPFParagraph para : doc.getParagraphs()) {
            for (XWPFRun run : para.getRuns()) {
                String text = run.getText(0);
                if (text != null && Util.isAccordRow(text)) {
                    Matcher matcher = pattern.matcher(text);
                    int index = 0;
                    StringBuilder newText = new StringBuilder();
                    while (matcher.find()) {
                        int start = matcher.start();
                        int end = matcher.end();

                        String chord = text.substring(index, start);
                        newText.append(chord);
                        String transposedChord = transposeAccord.
                                transposeAccord(chord, semitones);
                        newText.append(transposedChord);
                        index = end;
                    }
                    newText.append(text.substring(index));
                    run.setText(newText.toString(), 0);
                }
            }
        }

        return doc;

    }

    //fafasdfasdfsd
    public static void correctNoteNames(XWPFDocument doc,
            HashMap<String, String> map) {
        for (XWPFParagraph para : doc.getParagraphs()) {
            for (XWPFRun run : para.getRuns()) {
                String text = run.getText(0);
                if (text != null && Util.isAccordRow(text)) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        replaceTinySharpAndFlat(text);
                        text = text.replaceAll(key, value);
                    }
                    run.setText(text, 0);
                }
            }
        }
    }

    public static void replaceTinySharpAndFlat(String text) {
        text.replace("#", "♯"); // karaktercserélés
        text.replaceAll("\u266d", "b");
    }

}
