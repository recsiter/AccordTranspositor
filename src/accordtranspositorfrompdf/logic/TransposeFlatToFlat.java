package accordtranspositorfrompdf.logic;

import java.util.Arrays;

/**
 * @author G
 */
public class TransposeFlatToFlat implements TransposeAccord {

    public static final String[] FLATNOTES
            = {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Hb", "H"};

    @Override
    public String transposeAccord(String note, int semitones) {
        int indexNote = Arrays.asList(FLATNOTES).
                indexOf(note);
        indexNote += semitones;
        if (indexNote < 0) {
            indexNote = (FLATNOTES.length) + indexNote;
        }
        return FLATNOTES[indexNote % 11];
    }

}
