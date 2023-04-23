package accordtranspositorfrompdf.logic;

import java.util.Arrays;

/**
 * @author G
 */
public class TransposeFlatToSharp implements TransposeAccord {

    public static final String[] SHARPNOTES
            = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "H"};
    public static final String[] FLATNOTES
            = {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Hb", "H"};

    @Override
    public String transposeAccord(String note, int semitones) {

        int indexNote = Arrays.asList(FLATNOTES).
                indexOf(note);
        indexNote += semitones;
        if (indexNote < 0) {
            indexNote = (SHARPNOTES.length) + indexNote;
        }
        return SHARPNOTES[indexNote % 11];
    }
}
