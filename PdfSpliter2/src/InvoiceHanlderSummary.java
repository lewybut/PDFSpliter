import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InvoiceHanlderSummary extends InvoiceHandler  {

	@Override
	String[] regexPattern() {
		return new String[] {"(DE[\\d|\\w]{1}\\d{5})\\s+\\d+\\s+\\d{2}\\.\\d{2}\\.\\d{2}\\s+\\w{3}\\s+"};
	}

	@Override
	Invoice setInvoiceDetails(String invoicetext, Invoice invoice, String[] regexPattern) {
	System.out.println("setInvoiceDetails from Invoice Handler called");
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
