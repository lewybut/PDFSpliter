import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
	//private static String fileName = "H:\\Java\\test01.PDF";
	private static String fileName = "H:\\Java\\Einzelrechu\\testXD2.PDF";
	//private static PDFInvoice pdfInvoice = new PDFInvoice("H:\\Java\\Einzelrechu\\testXD.PDF");
	private static PDFInvoice pdfInvoice = new PDFInvoice(fileName);
	private static ArrayList<Invoice> invoices = new ArrayList<Invoice>();
	private static InvoiceHandler singleInvoice;
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		singleInvoice = new InvoiceHandlerSingle();
		File file;
		try {
			//invoices = InvoiceHandler.getInvoicesArrayList(pdfInvoice);
			invoices = singleInvoice.getInvoicesArrayList(pdfInvoice);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file = new File("H:\\Java\\test\\exceptionValues");
		file.mkdirs();
		for (Invoice invoice : invoices) {
//				invoice.getFileName();
//				System.out.println("Invoice");
//				System.out.println("---");
//				System.out.println(invoice.getStartPage());
//				System.out.println(invoice.getEndPage());
//				System.out.println(invoice.getClientNumber());
//				System.out.println(invoice.getInvoiceNumber());
//				System.out.println(invoice.getInvoiceDate());

				if(invoice.getClientNumber() != null && invoice.getInvoiceNumber() != null && invoice.getInvoiceDate() != null) {
				file = new File("H:\\Java\\test\\" + invoice.getClientNumber());
				file.mkdir();
				System.out.println("H:\\Java\\test\\" + invoice.getFileName());
				
				Spliter.processPDFFile(fileName, invoice.getStartPage(), 
						invoice.getEndPage(), "H:\\Java\\test\\" + invoice.getFileName());
				}
				else {
					//System.out.println("Error: " + "H:\\Java\\test\\exceptionValues\\" + invoice.getStartPage() + ".PDF");
					Spliter.processPDFFile(fileName, invoice.getStartPage(), 
							invoice.getEndPage(), "H:\\Java\\test\\exceptionValues\\" + invoice.getStartPage() + ".PDF");
				}
		}
	}
	
}
