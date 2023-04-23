package accordtranspositorfrompdf.logic;

import java.util.HashMap;

/**
 * @author G
 */
public class Util {

    private static final String[] onlyAccordChars
            = new String[]{"C", "D", "E", "F", "G", "H", "B", "c", "d", "f", "g", "h", "/", "\\", "#", "7"};
    public static HashMap<String, String> CORRECTNOTES
            = new HashMap<String, String>() {
        {
            put("Cisz", "C#");
            put("Disz", "D#");
            put("Fisz", "F#");
            put("Gisz", "G#");
            put("Aisz", "A#");
            put("cisz", "c#");
            put("disz", "d#");
            put("fisz", "f#");
            put("gisz", "g#");
            put("aisz", "a#");
            put("Esz", "Eb");
            put("Gesz", "Gb");
            put("Asz", "Ab");
            put("esz", "eb");
            put("gesz", "gb");
            put("asz", "ab");

        }
    };

    public static boolean isAccordRow(String text) {
        for (String c : onlyAccordChars) {
            if (text.indexOf(c) != -1) {
                return true;
            }
        }
        return false;
    }

}
