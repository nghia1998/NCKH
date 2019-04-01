package readFile;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;
import java.io.FileInputStream;
import java.util.List;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
public class readFile {
	public String readFileLine(String fileName) {
		String txt = "";
		
		String[] temp = fileName.split("\\.");
		if(temp[temp.length-1].equals("doc")||temp[temp.length-1].equals("docx")) {
			try {
				FileInputStream fis = new FileInputStream(fileName);
			      XWPFDocument document = new XWPFDocument(OPCPackage.open(fis));
			      List<XWPFParagraph> paragraphList = document.getParagraphs();
			      
			      XWPFWordExtractor wordExtractor = new XWPFWordExtractor(document);
			      txt = wordExtractor.getText();
			      wordExtractor.close();
			      document.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
        return txt;
	}
	
}
