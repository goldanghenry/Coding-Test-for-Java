package Basic;

import java.io.*;
import java.util.*;

/*
 * Prim 알고리즘 with PriorityQueue
 */

public class Prim_with_pq {

    static class Vertex implements Comparable<Vertex> {
        int no, weight;
        public Vertex(int np, int weight) {
            super();
            this.no = no;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.weight, o.weight);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        int[][] adjMatrix = new int[V][V];  // 인접 행렬
        boolean[] visited = new boolean[V]; // 트리 정점
        int[] minEdge = new int[V];         // 트리에 속한 타정점들과 자신(비트리)과의 최소간선비용

        // 인접행렬 입력
        StringTokenizer st;
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < V; j++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //step 0 : minEdge 배열 최대값으로 초기화 후 임의의 시작 정점이 트리에 속하는 시작정점이 되게 만들기
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(); // 비트리 정점중 최소 간선 비용의 장점을 찾기 위한 목적

        int result = 0, cnt = 0; // mst 비용, 트리에 추가된 정점 수
        minEdge[0] = 0; // 루틴에 의해 정점이 선택되도록 처리
        pq.offer(new Vertex(0, 0));
        
        while(!pq.isEmpty()) {
            Vertex minVertex = pq.poll();   // 힙의 루트를 가져옴(최소 가중치)

            if (visited[minVertex.no]) continue;

            result += minVertex.weight;
            visited[minVertex.no] = true;
            if(++cnt == V) break; 

            // step 2 : 새롭게 확장된 트리 정점과 나머지 비트리 정점간의 최소간선 비교 후 업데이트
            for (int i = 0; i < V; i ++) {
                if (!visited[i]                                     // 비트리 정점 확인
                        && adjMatrix[minVertex.no][i] != 0             // 인접여부 확인
                        && minEdge[i] > adjMatrix[minVertex.no][i]) {  // 최소간선 비용 확인
                    minEdge[i] = adjMatrix[minVertex.no][i];
                    pq.offer(new Vertex(i, minEdge[i]));
                }
            }
        }
        System.out.println(cnt==V ? result: -1); // 최소 신장 트리의 비용
    }
}