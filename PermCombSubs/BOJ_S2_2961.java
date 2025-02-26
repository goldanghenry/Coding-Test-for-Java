package PermCombSubs;

import java.util.Scanner;

/*
    Boj silver 2, 도영이가 만든 맛있는 음식
    https://www.acmicpc.net/problem/2961
    부분 집합
 */

public class BOJ_S2_2961 {
    static int N, result;
    static boolean[] sel;
    static long[] Arr, Brr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();    // 재료의 개수
        result = Integer.MAX_VALUE;
        sel = new boolean[N];
        Arr = new long[N];
        Brr = new long[N];

        for (int i = 0; i < N; i++) {
            Arr[i] = sc.nextLong();
            Brr[i] = sc.nextLong();
        }

        // 부분 집합 백트래킹
        dfs(0);

        System.out.println(result);
    }
    private static void dfs(int depth) {
        // 기저 조건
        boolean isEmpty = true;
        if (depth == N) {
            long A = 1; // 신맛
            long B = 0; // 쓴맛
            for (int i = 0; i < N; i++) {
                if (sel[i]) {
                    A *= Arr[i];
                    B += Brr[i];
                    isEmpty = false;
                }
            }

            if (isEmpty) return;
            result = (int)Math.min(result, Math.abs((A-B)));
            return;
        }

        // 선택하지 않을 떄
        sel[depth] = false;
        dfs(depth+1);

        // 선택했을 때
        sel[depth] = true;
        dfs(depth+1);
    }
}
