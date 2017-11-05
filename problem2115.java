import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Stream;
 
class Info implements Comparable<Info> {
    int x, y, cost;
     
    public Info(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }
     
    @Override
    public int compareTo(Info i) {
        return this.cost - i.cost;
    }
     
}
 
public class Solution {
 
    static int N, M, C;
     
    public static int availableMax(Integer[] ary) {
         
        int max = 0;
        int len = ary.length;
         
        Arrays.sort(ary, Collections.reverseOrder());
         
        for (int i = 0; i < len; i++) {
            int size = ary[i];
            int cost = ary[i] * ary[i];
            for (int j = i + 1; j < len; j++) {
                if ((size + ary[j]) <= C) {
                    size += ary[j];
                    cost += ary[j] * ary[j];
                }
            }
            if (max < cost)
                max = cost;
        }
         
        return max;
    }
     
    public static int harvest(int[][] hive) {
         
        int ans = 0;
        int len = 0;
        int[][] max_cost = new int[N][N - M + 1];
        Integer[] tmp = new Integer[M];
         
        if ( (N - M + 1) == 1 )
            len = 2;
        else
            len = N - M + 1 + 1;
         
        PriorityQueue<Info> pq = new PriorityQueue<>(len, Collections.reverseOrder());
        Info[] info =new Info[len];
        for (int i = 0 ; i < N; i++) {
            for (int j = 0; j < N - M + 1; j++) {
                for (int k = 0; k < M; k++) {
                    tmp[k] = hive[i][j + k];
                }
                max_cost[i][j] = availableMax(tmp);
                if (!pq.isEmpty() && pq.peek().cost < max_cost[i][j])
                    pq.add(new Info(i, j, max_cost[i][j]));
                else
                    pq.add(new Info(i, j, max_cost[i][j]));
            }
        }
        int count = 0;
        while(!pq.isEmpty() && count < len) {
            info[count] = pq.poll();
            count++;
        }
         
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (info[i].x != info[j].x || info[j].y - info[i].y >= M)
                    if (ans < info[i].cost + info[j].cost)
                        ans = info[i].cost + info[j].cost;
            }
        }
         
        return ans;
    }
     
    public static void main(String[] args) throws IOException {
 
        int tc;
        int[][] hive;
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
         
        tc = Integer.parseInt(br.readLine());
         
        for (int i = 0; i < tc; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            hive = new int[N][N];
            for (int j = 0; j < N; j++)
                hive[j] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();            
            sb.append("#" + (i + 1) + " " + harvest(hive) + "\n");
        }
        System.out.println(sb.toString());
         
    }
 
}
