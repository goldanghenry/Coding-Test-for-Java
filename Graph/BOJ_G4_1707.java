package Graph;

import java.io.*;
import java.util.*;

/* 
 * BOJ gold4 1707, 이분 그래프
 * https://www.acmicpc.net/problem/1707
 * 이분 그래프란? 인접한 정점끼리 서로 다른 색으로 칠해서 모든 정점을 두 가지 색으로만 칠할 수 있는 그래프
 * 
 * BFS, DFS를 활용해 각 정점을 두 가지 색 중 하나로 색칠하며 인접한 정접이 같은 색이 되지 않도록한다.
 * 
 */

class BOJ_G4_1797 {
    static List<Integer>[] graph;
    static int[] colors;
    static final int RED = 1;
    static final int BLUE = -1;

    private static boolean bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        colors[start] = RED;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph[cur]) {
                if (colors[next] == 0) {            // 방문하지 않은 정점
                    colors[next] = -colors[cur];    // 다른색으로 칠하기
                    queue.offer(next);
                } else if (colors[next] == colors[cur]) {
                    return false;   // 인접한 정점이 같은 색이면 이분 그래프가 아님
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            graph = new ArrayList[V + 1];
            colors = new int[V + 1];    // 0: 방문x, 1 : RED, -1 : BLUE

            for(int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            for(int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }

            boolean isBipartite = true;
            for(int i = 1; i <= V; i++) {
                if (!isBipartite) break;
                if (colors[i] == 0) {   // 아직 방문하지 않았다면
                    if (!bfs(i)) {
                        isBipartite = false;
                        break;
                    }
                }
            }
            System.out.println((isBipartite ? "YES" : "NO"));
        }
        
    }
}