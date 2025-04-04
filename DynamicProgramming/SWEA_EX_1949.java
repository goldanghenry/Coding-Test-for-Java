package DynamicProgramming;
/*
 * SWEA 1949, 등산로 조성
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PoOKKAPIDFAUq
 * 가장 높은 봉우리에서 시작
 * 높은 지형 -> 낮은 지형 -> 상하좌우
 * 한 곳을 최대 k 깊이 만큼 깍을 수 있다.
 */

import java.io.*;
import java.util.*;

public class SWEA_EX_1949 {
    static int N,M;
    static int[][] map;
    static boolean [][]v;
    static int longestLen, maxHeight;
    static List<int[]> startList;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};


    private static void dfs(int x, int y, boolean isDig, int depth, int curHeight) {
        longestLen = Math.max(longestLen, depth);

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if (v[nx][ny]) continue;

            if (map[nx][ny] < curHeight) {
                v[nx][ny] = true;
                dfs(nx, ny, isDig, depth + 1, map[nx][ny]);
                v[nx][ny] = false;
            }   
            else if (!isDig && map[nx][ny] - M < curHeight) {
                int original = map[nx][ny];
                map[nx][ny] = curHeight - 1; // 깎아서 이동 가능하게 만들기
                v[nx][ny] = true;
                dfs(nx, ny, true, depth + 1, map[nx][ny]);
                v[nx][ny] = false;
                map[nx][ny] = original; // 원상복구
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            int maxHeight = -1;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    map[i][j] = num;
                    if (maxHeight < num) {
                        startList = new ArrayList<>();
                        startList.add(new int[]{i,j});
                        maxHeight = num;
                    } else if (maxHeight == num) startList.add(new int[]{i,j});
                }
            }

            longestLen = -1;
            boolean isDig = false;
            for ( int[] target : startList ){
                v = new boolean[N][N];
                v[target[0]][target[1]] = true;
                dfs(target[0],target[1],isDig, 1, maxHeight);
            }
            sb.append("#").append(t).append(" ").append(longestLen).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}
