package PermCombSubs;

import java.io.*;
import java.util.*;

/*
 * BOJ 10819 Silver2 차이를 최대로
 * https://www.acmicpc.net/problem/10819
 */

public class BOJ_S2_10819 {
    static int N, result;
    static int[] arr, sel;
    static boolean[] visited;
    
    // 순열
    private static void dfs(int depth) {
        // 기저 조건
        
        if (depth == N) {
            int sum = 0;
            for (int i =0; i < N-1; i++) {
                sum += Math.abs(sel[i] - sel[i+1]);
            }
            result = Math.max(sum, result);
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            sel[depth] = arr[i];
            dfs(depth+1);
            visited[i] = false;

        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        result = 0;
        sel = new int[N];
        visited = new boolean[N];
        dfs(0);

        System.out.println(result);

    }
}
