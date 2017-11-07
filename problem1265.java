import java.util.Scanner;

public class Solution {
	
	public static long max(int talent, int binding) {
		
		long ans = 1;
		int mid = talent / binding;
		int rest = talent % binding;
		int[] group = new int[binding];
		
		for(int i = 0; i < binding; i++)
			group[i] = mid;
		
		for(int i = 0; i < rest; i++)
			group[i % binding] += 1;
		
		for(int i = 0; i < binding; i++)
			ans *= group[i];
		
		return ans;
	}

	public static void main(String[] args) {

		int tc;
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);

		try {
			tc = sc.nextInt();
			for (int i = 0; i < tc; i++) {
				int talent = sc.nextInt();
				int binding = sc.nextInt();
				sb.append("#" + (i + 1) + " " + max(talent, binding) + "\n");
			}
			System.out.println(sb.toString());
		} finally {
			if (sc != null)
				sc.close();
		}

	}

}
