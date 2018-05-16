import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InvoiceHandlerSingle extends InvoiceHandler {
	
	String[] regexPattern() {
		return new String[] {"DE(\\w|\\d){1}\\d{5}\\s+(\\w){3}\\s+.+\\s+.+"};
	}
	
	@Override
	Invoice setInvoiceDetails(String invoicetext, Invoice invoice, String[] regexPattern) {
		System.out.println("I am called!");
		String[] details;
		String patternString = regexPattern[0];
		Pattern p = Pattern.compile(patternString);
		Matcher m = p.matcher(invoicetext);
		
		while(m.find()) {
			details = m.group().split(System.getProperty("line.separator"));
			invoice.setClientNumber(details[0]);
			invoice.setInvoiceNumber(details[1]);
			invoice.setInvoiceDate(details[2]);	
		}
		if(invoice.getClientNumber() == null) {
			//System.out.println(invoice.getStartPage());
			//System.err.println(invoicetext);
		}
		return invoice;
	}

}
