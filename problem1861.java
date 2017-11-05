import java.io.*;
import java.util.stream.Stream;

public class Solution {
	
	static final int[] dx = { 0, 1, 0, -1 };
	static final int[] dy = { 1, 0, -1, 0 };
	static int[][] Room;
	static int N, RoomNum, MaxLen;
	
	
	public static void dfs(int startRoomNum, int row, int col, int len) {
		
		for(int i = 0; i < 4; i++) {
			int nx = row + dx[i];
			int ny = col + dy[i];
			if (nx >= 0 && nx < N && ny >= 0 && ny < N && Room[nx][ny] - Room[row][col] == 1) {
				if (MaxLen == len + 1) {
					RoomNum = (startRoomNum < RoomNum) ? startRoomNum : RoomNum;
				} else if (MaxLen < len + 1) {
					RoomNum = startRoomNum;
					MaxLen = len + 1;
				}
				dfs(startRoomNum, nx, ny, len + 1);
			}
		}
	}
	
	public static void max_move() {
		
		for(int row = 0; row < N; row++)
			for(int col = 0; col < N; col++)
				dfs(Room[row][col], row, col, 1);
	}

	public static void main(String[] args) throws IOException {
		
		int tc;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		tc = Integer.parseInt(br.readLine());
		for(int i = 0; i < tc; i++) {
			N = Integer.parseInt(br.readLine());
			Room = new int[N][N];
			RoomNum = N * N + 1;
			MaxLen = 0;
			for(int j = 0; j < N; j++)
				Room[j] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			max_move();
			sb.append("#" + (i + 1) + " " + RoomNum + " " + MaxLen + "\n");
		}
		System.out.println(sb.toString());

	}

}
