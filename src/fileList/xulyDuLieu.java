package fileList;

import java.io.File;

import boyer_moore.BoyerMoore;
import readFile.readFile;


public class xulyDuLieu {
	private static String txt = "";
	private static File dir;
	private static listFile files;
	private static readFile rf;
	private static String[] paths;
	public static listFile readData(String filename) {
		dir = new File(filename);
        files = new listFile();
        rf= new readFile(); 
        paths = dir.list();
        for (String path : paths){
        	txt = rf.readFileLine(filename+"\\"+path);
            unitFile file = new unitFile(path,txt,0);
            files.addFile(file);
        	txt = "";
        }
        return files;
	}
	
	public  void sortList(String nameSearch,listFile files) {
		BoyerMoore bm = new BoyerMoore(nameSearch);
        for (int i = 0;i<files.getFile().size();i++){
        	unitFile f = files.getFile().get(i);
        	int offset = bm.search(f.text);
        	int k = 1;
        	
            while(k != 0){
    			if(offset == f.text.length()) {
    				break;
    			}
    			if(k == 1) {
    				offset = offset+bm.search(f.text.substring(offset
    						+nameSearch.length()))+nameSearch.length();
    			}
    		}
            unitFile f2 = new unitFile(f.Path,f.text,bm.count);
            files.editFile(f, f2);
            bm.count = 0;
        }
		files.sortFile();
		System.out.println();
	        System.out.println("Sau khi sắp xếp: ");
	        files.show();
   }
}
