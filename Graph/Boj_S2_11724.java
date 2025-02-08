package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
    연결 요소의 개수
    https://www.acmicpc.net/problem/11724
 */

public class Boj_S2_11724 {
    static List<List<Integer>> graph;
    static boolean[] visited;

    public static void dfs(int node) {
        visited[node] = true;

        for( int next : graph.get(node)) {
            if(!visited[next]) {
                dfs(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 노드의 수
        int M = Integer.parseInt(st.nextToken());   // 간선의 수

        graph = new ArrayList<>();
        visited = new boolean[N + 1];

        // 그래프 입력
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        // 실수 : 무방향 그래프이므로 반대편 간선도 연결해야함
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        // connected component 확인
        int connectedComponents = 0;
        for ( int i = 1; i <= N; i++) {
            if (!visited[i]) {
                connectedComponents++;
                dfs(i);
            }
        }
        System.out.println(connectedComponents);
    }
}
