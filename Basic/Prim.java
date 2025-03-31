package Basic;

import java.io.*;
import java.util.*;

/*
 * Prim 알고리즘
 */

public class Prim {
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
        int result = 0; // mst 비용
        minEdge[0] = 0; // 루틴에 의해 정점이 선택되도록 처리
        
        int c;  // for문 밖에서 사용하기 위해 외부에 선언언
        for (c = 0; c < V; c++) {
            // step 1 : 비트리 정점 중, 트리에 속할 가장 유리한(간선의 비용이 적게 드는) 정점 찾기
            // 추후, priority queue로 관리리
            int min = Integer.MAX_VALUE;
            int minVertex = -1;
            for (int i = 0; i < V; i++) {
                if (!visited[i] && min > minEdge[i]) {
                    min = minEdge[i];
                    minVertex = i;
                }
            }

            if (minVertex == -1) break;

            result += min;              // 트리 가중치의 합에 더하기
            visited[minVertex] = true;  // 트리에 속한 정점으로 만들기

            // step 2 : 새롭게 확장된 트리 정점과 나머지 비트리 정점간의 최소간선 비교 후 업데이트
            for (int i = 0; i < V; i ++) {
                if (!visited[i]                                     // 비트리 정점 확인
                        && adjMatrix[minVertex][i] != 0             // 인접여부 확인
                        && minEdge[i] > adjMatrix[minVertex][i]) {  // 최소간선 비용 확인
                    minEdge[i] = adjMatrix[minVertex][i];
                }
            }
        }
        System.out.println(c==V ? result: -1); // 최소 신장 트리의 비용
    }
}