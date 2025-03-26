package DynamicProgramming;

import java.io.*;
import java.util.*;
/*
 * BOJ 1520 Gold3, 내리막 길
 * https://www.acmicpc.net/problem/1520
 */

public class BOJ_G3_1520 {
    static int N,M;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] map, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0,0));
    }

    private static int dfs(int x, int y) {
        // 기저 조건 -> 경로 1 추가
        if ( x== N-1 && y == M-1) return 1;
        // 메모이제이션
        if (dp[x][y] != -1) return dp[x][y];

        dp[x][y] = 0;
        for(int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if (map[x][y] <= map[nx][ny]) continue;
            dp[x][y] += dfs(nx,ny);
        }
        return dp[x][y];

    }
    
}
