package Basic;

import java.util.ArrayList;
import java.util.List;

class DFS {
    public static List<List<Integer>> graph;
    public static boolean[] visited;

    public static void dfs(int node) {
        visited[node] = true;
        System.out.print(node+" ");
        for (int next : graph.get(node)) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

    public static void main(String[] args) {
        int N = 5;
        graph = new ArrayList<>();
        visited = new boolean[N+1];

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(1).add(2);
        graph.get(1).add(3);
        graph.get(2).add(1);
        graph.get(2).add(4);
        graph.get(3).add(1);
        graph.get(4).add(2);

        dfs(1);

    }
}