import java.io.*;
import java.util.*;

class Info {
	char location;
	int playTime;
	int rate;
	
	public Info(char loc, int pt, int r) {
		this.location = loc;
		this.playTime = pt;
		this.rate = r;
	}
}

public class Solution {
	
	static int N, M, Pcount, Rate;
	static boolean[] Visited;
	static int[][] MoveTime;
	static Info[] InfoAry;
	static Stack<Integer> Route;
	
	public static void dfs(int curLocation, int time, int day, int rate) {
		
		if (day == M + 1) {
			return;
		}
		
		for (int place = 1; place <= Pcount; place++) {
			if (Visited[place] == true) continue;
			
			if (time + MoveTime[curLocation][place] + InfoAry[place].playTime < 540) {
				Visited[place] = true;
				Route.push(place + 1);
				dfs(place, time + MoveTime[curLocation][place] + InfoAry[place].playTime, day, rate + InfoAry[place].rate);
				Visited[place] = false;
				Route.pop();
			} else {
				if (day != M) {
					for(int hotel = Pcount + 1; hotel < N; hotel++) {
						if (time + MoveTime[curLocation][hotel] <= 540) {
							Route.push(hotel + 1);
							dfs(hotel, 0, day + 1, rate);
							Route.pop();
						}
					}
				} else {
					if (time + MoveTime[curLocation][0] <= 540) {
						if (Rate < rate)
							Rate = rate;
						return;
					} else {
						return;
					}
				}
			}
		}
	}
	
	public static int max_satisfaction() {
		
		dfs(0, 0, 1, 0);
		
		return Rate;
	}

	public static void main(String[] args) throws IOException {
		
		int tc;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		tc = Integer.parseInt(br.readLine());
		for(int testcase = 0; testcase < tc; testcase++) {
			// input & initialize
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			Pcount = Rate = 0;
			MoveTime = new int[N][N];
			InfoAry = new Info[N];
			Visited = new boolean[N];
			Route = new Stack<>();
			for(int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = i + 1; j < N; j++) {
					MoveTime[i][j] = MoveTime[j][i] = Integer.parseInt(st.nextToken());
				}
			}
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				char location = st.nextToken().charAt(0);
				if (location == 'P') {
					InfoAry[i] = new Info(location, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
					Pcount++;
				}
				else if (location == 'H') {
					InfoAry[i] = new Info(location, 0, 0);
				} else {
					InfoAry[i] = new Info(location, 0, 0);
				}
			}
			sb.append("#" + (testcase + 1) + " " + max_satisfaction() + "\n");
		}
		System.out.println(sb.toString());
		
	}

}
