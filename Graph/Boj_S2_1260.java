package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    Boj Silver 2, DFS와 BFS
    https://www.acmicpc.net/problem/1260
 */

public class BOJ_S2_1260 {
    static List<List<Integer>> graph;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    static void dfs(int node) {
        visited[node] = true;
        sb.append(node).append(" ");

        for( int next : graph.get(node)) {
            if(!visited[next]) {
                dfs(next);
            }
        }
    }

    static void bfs(int node) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(node);
        visited[node] = true;
        sb.append(node).append(" ");

        while(!queue.isEmpty()) {
            int current = queue.poll();

            for(int next : graph.get(current)) {
                if(!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    sb.append(next).append(" ");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        visited = new boolean[N+1];

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (List<Integer> list : graph) {
            Collections.sort(list);
        }

        dfs(V);
        System.out.println(sb.toString().trim());

        // 초기화
        visited = new boolean[N+1];
        sb = new StringBuilder();

        bfs(V);
        System.out.println(sb.toString().trim());
    }
}
