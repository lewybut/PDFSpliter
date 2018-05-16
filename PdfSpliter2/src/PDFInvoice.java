import java.io.File;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;


public class PDFInvoice {



	private static PDDocument pdDoc;
	private static COSDocument cosDoc;
	private static File file;
    
	public PDFInvoice(String fileName){
	
		
		try {
			file = new File(fileName);
			RandomAccessFile fileRandom = new RandomAccessFile(file, "rw");
			PDFParser parser = new PDFParser(fileRandom);
			parser.parse();
			cosDoc = parser.getDocument();
			pdDoc = new PDDocument(cosDoc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public PDDocument getPdDoc() {
		return pdDoc;
	}
	
}
