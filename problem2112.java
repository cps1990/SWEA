import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Solution {

	static int D, W, K;
	static int[][] Film, TmpFilm;
	static int[] ChemicalState;
	static int minChemicalCount;
	
	public static void useChemical(int depth, int chemi) {
		
		for(int col = 0; col < W; col++) {
			TmpFilm[depth][col] = Film[depth][col];
			Film[depth][col] = chemi;
		}
	}
	
	public static void restore(int depth, int chemi) {
		
		for(int col = 0; col < W; col++) {
			Film[depth][col] = TmpFilm[depth][col];
		}
	}

	public static boolean valid_check() {

		for (int col = 0; col < W; col++) {
			int max = 1;
			int cnt = 1;
			for (int row = 1; row < D; row++) {
				if (Film[row][col] == Film[row - 1][col])
					cnt++;
				else
					cnt = 1;
				max = (max < cnt) ? cnt : max;
			}
			if (max < K)
				return false;
		}
		return true;
	}
	
	public static void dfs(int depth, int chemicalCount) {
		
		if (minChemicalCount <= chemicalCount)
			return;
		
		if (depth == D) {
			if (valid_check())
				minChemicalCount = (minChemicalCount > chemicalCount) ? chemicalCount : minChemicalCount;
			return;
		}
		
		dfs(depth + 1, chemicalCount);
		
		useChemical(depth, 0);
		dfs(depth + 1, chemicalCount + 1);
		restore(depth, 0);
		
		useChemical(depth, 1);
		dfs(depth + 1, chemicalCount + 1);
		restore(depth, 1);
	}

	public static void main(String[] args) throws IOException {

		int tc;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		tc = Integer.parseInt(br.readLine());
		for (int i = 0; i < tc; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			Film = new int[D][W];
			TmpFilm = new int[D][W];
			minChemicalCount = K + 1;
			for (int j = 0; j < D; j++)
				Film[j] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			dfs(0, 0);
			sb.append("#" + (i + 1) + " " + minChemicalCount + "\n");
		}
		System.out.println(sb.toString());

	}

}
