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

        int indexNote = Arrays.asList(SHARPNOTES).
                indexOf(note);
        indexNote += semitones;
        if (indexNote < 0) {
            indexNote = (SHARPNOTES.length) + indexNote;
        }
        return SHARPNOTES[indexNote % 11];
    }
}
