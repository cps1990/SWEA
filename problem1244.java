import java.io.*;
import java.util.StringTokenizer;

public class Solution {

	static int[] Num, CmpNum;
	static int Exchange, Len, ans;

	public static void adjust() {

		if (Exchange > Len)
			Exchange = Len;
	}

	public static void update() {

		for (int i = 0; i < Len; i++) {
			if (Num[i] == CmpNum[i])
				continue;
			else if (Num[i] > CmpNum[i])
				break;
			else
				return;
		}

		ans = 0;
		for (int i = 0; i < Len; i++) {
			CmpNum[i] = Num[i];
			ans = ans * 10 + Num[i];
		}
	}

	public static void swap(int a, int b) {

		int tmp = Num[a];
		Num[a] = Num[b];
		Num[b] = tmp;
	}

	public static void dfs(int count) {

		if (count == Exchange) {
			update();
			return;
		}

		for (int i = 0; i < Len - 1; i++) {
			for (int j = i + 1; j < Len; j++) {
				swap(i, j);
				dfs(count + 1);
				swap(j, i);
			}
		}
	}

	public static void main(String[] args) throws IOException {

		int tc;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		tc = Integer.parseInt(br.readLine());
		for (int i = 0; i < tc; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char[] tmp = st.nextToken().toCharArray();
			Len = tmp.length;
			Num = new int[Len];
			CmpNum = new int[Len];
			for (int j = 0; j < Len; j++)
				Num[j] = CmpNum[j] = tmp[j] - '0';
			Exchange = Integer.parseInt(st.nextToken());
			adjust();
      dfs(0);
			sb.append("#" + (i + 1) + " " + ans + "\n");
		}
		System.out.println(sb.toString());

	}

}
