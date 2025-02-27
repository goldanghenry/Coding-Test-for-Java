package Graph;

import java.io.*;
import java.util.StringTokenizer;

/*
 * BOJ 3109 빵집
 * https://www.acmicpc.net/problem/3109
 */

public class BOJ_G2_3109 {
    static int R, C, ans;
    static char[][] map;
    static int[] dx = {-1, 0, 1}; // 우상, 우, 우하

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        ans = 0;
        for (int i = 0; i < R; i++) {
            // 시작점이 방문 가능하면 바로 표시
            if (map[i][0] == '.') {
                map[i][0] = 'x';
                if (dfs(i, 0)) {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }

    private static boolean dfs(int x, int y) {
        // 마지막 열에 도달하면 경로 완성
        if (y == C - 1) {
            return true;
        }

        // 우상, 우, 우하 순서로 탐색
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + 1;
            if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
            if (map[nx][ny] != '.') continue;
            // 해당 셀을 방문처리하여 재탐색 방지
            map[nx][ny] = 'x';
            if (dfs(nx, ny)) {
                return true;
            }
        }
        return false;
    }
}
