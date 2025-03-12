package Tree;

import java.io.*;
import java.util.*;

/*
 * BOJ silver 2, 트리의 부모 찾기
 * https://www.acmicpc.net/problem/11725
 */

public class BOJ_S2_11725 {
    static int[] v;
    static List<Integer>[] graph;

    private static void dfs(int cur) {
        for(int next : graph[cur]) {
            if(v[next] == 0) {
            v[next] = cur;
            dfs(next);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(e);
            graph[e].add(s);
        }

        v = new int[N+1];
        v[1] = -1;
        dfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(v[i]).append("\n");
        }
        System.out.print(sb.toString());
    }
}
