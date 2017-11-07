import java.io.*;
import java.*;
import java.util.stream.Stream;

class Point {
	int x;
	int y;
	int lapsed;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		this.lapsed = 1;
	}
	
	public Point(Point p) {
		this.x = p.x;
		this.y = p.y;
		this.lapsed = p.lapsed;
	}
}

public class Solution {
	
	static final int[] dx = { 0, 1, 0, -1 };
	static final int[] dy = { 1, 0, -1, 0 };
	static int[][] map;
	static int TC, N, M, R, C, L;
	static int ans;
	
	public static boolean check(int from_x, int from_y, int to_x, int to_y, int dir) {
		
		int from = map[from_x][from_y];
		int to = map[to_x][to_y];
		
		// // from left = 0, top = 1, right = 2, bottom = 3
		if (to == 1 && dir == 0)
			if (from == 1 || from == 3 || from == 4 || from == 5)
				return true;
		if (to == 1 && dir == 1)
			if (from == 1 || from == 2 || from == 5 || from == 6)
				return true;
		if (to == 1 && dir == 2)
			if (from == 1 || from == 3 || from == 6 || from == 7)
				return true;
		if (to == 1 && dir == 3)
			if (from == 1 || from == 2 || from == 4 || from == 7)
				return true;
		if (to == 2 && dir == 1)
			if (from == 1 || from == 2 || from == 5 || from == 6)
				return true;
		if (to == 2 && dir == 3)
			if (from == 1 || from == 2 || from == 4 || from == 7)
				return true;
		if (to == 3 && dir == 0)
			if (from == 1 || from == 3 || from == 4 || from == 5)
				return true;
		if (to == 3 && dir == 2)
			if (from == 1 || from == 3 || from == 6 || from == 7)
				return true;
		if (to == 4 && dir == 1)
			if (from == 1 || from == 2 || from == 5 || from == 6)
				return true;
		if (to == 4 && dir == 2)
			if (from == 1 || from == 3 || from == 6 || from == 7)
				return true;
		if (to == 5 && dir == 2)
			if (from == 1 || from == 3 || from == 6 || from == 7)
				return true;
		if (to == 5 && dir == 3)
			if (from == 1 || from == 2 || from == 4 || from == 7)
				return true;
		if (to == 6 && dir == 0)
			if (from == 1 || from == 3 || from == 4 || from == 5)
				return true;
		if (to == 6 && dir == 3)
			if (from == 1 || from == 2 || from == 4 || from == 7)
				return true;
		if (to == 7 && dir == 0)
			if (from == 1 || from == 3 || from == 4 || from == 5)
				return true;
		if (to == 7 && dir == 1)
			if (from == 1 || from == 2 || from == 5 || from == 6)
				return true;
		
		return false;
	}
	
	public static void trace(int[][] map, boolean[][] visited) {
		
		Point p = new Point(R, C);
		Queue<Point> q = new LinkedList<>();
		
		q.add(p);
		visited[R][C] = true;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && visited[nx][ny] == false
						&& map[nx][ny] != 0 && check(cur.x, cur.y, nx, ny, i) && cur.lapsed < L) {
					Point next = new Point(cur);
					visited[nx][ny] = true;
					next.x = nx;
					next.y = ny;
					next.lapsed++;
					q.add(next);
					ans++;
				}
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		TC = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < TC; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			boolean[][] visited = new boolean[N][M];
			ans = 1;
			for (int j = 0; j < N; j++)
				map[j] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			trace(map, visited);
			sb.append("#" + (i + 1) + " " + ans + "\n");
		}
		System.out.println(sb.toString());

	}

}
