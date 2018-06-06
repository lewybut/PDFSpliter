import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InvoiceHandlerSingle extends InvoiceHandler {
	
	String[] regexPattern() {
//		return new String[] {"DE(\\w|\\d){1}\\d{5}\\s+", "Rechnungs-Nr.\\s+(\\d){8}", 
//				"(DE\\w{9}\\s+\\d{2}\\-\\d{2}\\-\\d{2}\\s+)|(DE\\w{11}\\s+\\d{2}\\-\\d{2}\\-\\d{2}\\s+)"};
		return FilesHandler.readCSV("H:\\Java\\database\\regexTest.csv");
	}
	
	@Override
	Invoice setInvoiceDetails(String invoicetext, Invoice invoice, String[] regexPattern) {

		String[] details;
		invoice.setType("Single");
		
		//get client number
		String patternString = regexPattern[0];
		Pattern p = Pattern.compile(patternString);
		Matcher m = p.matcher(invoicetext);
		while(m.find()) {
			details = m.group().split(System.getProperty("line.separator"));
			invoice.setClientNumber(details[0]);	
		}
		if(invoice.getClientNumber() == null) {
			patternString = "Tage netto\\s+(DE[\\d|\\w]{1}\\d{5})\\s+EUR\\s+";
			p = Pattern.compile(patternString);
			m = p.matcher(invoicetext);
			while(m.find()) {
				details = m.group().split(System.getProperty("line.separator"));
				invoice.setClientNumber(details[1]);	
			}
		}
		if(invoice.getClientNumber() == null) {
			invoice.setError(true);
		}
		
		//get invoice number
		patternString = regexPattern[1];
		p = Pattern.compile(patternString);
		m = p.matcher(invoicetext);
		while(m.find()) {
			details = m.group().split(" ");
			invoice.setInvoiceNumber(details[1]);	
		}
		if(invoice.getInvoiceNumber() == null) {
			patternString = "Rechnungs-Nr. (\\d{8})";
			p = Pattern.compile(patternString);
			m = p.matcher(invoicetext);
			while(m.find()) {
				details = m.group().split(" ");
				invoice.setInvoiceNumber(details[1]);	
			}
		}
		if(invoice.getInvoiceNumber() == null) {
			invoice.setError(true);
		}
		
		
		//get invoice Date
		patternString = regexPattern[2];
		p = Pattern.compile(patternString);
		m = p.matcher(invoicetext);
		while(m.find()) {
			details = m.group().split(System.getProperty("line.separator"));
			if (details.length == 2) {
				invoice.setInvoiceDate(details[1]);

			}
		}
		if(invoice.getInvoiceDate()==null) {
			patternString = "Rabatt %\\s+\\d{2}-\\d{2}-\\d{2}";
			p = Pattern.compile(patternString);
			m = p.matcher(invoicetext);
			while(m.find()) {
				details = m.group().split(System.getProperty("line.separator"));
				invoice.setInvoiceDate(details[1]);	
			}
		}
		if(invoice.getInvoiceDate()==null) {
			invoice.setError(true);
		}
		
		patternString = regexPattern[3];
		p = Pattern.compile(patternString);
		m = p.matcher(invoicetext);
		while(m.find()) {
				invoice.setDates(m.group());
		}
		
		if(invoice.getClientNumber() == null) {
			invoice.setError(true);
		}
		
		
		if(invoice.isError()) {
			invoice = InvoiceHandlerSummary.actOnException(invoicetext, invoice);
		}
		
		try {
			for (String string : invoice.getDates()) {
				System.out.println(invoice.getDates().length);
			}
		} catch (Exception e) {
			invoice.setError(true);
		}

		return PathAssignerSingle.assignPath(invoicetext, invoice);
	}

}
