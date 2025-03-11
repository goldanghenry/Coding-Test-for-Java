package TopologySort;

import java.io.*;
import java.util.*;

/*
 * BOJ G3 게임 개발
 * https://www.acmicpc.net/problem/1516
 */

public class BOJ_G3_1516 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());    // 건물의 수
        int[] buildTime = new int[N+1];     // 각 건물의 건설 시간
        int[] dp = new int[N+1];            // 각 건물의 최소 완성 시간
        int[] inDegree = new int[N+1];      // 각 건물의 진입 차수

        // 그래프 : 선행 -> 후속 건물
        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 각 건물의 정보를 입력 받기
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            buildTime[i] = Integer.parseInt(st.nextToken());
            dp[i] = buildTime[i];   // 초기 dp 값은 자기 자신의 건설 시간

            while(true) {
                int pre = Integer.parseInt(st.nextToken());
                if (pre == -1) break;
                // 건물 i를 짓기 위해 pre 건물이 선행되어야 하므로
                // pre -> i 방향의 간선을 추가하고, i의 진입 차수를 증가
                graph[pre].add(i);
                inDegree[i]++;
            }
        }

        // 위상 정렬을 위한 큐 (진입 차수가 0인 건물)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        // 위상 정렬 진행
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph[cur]) {
                dp[next] = Math.max(dp[next], dp[cur]+buildTime[next]);
                inDegree[next]--;
                if (inDegree[next]==0) {
                    queue.offer(next);
                }
            }
        }

        // 결과 출력 : 각 건물이 완성되기까지 걸리는 최소 시간
        for (int i = 1; i <= N; i++) {
            sb.append(dp[i]).append("\n");
        }

        System.out.println(sb);
    }
}
