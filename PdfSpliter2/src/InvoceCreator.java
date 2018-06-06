import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class InvoceCreator{
	
	private InvoiceHandler invoiceType;
	private PDFInvoice sourcePFDFile;
	private String sourcePDFPath;
	private String output;
	private static ArrayList<Invoice> invoices = new ArrayList<Invoice>();
	private Thread t;
	private String pdfName;
	private CSVWriter csvwriter;
	
	public InvoceCreator(InvoiceHandler invoiceType, String sourcePDF, String output) {
		this.invoiceType = invoiceType;
		this.sourcePFDFile = new PDFInvoice(sourcePDF);
		this.sourcePDFPath = sourcePDF;
		this.output = output;
	}
	
	public void create() {
		try {
			csvwriter = new CSVWriter("H:\\Java\\report.csv");
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		System.out.println("Running " +  sourcePDFPath);
		File file;
		try {
			invoices = invoiceType.getInvoicesArrayList(sourcePFDFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		file = new File("H:\\Java\\" + output + "\\exceptionValues");
		file.mkdirs();
		for (Invoice invoice : invoices) {
			//invoice.getFileName();

			if(invoice.getClientNumber() != null && invoice.getInvoiceNumber() != null && invoice.getInvoiceDate() != null) {
			file = new File("H:\\Java\\" + output + "\\" + invoice.getPath());
			file.mkdir();
			file = new File("H:\\Java\\" + output + "\\" + invoice.getPath() + invoice.getClientNumber());
			file.mkdir();
			System.out.println("H:\\Java\\" + output + "\\" + invoice.getPath() + invoice.getClientNumber());
			pdfName = "H:\\Java\\" + output + "\\" + invoice.getPath() + invoice.getFileName();
			Spliter.processPDFFile(sourcePDFPath, invoice.getStartPage(), 
					invoice.getEndPage(), pdfName);
			try {
				csvwriter.UpdateRaport(pdfName, Integer.toString(invoice.getlength()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			else {
				Spliter.processPDFFile(sourcePDFPath, invoice.getStartPage(), 
						invoice.getEndPage(), "H:\\Java\\" + output + "\\exceptionValues\\" + invoice.getStartPage() + ".PDF");
				try {
					FilesHandler.newTxtFile("H:\\Java\\" + output + "\\exceptionValues\\" + invoice.getStartPage() + ".txt", TextExtract.getText(sourcePFDFile, invoice.getStartPage(), invoice.getEndPage()), true);
					FilesHandler.newTxtFile("H:\\Java\\" + output + "\\exceptionValues\\" + "All Exceptions.txt", TextExtract.getText(sourcePFDFile, invoice.getStartPage(), invoice.getEndPage()), true);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			csvwriter.createReport();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
//	public void start() {
//		System.out.println("Starting " +  sourcePDFPath);
//		if (t == null) {
//	        t = new Thread (this, sourcePDFPath);
//	        t.start ();
//	     }
//	}
		
	}
