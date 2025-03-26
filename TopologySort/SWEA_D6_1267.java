package TopologySort;

import java.io.*;
import java.util.*;
/*
 * SWEA 1267 D6, 작업 순서
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV18TrIqIwUCFAZN&
 * 위상 정렬
 */

public class SWEA_D6_1267 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= 10; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            List<Integer>[] graph = new ArrayList[V+1];
            int[] inD = new int[V+1];
            for(int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < E; i++) {
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                graph[s].add(e);
                inD[e]++;
            }

            // 진입차수가 0인 시작 노드 찾기
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 1; i <= V; i++) {
                if (inD[i] == 0) queue.offer(i);
            }

            List<Integer> result = new ArrayList<>();
            while(!queue.isEmpty()) {
                int cur = queue.poll();
                result.add(cur);

                for (int next : graph[cur]) {
                    inD[next]--;
                    if (inD[next] == 0) {
                        queue.offer(next);
                    }
                }
            }

            sb.append("#").append(t).append(" ");
            for (int i : result) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
