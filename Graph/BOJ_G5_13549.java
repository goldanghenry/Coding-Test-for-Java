package Graph;

import java.util.*;

/*
 * BOJ 13549 Gold 5, 숨바꼭질 3
 * https://www.acmicpc.net/problem/13549
 * 가중치가 양수이고 다름(1 or 0) -> 다익스트라
 */

public class BOJ_G5_13549 {
    static int n, k;
    static final int MAX_POSITION = 100_000;
    static final int INF = 100_000;
    static int[] time = new int[MAX_POSITION+1];
    static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.time));

    public static class Node {
        int pos, time;
        public Node(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }

    private static void dijkstra() {
        
        Arrays.fill(time, INF);
        time[n] = 0;
        
        pq.offer(new Node(n, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int curPos = cur.pos;
            int curTime = cur.time;

            if (time[curPos] < curTime) continue;

            // -
            int np = curPos -1;
            if (valid(np) && time[np] > curTime+1) {
                time[np] = curTime+1;
                pq.offer(new Node(np,curTime+1));
            }
            
            // +
            np = curPos+1;
            if (valid(np) && time[np] > curTime+1) {
                time[np] = curTime+1;
                pq.offer(new Node(np, curTime+1));
            }

            // x2
            np = curPos *2;
            if (valid(np) && time[np] > curTime) {
                time[np] = curTime;
                pq.offer(new Node(np,curTime));
            }

        }
    }

    private static boolean valid(int np) {
        return 0 <= np && np <= INF;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        if (n >= k) System.out.println(n-k);
        else {
            dijkstra();
            System.out.println(time[k]);
        }

    }
}
