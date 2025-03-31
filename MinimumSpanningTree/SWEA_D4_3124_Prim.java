package MinimumSpanningTree;

import java.io.*;
import java.util.*;

/*
 * SWEA 3124 D4, 최소 스패닝 트리
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV_mSnmKUckDFAWb
 */

public class SWEA_D4_3124_Prim {

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

            List<int[]>[] adjList = new ArrayList[V];
            boolean[] visited = new boolean[V];
            PriorityQueue<int[]> minEdge = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            long result = 0;
            int cnt = 0; // MST에 포함된 정점 수

            for (int i = 0; i < V; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken())-1;
                int end = Integer.parseInt(st.nextToken())-1;
                int weight = Integer.parseInt(st.nextToken());

                adjList[start].add(new int[]{end,weight});
                adjList[end].add(new int[]{start,weight});
            }
            
            // step 1 : 임의의 시작 정점(0번)을 트리에 추가
            minEdge.offer(new int[]{0,0});

            // step 2 : prim's Algorithm 실행
            while(!minEdge.isEmpty() && cnt < V) {
                int[] cur = minEdge.poll(); // 
                int v = cur[0]; // 해당 정점
                int w = cur[1]; // 해당 간선의 가중치

                if (visited[v]) continue;

                visited[v] = true;  // 이미 방문한 정점이면 스킵
                result += w;        // 가중치 추가
                cnt++;

                // 현재 정점(v)과 연결된 모든 간선을 우선순위 큐에 추가
                for (int[] next : adjList[v]) {
                    if (!visited[next[0]]) {
                        minEdge.offer(new int[]{next[0], next[1]});
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }    
}

