package DynamicProgramming;

import java.util.*;

/*
 * BOJ 1463 silver 3, 1로 만들기
 * https://www.acmicpc.net/problem/1463
 */

public class BOJ_S3_1463 {
    static int[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n  = sc.nextInt();
        
        dp = n>4? new int[n+1] : new int[5];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[1] = 0; dp[2] = 1; dp[3] = 1;
        for (int  i = 4; i <= n; i++) {
            int temp = 1<<6;
            if (i%3==0) {
                temp = Math.min(dp[i/3], temp);
            }
            if (i%2 ==0) {
                temp = Math.min(dp[i/2], temp);
            }
            dp[i] = Math.min(dp[i-1], temp)+1;
        }
        System.out.println(dp[n]);
    }
}
