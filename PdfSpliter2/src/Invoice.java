
public class Invoice {

	private Integer startPage;
	private Integer length;
	private String clientNumber;
	private String invoiceNumber;
	private String invoiceDate;
	
	public Invoice(Integer start, Integer length, String clientNumber, String invoiceNumber, String invoiceDate) {
		this.startPage = start;
		this.length = length;
		this.clientNumber = clientNumber;
		this.invoiceNumber = invoiceNumber;
		this.invoiceDate = invoiceDate;
	}
	
	public Invoice(Integer start, Integer length) {
		this.startPage = start;
		this.length = length;
	}


	public void setStartPage(Integer startPage) {
		this.startPage = startPage;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	public Integer getStartPage() {
		return startPage;
	}

	public Integer getlength() {
		return length;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	
	public String getInvoiceDate() {
		return invoiceDate;
	}
	
	public Integer getEndPage() {
		return (startPage + length) - 1;
	}
	
	public String getFileName() {
		try {
			return new String("\\" + clientNumber + "\\" + clientNumber + "_" + invoiceNumber + "_"
					+ invoiceDate.replace(".", "")).replace(" ", "").replace("/", "").replace("-", "") + ".PDF";
		} catch (Exception e) {
			System.out.println(this.getClientNumber() + " " + this.getStartPage() + " ");
		}
		return null;
	}
}
