import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean[][] visited;
	static final int N = 100;
	static int ans;
	
	public static void maze(int[][] map, int curX, int curY, int desX, int desY) {
		
		if (curX == desX && curY == desY) {
			ans = 1;
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int nx = curX + dx[i];
			int ny = curY + dy[i];
			if (map[nx][ny] != 1 && visited[nx][ny] == false) {
				visited[nx][ny] = true;
				maze(map, nx, ny, desX, desY);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		
		int tc = 10;
		int caseNum, cmp;
		int startX = -1, startY = -1, endX = -1, endY = -1;
		int[][] maze = new int[N][N];
		char[] input;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int testcase = 0; testcase < tc; testcase++) {
			caseNum = Integer.parseInt(br.readLine());
			ans = 0;
			visited = new boolean[N][N];
			for(int row = 0; row < N; row++) {
				input = br.readLine().toCharArray();
				for(int col = 0; col < N; col++) {
					cmp = input[col] - '0';
					if (cmp == 2) {
						startX = row; startY = col;
					} else if (cmp == 3) {
						endX = row; endY = col;
					}
					maze[row][col] = cmp;
				}
			}
			maze(maze, startX, startY, endX, endY);
			sb.append("#" + caseNum + " " + ans  + "\n");
		}
		System.out.println(sb.toString());
	}

}
