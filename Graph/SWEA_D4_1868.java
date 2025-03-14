package Graph;

import java.io.*;
import java.util.*;

/*
 * SWEA 1868. 파핑파핑 지뢰찾기 (D4)
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LwsHaD1MDFAXc
 * 1. N * N 맵을 순회하면서 8방위를 조사하며 해당 자리의 숫자를 구한다.
 * 2. 해당 자리의 수가 0이라면 click을 증가 후 bfs(i,j) 를 수행한다.
 * 2.1. bfs를 통해 현재 위치(cur)을 중심으로 8방위를 조사하며 해당 자리가 0이라면 큐에 넣는다.
 * 2.2. 해당 자리가 0이 아니라면, 방문 배열(visited)에 마킹한다.
 * 3. 맵을 순회하며 마킹되지 않은 위치만큼 click을 증가하고, 정답 출력
 */

public class SWEA_D4_1868 {
    static int N, click;
    static char[][] map;
    static int[] dx = {-1,-1,-1, 0,0, 1,1,1}; // 좌상, 상, 우상, 좌, 우, 하상, 하, 우하
    static int[] dy = {-1, 0, 1,-1,1,-1,0,1};
    static boolean[][] visited;

    // 8방위를 돌며 지뢰 개수 카운팅
    private static int countingMine(int x, int y) {
        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 맵을 벗어나거나, 체크한 곳이라면
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if (map[nx][ny] == '*') cnt++;
        }
        return cnt;
    }

    // bfs
    private static void bfs(int x, int y ) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x,y});
        visited[x][y] = true;
        click ++;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int i = 0; i < 8; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny]) continue;

                if (countingMine(nx, ny) == 0)  queue.offer(new int[]{nx,ny});
                visited[nx][ny] = true;
            }

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            N = Integer.parseInt(br.readLine());
            click = 0;
            visited = new boolean[N][N];
            map = new char[N][N];

            // 지뢰찾기 맵입력 받기(. : 빈칸, * : 지뢰)
            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = input.charAt(j);
                }
            }

            // 클릭했을 때 0인 지점 찾기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '*' || visited[i][j] || countingMine(i,j) != 0) continue;
                    bfs(i,j);
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.' && !visited[i][j]) click++;
                }
            }

            System.out.println("#" + t + " " + click);

        }
    }
}
