package accordtranspositorfrompdf.logic;

import java.util.Arrays;

/**
 * @author G
 */
public class TransposeSharpToSharp implements TransposeAccord {

    public static final String[] SHARPNOTES
            = {"C", "c", "C#", "c#", "D", "d", "D#", "d#", "E", "e", "F", "f", "F#", "f#", "G", "g", "G#", "g#", "A", "a", "A#", "a#", "H", "h"};

    @Override
    public String transposeAccord(String note, int semitones) {
//        System.out.println("[" + note + "]");
        int indexNote = Arrays.asList(SHARPNOTES).
                indexOf(note);
//        System.out.println(indexNote);
        indexNote += semitones * 2;
        if (indexNote < 0) {
            indexNote = (SHARPNOTES.length) + indexNote;
        }
        return SHARPNOTES[indexNote % 24];
    }
}
