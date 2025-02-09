package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    Boj Silver 1, 미로 탐색
    https://www.acmicpc.net/problem/2178
    최단 경로 탐색이므로 BFS를 통해 거리를 기록하며 탐색을 진행
 */

public class Boj_S1_2178 {
    static int N,M;
    static int[][] maze;   // 맵이므로 ArrayList 보다는 matrix로
    static boolean[][] visited;
    static int[][] dist;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new int[N][M];           // 맵
        visited = new boolean[N][M];    // 방문 체크
        dist = new int[N][M];

        // 맵 입력받기
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = input.charAt(j) -'0';
            }
        }

        // BFS 탐색 시작
        System.out.println(bfs(0,0));
    }
    static int bfs(int x, int y) {
        // 큐 선언
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x,y});
        visited[x][y] = true;
        dist[x][y] = 1;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // 맵을 벗어나지 않았고, 이동가능하고(1), 방문하지 않았다면(false) 방문
                if ( nx >= 0 && ny >= 0 && nx < N && ny < M &&
                        maze[nx][ny] == 1 && !visited[nx][ny] ) {
                    queue.add(new int[]{nx,ny});
                    visited[nx][ny] = true;
                    dist[nx][ny] = dist[cur[0]][cur[1]]+1;
                }
            }
        }
        return dist[N-1][M-1];
    }
}