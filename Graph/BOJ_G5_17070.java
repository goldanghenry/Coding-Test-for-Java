package Graph;

import java.io.*;
import java.util.*;

/*
 * BOJ 17070 Gold 5, 파이프 옮기기 1
 * https://www.acmicpc.net/problem/17070
 * [ 규칙 ]
 * 파이프는 2칸을 차지하고, 밀 수 있는 방향은 우, 우하단, 아래
 * 파이프의 한쪽 끝이 N,N에 도달하는 경우의 수
 * [ 접근 방법 ]
 * 파이프 클래스를 만들고 2개의 좌표를 기록
 * 이동 가능한지 확인하는 함수
 */

public class BOJ_G5_17070 {
    static int N, res;
    static int[][] map;
    static int[] dx = {0,1,1};    // 오른쪽, 우하단, 아래
    static int[] dy = {1,1,0};

    private static void dfs(int x1, int y1, int x2, int y2) {
        if (x1 == N-1 && y1 == N-1 ) {
            res++;
            return;
        }

        // 파이프 상태 curPosition : 가로(1), 세로(2), 대각선(3)
        int curPosition;
        if (x1 == x2) curPosition = 1;  // 가로
        else if (y1==y2) curPosition = 2; // 세로
        else curPosition = 3;   // 대각선

        // 이동방향 k : 오른쪽(0), 대각선(1), 아래(2)
        for (int k = 0; k < 3; k++) {
            if (curPosition == 1 && k == 2) continue;
            if (curPosition == 2 && k == 0) continue;
            int nx = x1 + dx[k];
            int ny = y1 + dy[k];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 1 ) continue;
            if (k==1){
                if (x1 + 1 >= N || y1 +1 >= N) continue;
                if (map[x1][y1+1] == 1 || map[x1+1][y1] == 1) continue;
            }
            dfs(nx,ny,x1,y1);
            
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 받기
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dfs
        res = 0;
        dfs(0,1,0,0);
        System.out.println(res);
    }
}
