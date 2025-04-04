package DynamicProgramming;

import java.util.Scanner;
/*
 * DP를 적용한 이항 계수 구하기
 * ( x + y )^n 을 전개 했을 때, x^(k)y&(n-k)의 계수 값은?
 * n개에서 k개를 고르는 조합의 가짓수인 nCk이고 이를 이항 계수라고 부른다.
 */
public class BinomialCoefficient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        
        int[][]B = new int[n+1][k+1];
        for (int i = 0; i <= n ; i++) {
            int iter = Math.min(i,k);
            for (int j = 0; j <= iter; j++) {
                if (j == 0 || j == i) B[i][j] = 1;
                else B[i][j] = B[i-1][j-1] + B[i-1][j];
            }
        }

        System.out.println(B[n][k]);
    }
}
