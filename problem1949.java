import java.io.*;
import java.util.*;
import java.util.stream.*;

class Point {
	int x, y;
	int height;
	int dist;
	boolean check;

	public Point(int x, int y, int height) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.dist = 1;
		this.check = false;
	}
	
	public Point(Point p) {
		this.x = p.x;
		this.y = p.y;
		this.height = p.height;
		this.dist = p.dist;
		this.check = p.check;
	}
}

public class Solution {

	static final int[] dx = { -1, 0, 1, 0 };
	static final int[] dy = { 0, -1, 0, 1 };
	static boolean[][] visited;
	static int[][] ary;
	static int n, k;
	static int ans;
	
	public static void dfs(Point cur) {
		
		ans = ans < cur.dist ? cur.dist : ans;
		
		for (int i = 0; i < 4; i++) {
			Point next = new Point(cur);
			int nx = next.x + dx[i];
			int ny = next.y + dy[i];

			if (nx >= 0 && nx < n && ny >= 0 && ny < n && visited[nx][ny] == false) {
				next.x = nx;
				next.y = ny;
				next.height = ary[nx][ny];
				
				// no work and downhill
				if (cur.height > next.height) {
					visited[nx][ny] = true;
					next.dist++;
					dfs(next);
					visited[nx][ny] = false;
					
				// work and height check 
				} else {
					if (cur.check == false && cur.height > next.height - k) {
						visited[nx][ny] = true;
						next.height = cur.height - 1;
						next.dist++;
						next.check = true;
						dfs(next);
						visited[nx][ny] = false;
					}
				}
			}
		}
		
		return;
	}

	public static void route() {
		
		int top = 0;
		visited = new boolean[n][n];
		
		// find top
		for(int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				top = top < ary[i][j] ? ary[i][j] : top;
		
		// run route only for top
		for(int i = 0; i < n;  i++)
			for(int j = 0; j < n; j++) {
				if(ary[i][j] == top) {
					Point cur = new Point(i, j, ary[i][j]);
					visited[i][j] = true;
					dfs(cur);
					visited[i][j] = false;
				}
			}
		return;
	}

	public static void main(String[] args) throws IOException {

		int t;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			ans = 0;
			ary = new int[n][n];
			for (int j = 0; j < n; j++)
				ary[j] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			route();
			sb.append("#" + (i + 1) + " " + ans + "\n");
		}
		System.out.println(sb.toString());

	}

}
