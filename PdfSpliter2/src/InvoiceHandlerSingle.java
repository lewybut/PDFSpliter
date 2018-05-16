import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InvoiceHandlerSingle extends InvoiceHandler {
	
	String[] regexPattern() {
		return new String[] {"DE(\\w|\\d){1}\\d{5}\\s+", "Rechnungs-Nr.\\s+(\\d){8}", 
				"(DE\\w{9}\\s+\\d{2}\\-\\d{2}\\-\\d{2}\\s+)|(DE\\w{11}\\s+\\d{2}\\-\\d{2}\\-\\d{2}\\s+)"};
	}
	
	@Override
	Invoice setInvoiceDetails(String invoicetext, Invoice invoice, String[] regexPattern) {

		String[] details;
		
		String patternString = regexPattern[0];
		Pattern p = Pattern.compile(patternString);
		Matcher m = p.matcher(invoicetext);
		while(m.find()) {
			details = m.group().split(System.getProperty("line.separator"));
			invoice.setClientNumber(details[0]);	
		}
		if(invoice.getClientNumber() == null) {
			//System.out.println(invoice.getStartPage());
			//System.err.println(invoicetext);
		}
		
		patternString = regexPattern[1];
		p = Pattern.compile(patternString);
		m = p.matcher(invoicetext);
		while(m.find()) {
			details = m.group().split(" ");
			invoice.setInvoiceNumber(details[1]);	
		}
		if(invoice.getClientNumber() == null) {
			//System.out.println(invoice.getStartPage());
			//System.err.println(invoicetext);
		}
		
		patternString = regexPattern[2];
		p = Pattern.compile(patternString);
		m = p.matcher(invoicetext);
		while(m.find()) {
			details = m.group().split(System.getProperty("line.separator"));
			if (details.length == 2) {
				invoice.setInvoiceDate(details[1]);
//				for (int i = 0; i < details.length; i++) {
//					System.out.println(i + " " + details[i]);
//					System.out.println(invoice.getStartPage());
//					System.out.println("");
//					System.out.println("");
//				}
			}
			else {
				System.out.println("ZONK!");
//				for (int i = 0; i < details.length; i++) {
//					System.out.println(i + " " + details[i]);
//					System.out.println(invoice.getStartPage());
//					System.out.println("");
//					System.out.println("");
			}

		}
			
//			for (int i = 0; i < details.length; i++) {
//				System.out.println(i + " " + details[i]);
//			}
//			
//			System.out.println("");
//			System.out.println("");
		
		if(invoice.getClientNumber() == null) {
			//System.out.println(invoice.getStartPage());
			//System.err.println(invoicetext);
		}
		return invoice;
	}

}
