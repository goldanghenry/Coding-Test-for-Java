package Graph;

import java.io.*;
import java.util.*;

/*
 * BOJ 1854 Platinum 4, K번째 최단경로 찾기
 * https://www.acmicpc.net/problem/1854
 */

public class BOJ_P4_1854 {
    private static class Edge {
        int target, weight;
        public Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    private static void dijkstra(List<Edge>[] graph, int start, int k) {
        int n = graph.length;
        PriorityQueue<Integer>[] dist = new PriorityQueue[n];   // 3개를 저장

        for(int i = 1; i < n; i++) {
            dist[i] = new PriorityQueue<>(Comparator.reverseOrder());   // k번째까지 저장하는 큐
        }
        dist[1].offer(0);   // 초기화

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));    // 다익스트라를 위한 우선순위 큐
        pq.offer(new int[]{start,0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curDist = cur[1];

            for(Edge next : graph[curNode]) {
                int nextNode = next.target;
                int nextDist = next.weight + curDist;

                if (dist[nextNode].size() == k) {   // 큐의 크기가 k와 같다면
                    if (nextDist < dist[nextNode].peek()) { // 큐의 탑과 비교해서 새로운 거리가 작다면
                        dist[nextNode].poll();
                        dist[nextNode].offer(nextDist);
                        pq.offer(new int[]{nextNode, nextDist});
                    }
                }
                else {
                    dist[nextNode].offer(nextDist);
                    pq.offer(new int[]{nextNode, nextDist});
                }
                
            }
        }

        for( int i = 1; i < n; i++){
            if( dist[i].size() == k ) {
                System.out.println(dist[i].poll());
            }
            else System.out.println(-1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[s].add(new Edge(e,d));
        }

        dijkstra(graph, 1, k);
    }
}
