package accordtranspositorfrompdf.logic;

import java.util.Arrays;

/**
 * @author G
 */
public class TransposeSharpToFlat implements TransposeAccord {

    public static final String[] SHARPNOTES
            = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "H"};
    public static final String[] FLATNOTES
            = {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Hb", "H"};

    @Override
    public String transposeAccord(String note, int semitones) {

        int indexNote = Arrays.asList(SHARPNOTES).
                indexOf(note);
        indexNote += semitones;
        if (indexNote < 0) {
            indexNote = (FLATNOTES.length) + indexNote;
        }
        return FLATNOTES[indexNote % 11];
    }
}
