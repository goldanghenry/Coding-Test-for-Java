package MinimumSpanningTree;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * SWEA 1251 D4, 하나로
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD
 * N개의 섬들을 모두 연결하는 시스템
 * 가중치 : 환경부담금 * 터널 길이^2
 */

public class SWEA_D4_1251 {
    static long result;
    static int[] parents;
    static Edge[] edgeList;
    static Node[] nodeList;
    
    static class Node {
        int x, y, no;
        public Node(int x, int y, int no) {
            this.x = x;
            this.y = y;
            this.no = no;
        }
    }

    static class Edge implements Comparable<Edge> {
        Node start, end;
        double weight;
        public Edge(Node start, Node end, double weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }

    static private boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return false;   // 사이클 발생
        if (rootX < rootY) parents[rootY] = rootX;
        else parents[rootX] = rootY;
        return true;
    }

    static private int find(int x) {
        while(x != parents[x]) {
            parents[x] = parents[parents[x]];
            x = parents[x];
        }
        return x;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());    // 섬의 개수
            int M = (N*(N-1)) /2;   // 완전 그래프의 간선 개수

            parents = new int[N];
            nodeList = new Node[N];
            edgeList = new Edge[M];
            result = 0L;

            // 노드 입력 받기
            StringTokenizer PointsX = new StringTokenizer(br.readLine());
            StringTokenizer PointsY = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(PointsX.nextToken());
                int y = Integer.parseInt(PointsY.nextToken());
                nodeList[i] = new Node(x,y,i);
            }
            double E = Double.parseDouble(br.readLine());   // 환경 부담 세율

            // 간선 저장하기
            int idx = 0;
            for (int i = 0; i < N; i++) {
                int x1 = nodeList[i].x;
                int y1 = nodeList[i].y;
                for (int j = i+1; j < N; j++) {
                    int x2 = nodeList[j].x;
                    int y2 = nodeList[j].y;
                    double dist = Math.pow(( x1 - x2),2)+Math.pow((y1 - y2),2);
                    edgeList[idx++] = new Edge(nodeList[i], nodeList[j], dist);
                }
            }

            // 간선의 가중치 오름차순 정렬
            Arrays.sort(edgeList);

            // 트리 생성
            parents = new int[N];
            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }

            int cnt = 0;
            for (Edge edge : edgeList) {
                if (union(edge.start.no, edge.end.no)) {
                    result += edge.weight;
                    if (++cnt == N-1) break;
                }
            }
            sb.append("#").append(t).append(" ").append(Math.round(result*E)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}