package MinimumSpanningTree;
import java.io.*;
import java.util.*;

/*
 * BOJ 1922 Gold4, 네트워크 연결
 * https://www.acmicpc.net/problem/1922
 */
public class BOJ_G4_1922 {
    static int[] parents;
    static Edge[] edgeList;
    static long result;

    private static class Edge implements Comparable<Edge> {
        int start, end, weight;
        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
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
        if (rootX == rootY) return false; // 사이클 발생
        if (rootX < rootY) parents[rootY] = rootX;
        else parents[rootX] = rootY;
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parents = new int[N];
        edgeList = new Edge[M];
        result = 0;

        for(int i = 0; i < N; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(a,b,w);
        }

        Arrays.sort(edgeList);

        int cnt = 0;
        for (Edge edge : edgeList) {
            if (union(edge.start, edge.end)) {
                result += edge.weight;
                if (++cnt == N-1) break;
            }
        }
        System.out.println(result);
    }
}
