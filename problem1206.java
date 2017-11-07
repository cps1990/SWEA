import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Solution {
	
	public static int maxHeight(int a, int b, int c, int d) {
		
		int tmp1 = (a > b) ? a : b;
		int tmp2 = (c > d) ? c : d;
		
		return (tmp1 > tmp2) ? tmp1 : tmp2; 
	}
	
	public static int view(int[] b) {
		
		int ans = 0;
		int len = b.length;
		
		// always begin at index 2 (index 0, 1, len - 2, len - 1 : land))
		int tmp;
		for(int i = 2; i < len - 2; i++) {
			tmp = b[i] - maxHeight(b[i - 2], b[i - 1], b[i + 1], b[i + 2]);
			ans += (tmp > 0) ? tmp : 0;
		}
		return ans;
	}

	public static void main(String[] args) throws IOException {
		
		int tc, size;
		int[] buildings;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		tc = 10;
		for(int testcase = 0; testcase < tc; testcase++) {
			size = Integer.parseInt(br.readLine());
			buildings = new int[size];
			buildings = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			sb.append("#" + (testcase + 1) + " " + view(buildings) + "\n");
		}
		System.out.println(sb.toString());

	}

}
