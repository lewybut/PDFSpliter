import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FilesHandler {
	
	public static void newTxtFile(String fileName, String text, boolean createNewFile) {
		try(FileWriter fw = new FileWriter(fileName, createNewFile);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(text);
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
				
			}
	}
	
	public static String[] readCSV(String csvFilePath) {
		
		String csvFile = csvFilePath;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String[] values;
            try {
				br = new BufferedReader(new FileReader(csvFile));
				line = br.readLine();
				br.close();
				values =		line.split(cvsSplitBy);
				return values;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return null;
		
	}
	
//	public static void main(String[] args) {
//		
//		for (String string : readCSV("H:\\Java\\database\\regexTest.csv")) {
//			System.out.println(string);
//		}
//		newTxtFile("file1.txt", "LOL", false);
//	}
}
