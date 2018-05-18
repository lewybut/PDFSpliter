import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.pdfbox.text.PDFTextStripper;

public class TextExtract {
	
	private static String parsedText;
	private static PDFTextStripper pdfStripper;
	
	public static String getText(PDFInvoice pdfInvoice, Integer StartPage, Integer EndPage) throws FileNotFoundException {

        try {
        	pdfStripper = new PDFTextStripper();
            pdfStripper.setStartPage(StartPage);
            pdfStripper.setEndPage(EndPage);
            parsedText = pdfStripper.getText(pdfInvoice.getPdDoc());

        } catch (IOException e) {

            e.printStackTrace();
        }
		return parsedText; 
    }

	//test main to extract text values
	public static void main(String[] args) throws FileNotFoundException {
		PDFInvoice pdfInvoice = new PDFInvoice("C:\\Users\\u4012273\\Desktop\\FE 13 03 18 F053 BACKUP.pdf");		
		System.out.println(TextExtract.getText(pdfInvoice, 1, 1));
	}
}
