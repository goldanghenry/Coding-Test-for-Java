package Graph;

import java.io.*;
import java.util.*;

/*
 * BOJ 1504 Gold 4, 특정한 최단 경로
 * https://www.acmicpc.net/problem/1504
 * 최단 경로
 * 1 -> V1 -> V2 -> N 또는 1 -> V2 -> V1 -> N
 * 다익스트라 3번(start, v1, v2) -> 최단 경로 계산
 */

public class BOJ_G4_1504 {
    static final int INF = Integer.MAX_VALUE;
    static int N, E;
    static List<Node>[] graph;
    static long res;

    static class Node implements Comparable<Node> {
        int idx, weight;
        Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }

    }

    static long[] dijkstra(int start) {
        long[] dist = new long[N];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            
            for (Node next: graph[cur.idx]) {
                long newWeight = cur.weight + next.weight;
                if (newWeight < dist[next.idx]) {
                    dist[next.idx] = newWeight;
                    pq.offer(new Node(next.idx, (int)newWeight));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 그래프 입력받기
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b,c));
            graph[b].add(new Node(a,c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken())-1;
        int v2 = Integer.parseInt(st.nextToken())-1;

        long[] base = dijkstra(0);
        long[] v1Base = dijkstra(v1);
        long[] v2Base = dijkstra(v2);

        long path1 = INF;
        long path2 = INF;
        if (base[v1]!=INF && v1Base[v2]!= INF && v2Base[N-1] != INF) {
            path1 = base[v1] + v1Base[v2] + v2Base[N-1];
        }
        if (base[v2]!=INF && v2Base[v1]!= INF && v1Base[N-1] != INF) {
            path2 = base[v2] + v2Base[v1] + v1Base[N-1];
        }

        res = Math.min(path1, path2);
        if (res == INF) System.out.println(-1);
        else System.out.println(res);

    }
}

