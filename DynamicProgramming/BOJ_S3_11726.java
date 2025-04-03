package DynamicProgramming;

import java.util.*;

/*
 * BOJ 11726 Silver 3, 2 x n 타일링
 * https://www.acmicpc.net/problem/11726
 * 
 */

class BOJ_S3_11726 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[1001];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 5;
        for (int i = 5; i <= n; i++) {
            dp[i] = (dp[i-1] + dp[i-2])%10007;
            
        }
        System.out.println(dp[n]);
    }
}