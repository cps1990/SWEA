import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Solution {
	
	static final int MAX = 50;
	
	public static int[] countingSort(int[] ary) {
		
		int len = ary.length;
		int[] counting = new int[MAX];
		int[] result = new int[len];
		
		for(int i = 0; i < len; i++)
			counting[ary[i]]++;
		
		for(int i = 1; i < MAX; i++)
			counting[i] += counting[i - 1];
		
		for(int i = 0; i < len; i++) {
			result[counting[ary[i]] - 1] = ary[i];
			counting[ary[i]]--;
		}
		
		return result;
	}

	public static void main(String[] args) throws IOException {
		
		int tc;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		tc = Integer.parseInt(br.readLine());
		for(int i = 0; i < tc; i++) {
			int n = Integer.parseInt(br.readLine());
			int[] arr = new int[n];
			arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int[] ans = countingSort(arr);
			sb.append("#" + (i + 1) + " ");
			for(int j = 0; j < n; j++)
				sb.append(ans[j] + " ");
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}

}
