package Graph;

import java.io.*;
import java.util.*;

/*
 * BOJ 4485 Gold 4, 녹색 옷 입은 애가 젤다지?
 * https://www.acmicpc.net/problem/4485
 * 다익스트라
 * 
 */

public class BOJ_G4_4485 {
    static int N;
    static int[][] map;

    public static class Node implements Comparable<Node> {
        int x, y, cost;
        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    private static int dijkstra() {
        // 최소 비용을 저장할 배열
        int[][] time = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        // 최대값으로 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(time[i], Integer.MAX_VALUE);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        time[0][0] = map[0][0];
        pq.offer(new Node(0,0,time[0][0]));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int x = cur.x;
            int y = cur.y;
            int cost = cur.cost;

            if (visited[x][y]) continue;
            visited[x][y] = true;

            if (x == N-1 && y == N-1) return cost;
            
            for(int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;   //   범위 체크
                int newCost = cost + map[nx][ny];
                if (newCost > time[nx][ny]) continue;
                time[nx][ny] = newCost;
                pq.offer(new Node(nx,ny,newCost));
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tcase = 0;
        while(true) {
        N = Integer.parseInt(br.readLine());
        if (N == 0) break;
        tcase++;

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int res = dijkstra();
        System.out.println("Problem "+tcase+": "+res);
        }
    }
}
