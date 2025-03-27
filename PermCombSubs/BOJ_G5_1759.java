package PermCombSubs;

import java.io.*;
import java.util.*;

/*
 * BOJ 1758 Gold 5, 암호 만들기
 * https://www.acmicpc.net/problem/1759
 * L 개의 알파벳 소문자
 * 최소 한 개의 모음(a,e,i,o,u)
 * 최소 두 개의 자음
 * 오름차순 -> 조합 문제
 */

public class BOJ_G5_1759 {
    static int L, C;
    static HashSet<Character> set = new HashSet<>(Arrays.asList('a','e','i','o','u'));
    static char[] sel;
    static char[] arr;
    static StringBuilder sb = new StringBuilder();

    private static void dfs(int depth, int start) {
        if (depth == L) {
            int moumCnt = 0;
            StringBuilder sbTmp = new StringBuilder();
            for (int i = 0; i <L;i++ ) {
                if (set.contains(sel[i])) moumCnt++;
                sbTmp.append(sel[i]);
            }
            
            if (moumCnt < 1) return;
            if (L-moumCnt < 2) return;
            sb.append(sbTmp.toString()).append("\n");
            return;
        }

        for (int i = start; i < arr.length; i++) {
            sel[depth] = arr[i];
            dfs(depth+1, i+1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);
        sel = new char[L];
        dfs(0, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
