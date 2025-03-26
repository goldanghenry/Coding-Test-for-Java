package UnionFind;

import java.io.*;
import java.util.*;

/*
 * SWEA 3289 D4, 서로소 집합
 * https://swexpertacademy.com/main/solvingProblem/solvingProblem.do
 */

public class SWEA_D4_3289 {
    static int[] parent;
    private static void union(int x, int y){
        int X = find(x);
        int Y = find(y);

        if (X != Y) {
            parent[X] = Y;
        }

    }
    private static int find(int x) {
        while(x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            // 초기 셋팅
            parent = new int[n+1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
            sb.append("#").append(t).append(" ");

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (cmd == 0) {
                    union(a, b);
                } else {
                    sb.append(find(a)==find(b)?"1":"0");
                }
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

