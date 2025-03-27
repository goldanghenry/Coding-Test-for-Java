package UnionFind;

import java.io.*;
import java.util.*;

/*
 * JO 1863 Gold5, 종교
 * 같은 종교를 가지는 사람들끼리 짝을 지었을 때, 학생들이 최대한 가질 수 있는 종교의 가지 수
 */

public class JO_G5_1863 {
    static int[]parent;

    private static int find(int x) {
        while(x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    private static void union(int x, int y) {
        int X = find(x);
        int Y = find(y);
        if (X != Y) {
            parent[X] = Y;  // Y를 X의 부모로
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Set<Integer> lst = new HashSet<>();
        int n = Integer.parseInt(st.nextToken());   // 학생 수
        int m = Integer.parseInt(st.nextToken());   // 관계 수

        parent = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;  // 자기 자신을 부모로
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lst.add(a);
            lst.add(b);
            union(a,b);
        }
        
        List<Integer> arr = new ArrayList<>();
        boolean[] v = new boolean[n+1];
        for (int i = 1; i <=n; i++) {
            // if (lst.contains(i)) {
                arr.add(i);
                v[find(i)] = true;
            // }
        }

        int cnt = 0;
        for (int cur : arr) {
            if(v[cur]) cnt++;
        }
        System.out.println(cnt);
    }
}
