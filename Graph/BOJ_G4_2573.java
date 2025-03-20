package Graph;

import java.io.*;
import java.util.*;

/*
 * BOJ Gold4 2573, 빙산
 * depth로 관리
 * count? 
 */

public class BOJ_G4_2573 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    // 빙산 덩어리 세기
    private static void bfs(int[] start) {
        Queue<int[]>queue = new LinkedList<>();
        visited[start[0]][start[1]] = true;
        queue.offer(start);

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nx = cur[0] + dx[k];
                int ny = cur[1] + dy[k];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny]<=0 || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                queue.offer(new int[]{nx,ny});
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 얼름 녹이기 시작
        int year = 0;

        while(true) {

            year++;
            // 임시로 사용하는 맵으로 복사
            int[][]temp = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    temp[i][j] = map[i][j];
                }
            }
            // 주변에 바다가 있다면 접한 면만큼 높이 감소
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) continue;

                    int cnt = 0;
                    // 4방위에 바다가 몇개 있는지 탐색
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= M || temp[nx][ny]>0) continue;
                        cnt++;
                    }
                    map[i][j] -= cnt;
                }
            }

            // 빙산의 덩어리 개수 세기
            visited = new boolean[N][M];
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {

                    if(map[i][j] <= 0 || visited[i][j]) continue;
                    cnt++;
                    bfs(new int[]{i,j});
                }
            }
            if (year==1) continue;
            if (cnt>= 2) break;
            if (cnt == 0) {
                year = 0;
                break;
            }
        }

        System.out.println(year);
    }
}
