import java.io.*;
import java.util.HashSet;
import java.util.stream.Stream;

public class Solution {
	
	static final int[] dx = { 0, 1, 0, -1 };
	static final int[] dy = { 1, 0, -1, 0 };
	
	static final int N = 4;
	static int ans;
	static int[][] board;
	static HashSet<Integer> hs;
	
	public static void dfs(int count, int x, int y, int sum) {
		
		if (count == 7) {
			hs.add(sum);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx >= 0 && nx < N && ny >= 0 && ny < N)
				dfs(count + 1, nx, ny, sum * 10 + board[nx][ny]);
		}
		
	}

	public static void main(String[] args) throws IOException {
		
		int tc;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		board = new int[N][N];
		tc = Integer.parseInt(br.readLine());
		for(int i = 0; i < tc; i++) {
			hs = new HashSet<>();
			ans = 0;
			for(int j = 0; j < N; j++)
				board[j] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			for(int r = 0; r < N; r++)
				for(int c = 0; c < N; c++)
					dfs(1, r, c, board[r][c]);
			sb.append("#" + (i + 1) + " " + hs.size() + "\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}
