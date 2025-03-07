package PermCombSubs;

import java.io.*;
import java.util.*;

/*
 * SWEA 1486 D4, 장훈이의 높은 선반
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV2b7Yf6ABcBBASw
 * 부분 집합으로 풀기
 */

public class SWEA_D4_1486 {
    static int N, B, ans;
    static int[] height;

    private static void dfs(int depth, int sum) {       
        if (sum >= B) { //  키의 합이 B를 넘기는가?
            ans = Math.min(ans, sum);
            return;
        }
        
        if (N == depth) return; // 모든 원소를 고려했으므로 종료

        // 현재 원소 선택
        dfs(depth+1, sum+height[depth]);
        // 현재 원소 미선택
        dfs(depth+1, sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            height = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                height[i] = Integer.parseInt(st.nextToken());
            }

            ans = Integer.MAX_VALUE;
            dfs(0,0);
            sb.append("#").append(t).append(" ").append(ans-B).append("\n");
        }
        System.out.println(sb);

    }
    
}
