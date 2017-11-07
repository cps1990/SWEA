package problem1824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static final int[] dx = { 1, 0, 0, -1 };
	static final int[] dy = { 0, 1, -1, 0 };
	
	static final int Limit = 5;
	static int[][] Visited;
	static char[][] Command;
	static int ans, Memory, R, C;
	
	public static void restore(int[][] tmp) {
		
		for(int i = 0; i < R; i++)
			for(int j = 0; j < C; j++)
				Visited[i][j] = tmp[i][j];
	}

	public static int new_x(int x, int d) {

		int nx = x + dx[d];
		if (nx < 0)
			nx = R - 1;
		if (nx >= R)
			nx = 0;

		return nx;
	}

	public static int new_y(int y, int d) {

		int ny = y + dy[d];
		if (ny < 0)
			ny = C - 1;
		if (ny >= C)
			ny = 0;

		return ny;
	}

	public static int exe(char c, int x, int y, int direction) {

		int result = direction;

		if (c == '@') {
			result = -1;
			ans = 1;
		} else if (c == '<') {
			result = 2;
		} else if (c == '>') {
			result = 1;
		} else if (c == '^') {
			result = 3;
		} else if (c == 'v') {
			result = 0;
		} else if (c == '_') {
			result = (Memory == 0) ? 1 : 2;
		} else if (c == '|') {
			result = (Memory == 0) ? 0 : 3;
		} else if (c == '.') {
			result = direction;
		} else if (c >= '0' && c <= '9') {
			Memory = c - '0';
		} else if (c == '+') {
			if (Memory < 15) {
				Memory++;
			} else {
				Memory = 0;
			}
		} else if (c == '-') {
			if (Memory > 0) {
				Memory--;
			} else {
				Memory = 15;
			}
		}

		return result;
	}

	public static void dfs(int x, int y, int direction) {

   		Visited[x][y] += 1;
		if (Visited[x][y] >= Limit)
			return;
		
		char cmd = Command[x][y];
		if (cmd == '?') {
			int nx, ny;
			for (int i = 0; i < 4; i++) {
				if (ans == 1) {
					return;
				}
				nx = new_x(x, i);
				ny = new_y(y, i);
				dfs(nx, ny, i);
			}
		} else {
			direction = exe(cmd, x, y, direction);
			if (direction == -1)
				return;
			int nx = new_x(x, direction);
			int ny = new_y(y, direction);
			dfs(nx, ny, direction);
		}
	}

	public static void main(String[] args) throws IOException {

		int tc;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		tc = Integer.parseInt(br.readLine());
		for (int testcase = 0; testcase < tc; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			ans = 0;
			Memory = 0;
			Command = new char[R][C];
			Visited = new int[R][C];
			for (int row = 0; row < R; row++)
				Command[row] = br.readLine().toCharArray();
			dfs(0, 0, 1);
			sb.append("#" + (testcase + 1) + " " + ((ans == 1) ? "YES" : "NO") + "\n");
		}
		System.out.println(sb.toString());
	}

}
