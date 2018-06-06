
public class PathAssignSummary {

	public static Invoice assignPath (String invoiceText, Invoice invoice) {
		
		String[] charlie = {"DE029506", "DE001309", "DE001213"};
		String[] brennen = {"DE001357", "DE001768", "DE001286", "DE002113", 
					"DE001268", "DE001217", "DE001340", "DE001709", "DE001736"};
		
		if(invoice.getClientNumber() == null) {
			invoice.setError(true);
			return invoice;
		}
		
		for (String string : brennen) {
			if(invoice.getClientNumber().equals(string)) {
				invoice.setPath("CD Brennen\\");
				return invoice;
			}
		}
		
		for (String string : charlie) {
			if(invoice.getClientNumber().equals(string)) {
				invoice.setPath("Charite Bestellungen\\");
				return invoice;
			}
		}	
		
		invoice.setPath("All other\\");
		return invoice;
	}
}
