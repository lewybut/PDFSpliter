import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class InvoiceHandler {
	
	abstract String[] regexPattern();
	
	public ArrayList<Invoice> getInvoicesArrayList(PDFInvoice pdfInvoice) throws Exception{
		System.out.println("getInvoicesArrayList called");
		ArrayList<Invoice> invoices = new ArrayList<>();
		ArrayList<Integer> lastPages = new ArrayList<>();
		lastPages = (getInvoiceLength(pdfInvoice));
		invoices = setInvoicesSimple(lastPages);
		
		for (Invoice invoice : invoices) {
			setInvoiceDetails(TextExtract.getText(pdfInvoice, invoice.getStartPage(), invoice.getEndPage()), invoice, regexPattern());
		}
		if(lastPages.stream().mapToInt(Integer::intValue).sum() == pdfInvoice.getPdDoc().getNumberOfPages()) {
			return invoices;
		}
		else {
			throw new Exception();
		}
	}
	
	private static ArrayList<Integer> getInvoiceLength(PDFInvoice pdfInvoice) {
		ArrayList<Integer> lastPages = new ArrayList<>();
		Matcher m = null;
		try {
			String patternString = "(?<!\\d)1\\((\\d+)\\)";
			Pattern p = Pattern.compile(patternString);
			m = p.matcher(TextExtract.getText(pdfInvoice, 1, pdfInvoice.getPdDoc().getNumberOfPages()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(m.find()) {
			lastPages.add(Integer.parseInt(m.group().substring(m.group().indexOf("(") + 1, m.group().indexOf(")"))));
		}
		return lastPages;
	}

//	private static Invoice setInvoiceDetails(String invoicetext, Invoice invoice) {
//		System.out.println("setInvoiceDetails from Invoice Handler called");
//		String[] details;
//		//enisss
//		String patternString = "(DE[\\d|\\w]{1}\\d{5})\\s+\\d+\\s+\\d{2}\\.\\d{2}\\.\\d{2}\\s+\\w{3}\\s+";
//		//single invo
//		//String patternString = "DE(\\w|\\d){1}\\d{5}\\s+(\\w){3}\\s+.+\\s+.+";
//		Pattern p = Pattern.compile(patternString);
//		Matcher m = p.matcher(invoicetext);
//		
//		while(m.find()) {
//			details = m.group().split(System.getProperty("line.separator"));
//			invoice.setClientNumber(details[0]);
//			invoice.setInvoiceNumber(details[1]);
//			invoice.setInvoiceDate(details[2]);	
//		}
//		if(invoice.getClientNumber() == null) {
//			//System.out.println(invoice.getStartPage());
//			//System.err.println(invoicetext);
//		}
//		return invoice;
//	}
	
	abstract Invoice setInvoiceDetails(String invoicetext, Invoice invoice, String[] regexPattern);
	
	private static ArrayList<Invoice> setInvoicesSimple(ArrayList<Integer> lastPages) {
		
		ArrayList<Invoice> invoices = new ArrayList<>();
		Integer firstPage = 1;
		for (Integer length : lastPages) {
				invoices.add(new Invoice(firstPage, length));
				firstPage+=length;
		}	
		return invoices;
	}
		
}


