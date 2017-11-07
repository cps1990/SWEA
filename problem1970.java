import java.util.Scanner;

public class Solution {
	
	static final int[] Changes = { 50000, 10000, 5000, 1000, 500, 100, 50, 10 };

	public static void main(String[] args) {
		
		int tc;
		int money = 0;
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		try {
			tc = sc.nextInt();
			for (int testcase = 0; testcase < tc; testcase++) {
				money = sc.nextInt();
				int[] ans = new int[8];
				
				sb.append("#" + (testcase + 1) + "\n");
				for(int i = 0; i < 8; i++) {
					ans[i] = money / Changes[i];
					money %= Changes[i];
					sb.append(ans[i] + " ");
				}
				sb.append("\n");
			}
			System.out.println(sb.toString());
			
		} finally {
			if (sc != null)
				sc.close();
		}
		
	}

}
