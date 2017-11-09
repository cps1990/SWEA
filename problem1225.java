import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Solution {
	
	static final int Size = 8;

public static int[] pwGen(int[] ary) {
		
		int minus = 1;
		int index = 0;
		
		while(ary[index] != 0) {
			if (ary[index] - minus <= 0) {
				ary[index] = 0;
				break;
			}
			else
				ary[index] -= minus;
			index = (index + 1) % Size;
			minus++;
			if (minus == 6)
				minus = 1;
		}
		// index + 1 : start
		int[] ans = new int[Size];
		for(int i = 0; i < Size; i++) {
			index = (index + 1) % Size;
			ans[i] = ary[index];
		}
		
		return ans;
	}

	public static void main(String[] args) throws IOException {
		
		int tc;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 10; i++) {
			tc = Integer.parseInt(br.readLine());
			int[] ary = new int[Size];
			ary = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int[] result = pwGen(ary);
			sb.append("#" + tc + " ");
			for(int n = 0; n < 8; n++)
				sb.append(result[n] + " ");
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}

}
