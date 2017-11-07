import java.io.*;
import java.util.*;

public class Solution {

	static int N, M, K, A, B;
	static int[] Reception, Repair;
	static int[] Customer;

	public static int empty_check(int[][] ary) {

		for (int i = 1; i <= ary.length - 1; i++) {
			if (ary[i][0] == 0)
				return i;
		}
		return -1; // full
	}

	public static int run() {

		int endCount = 0, ans = 0;
		int time = 0;
		int ccount = 1;
		int recMatch = 0, repMatch = 0;

		Queue<Integer> rec_queue = new LinkedList<>();
		Queue<Integer> rep_queue = new LinkedList<>();
		int[][] rec = new int[N + 1][2];
		int[][] rep = new int[M + 1][2];
		int[][] match = new int[2][K + 1];

		while (true) {
			int receptionNum, repairNum;
			// #1 : Queue routine
			while (!rec_queue.isEmpty() && (receptionNum = empty_check(rec)) != -1)
				rec[receptionNum][0] = rec_queue.poll();

			while (!rep_queue.isEmpty() && (repairNum = empty_check(rep)) != -1)
				rep[repairNum][0] = rep_queue.poll();

			// #2 : Reception routine
			while (ccount <= K && Customer[ccount] == time) {
				receptionNum = empty_check(rec);
				if (receptionNum == -1) {
					rec_queue.add(ccount);
				} else {
					rec[receptionNum][0] = ccount;
				}
				ccount++;
			}
			for (receptionNum = 1; receptionNum <= N; receptionNum++) {
				if (rec[receptionNum][0] != 0) { // not empty
					if (++rec[receptionNum][1] == Reception[receptionNum]) {
						if (receptionNum == A)
							match[0][recMatch++] = rec[receptionNum][0];
						rec[receptionNum][1] = 0;
						rep_queue.add(rec[receptionNum][0]);
						rec[receptionNum][0] = 0;
					}
				}
			}
			// #3 : Repair routine
			for (repairNum = 1; repairNum <= M; repairNum++) {
				if (rep[repairNum][0] != 0) { // not empty
					if (++rep[repairNum][1] == Repair[repairNum]) {
						if (repairNum == B)
							match[1][repMatch++] = rep[repairNum][0];
						rep[repairNum][1] = 0;
						rep[repairNum][0] = 0;
						endCount++;
					}
				}
			}
			// #4 : Quit routine
			if (endCount == K)
				break;
			time++;
		}
		for(int i = 0; i < recMatch; i++) {
			for(int j = 0; j < repMatch; j++) {
				if (match[0][i] == match[1][j])
					ans += match[0][i];
			}
		}
		return (ans != 0) ? ans : -1;
	}

	public static void main(String[] args) throws IOException {

		int tc;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		tc = Integer.parseInt(br.readLine());
		for (int i = 0; i < tc; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			Reception = new int[N + 1];
			Repair = new int[M + 1];
			Customer = new int[K + 1];
			st = new StringTokenizer(br.readLine());
			for (int n = 1; n <= N; n++)
				Reception[n] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int n = 1; n <= M; n++)
				Repair[n] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int n = 1; n <= K; n++)
				Customer[n] = Integer.parseInt(st.nextToken());
			sb.append("#" + (i + 1) + " " + run() + "\n");
		}
		System.out.println(sb.toString());

	}

}
