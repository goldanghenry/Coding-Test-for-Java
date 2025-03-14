package Graph;

import java.io.*;
import java.util.*;

/*
 * BOJ 17471, 게리맨더링 (Gold 3)
 * https://www.acmicpc.net/problem/17471
 */

public class BOJ_G3_17471 {
    static int N, minDiff;
    static int[] populations, sel;
    static List<Integer>[] graph;
    static boolean[] visited;

    private static boolean bfs(List<Integer> list) {
        Queue<Integer> queue = new LinkedList<>();
        int start = list.get(0);
        queue.offer(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph[cur]) {
                if (visited[next] || !list.contains(next)) continue; // 해당 지역구만 방문
                visited[next] = true;
                queue.offer(next);
            }
        }

        // 모두 방문 되었는지 검사
        for(int i = 0; i < list.size(); i++) {
            if (!visited[list.get(i)]) return false;
        }
        return true;
    }

    private static void dfs(int depth) {
        // 기저조건 -> 모두 정함
        if (depth > N) {
            // A, B로 나누기
            List<Integer> A = new ArrayList<>();
            List<Integer> B = new ArrayList<>();
            for(int i = 1; i <= N; i++) {
                if (sel[i] == 0) A.add(i);
                else B.add(i);
            }

            // 소속 지역이 0인 소속구가 있다면 종료
            if ( A.size() == 0 || B.size() == 0) return;

            // bfs로 해당 선거구 내 모두 연결되었는지 확인
            // 한 선거구라도 연결되지 않으면 종료
            visited = new boolean[N+1];
            if (!bfs(A) || !bfs(B)) return; 

            // 각 선거구 인구 차이 계산
            int a = 0, b = 0;
            for (int i = 1; i <= N; i++) {
                if (sel[i] == 0) a += populations[i];
                else b += populations[i];
            }

            minDiff = Math.min(minDiff, Math.abs(a-b));
            return;
        }

        // 선거구 선택, 0 : A, 1 : B
        sel[depth] = 0;
        dfs(depth+1);

        sel[depth] = 1;
        dfs(depth+1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        minDiff = 1001;
        populations = new int[N+1];
        graph = new ArrayList[N+1];
        sel = new int[N+1];

        // 그래프 초기화 및 인구수 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            populations[i] = Integer.parseInt(st.nextToken());
        }

        // 연결 리스트 입력받기
        for (int s = 1; s <= N; s++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int c = 0; c < cnt; c++) {
                int e = Integer.parseInt(st.nextToken());
                graph[s].add(e);    // 간선 연결
                graph[e].add(s);
            }            
        }

        // 조합으로 소속의 경우의 수를 나누고
        // bfs를 통해 그래프가 연결되었는지 확인 후
        // 선거구의 인구차이 구하기
        dfs(1); // 1번부터 시작

        if (minDiff == 1001) System.out.println(-1);
        else System.out.println(minDiff);
    }
}
