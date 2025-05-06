package DynamicProgramming;

import java.io.*;
import java.util.*;

/*
    BOJ Silver 1, 스티커
    https://www.acmicpc.net/problem/9465
    - 2n개의 스티커
    - 뗄 수 있는 스티커 점수의 최댓값을 구하는 문제
    - 한 스티커를 떼면, 주변(상,하,좌,우) 스티커는 망가져서 뗄 수 없다
    -> DP[i][j] : 대각선과 대각선 왼쪽 중 큰 것 선택 + 현재 점수 -> 누적 -> max(dp[0][n] , do[1][n])
    - test case 1 : dp table
        0   1   2   3   4   5
    0 : 0   50  40  200 140 250
    1 : 0   30  100 120 210 260
 */

public class BOJ_S1_9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int n = Integer.parseInt(br.readLine());

            // 초기화
            int[][] scores = new int[2][n+1];
            int[][] dp = new int[2][n+1];

            // 입력
            for (int i = 0; i < 2;i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j < n+1; j++ ) {
                    scores[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][1] = scores[0][1];
            dp[1][1] = scores[1][1];

            for (int j = 2; j <= n; j++) {
                // 왼쪽 대각선 vs 왼쪽 대각선의 왼쪽 중 큰 것 + 현재 스티커 점수
                dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + scores[0][j];
                dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + scores[1][j];
            }

            System.out.println(Math.max(dp[0][n],dp[1][n]));
        }
    }
}
