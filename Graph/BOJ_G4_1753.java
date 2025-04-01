package Graph;

import java.io.*;
import java.util.*;

/*
 * BOJ Gold 4 1753, 최단경로
 * https://www.acmicpc.net/problem/1753
 * 
 * 다익스트라 알고리즘
 * 음의 가중치가 없는 그래프에서 한 시작 노드로부터 다른 모든 노드까지의 최단 경로를 찾는 알고리즘
 * 우선순위 큐를 사용해, 매 단계마다 현재까지의 최단 거리가 가장 짧은 노드를 선택하고
 * 그 노드를 기준으로 인접한 노드들의 거리를 갱신하는 방식
 */

public class BOJ_G4_1753 {
    private static class Edge {
        int target, weight;

        public Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static int[] dijkstra(List<Edge>[] graph, int start) {
        int n = graph.length;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // 노드 번호, 시작 노드로부터의 누적거리를 저장하는 우선 순위 큐
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.target));
        pq.offer(new Edge(start, 0));

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            int curNode = cur.target;
            int curDist = cur.weight;

            // 기존 경로가 더 짧다면 넘어감
            if (curDist > dist[curNode]) continue;

            // 현재 노드와 인접한 모든 노드에 대해 거리 갱신 시도
            for (Edge edge : graph[curNode]) {
                int nextNode = edge.target;
                int newDist = curDist + edge.weight;

                if (newDist < dist[nextNode]) { // 새로운 경로가 더 짧은 경우
                    dist[nextNode] = newDist;
                    pq.offer(new Edge(nextNode, newDist));
                }
            }
        }
        return dist;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        List<Edge>[] graph = new ArrayList[V+1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[s].add(new Edge(e,d));
        }

        int[] res = dijkstra(graph, start);
        for (int i = 1; i <= V; i++) {
            if (res[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(res[i]);
        }

    }
}
