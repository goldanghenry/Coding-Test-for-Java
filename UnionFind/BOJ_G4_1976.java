package UnionFind;

import java.io.*;
import java.util.*;

/*
 * BOJ Gold4 1976, 여행가자
 * https://www.acmicpc.net/problem/1976
 */

public class BOJ_G4_1976 {
    static int N;
    static int[] parent;

    // Find 연산: 경로 압축 적용
    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    // Union 연산: 두 집합을 합침
    private static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());        // 도시의 수
        int M = Integer.parseInt(br.readLine());    // 여행 계획에 속한 도시 수

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i; // 초기에는 자기 자신을 부모로 설정
        }

        // 인접 행렬 입력 및 Union-Find를 통한 연결 정보 구축
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int isConnected = Integer.parseInt(st.nextToken());
                if (isConnected == 1) {
                    union(i, j);
                }
            }
        }

        // 여행 계획 입력
        st = new StringTokenizer(br.readLine());
        int[] path = new int[M];
        for (int i = 0; i < M; i++) {
            path[i] = Integer.parseInt(st.nextToken()) - 1; // 0-index로 변환
        }

        // 여행 계획에 포함된 모든 도시가 같은 집합에 속하는지 확인
        boolean isPossible = true;
        int root = find(path[0]);
        for (int i = 1; i < M; i++) {
            if (find(path[i]) != root) {
                isPossible = false;
                break;
            }
        }

        System.out.println(isPossible ? "YES" : "NO");
    }
}
