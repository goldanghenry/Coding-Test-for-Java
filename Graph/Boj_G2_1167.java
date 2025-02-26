package Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.IOException;

/*
    Boj Gold 2, 트리의 지름
    - https://www.acmicpc.net/problem/1167
    - 트리의 지름이란, 임의의 두 점 사이의 거리 중 가장 긴 것
    - 가중치가 있는 무방향 그래프
    - dfs(bfs) -> 가장 먼 노드와 거리 기록 -> 그 노드에서 한번 더 dfs(bfs) 했을 때의 거리
 */

public class BOJ_G2_1167 {
    static int N;
    static List<int[]>[] tree;     // 여러 개의 List<int[]>가 각각 저장됨
    static boolean[] visited;      // 방문 검사
    static int maxDist = 0;        // 최대 거리
    static int longestNode = 0;    // 임의의 노드에서 가장 먼 노드

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());   // 노드의 개수

        tree = new ArrayList[N+1];          // 트리는 리스트의 배열이기 때문에
        for (int i = 0; i <= N; i++ ) {
            tree[i] = new ArrayList<>();    // 각 리스트는 ArrayList 로 되어 있다.
        }

        // 트리 연결하기
        for ( int i = 0; i < N; i ++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());

            // -1 이 나올 때까지 반복하며 간선 추가
            while(true) {
                int v = Integer.parseInt(st.nextToken());
                if (v == -1) break;
                int w = Integer.parseInt(st.nextToken());
                tree[u].add(new int[]{v, w});    // 각 리스트[u]에 (간선, 가중치) 추가
            }
        }

        // 임의의 노드에서 dfs 시행
        visited = new boolean[N+1];
        dfs(1,0);

        // 가장 먼 노드에서 dfs 시행
        visited = new boolean[N+1];
        maxDist = 0;
        dfs(longestNode, 0);

        // 지름 출력
        System.out.println(maxDist);
    }

    static void dfs(int node, int dist){
        if (dist > maxDist) {
            maxDist = dist;
            longestNode = node;
        }
        visited[node] = true;
        for(int[] next : tree[node]) {
            if (!visited[next[0]]) {
                dfs(next[0], dist+next[1]);
            }
        }
    }
}
