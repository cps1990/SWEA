import java.io.*;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, M, ans;
	static int[][] Map;
	static boolean[] Visited;
	
	public static void dfs(int currentNode, int max) {
		
		if (max > ans)
			ans = max;
		
		for(int i = 1 ; i <= N; i++) {
			if (Map[currentNode][i] == 1 && Visited[i] == false) {
				Visited[i] = true;
				dfs(i, max + 1);
				Visited[i] = false;
			}
		}
	}
	
	public static int max_route() {
		
		ans = 0;
		for(int i = 1; i <= N; i++) {
			Visited[i] = true;
			dfs(i, 1);
			Visited[i] = false;
		}
		
		return ans;
	}

	public static void main(String[] args) throws IOException {
		
		int tc;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		tc = Integer.parseInt(br.readLine());
		for(int i = 0; i < tc; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Map = new int[N + 1][N + 1];
			Visited = new boolean[N + 1];
			ans = 0;
			M = Integer.parseInt(st.nextToken());
			for (int n = 0; n < M; n++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				Map[r][c] = Map[c][r] = 1;
			}
			sb.append("#" + (i + 1) + " " + max_route() + "\n");
		}
		System.out.println(sb.toString());

	}

}
