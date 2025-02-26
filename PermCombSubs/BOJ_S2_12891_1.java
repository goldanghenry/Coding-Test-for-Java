package PermCombSubs;

import java.io.*;
import java.util.StringTokenizer;

/*
    Boj 12891 silver 2, DNA 비밀번호
    https://www.acmicpc.net/problem/12891
    부분 집합
 */

public class BOJ_S2_12891_1 {
    static int S, P, result;    // DNA 문자열의 길이, 비밀번호로 사용할 부분 문자열의 길이
    static char[] inputs;       // DNA 문자열
    static int[] std;           // 최소 사용 기준

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        inputs = new char[S];
        std = new int[4];
        result = 0;

        st = new StringTokenizer(br.readLine());
        inputs = st.nextToken().toCharArray();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            std[i] = Integer.parseInt(st.nextToken());
        }

        // dfs
        dfs(0,0,0,0,0,0);

        System.out.println(result);
    }
    // 현재 재귀 깊이, 현재까지 사용한 A, C, G, T, 총길이
    private static void dfs(int depth, int aCnt, int cCnt, int gCnt, int tCnt,int len) {
        // 가지치기 조건
        if (len > P) return;

        // 기저 조건
        if (depth == S) {
            if (len < P) return;
            if (aCnt < std[0] || cCnt < std[1] || gCnt < std[2] || tCnt < std[3]) return;
            result++;
            return;
        }

        // 선택한 경우
        if (inputs[depth] == 'A') dfs(depth+1, aCnt+1, cCnt, gCnt, tCnt, len+1);
        else if (inputs[depth] == 'C') dfs(depth+1, aCnt, cCnt+1, gCnt, tCnt, len+1);
        else if (inputs[depth] == 'G') dfs(depth+1, aCnt, cCnt, gCnt+1, tCnt, len+1);
        else dfs(depth+1, aCnt, cCnt, gCnt, tCnt+1, len+1);

        // 선택하지 않는 경우
        dfs(depth+1, aCnt, cCnt, gCnt, tCnt, len);
    }
}
