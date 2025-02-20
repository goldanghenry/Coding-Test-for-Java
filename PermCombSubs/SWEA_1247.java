package PermCombSubs;

import java.util.StringTokenizer;
import java.io.*;

/*
 * SWEA 최적경로
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD
 * 회사 - N명의 고객 - 집 순서로 방문
 * 회사와 집은 고정
 * 회사에서 출발하여 N명의 고객을 모두 방문하고 집으로 돌아가는 경로 중 최소 이동거리
 */

public class SWEA_1247 {
    static int N, min;
    static int[] company, home, sel;
    static int[][] customers;
    static boolean[] v;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 테스트 케이스 수
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());    // 고객 수
            min = Integer.MAX_VALUE;                // 최소 이동거리
            v = new boolean[N];                     // 방문 체크
            sel = new int[N];                       // 순열 저장

            // 회사, 집 좌표
            StringTokenizer st = new StringTokenizer(br.readLine());
            company = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            home = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

            // 고객 좌표
            customers = new int[N][2];
            for (int i = 0; i < N; i++) {
                customers[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            }
            
            dfs(0);
            System.out.println("#" + t + " " + min);
        }
    }

    private static void dfs(int depth) {
        if (depth == N) {
           // 회사 -> 첫번째 고객 -> ... -> 마지막 고객 -> 집
           // 이동거리 계산
            int sum = 0;
            sum += Math.abs(company[0] - customers[sel[0]][0]) + Math.abs(company[1] - customers[sel[0]][1]);
            for (int i = 0; i < N-1; i++) {
                sum += Math.abs(customers[sel[i]][0] - customers[sel[i+1]][0]) + Math.abs(customers[sel[i]][1] - customers[sel[i+1]][1]);
            }
            sum += Math.abs(customers[sel[N-1]][0] - home[0]) + Math.abs(customers[sel[N-1]][1] - home[1]);
            min = Math.min(min, sum);
           // 최소값 갱신
            return; 
        }

        for (int i = 0; i < N; i++) {   // N명의 고객
            if (v[i]) continue;
            v[i] = true;
            sel[depth] = i; // 고객 순서 저장
            dfs(depth+1);
            v[i] = false;
        }
    }
}
