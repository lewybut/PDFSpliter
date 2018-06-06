

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPageTree;

public class Spliter {

    public static void processPDFFile(String inputFileName, int startPage, int endPage, String outputFileName) {
        PDDocumentInformation info = new PDDocumentInformation();
        info.setProducer("ACME PDF Splitter v1.1");

        File file = new File(inputFileName);
        PDDocument document = new PDDocument();
        try {
            PDDocument mergedDoc = new PDDocument();
            mergedDoc.setDocumentInformation(info);
            document = PDDocument.load(file);
            Splitter splitter = new Splitter();
            splitter.setStartPage(startPage);
            splitter.setEndPage(endPage);
            List<PDDocument> outputList = splitter.split(document);
            for (PDDocument doc : outputList) {
                PDPageTree ptree = doc.getPages();
                for (int i = 0; i < ptree.getCount(); i++) {
                    mergedDoc.addPage(ptree.get(i));
                }
            }
            mergedDoc.save(outputFileName);
            mergedDoc.close();
            document.close();

        } catch (Exception e) {
        	System.out.println("Zonk! PDF SPLITER");
            System.out.print(e);
            System.exit(-1);
       }	finally {
    	   
       }

    }
}
