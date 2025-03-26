package Graph;

import java.io.*;
import java.util.*;

/*
 * SWEA 1238 D4, Contact
 * https://swexpertacademy.com/main/solvingProblem/solvingProblem.do
 */

public class SWEA_D4_1238 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int t = 1; t <= 10; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int leng = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            List<Integer>[] graph = new ArrayList[101];
            for (int i = 1; i < 101; i++) {
                graph[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < leng/2; i++) {
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                boolean flag = true;
                for (int node : graph[s]) {
                    if (node == e) flag = false;
                }
                if (flag) graph[s].add(e);
            }

            Queue<Integer> queue = new LinkedList<>();
            boolean[] visited = new boolean[101];
            queue.offer(start);
            visited[start] = true;
            int res = -1;

            while(!queue.isEmpty()) {
                int size = queue.size();
                res = 0;
                for (int i = 0; i < size; i++) {
                    int cur = queue.poll();
                    res = Math.max(res, cur);
                    for (int next : graph[cur]) {
                        if (visited[next]) continue;
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
            System.out.println("#"+t+" "+res);
        }
    }
}
