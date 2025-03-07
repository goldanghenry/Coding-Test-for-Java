package Graph;

import java.io.*;
import java.util.*;

/*
 * SWEA 7733 D4, 치즈 도둑
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWrDOdQqRCUDFARG
 */

public class SWEA_D4_7733 {
    static int N, day, result;      // 맵의 크기, 날짜, 결과
    static int[][] map;             // 치즈 맛있는 정도 기록 맵
    static boolean[][] v;           // 방문 체크
    static int[] dx = {-1,1,0,0};   // 상하좌우
    static int[] dy = {0,0,-1,1};

    private static void bfs(int i, int j) {
        Queue<int[]>queue = new LinkedList<>();
        queue.offer(new int[]{i,j});
        v[i][j] = true;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int k = 0; k < 4; k++) {   // 상하좌우
                int nx = cur[0] + dx[k];
                int ny = cur[1] + dy[k];
                
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;   // 맵을 벗어나는가
                if (v[nx][ny] || map[nx][ny] <= day) continue;          // 요정이 먹었는가
                queue.offer(new int[]{nx,ny});
                v[nx][ny] = true;
            }
        }
        
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 테스트 케이스
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            N = Integer.parseInt(br.readLine());    // 맵의 크기 입력
            map = new int[N][N];                    // 맵 초기화

            // 맵 입력
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 100일간 진행
            result = 0;
            for (int x = 0; x <= 100; x++) {
                day = x;
                v = new boolean[N][N];
                int cnt = 0;

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (v[i][j] || map[i][j] <= day) continue;
                        bfs(i,j);
                        cnt++;
                    }
                }
                result = Math.max(result, cnt);
            }

            System.out.println("#"+t+" "+result);
        }
        
    }
}
