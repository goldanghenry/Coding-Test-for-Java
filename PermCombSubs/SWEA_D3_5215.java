package PermCombSubs;

import java.util.StringTokenizer;
import java.io.*;

/*
    SWEA 5215 햄버거 다이어트
    https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWT-lPB6dHUDFAVT
    순서 의미x, 중복x -> 조합
 */

public class SWEA_D3_5215 {
    static int N, L, result;
    static int[] scores, cals;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());   // 재료의 수
            L = Integer.parseInt(st.nextToken());   // 제한 칼로리

            scores = new int[N];    // 선호도 배열
            cals = new int[N];      // 칼로리 배열
            result = 0;             // 최대 선호도 결과 초기화

            // 재료 입력 받기
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                scores[i] = Integer.parseInt(st.nextToken());
                cals[i] = Integer.parseInt(st.nextToken());
            }

            // 재료 선택 : 현재 인덱스, 누적 선호도, 누적 칼로리
            dfs(0,0,0);
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int depth, int sumScore, int sumCal) {
        // 칼로리 제한 초과시 가지치기
        if (sumCal > L) return;

        // 기저 조건 : 모든 재료에 대해 결정한 경우 결과 갱신
        result = Math.max(result, sumScore);
        if (depth == N) return;

        // 현재 재료를 선택한 경우
        dfs(depth+1, sumScore+scores[depth], sumCal+cals[depth]);
        // 현재 재료를 선택하지 않은 경우
        dfs(depth+1, sumScore, sumCal);
    }
}
