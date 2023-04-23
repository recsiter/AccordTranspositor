package accordtranspositorfrompdf.logic;

import java.util.Arrays;

/**
 * @author G
 */
public class TransposeSharpToSharp implements TransposeAccord {

    public static final String[] SHARPNOTES
            = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "H"};

    @Override
    public String transposeAccord(String note, int semitones) {
        System.out.println("[" + note + "]");
        int indexNote = Arrays.asList(SHARPNOTES).
                indexOf(note);
        System.out.println(indexNote);
        indexNote += semitones;
        if (indexNote < 0) {
            indexNote = (SHARPNOTES.length) + indexNote;
        }
        return SHARPNOTES[indexNote % 11];
    }
}
