package Graph;

import java.io.*;
import java.util.*;

/*
 * BOJ silver 2, 촌수계산
 * https://www.acmicpc.net/problem/2644
 */

public class BOJ_S2_2644 {
    static int a,b;
    static List<Integer>[] graph;
    static int[] v;

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        v[start] = 0;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            
            if (cur == b) {
                System.out.println(v[cur]);
                return;
            }
            for (int next : graph[cur]) {
                if (v[next] > 0) continue;
                v[next] = v[cur]+1;
                queue.offer(next);
            }
        }

        System.out.println(-1);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 전체 사람 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());   // target a
        b = Integer.parseInt(st.nextToken());   // target b
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());   // 관계 수

        graph = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            graph[y].add(x);
        }

        v = new int[N+1];
        bfs(a);
    }
}
