package accordtranspositorfrompdf.logic;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author G
 */
public class Util {

    private static final double WHITESPACERELATION = 0.5;
    private static String chordRegex
            = "[CcDdFfGgBbHh][#b]?(?![a-zA-Z])[^\\w]*";
    private static String specialCharacters = "#|[0-9]";
    public static HashMap<String, String> CORRECTNOTES
            = new HashMap<String, String>() {
        {
            put("Cisz", "C# ");
            put("Disz", "D# ");
            put("Fisz", "F# ");
            put("Gisz", "G# ");
            put("Aisz", "A# ");
            put("cisz", "c# ");
            put("disz", "d# ");
            put("fisz", "f# ");
            put("gisz", "g# ");
            put("aisz", "a# ");
            put("Esz", "Eb ");
            put("Gesz", "Gb ");
            put("Asz", "Ab ");
            put("esz", "eb ");
            put("gesz", "gb ");
            put("asz", "ab ");

        }
    };

    public static boolean isAccordRow(String text) {
//        Pattern patternSpecial = Pattern.compile(specialCharacters);
        System.out.println("---" + text + "---");
        Pattern pattern = Pattern.compile(chordRegex);

//        Matcher matcherSpecial = patternSpecial.matcher(text);
//        Matcher matcher = pattern.matcher(text);
        return countWhiteSpaces(text) <= WHITESPACERELATION;
    }

    private static double countWhiteSpaces(String text) {
        double whiteSpace = 0.0;
        double nonWhiteSpace = 0.0;

        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            if (Character.isWhitespace(letter)) {
                whiteSpace++;
            } else {
                nonWhiteSpace++;
            }
        }
        System.out.println("arány: " + nonWhiteSpace / whiteSpace
        );
        return nonWhiteSpace / whiteSpace;
    }
}
