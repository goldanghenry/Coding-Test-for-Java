package Basic;

import java.io.*;
import java.util.*;

public class MST_Kruskal {
    static class Edge implements Comparable<Edge>{
        int start, end, weight;
        public Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static Edge[] edgeList;
    static int[] parents;
    static int V, E;

    static void make() {
        parents = new int[V];
        for (int i = 0; i < V; i++) {
            parents[i] = i;
        }
    }

    static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false; // 사이클 발생
        // 랜덤 요소를 가미해 한쪽으로 치우쳐지지 않도록 보정
        if (aRoot > bRoot) parents[bRoot] = aRoot;  
        else parents[aRoot] = bRoot;
            
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgeList = new Edge[E];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(start, end, weight); // 무향이더라도 뒤집지 않아도 된다. 의미만
        }

        make();     // v개의 독립된 트리 생성
        Arrays.sort(edgeList);  // 간선의 가중치를 기준으로 정렬
        int result = 0, count = 0;
        for (Edge edge : edgeList) {
            if (union(edge.start, edge.end)) {  // 두 정점이 속한 각 트리 합치기
                result += edge.weight;
                if (++count == V-1) {
                    break;
                }
            }
        }
        System.out.println(result);
    }
}
