package Graph;

import java.io.*;
import java.util.*;
/*
 * SWEA EX 2383. 점심 식사시간
 * 이동 시간 : 모든 사람들이 계단을 내려가 아래 층으로 이동을 완료한 시간
 * 계단 입구까지 이동 시간 : 유클리디안
 * 계단을 내려가는 시간 : 1분 후 한칸 씩, K 칸
 * 계단 위는 동시에 최대 3명까지, 입구는 상관 없음
 * 순열 - 백트래킹
 * 기저조건 -> min(출구별 시간)
 */

public class SWEA_EX_2383 {
    static int idx, pCnt;
    static int[] type, sel,s1,s2;
    static int[][] map, list;
    static boolean[] v;
    private static void dfs(int depth) {
        // 기저 조건
        if (depth == idx) {

        }

        // 가지치기

        // 실행
        
        
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t<=T; t++) {
            int N = Integer.parseInt(br.readLine());
            
            map = new int[N][N];
            list = new int[12][2];    // 사람 + 계단 좌표
            type = new int[12];       // 사람 : 1, 계단의 길이 저장
            idx = 0;
            s1 = new int[]{0,0,0};

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] > 0) {
                        // 사람인 경우
                        if (map[i][j] == 1) list[idx++] = new int[]{i,j};
                        // 계단인 경우
                        else {
                            if (s1[0] == 0) s1 = new int[]{map[i][j], i, j};
                            else s2 = new int[]{map[i][j], i, j};
                        }
                    }
                }
            }

            // 백트래킹
            sel = new int[idx];
            v = new boolean[idx];
            dfs(0);

            

        }

    }
}
