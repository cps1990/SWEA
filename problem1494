package problem1494;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] X, Y;
	static int N, MinusCount;
	static long ans;
	
	public static long calc(long x, long y) {
		
		return x * x + y * y;
	}
	
	public static void dfs(int n, int acc_X, int acc_Y) {
		
		if (n == N) {
			if (MinusCount == N / 2) {
				long tmp = calc(acc_X, acc_Y);
				ans = (tmp < ans) ? tmp : ans;
			}
			return;
		}

		MinusCount += 1;
		dfs(n + 1, acc_X - X[n], acc_Y - Y[n]);
		MinusCount -= 1;
		dfs(n + 1, acc_X + X[n], acc_Y + Y[n]);
	}

	public static void main(String[] args) throws IOException {

		int tc;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		tc = Integer.parseInt(br.readLine());
		for(int i = 0; i < tc; i++) {
			N = Integer.parseInt(br.readLine());
			X = new int[N];
			Y = new int[N];
			MinusCount = 0;
			ans = Long.MAX_VALUE;
			for(int j = 0; j < N; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				X[j] = Integer.parseInt(st.nextToken());
				Y[j] = Integer.parseInt(st.nextToken());
			}
			dfs(0, 0, 0);
			sb.append("#" + (i + 1) + " " + ans + "\n");
		}
		System.out.println(sb.toString());
		
	}

}
