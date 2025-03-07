package Graph;

import java.util.*;

/*
 * BOJ S1 1697 숨바꼭질
 * https://www.acmicpc.net/status?user_id=wshdhkd&problem_id=1697&from_mine=1
 */

public class BOJ_S1_1697 {
    static int N,K;
    static int[] v;

    static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        v[start] = 1;

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            if(cur == K) {
                return v[cur];
            }
            
            // +1
            if (cur+1 < 100001 && v[cur+1]==0) {
                queue.offer(cur+1);
                v[cur+1] = v[cur]+1;
            }
            
            // -1
            if (cur-1 >= 0 && v[cur-1]==0) {
                queue.offer(cur-1);
                v[cur-1] = v[cur]+1;
            }
            
            // x2
            if (cur*2 < 100001 && v[cur*2]==0) {
                queue.offer(cur*2);
                v[cur*2] = v[cur]+1;
            }
            

        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        v = new int[100001];

        System.out.println(bfs(N)-1);
    }
}
