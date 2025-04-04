package DynamicProgramming;

import java.util.*;
/*
 * 동전의 종류가 1원, 4원, 6원이 있을 때, 8원을 거슬러주려 한다.
 * 최소 몇 개의 동전을 거슬러 주면 되나?
 * -> 종류가 배수가 아니기 때문에 그리디하게 풀 수 없다. -> DP
 */
public class MinCoinChange {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("금액을 입력하세요: ");    // 8
        int n = sc.nextInt();

        int[] C = new int[n + 1];

        // 초기값 설정
        C[0] = 0;
        for (int i = 1; i <= n; i++) {
            int min = C[i - 1]; // 1원 사용하는 경우

            if (i >= 4) {
                min = Math.min(min, C[i - 4]);
            }

            if (i >= 6) {
                min = Math.min(min, C[i - 6]);
            }

            C[i] = min + 1;
        }

        System.out.println("최소 동전 개수: " + C[n]);
    }
}

