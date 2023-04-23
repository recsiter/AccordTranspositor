package accordtranspositorfrompdf.logic;

import io.github.jonathanlink.PDFLayoutTextStripper;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import static org.jdom2.filter.Filters.content;
import org.xml.sax.SAXException;

/**
 * @author G
 */
public class FileHandler {
//<editor-fold defaultstate="collapsed" desc="readPDFToString">

    public static String readFromPDF(String PATH) throws IOException {
        File file = new File(
                PATH);
        try ( PDDocument document = PDDocument.load(file)) {

            PDFTextStripper pdfStripper = new PDFTextStripper();
            String result = pdfStripper.getText(document);
            System.out.println(result);
            return result;
        }

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="readPDFLayout">

    public static String readFromPDF2(String PATH) throws IOException {
        File file = new File(PATH);
        try ( PDDocument document = PDDocument.load(file)) {
            PDFLayoutTextStripper stripper = new PDFLayoutTextStripper();
            String result = stripper.getText(document);
            System.out.println(result);
            return result;
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="readPDFTika">

    public static String readFromPDF3(String PATH) throws IOException, SAXException {
        File file = new File(PATH);
        try ( InputStream input = new FileInputStream(file)) {
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            PDFParser pdfParser = new PDFParser();
            pdfParser.parse(input, handler, metadata, new ParseContext());
            String result = handler.toString();
            System.out.println(result);
            return result;
        } catch (TikaException ex) {
            System.err.println("Error extracting text from PDF file: " + ex.
                    getMessage());
            return null;
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="writePDF">

    public static void writeToPDF(String path, String text) {

        try {
            PDDocument document = new PDDocument();
            PDFont font = PDType0Font.load(document, new File(
                    "c:/windows/fonts/times.ttf"));

            PDPage page = new PDPage();
            float startX = 50;
            float startY = 700;

            document.addPage(page);
            PDPageContentStream contentStream
                    = new PDPageContentStream(document, page);
            float fontSize = 12;
            contentStream.setFont(font, fontSize);
            float leading = 20f;
            contentStream.setLeading(leading);
            contentStream.beginText();
            contentStream.newLineAtOffset(startX, startY);
            String[] lines = text.split(System.getProperty("line.separator")); // szétválasztjuk a sorokat

            for (String line : lines) {
                float textwidth = font.getStringWidth(line) / 1000 * fontSize;
                float textHeight = font.getFontDescriptor().
                        getFontBoundingBox().
                        getHeight() / 1000 * fontSize;

                if (startY - textHeight < 50) { // 45 sor után új oldalra váltunk
                    contentStream.endText();
                    contentStream.close();
                    page = new PDPage();

                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    contentStream.setFont(font, fontSize);
                    contentStream.setLeading(leading);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(startX, startY = 700);

                }
                contentStream.showText(line); // kiírjuk a sorokat külön-külön
                contentStream.newLineAtOffset(0, -textHeight);
                startY -= textHeight;
            }
            contentStream.endText(); // bezárjuk a szövegkezelő módot
            contentStream.close();
            document.save(path);
            document.close();
            System.out.println("PDF file created!");
        } catch (IOException ex) {
            System.err.println("Error creating PDF file: " + ex.getMessage());
        }

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="writePDF2">

    public static void writeToPDF2(String path, String text) {

        try {
            // U+000A karakterek helyett U+2028 karakterek használata
            text = text.replace("\n", "\u2028");

            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream
                    = new PDPageContentStream(document, page);

            PDFont font = PDType0Font.load(document, new File(
                    "c:/windows/fonts/times.ttf"));
            float fontSize = 10;
            contentStream.setFont(font, fontSize);
            float leading = 20f;
            contentStream.setLeading(leading);

            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);

            // Új sorok beillesztése a szöveg helyett
            String[] lines = text.split("\u2028");
            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLine();
            }

            contentStream.endText();
            contentStream.close();
            document.save(path);
            document.close();
            System.out.println("PDF file created!");
        } catch (IOException ex) {
            System.err.println("Error creating PDF file: " + ex.getMessage());
        }
    }
//</editor-fold>

    public static XWPFDocument readDocxFile(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        XWPFDocument document = new XWPFDocument(fis);
        fis.close();
        return document;
    }

    public static void writeToDocx(XWPFDocument document, String path) throws IOException {
        // Write the modified document to a file
        FileOutputStream fos = new FileOutputStream(path);
        document.write(fos);
        fos.close();
    }

    public static byte[] readDocxFileArray(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        fis.close();
        baos.flush(); // flush a ByteArrayOutputStream
        baos.close(); // close a ByteArrayOutputStream
        return baos.toByteArray();
    }

    public static void writeToDocxArray(byte[] data, String path) throws IOException {
        // Create a new document
        XWPFDocument document = new XWPFDocument(new ByteArrayInputStream(data));

        // Modify the document as needed (e.g. add or modify text, formatting, etc.)
        // Write the modified document to a byte array
        FileOutputStream fos = new FileOutputStream(path);
        document.write(fos);

    }
}
