package Basic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BFS {
    static List<List<Integer>> graph;
    static boolean[] visited;

    public static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;

        while( !queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int next : graph.get(node)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        int N = 5;
        graph = new ArrayList<>();
        visited = new boolean[N+1];

        for (int i = 0; i < N+1; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(0);
    }


}
