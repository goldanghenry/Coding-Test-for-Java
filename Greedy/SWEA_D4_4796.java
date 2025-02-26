package Greedy;

import java.util.Scanner;
/*
 * SWEA 4796 D4, 의석이의 우뚝 선 산
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWS2h6AKBCoDFAVT
 * 목표 -> 우뚝선 산이 몇 개인가?
 * 서쪽에서 i 번째 지점 -> i, 높이는 h_i
 * 두 지점이 같은 높이를 가지는 경우는 없다
 * 두 지점 사이에 있는 모든 지점을 볼 때, 
 */


public class SWEA_D4_4796 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = sc.nextInt();
            int[] height = new int[N];
            for (int i = 0; i <N; i++) {
                height[i] = sc.nextInt();
            }

            // 우뚝선 산의 개수 int upHill * downHill
            int upHill = 0;
            int downHill = 0;
            int result = 0;

            for (int i = 1; i < N; i++) {
                if (height[i-1] < height[i]) {  // 업힐
                    if (downHill > 0) {
                    result += upHill * downHill;
                    upHill = 0;
                    downHill = 0;
                    }
                    upHill++;
                } else  {
                    downHill++;
                }
            }

            result += upHill * downHill;

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }
}
