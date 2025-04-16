package Implementation;

import java.io.*;
import java.util.*;

/*
 * BOJ 14499 Gold 4, 주사위 굴리기
 * https://www.acmicpc.net/problem/14499
 * x,y에 놓여져 있음, 초기에는 모든 면이 0.
 * 이동한 칸이 0이라면, 주사위 바닥면에 쓰인 수가 복사된다.
 *        0이 아니라면, 칸 -> 주사위 바닥, 칸은 0
 * --> 이동할 때마다, 상단에 쓰여 있는 값을 구하는 프로그램
 * 밖으로 이동x, 해당 명령 무시, 출력x
 */

public class BOJ_G4_14499 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 가로
        int M = Integer.parseInt(st.nextToken());   // 세로
        int x = Integer.parseInt(st.nextToken());   // 시작 위치
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());   // 명령어 개수
        int[] dx = {0,0,-1,1};  // 동,서,북,남
        int[] dy = {1,-1,0,0};
        int[] diceX = {0,0,0,0};   // 주사위 가로방향 - [0]: 좌, [1]: 바닥, [2]: 우, [3]: 윗면
        int[] diceY = {0,0,0,0};   // 주사위 세로방향 - [0]: 상, [1]: 바닥, [2]: 하, [3]: 윗면
        int[][] map = new int[N][M];

        // 맵 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int t = 0; t < K; t++) {
            int cmd = Integer.parseInt(st.nextToken())-1;
            
            int nx = x + dx[cmd];
            int ny = y + dy[cmd];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            x = nx; // 이동
            y = ny;

            // 주사위 돌리기
            // dx - [0]: 좌, [1]: 바닥, [2]: 우, [3]: 윗면
            // dy - [0]: 상, [1]: 바닥, [2]: 하, [3]: 윗면
            if (cmd == 0) {         
                // 동쪽 - dx를 왼쪽으로 한칸씩 밀기
                int fist = diceX[0];
                for (int i = 0; i < 3; i++) {
                    diceX[i] = diceX[i+1];
                }
                diceX[3] = fist;
                diceY[3] = diceX[3];      // 윗면 저장
                diceY[1] = diceX[1];
            } else if (cmd == 1) {
                // 서쪽 - dx를 오른쪽으로 한칸씩 밀기
                int last = diceX[3];
                for (int i = 3; i > 0; i--) {
                    diceX[i] = diceX[i-1];
                }
                diceX[0] = last;
                diceY[3] = diceX[3];
                diceY[1] = diceX[1];
            } else if (cmd == 2) {  
                // 북쪽 - dy를 오른쪽으로 한칸씩 밀기
                int last = diceY[3];
                for (int i = 3; i > 0; i--) {
                    diceY[i] = diceY[i-1];
                }
                diceY[0] = last;
                diceX[3] = diceY[3];
                diceX[1] = diceY[1];
            } else {                
                // 남쪽 - dy를 왼쪽으로 한칸씩 밀기
                int fist = diceY[0];
                for (int i = 0; i < 3; i++) {
                    diceY[i] = diceY[i+1];
                }
                diceY[3] = fist;
                diceX[3] = diceY[3];      // 윗면 저장
                diceX[1] = diceY[1];      // 아랫면 저장
            }

            // 맵의 수가 0이라면 
            if (map[x][y] == 0) {
                map[x][y] = diceX[1];   // 주사위 바닥 수 -> 칸
            } else {    // 0이 아니라면
                diceX[1] = map[x][y];   // 칸 -> 주사위 바닥
                diceY[1] = map[x][y];
                map[x][y] = 0;          // 칸 = 0
            }

            sb.append(diceX[3]+"\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

