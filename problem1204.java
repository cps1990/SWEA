import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		
		int tc, ans = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
				
		for(int testcase = 1; testcase <= 10; testcase++) {
			tc = Integer.parseInt(br.readLine());
			int[] score = new int[101];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int tmp;
			for(int i = 0; i < 1000; i++) {
				tmp = Integer.parseInt(st.nextToken());
				score[tmp]++;
			}
			
			int max_count = 0;
			for(int i = 0; i <= 100; i++) {
				if (score[i] >= max_count) {
					ans = i;
					max_count = score[i];
				}
			}

			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.println(sb.toString());
	}

}
