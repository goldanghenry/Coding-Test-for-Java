package Basic;
import java.util.Scanner;

/*
 * zero-one knapsack
 * W = 배낭의 용량
 * (V_i, W_i) = 가치, 무게
 * D[i,w] = 물건 1~i까지만 고려하고, 배낭의 용량이 w일 때의 최대 가치
 * 
 */

public class Knapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int W = sc.nextInt();

        int[] weights = new int[N+1];
        int[] profits = new int[N+1];

        for (int i = 1; i <= N; i++) {
            weights[i] = sc.nextInt();
            profits[i] = sc.nextInt();
        }

        int[][] D = new int[N+1][W+1];  // 각 물건까지 각 무게를 만족하는 최적 가치
        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <=  W; w++) {
                if (weights[i] <= w) {  // 물건의 무게가 가방의 무게를 초과하지 않는 경우
                    D[i][w] = Math.max(D[i-1][w], D[i-1][w-weights[i]]+profits[i]);
                } else {    // 물건의 무게가 가방의 무게를 초과하는 경우
                    D[i][w] = D[i-1][w];
                }
            }
        }
        System.out.println(D[N][W]);
    }
}
