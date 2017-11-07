import java.io.*;
import java.util.stream.*;

public class Solution {
	
	public static int min(int a, int b) {
		return a < b ? a : b; 
	}
	
	public static int cost(int[] ticket, int[] schedule) {
		
		int[] tmp = new int[13];
		int[] d = new int[13];
		int min = 0;
		int bound;
		
		// day to month
		bound = (ticket[1] / ticket[0]) + 1;
		for (int i = 1; i <= 12; i++) {
			if (schedule[i - 1] >= bound)
				tmp[i] = ticket[1];
			else
				tmp[i] = ticket[0] * schedule[i - 1];
		}
		
		// d
		if (tmp[1] + tmp[2] + tmp[3] > ticket[2])
			d[1] = d[2] = d[3] = ticket[2];
		else {
			d[1] = tmp[1];
			d[2] = tmp[1] + tmp[2];
			d[3] = tmp[1] + tmp[2] + tmp[3];
		}
		for (int i = 4; i <= 12; i++)
			d[i] = min(d[i - 1] + tmp[i], d[i - 3] + ticket[2]);
		
		min = d[12] < ticket[3] ? d[12] : ticket[3];
		
		return min;
	}

	public static void main(String[] args) throws IOException {

		int t;
		int ans = 0;
		int[] ticket = new int[4];
		int[] schedule = new int[12];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		t = Integer.parseInt(br.readLine()); 
		
		for (int i = 0; i < t; i++) {
			ticket = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			schedule = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			ans = cost(ticket, schedule);
			sb.append("#" + (i + 1) + " " + ans + "\n");
		}
		System.out.println(sb.toString());

	}

}
