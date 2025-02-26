package Implementation;

import java.util.Scanner;
import java.io.*;

/*
 * SWEA 1954 달팽이 숫자 D2
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PobmqAPoDFAUq
 * N x N 배열에 1부터 N^2까지 달팽이 모양으로 채우기
 * 재귀로 풀어보기
 */

public class SWEA_D2_1954_2 {
    static int N, num, top, bottom, left, right;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int t = 1; t < T; t++) {
            
            N = sc.nextInt();       // 배열 크기
            map = new int[N][N];    // 배열
            num = 1;                // 1부터 N^2까지 채우기
            top = 0; bottom = N-1;  // 행
            left = 0; right = N-1;  // 열

            dfs();

            sb.append("#").append(t).append("\n");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }

        sc.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void dfs() {
        if (num > N*N) return;

        // 위쪽 행
        for (int j = left; j <= right; j++) {
            map[top][j] = num++;
        }
        top++;  // 안 쪽 행(아래)으로 이동

        // 왼쪽 행
        for (int i = top; i <= bottom; i++) {
            map[i][right] = num++;
        }
        right--;    // 안 쪽 열(왼쪽)로 이동

        // 아래쪽 행
        if (top <= bottom) {
            for (int j = right; j >= left; j--) {
                map[bottom][j] = num++;
            }
            bottom--;   // 안 쪽 행(위)으로 이동
        }

        // 오른쪽 행
        if (left <= right) {
            for (int i = bottom; i >= top; i--) {
                map[i][left] = num++;
            }
            left++; // 안 쪽 열(오른쪽)으로 이동
        }
        dfs();
    }
}
