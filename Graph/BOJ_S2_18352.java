package Graph;

import java.io.*;
import java.util.*;

/*
 * BOJ silver2 18352 특정 거리의 도시 찾기
 * https://www.acmicpc.net/problem/18352
 * BFS
 */

public class BOJ_S2_18352 {     
    static int N, M, K, X;
    static List<Integer>[] adj;
    static int[] dist;
    static boolean[] visited;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 도시의 개수
        M = Integer.parseInt(st.nextToken());   // 도로의 개수
        K = Integer.parseInt(st.nextToken());   // 거리 정보
        X = Integer.parseInt(st.nextToken());   // 도시의 번호

        adj = new ArrayList[N+1];   // 인접 리스트
        dist = new int[N+1];        // 거리 정보
        visited = new boolean[N+1]; // 방문 여부

        for(int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
        }

        bfs(X);
    }

    static void bfs(int start) {
        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int next : adj[cur]) {
                if(!visited[next]) {
                    q.offer(next);
                    visited[next] = true;
                    dist[next] = dist[cur] + 1;
                }
            }
        }

        boolean flag = false;
        for(int i = 1; i <= N; i++) {
            if(dist[i] == K) {
                System.out.println(i);
                flag = true;
            }
        }

        if(!flag) {
            System.out.println(-1);
        }
    }
    
}
