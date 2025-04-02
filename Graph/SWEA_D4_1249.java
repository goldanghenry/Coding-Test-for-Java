package Graph;
import java.io.*;
import java.util.*;
/*
 * SWEA 1249 D4. 보급로
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15QRX6APsCFAYD
 * 최단거리, 양의 가중치(파여진 깊이 = 시간) -> 다익스트라
 * 이동시간은 고려x
 */

public class SWEA_D4_1249 {
    static int N;
    static int[][] map;
    private static class Node {
        int x,y,cost; 
        
        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    private static int dijkstra() {
        // 각 좌표까지의 최소 비용
        int[][] time = new int[N][N];

        // 최대 값으로 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(time[i], Integer.MAX_VALUE);
        }
        
        time[0][0] = 0; // 시작 노드
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        // 누적 비용을 기준으로 정렬하는 우선순위 큐
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        pq.offer(new Node(0,0, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int x = cur.x;
            int y = cur.y;
            int cost = cur.cost;

            // 도착점에 도달하면 최소 비용 반환
            if (x == N-1 && y == N-1) return cost;

            // 이미 더 작은 비용으로 방문한 경우 무시(방문 체크)
            if (cost > time[x][y]) continue;

            // 네 방향 탐색
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                // 범위 체크
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                int newCost = cost + map[nx][ny];   // 새로운 비용 검색
                if (newCost < time[nx][ny]) {       // 새로운 비용이 이전 값보다 작다면 업데이트
                    time[nx][ny] = newCost;
                    pq.offer(new Node(nx,ny,newCost));
                }
            }
        }
        return -1;  // 도달 불가능한 경우

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t =1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            // 맵 입력
            for (int i = 0; i < N; i++) {
                String in = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = in.charAt(j)-'0';   // char -> int
                }
            }
            int ans = dijkstra();
            System.out.println("#"+t+" "+ans);
        }
    }
}
