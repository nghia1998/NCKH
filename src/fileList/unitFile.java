package fileList;
public class unitFile implements Comparable{
	public String Path;
	public String text;
	public int rank;
	public String getPath() {
		return Path;
	}
	public unitFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public unitFile(String path, String text, int id) {
		super();
		Path = path;
		this.text = text;
		this.rank = id;
	}
	public void setPath(String path) {
		Path = path;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getId() {
		return rank;
	}
	public void setId(int id) {
		this.rank = id;
	}
	public int ssFlie(unitFile e) {
		if(e.rank>this.rank)  return -1;
		else if(e.rank<rank) return 1;
		return 0;
	}
	public int compareTo(Object e1) {
		unitFile e = (unitFile)e1;
		return ssFlie(e);
	}
}
