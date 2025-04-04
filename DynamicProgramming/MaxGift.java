package DynamicProgramming;
/*
 * 나고수는 NxM 크기의 미로에 갇혀 있다.
 * 미로는 1x1 크기의 방으로 나누어져 있고, 각 방에는 선물이 0개 이상 놓여져 있다.
 * 미로의 가장 왼쪽 윗 방은 (1,1)이고, 가장 오른쪽 아랫 방은 (N,M)이다.
 * 나고수는 현재 (1,1)에 있고, (N,M)으로 이동하려고 한다.
 * 이동 가능한 방향은 우, 하, 우하 이며 각 방을 방문할 때마다 방에 놓여져 있는 선물을 모두 가져갈 수 있다.
 * 나고수가 (N,M)으로 이동할 때, 가져올 수 있는 선물 개수의 최댓값을 구하시오.
 * (1 <= N,M <= 1000), (0 <= 선물 개수 <= 100)
 */
import java.util.Scanner;

public class MaxGift {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 행
        int M = sc.nextInt(); // 열

        int[][] gift = new int[N + 1][M + 1];
        int[][] dp = new int[N + 1][M + 1];

        // 선물 개수 입력
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                gift[i][j] = sc.nextInt();
            }
        }

        // DP 계산
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = Math.max(
                    Math.max(dp[i - 1][j], dp[i][j - 1]),
                    dp[i - 1][j - 1]
                ) + gift[i][j];
            }
        }

        System.out.println(dp[N][M]); // 최종 결과 출력
    }
}

