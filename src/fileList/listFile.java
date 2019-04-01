package fileList;

import java.util.ArrayList;
import java.util.List;
public class listFile {
	List<unitFile> files;

	@Override
	public String toString() {
		return "listFile [file=" + files + "]";
	}
	public listFile() {
		files = new ArrayList<unitFile>();
	}

	public List<unitFile> getFile(){
		return files;
	}
	public void setFile(List<unitFile> file){
		this.files = file;
	}
	
	public  void sortFile(){
		unitFile[] arrFile = new unitFile[files.size()];
		arrFile = files.toArray(arrFile);
		Heap.sort(arrFile);
		files.clear();
		for(int j = arrFile.length-1 ; j >= 0; j --)
			files.add(arrFile[j]);
	}
	
	public void deleteFile(int i){
		files.remove(i);
	}
	
	public void editFile(unitFile f1,unitFile f2){
		int i = files.indexOf(f1);
		files.set(i, f2);
	}	 
	
	public void addFile(unitFile f){
		files.add(f);
	}
	public void show(){
		for(unitFile f:files){
			System.out.println(f.getPath());
		}
	}
	public int size() {
		return files.size();
	}
	
}

