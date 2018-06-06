
public class PathAssignerSingle {
	
	public static Invoice assignPath (String invoiceText, Invoice invoice) {
		
		//as check for 6th Group in BP
		//group 6
		if(invoice.getClientNumber().equals("DER01586") || invoice.getClientNumber().equals("DE001219")) {
			invoice.setPath("DER01586_DE001219\\");
			return invoice;
		}
		
		//as check for 7th Group in BP
		//group 7
		if(invoice.getClientNumber().equals("DER02419")) {
			invoice.setPath("Rudolf Presl GmbH & Co Kinik in Kreischa\\");
			return invoice;
		}
		
		//as in the Loop Start3 in BP
		//group 3
		if(invoiceText.indexOf("Oasis")>-1) {
			invoice.setPath("Oasis\\");
			return invoice;
		}
		//group 4
		else if(((invoiceText.indexOf("Enterprise")>-1 || invoiceText.indexOf("Contoura")>-1)) 
					&& invoice.getClientNumber().indexOf("DER") ==-1) {
			invoice.setPath("Enterprise & Contoura\\");
			return invoice;
		}
		//error group
		if(invoice.isError()) {
			invoice.setPath("error\\");
			return invoice;
		}
		
		
		if(invoice.getDates().length == 2) {
			invoice.setPath("Agreement still active\\");
			return invoice;
		}
		else if(invoice.getDates().length == 3) {
			invoice.setPath("All other\\");
			return invoice;
		}
		
		invoice.setPath("error\\");
		return invoice;
	}
}
