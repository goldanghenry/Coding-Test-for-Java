package DynamicProgramming;

import java.util.Scanner;

/*
 * BOJ 2xn 타일링 2
 * https://www.acmicpc.net/problem/11727
 */

public class BOJ_S3_11727 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] dp = new int[1001];
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 5;
        dp[4] = 11;
        for (int i = 5; i <= n; i++) {
            dp[i] = (dp[i-2]*2 + dp[i-1])%10007;
        }
        System.out.println(dp[n]);
    }
}
