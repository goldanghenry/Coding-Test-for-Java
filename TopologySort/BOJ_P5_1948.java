package TopologySort;

import java.io.*;
import java.util.*;

/*
 *  BOJ platinum5 임계경로
 * https://www.acmicpc.net/problem/1948
 * bfs
 */

public class BOJ_P5_1948 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());    // 도시의 개수
        int M = Integer.parseInt(br.readLine());    // 간선의 개수

        ArrayList<int[]>[] graph = new ArrayList[N+1];  // start -> end
        ArrayList<int[]>[] graph2 = new ArrayList[N+1]; // end -> start
        int[] v = new int[N+1];     // 가중치
        int[] inD = new int[N+1];
        int[] rCnt = new int[N+1];   // 도로 개수


        for (int i = 1; i <= N ; i++ ) {
            graph[i] = new ArrayList<>();
            graph2[i] = new ArrayList<>();
        }

        // 그래프 입력받기
        for (int i = 0; i < M ; i++ ) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[s].add(new int[]{e,d});
            graph2[e].add(new int[]{s,d});
            inD[e]++;
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int count =0;
        // bfs
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        v[start] = 0;

        int result = 0;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            
            for (int[] node : graph[cur]) {
                int next = node[0];
                int dist = node[1];
                inD[next]--;

                if (inD[next] == 0) queue.offer(next);
                
                
                if (v[next] < v[cur] + dist) {
                    v[next] = v[cur] + dist;
                }
            }
        }
        int[] visited = new int[N+1];
        visited[end] =1;
        queue.add(end);
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            
            for (int[] node : graph2[cur]) {
                int next = node[0];
                int dist = node[1];
                if(v[cur]-dist ==v[next])
                {
                    count++;
                    if(visited[next] == 0)
                    {
                        queue.add(next);
                        visited[next] = 1;
                    }
                }
            }
        }
        int max = v[end]; // 최장거리

        
        System.out.println(v[end]);
        System.out.println(count);


    }
}

