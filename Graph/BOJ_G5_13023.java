package Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
    Boj Gold 5, ABCDE
    https://www.acmicpc.net/problem/13023
    5개의 연속된 노드가 있는지 탐색 -> 깊이가 5인 연결이 있는가?
    => dfs
 */

public class BOJ_G5_13023 {
    static List<List<Integer>> graph;
    static boolean[] visited;
    static boolean found = false;

    static void dfs(int node, int depth) {
        if (depth == 5) {
            found = true;
            return;
        }
        visited[node] = true;
        for (int next : graph.get(node)) {
            if (!visited[next]) {
                dfs(next, depth + 1);
                if (found) return;  // 조기 종료
            }
        }
        visited[node] = false;  // 백트래킹
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 친구 수, 관계 수 입력
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 그래프 생성
        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        // 방문 배열 초기화
        visited = new boolean[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        // dfs 탐색 시작, 깊이가 5라면 1 출력
        for ( int i = 0; i < N; i++) {
            dfs(i,1);
        }
        System.out.println(found ? 1: 0);
    }
}
