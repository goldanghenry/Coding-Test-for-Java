package Implementation;

import java.util.*;
import java.io.*;

/*
 * BOJ 14500 gold 4, 테트로미노
 * https://www.acmicpc.net/problem/14500
 * N*M인 종이 위에 테트로미노 하나가 놓인 칸에 쓰여있는 수들의 합을 최대로 하는 프로그램
 */

public class BOJ_G4_14500 {
    static int N, M, res, maxValue;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] map;
    static boolean[][] visited;


    private static void dfs(int x, int y, int depth, int sum) {
        // 기저조건
        if (depth == 4) {
            res = Math.max(res, sum);
            return;
        }

        // 가지치기
        if (sum + maxValue*(4-depth) <= res) {
            return;
        }
        
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            
            if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;
            visited[nx][ny] = true;
            dfs(nx,ny,depth+1, sum+map[nx][ny]);
            visited[nx][ny] = false;
        }
    }
    // dfs로 처리되지 않는 ㅜ를 처리, 중심은 x,y
    private static void bruteForce(int x, int y) {
        // 범위 체크 -> 값 뽑아내기 -> 합산 -> maxValue 업데이트
        // 십자 모양인데 한번만 계산할 수는 없을까? 4방위
        // 상하좌우, 0, 1, 2, 3
        int[] values = new int[4];
        boolean[] isMap = new boolean[4];
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            isMap[k] = true;
            values[k] = map[nx][ny];
        }

        for (int i = 0; i < 4; i++) {
            if (isMap[(i+1)%4] && isMap[(i+2)%4] && isMap[(i+3)%4]) {
                res = Math.max(res, values[(i+1)%4]+values[(i+2)%4]+values[(i+3)%4]+map[x][y]);
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
        maxValue = -1;
        // 칸의 최대 값 찾기
        for (int i = 0; i < N ; i++) {
            for (int j = 0 ; j < M; j++) {
                if (map[i][j] > maxValue) maxValue = map[i][j];
            }
        }

        // 칸 마다 블럭 만들기 시행
        // ㅜ 모양은 dfs로 생성할 수 없음, 순차적으로 생성될 수 없기 때문
        res = -1;
        visited = new boolean[N][M];
        for (int i = 0; i < N ; i++) {
            for (int j = 0 ; j < M; j++) {
                visited[i][j] = true;
                dfs(i,j,1, map[i][j]);
                visited[i][j] = false;
                bruteForce(i,j);
            }
        }
        System.out.println(res);
    }
}
