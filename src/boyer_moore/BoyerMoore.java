package boyer_moore;

public class BoyerMoore {
	private String pat;
	private String pat1;
	public int count = 0;
	int R = 2000000;
	int k;
	int[] u;
	int[] u1;
	int[] s;
	int[] right = new int[R];

	public BoyerMoore(String pat) {
		this.pat = pat;
		rightMost(pat);
		computePrefix(pat);
		reversePat(pat);
		computePrefix1(pat1);
		computeGoodsuffix(pat);
	}
//Vị trí trái nhất của từng kí tự có trong chuỗi cần tìm se được lưu vào mang right
	public void rightMost(String pat) {
		pat = " "+ pat;

		int M = pat.length();

		for (int c = 0; c < R; c++)
			right[c] = -1;
		for (int j = 1; j < M; j++)
			right[Character.toLowerCase(pat.charAt(j))] = j;
	}
//tìm giá trị pi của từng vị trí trong chuỗi cần tìm
	public void computePrefix(String pat) {
		pat = " " + pat;
		u = new int[pat.length()];
		u[1] = 0;
		int k = 0;
		for (int q = 2; q < pat.length(); q++) {

			while (k > 0 && Character.toLowerCase(pat.charAt(k + 1)) != Character.toLowerCase(pat.charAt(q))) {
				k = u[k];
			}
			;
			if (Character.toLowerCase(pat.charAt(k + 1)) == Character.toLowerCase(pat.charAt(q))) {
				k = k + 1;
			}
			u[q] = k;
		}
	}
//đảo ngược chuỗi cần tìm
	public void reversePat(String pat) {
		pat = new StringBuffer(pat).reverse().toString();
		this.pat1 = pat;
	}
//tim giá trị pi của chuỗi đảo ngược
	public void computePrefix1(String pat) {
		pat = " " + pat1;
		u1 = new int[pat.length()];
		u1[1] = 0;
		int k = 0;
		for (int q = 2; q < pat.length(); q++) {

			while (k > 0 && Character.toLowerCase(pat.charAt(k + 1)) != Character.toLowerCase(pat.charAt(q))) {
				k = u1[k];
			}
			;
			if (Character.toLowerCase(pat.charAt(k + 1)) == Character.toLowerCase(pat.charAt(q))) {
				k = k + 1;
			}
			u1[q] = k;
		}
	}
//Tìm đoạn dịch chuyển ở từng vị trí sai trong chuỗi cần tìm
	public void computeGoodsuffix(String pat) {
		int M = pat.length();
		s = new int[M + 1];
		for (int i = 1; i <= M; i++)
			s[i] = M - u[M];
		int j;
		for (int l = 1; l <= M; l++) {
			j = M - u1[l];
			if (s[j] > l - u1[l])
				s[j] = l - u1[l];
		}
	}
//tìm đoạn dịch chuỗi tồi ưu bằng cách lấy giá trị lớn nhất của hai phương phát kí tự tồi và hậu tố tốt
	public int search(String txt) {
		int N = txt.length();
		int M = pat.length();
		int skip = 0;
		for (int i = 0; i <= N - M; i += skip) {
			skip = 0;
			for (int j = M - 1; j >= 0; j--)
				if (Character.toLowerCase(pat.charAt(j)) != Character.toLowerCase(txt.charAt(i + j))) {
					skip = Math.max(j - right[Character.toLowerCase(txt.charAt(i + j))], s[j+1]);
					break;
				}
			if (skip == 0) {
				count++;
				return i;
			}
		}

		return N;
	}
}
