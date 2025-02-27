package Graph;

import java.io.*;
import java.util.StringTokenizer;

/*
 * SWEA 1952 모의 역량테스트, 수영장
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq
 * 1년 이용권을 기본으로 두고, 각 달에 대해 1일, 1달, 3달 이용권 백트래킹
 */

public class SWEA_EX_1952 {
    static int result;
    static int[] tickets, attends;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            st = new StringTokenizer(br.readLine());
            tickets = new int[4];
            for (int i = 0; i < 4; i++) {
                tickets[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            attends = new int[12];
            for (int i = 0; i <12; i++) {
                attends[i] = Integer.parseInt(st.nextToken());
            }

            // dfs
            result = tickets[3]; // 1년 이용권을 기본으로
            dfs(0,0);

            System.out.println("#"+t+" "+result);
        }
    }

    private static void dfs(int depth, int cost) {
        // 기저 조건
        if (depth > 12) {
            result = Math.min(result, cost);
            return;
        }

        // 백트래킹
        if (cost >= result) return;

        // 1일 이용권
        dfs(depth+1, cost+tickets[0]*attends[depth]);

        // 1달 이용권
        dfs(depth+1, cost + tickets[1]);

        // 3달 이용권
        dfs(depth+3, cost + tickets[2]);
    }
}
