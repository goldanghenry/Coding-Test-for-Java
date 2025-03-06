package UnionFind;

import java.io.*;
import java.util.*;

/*
 * BOJ Gold5 1717, 집합의 표현
 * https://www.acmicpc.net/problem/1717
 */

public class BOJ_G5_1717 {
    static int[] parent;

    // 부모를 찾을 때까지
    private static int find(int x) {
        while(x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    // 집합 합치기
    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            if (x < y) parent[y] = x;
            else parent[x] = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());   // 명령의 수

        // 기본 셋팅
        parent = new int[N+1];
        for (int i =1; i <= N; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd == 0) union(a,b);
            else if (cmd == 1) {
                if (find(a) == find(b)) System.out.println("YES");
                else System.out.println("NO");
            }
        }
    }
}
