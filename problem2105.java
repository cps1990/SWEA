import java.io.*;
import java.util.stream.*;
 
public class Solution {
 
    static boolean[] visited = new boolean[101];
    static int n;
     
    public static void clearVisited(boolean[] v) {
        for (int i = 0; i < v.length; i++) {
            v[i] = false;
        }
    }
     
    public static int route(int[][] ary) {
         
        int ans = -1;
         
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int a = 1; a < n; a++) {
                    for (int b = 1; b < n; b++) {
                        if (j + a < n && i + a + b < n && j - b >= 0 && 2 * (a + b) > ans) {
                            int x = i;
                            int y = j;
                            boolean flag = true;
                            clearVisited(visited);
                            visited[ary[i][j]] = true;
                            // start -> right
                            for (int k = 0; k < a; k++) {
                                if (visited[ary[++x][++y]] == false) {
                                    visited[ary[x][y]] = true;
                                } else {
                                    flag = false;
                                    break;
                                }
                            }
                            if (!flag) continue;
                            // right -> bottom
                            for (int k = 0; k < b; k++) {
                                if (visited[ary[++x][--y]] == false) {
                                    visited[ary[x][y]] = true;
                                } else {
                                    flag = false;
                                    break;
                                }
                            }
                            if (!flag) continue;
                            // bottom -> left
                            for (int k = 0; k < a; k++) {
                                if (visited[ary[--x][--y]] == false) {
                                    visited[ary[x][y]] = true;
                                } else {
                                    flag = false;
                                    break;
                                }
                            }
                            if (!flag) continue;
                            // left -> start
                            for (int k = 0; k < b; k++) {
                                if ( (x - 1) == i && (y + 1) == j) {
                                    flag = true;
                                    break;
                                }
                                if (visited[ary[--x][++y]] == false) {
                                    visited[ary[x][y]] = true;
                                } else {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag)
                                ans = 2 * (a + b);
                        }
                         
                    }
                }
            }
        }
        return ans;
    }
 
    public static void main(String[] args) throws IOException {
 
        int t;
        int[][] a;
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        t = Integer.parseInt(br.readLine());
 
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            int ans = 0;
            a = new int[n][n];
            for (int j = 0; j < n; j++)
                a[j] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            ans = route(a);
            sb.append("#" + (i + 1) + " " + ans + "\n");
        }
        System.out.println(sb.toString());
 
    }
 
}
