package MinimumSpanningTree;

import java.io.*;
import java.util.*;

/*
 * SWEA 3124 D4, 최소 스패닝 트리
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV_mSnmKUckDFAWb
 */

public class SWEA_D4_3124 {
    static int[] parents;
    static Edge[] edgeList;
    static long result;
    static class Edge implements Comparable<Edge> {
        int start, end, weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o1) {
            return Integer.compare(this.weight, o1.weight);
        }
    }
    private static int find(int x) {
        while(x != parents[x]) {
            parents[x] = parents[parents[x]];
            x = parents[x];
        }
        return x;
    }

    private static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return false;
        if (rootX < rootY) parents[rootY] = rootX;
        else parents[rootX] = rootY;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            edgeList = new Edge[E];
            parents = new int[V];

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken())-1;
                int end = Integer.parseInt(st.nextToken())-1;
                int weight = Integer.parseInt(st.nextToken());
                edgeList[i] = new Edge(start, end, weight);
            }
            // V개의 트리 만들기
            for (int i = 0; i < V; i++) {
                parents[i] = i;
            }
            // 가중치로 오름차순 정렬
            Arrays.sort(edgeList);
            int cnt = 0;
            result = 0;
            // 가중치가 작은 간선부터 트리 합치기
            for(Edge edge : edgeList) {
                if (union(edge.start, edge.end)) {
                    result+=edge.weight;
                    if (++cnt == V-1) break;
                }
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }    
}
