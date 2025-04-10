package Implementation;

import java.io.*;
import java.util.*;

/*
    BOJ 14503 Gold 5, 로봇 청소기
    https://www.acmicpc.net/problem/14503
 */

public class BOJ_G5_14503 {
    static int N, M, res;
    // 0-북, 1-동, 2-남, 3-서
    static int[] dx = {-1,0,1, 0};
    static int[] dy = {0,1, 0, -1};
    static int[][] map;
    static boolean[][] visited;

    static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M && map[x][y] == 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 방의 세로 크기
        M = Integer.parseInt(st.nextToken());   // 방의 가로 크기

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());   // 로봇 청소기 초기 세로 위치
        int c = Integer.parseInt(st.nextToken());   // 로봇 청소기 초기 가로 위치
        int d = Integer.parseInt(st.nextToken());   // 초기 방향

        res = 0;
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            // 1. 현재 위치 청소
            if (!visited[r][c]) {
                visited[r][c] = true;
                res++;
            }

            boolean cleanFlag = false;
            for (int i = 0; i < 4; i++) {
                // 2. 왼쪽 방향으로 회전
                d = (d + 3) % 4;
                int nx = r + dx[d];
                int ny = c + dy[d];

                // 3. 해당 방향에 청소되지 않은 빈 칸이 있는 경우 전진
                if (isValid(nx, ny) && !visited[nx][ny]) {
                    r = nx;
                    c = ny;
                    cleanFlag = true;
                    break;
                }
            }

            // 4. 네 방향 모두 청소되어 있거나 벽인 경우
            if (!cleanFlag) {
                int backDir = (d + 2) % 4; // 후진 방향
                int bx = r + dx[backDir];
                int by = c + dy[backDir];

                // 후진할 수 없으면 종료
                if (!isValid(bx, by)) {
                    break;
                }

                // 후진할 수 있으면 후진
                r = bx;
                c = by;
            }
        }

        System.out.println(res);
    }
}