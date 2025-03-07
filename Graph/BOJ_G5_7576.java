package Graph;

import java.io.*;
import java.util.*;

/*
 * BOJ Gold 5, 토마토
 * http://acmicpc.net/problem/7576
 * N * M
 * 익은 토마토 / 익지 않은 토마토
 * 하루 뒤, 익은 토마토 -> 인근(사방위) 익지 않은 토마토 익힘
 * 몇 일이 지나면 모두 다 익는가?
 */

public class BOJ_G5_7576 {
    static Queue<int[]> startQ = new LinkedList<>();
    static int N,M, day, yet;
    static int[][]map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    private static void bfs() {
        Queue<int[]>queue = new LinkedList<>();
        
        while(!startQ.isEmpty()) {
            queue = startQ;
            startQ = new LinkedList<>();    // 초기화
            
            while(!queue.isEmpty()) {
                int[] cur = queue.poll();

                for(int k = 0; k < 4; k++) {
                    int nx = cur[0] + dx[k];
                    int ny = cur[1] + dy[k];

                    if(nx < 0 || ny <0 || nx >= N || ny >= M) continue; // 맵을 벗어나는 경우
                    if(map[nx][ny] > 0 || map[nx][ny] == -1) continue;

                    map[nx][ny] = map[cur[0]][cur[1]]+1;    // 방문
                    yet--;                                  // 안 익은 개수 감수
                    startQ.offer(new int[]{nx,ny});
                }
            }
            day++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        yet = 0;
        map = new int[N][M];
        
        day = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) startQ.offer(new int[]{i,j});
                else if (map[i][j]== 0) yet++;
            }
        }
        
        if (yet == 0) System.out.println(0);
        else {
            bfs();
            if (yet == 0) System.out.println(day-1);
            else System.out.println(-1);
            
        }
    }
}
