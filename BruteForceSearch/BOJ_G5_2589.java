package BruteForceSearch;

import java.io.*;
import java.util.*;

/*
 * BOJ 2589 Gold 5, 보물섬
 * https://www.acmicpc.net/problem/2589
 * N x M, 육지 - L, 바다 - W, 상하좌우로 인접한 육지로만 이동 가능
 * 각 포인트를 bfs + depth 를 기록하며 최장 거리를 업데이트
 */

public class BOJ_G5_2589 {
    static int N,M,res;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    // depth를 기록하며 연결된 육지를 모두 탐색
    private static int bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y,0});
        visited[x][y] = true;
        int maxLen = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if ( maxLen < cur[2]) maxLen = cur[2];

            for (int k = 0; k < 4; k++) {
                int nx = cur[0] + dx[k];
                int ny = cur[1] + dy[k];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (map[nx][ny] == 'W' || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx,ny,cur[2]+1});
            }
        }
        return maxLen;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        res = -1;

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] =='L') {
                    visited = new boolean[N][M];
                    int maxLength = bfs(i,j);
                    if (maxLength > res) res = maxLength;
                }
            }
        }
        System.out.println(res);
    }
}
