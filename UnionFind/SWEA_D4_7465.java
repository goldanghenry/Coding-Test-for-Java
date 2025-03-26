package UnionFind;
import java.io.*;
import java.util.*;
/*
 * SWEA 7456 D4, 창용 마을 무리의 개수
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWngfZVa9XwDFAQU
 */

public class SWEA_D4_7465 {
    static int[] parent;
    private static int find(int x) {
        while(x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
    
    private static void union(int a, int b) {
        int A = find(a);
        int B = find(b);
        if (A != B) {
            parent[A] = B;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            parent = new int[N+1];
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a,b);
            }

            boolean[] v = new boolean[N+1];
            for (int i = 1; i <= N; i++) {
                v[find(i)] = true;
            }
            // System.out.println(Arrays.toString(parent));
            // System.out.println(Arrays.toString(v));

            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                if (v[i]) cnt++;
            }

            System.out.println("#"+t+" "+cnt);
        }
    }
}
