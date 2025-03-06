package Graph;

import java.io.*;
import java.util.*;

/*
 * BOJ silver 2, 섬의 개수
 * https://www.acmicpc.net/problem/4963
 */

public class BOJ_S2_4963 {
    static int w,h;
    static int[][] graph;
    static boolean[][] v;
    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy = {-1,0,1,-1,1,-1,0,1};

    private static void bfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i,j});
        v[i][j] = true;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            
            for(int l = 0; l <8; l++) {
                int nx = x + dx[l];
                int ny = y + dy[l];

                if (nx <0 || ny <0 || nx >= h || ny >= w) continue;
                if (v[nx][ny] || graph[nx][ny] == 0) continue;

                queue.offer(new int[]{nx,ny});
                v[nx][ny] = true;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) { 
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) break;

            graph = new int[h][w];
            v = new boolean[h][w];

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int cnt = 0;
            for(int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (v[i][j] || graph[i][j] == 0) continue;
                    bfs(i,j);
                    cnt++;
                }
            }

            sb.append(cnt).append("\n");
            

        }
        System.out.print(sb);
    }
}
