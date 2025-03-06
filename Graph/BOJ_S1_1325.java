package Graph;

import java.io.*;
import java.util.*;

/*
 * BOJ silver 1 1325 효율적인 해킹
 * https://www.acmicpc.net/problem/1325
 */

class BOJ_S1_1325 {
    static int N, M;
    static int[] ans;
    static List<Integer>[] adj;
    static boolean[] visited;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());   // 컴퓨터의 수
        M = Integer.parseInt(st.nextToken());   // 관계의 수

        adj = new ArrayList[N+1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[b].add(a);  // a -> b를 신뢰한다면, b -> a 방문 가능
        }

        int maxValue = 0;
        ans = new int[N+1];
        for (int i = 1; i <= N; i++) {
            int cnt = bfs(i);
            ans[i] = cnt;
            maxValue = Math.max(maxValue, cnt);
        }

        // 출력
        for (int i = 1; i <= N; i++) {
            if (ans[i] == maxValue) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb);
    }

    private static int bfs(int start) {
        q = new LinkedList<>();
        visited = new boolean[N+1];
        q.offer(start);
        visited[start] = true;
        
        int cnt = 0;
        while(!q.isEmpty()) {
            int cur = q.poll();
            cnt++;
            for (int next : adj[cur]) {
                if (visited[next]) continue;
                q.offer(next);
                visited[next] = true;
            }
        }
        return cnt;
    }
}