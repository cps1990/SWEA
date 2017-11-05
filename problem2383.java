import java.io.*;
import java.util.StringTokenizer;
 
class Person {
    int x, y;
     
    public Person(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
 
class Stair {
    int x, y, height;
     
    public Stair(int x, int y, int h) {
        this.x = x;
        this.y = y;
        this.height = h;
    }
}
 
public class Solution {
     
    static final int MAX = 20 + 100;        // N <= 10
    static int N, ans, pcount, scount;
    static Person[] P;
    static Stair[] S;
    static int[] Case;
     
    public static int calc() {
         
        int result_time = 0;
         
        for (int stair_index = 1; stair_index <= 2; stair_index++) {
            int max_time = 0;
            int[] arrival_time = new int[20];   // the number of persons at index time
            for (int i = 0; i < pcount; i++) {
                if (Case[i] == stair_index) {
                    int time = Math.abs(P[i].x - S[stair_index - 1].x) + Math.abs(P[i].y - S[stair_index - 1].y);
                    arrival_time[time + 1]++;   // you can go down after 1 minute
                }
            }
             
            int[] current_stair_time = new int[MAX];
            for (int time = 1; time < 20; time++) {
     
                while(arrival_time[time] > 0) {
                    arrival_time[time]--;
                    int height = S[stair_index - 1].height;
                     
                    for(int walk_time = time; walk_time < MAX; walk_time++) {
                        if (current_stair_time[walk_time] < 3) {
                            current_stair_time[walk_time]++;
                            height--;
                             
                            if (height == 0) {
                                max_time = walk_time + 1;
                                break;
                            }
                        }
                    }
                }
            }
            result_time = (result_time < max_time) ? max_time : result_time;
        }
         
        return result_time;
    }
     
    public static void dfs(int n) {
         
        if (n == N) {
            int min = calc();
            ans = (ans > min) ? min : ans;
            return;
        }
         
        for(int index = 1; index <= 2; index++) {
            Case[n] = index;
            dfs(n + 1);
        }
    }
 
    public static void main(String[] args) throws IOException {
         
        int tc;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        tc = Integer.parseInt(br.readLine());
         
        for (int i = 0; i < tc; i++) {
            N = Integer.parseInt(br.readLine());
            // initialize
            pcount = 0;
            scount = 0;
            P = new Person[10];
            S = new Stair[2];
            Case = new int[10];
            ans = Integer.MAX_VALUE;
            for (int x = 0; x < N; x++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int y = 0; y < N; y++) {
                    int val = Integer.parseInt(st.nextToken());
                    if (val == 1)
                        P[pcount++] = new Person(x, y);
                    else if (val > 1)
                        S[scount++] = new Stair(x, y, val);
                }
            }
            dfs(0);
            sb.append("#" + (i + 1) + " " + ans + "\n");
        }
        System.out.println(sb.toString());
 
    }
 
}
