import java.io.*;
import java.util.*;
import java.util.stream.Stream;
 
class Bio {
 
    int x, y;
    int size;
    int direction;
     
    public Bio(int x, int y, int size, int direction) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.direction = direction;
    }
}
 
public class Solution {
     
    static int N, M, K;
     
    public static int changeDir(int from) {
         
        if (from == 1) return 2;
        if (from == 2) return 1;
        if (from == 3) return 4;
        if (from == 4) return 3;
         
        return 0;
    }
     
    public static boolean boundCheck(int x, int y) {
         
        if(x == 0 || x == N - 1 || y == 0 || y == N - 1)
            return true;
         
        return false;
    }
     
    public static int run(LinkedList<Bio> list, int[][] map) {
         
        int amount = 0;
        Iterator<Bio> iter;
         
        for(int i = 0; i < M; i++) {
            iter = list.iterator();
            while(iter.hasNext()) {
                Bio cur = iter.next();
                 
                // move & bound check
                map[cur.x][cur.y]--;
                if (cur.direction == 1) {           // up
                    if (cur.x - 1 >= 0)
                        cur.x--;
                } else if (cur.direction == 2) {    // down
                    if (cur.x + 1 < N)
                        cur.x++;
                } else if (cur.direction == 3) {    // left
                    if (cur.y - 1 >= 0)
                        cur.y--;
                } else {
                    if (cur.y + 1 < N)               // right
                        cur.y++;
                }
                map[cur.x][cur.y]++;
                if (boundCheck(cur.x, cur.y)) {
                    cur.size /= 2;
                    cur.direction = changeDir(cur.direction); 
                    if (cur.size == 0)
                        iter.remove();
                }
            }
            // change
            for(int x = 0; x < N; x++) {
                for(int y = 0; y < N; y++) {
                    if (map[x][y] > 1) {     // merge Bio
                        int sum = 0;
                        int top = 0;
                        int dir = 0;
                        iter = list.iterator();
                        while(iter.hasNext()) {
                            Bio cur = iter.next();
                            if (cur.x == x && cur.y == y) {
                                if (cur.size > top) {
                                    top = cur.size;
                                    sum += cur.size;
                                    dir = cur.direction;
                                } else {
                                    sum += cur.size;
                                }
                                iter.remove();
                            }
                        }
                        list.add(new Bio(x, y, sum, dir));
                    }
                }
            }
        }
        iter = list.iterator();
        while(iter.hasNext()) {
            amount += iter.next().size;
        }
         
        return amount;
    }
 
    public static void main(String[] args) throws IOException {
         
        int tc = 0;
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        LinkedList<Bio> list;
         
        tc = Integer.parseInt(br.readLine());
        for(int i = 0; i < tc; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            int[][] map = new int[N][N];
            list = new LinkedList<Bio>();
            for (int j = 0; j < K; j++) {
                int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                list.add(new Bio(input[0], input[1], input[2], input[3]));
                map[input[0]][input[1]]++;
            }
            sb.append("#" + (i + 1) + " " + run(list, map) + "\n");
        }
        System.out.println(sb.toString());
 
    }
 
}
