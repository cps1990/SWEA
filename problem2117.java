import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;
 
public class Solution {
     
    static int N, M;
    static int[] opCost;
     
    public static int cover(int[][] map, int i, int j) {
         
        int cost = 0;
        int count;
        for(int k = 1; k <= 21; k++) {
            count = 0;
            for(int n = k; n > 0; n--) {         // x index
                int area = n * 2 - 1;
                int start = area / 2;
                int y = j + (k - n);
                int y2 = j - (k - n);
                for(int p = 0; p < area; p++) {      // y index
                    int x = i - start;
                    if (x >= 0 && x < N && y >= 0 && y < N) {   // right-side
                        if (map[x][y] == 1)
                            count++;
                    }
                    if (!(n == k) && x >= 0 && x < N && y2 >= 0 && y2 < N) {    // left-side
                        if (map[x][y2] == 1)
                            count++;
                    }
                    start--;
                }
            }
            int earnings = M * count;
            if (opCost[k] <= earnings)
                cost = count;
        }
         
        return cost;
    }
     
    public static int homeSecurity(int[][] map) {
         
        int ans = 0;
         
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int tmp = cover(map, i, j);
                if (ans < tmp)
                    ans = tmp;
            }
        }
         
        return ans;
    }
 
    public static void main(String[] args) throws IOException {
         
        int tc;
        int[][] map;
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        opCost = new int[22];
        opCost[1] = 1;
        for (int i = 2; i <= 21; i++) {
            opCost[i] = opCost[i - 1] + 4 * (i - 1);
        }
         
        tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            for (int j = 0; j < N; j++)
                map[j] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sb.append("#" + (i + 1) + " " + homeSecurity(map) + "\n");
        }
        System.out.println(sb.toString());
 
    }
 
}
