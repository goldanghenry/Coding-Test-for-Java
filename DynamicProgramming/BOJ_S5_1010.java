package DynamicProgramming;

import java.io.*;
import java.util.*;

/*
 * BOJ 1010 Silver 5, 다리 놓기
 * https://www.acmicpc.net/problem/1010
 * 서쪽 N개 | 동쪽 M개
 * N개의 다리를 지을 때 -> 조합 MCN
 * bottom up -> 파스칼의 삼각형
 */
public class BOJ_S5_1010 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int dp[][] = new int[31][31];    
        // dp 초기화
        for (int i = 0; i <= 30; i++){
            dp[i][0] = 1;
            dp[i][i] = 1;
        }

        // bottom up
        for (int n = 1; n <= 30;n++) {
            for (int k = 1; k <n; k++ ) {
                dp[n][k] = dp[n-1][k-1] + dp[n-1][k];
            }
            
        }

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            System.out.println(dp[M][N]);
        }
    }
}
