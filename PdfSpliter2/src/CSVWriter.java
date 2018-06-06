import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
	
	private static String csvPath;
	private static FileWriter writer = null;
	private static File file;
	
	public CSVWriter(String csvPath) throws IOException {
		CSVWriter.csvPath = csvPath;
		file = new File(csvPath);
		writer = new FileWriter(csvPath);
	}
	
	public void UpdateRaport(String FilesPath, String InvoiceCount) throws IOException {

		if(!file.exists()) {
			writer.append("Files Path");
			writer.append(",");
			writer.append("Invoice Count");
			writer.append("\n");
		}
		
		writer.append(FilesPath);
		writer.append(",");
		writer.append(InvoiceCount);
		writer.append("\n");
		writer.flush();
	}
	
	public void  createReport() throws IOException {

		writer.close();
	}
	
	public static void main(String[] args) {
		try {
			CSVWriter csv = new CSVWriter("H:\\Java\\out.csv");
			System.out.println("XD");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
