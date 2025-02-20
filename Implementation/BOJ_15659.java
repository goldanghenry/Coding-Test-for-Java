package Implementation;

import java.io.*;
import java.util.Scanner;

/*
 * Boj silver3, N과 M (2)
 * https://www.acmicpc.net/problem/15659
 * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
 * 고른 수열은 오름차순이어야 한다.
 * 
 * -> 조합 문제 (중복 x, 순서 상관 x)
 */

public class BOJ_15659 {
    static int N, M;
    static int[] nums, sel;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = sc.nextInt();
        M = sc.nextInt();
        sel = new int[M];
        

        // 자연수 배열 생성
        nums = new int[N];
        for (int i = 1; i <= N; i++) {
            nums[i-1] = i;
        }

        // 조합 시행
        dfs(0, 0);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void dfs(int depth, int start) {
        if (depth == M) {
            // 출력
            for (int i = 0; i < M; i++) {
                sb.append(sel[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        
        for (int i = start; i < N; i++) {   // 오름차순 조건
            sel[depth] = nums[i];
            dfs(depth+1, i+1);
        }
    }
    
}
