package DynamicProgramming;

import java.io.*;
import java.util.*;

/*
 * BOJ 7579 Gold3, 앱
 * https://www.acmicpc.net/problem/7579
 * 최소 가치 냅색 (0/1 Knapsack 응용)
 */

public class BOJ_G3_7579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 앱의 수
        int M = Integer.parseInt(st.nextToken());   // 필요한 총 메모리 용량

        int[] memory = new int[N];  // 각 앱이 차지하는 메모리 용량
        int[] cost = new int[N];    // 각 앱을 비활성화하는 데 드는 비용

        // 메모리 용량 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        // 비용 입력 및 최대 비용 계산
        st = new StringTokenizer(br.readLine());
        int maxCost = 0;
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            maxCost += cost[i];  // 총 비용의 최댓값을 계산 (dp 배열의 범위로 사용)
        }

        // dp[cost] = 해당 비용으로 확보 가능한 최대 메모리 용량
        int[] dp = new int[maxCost + 1];
        Arrays.fill(dp, -1);    // -1은 아직 메모리를 확보할 수 없다는 의미
        dp[0] = 0;              // 비용 0으로 확보 가능한 메모리는 0

        // 0/1 Knapsack 알고리즘: 각 앱을 한번씩만 선택할 수 있음
        for (int i = 0; i < N; i++) {
            // 비용을 역순으로 탐색하여 중복 선택을 방지
            for (int c = maxCost; c >= cost[i]; c--) {
                // 이전 상태가 유효한 경우에만 업데이트
                if (dp[c - cost[i]] != -1) {
                    // 현재 비용 c에서 확보 가능한 최대 메모리를 갱신
                    dp[c] = Math.max(dp[c], dp[c - cost[i]] + memory[i]);
                }
            }
        }

        // 최소 비용을 탐색: M 이상의 메모리를 확보할 수 있는 가장 작은 비용을 출력
        for (int i = 0; i <= maxCost; i++) {
            if (dp[i] >= M) {
                System.out.println(i);  // 조건을 만족하는 최소 비용 출력
                break;
            }
        }
    }
}
