package Graph;

import java.io.*;
import java.util.*;
/*
    BOJ 10026 Gold 5, 적록색약
    https://www.acmicpc.net/problem/10026
    적록색약 -> 빨강 = 초록
    N * N, RGB 중 하나, 그림은 구역으로, 구역은 같은 색으로 이뤄짐.
    => 적록색약인 사람이 봤을 때의 구역 수와 아닌 사람이 봤을 때의 구역 수를 구하라
    ==> 두 종류의 맵을 만들고 bfs로 영역 탐색
 */

public class BOJ_G5_10026 {
    static int N,rb,rgb;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;

    private static void bfs(int x,int y,char color, char[][] map) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nx = cur[0] + dx[k];
                int ny = cur[1] + dy[k];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny] || color != map[nx][ny]) continue;
                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        char[][] rgbMap = new char[N][N];
        char[][] rbMap = new char[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                rgbMap[i][j] = s.charAt(j);
                if (rgbMap[i][j] == 'G') {  // G 의 경우 적록색약 맵에서 R로
                    rbMap[i][j] = 'R';
                } else rbMap[i][j] = rgbMap[i][j];
            }
        }

        // 적록색약이 보는 구역
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                bfs(i,j, rbMap[i][j], rbMap);
                rb++;
            }
        }
        visited = new boolean[N][N];
        // 보통 사람이 보는 구역
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                bfs(i,j,rgbMap[i][j],rgbMap);
                rgb++;
            }
        }
        System.out.println(rgb+" "+ rb);
    }
}
