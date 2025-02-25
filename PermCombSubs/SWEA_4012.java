package PermCombSubs;


import java.io.*;
import java.util.StringTokenizer;

/*
 * SWEA 4012 요리사
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeUtVakTMDFAVH
 * 비트 마스킹으로 풀어보기
 */

public class SWEA_4012 {
    static int N, sel, result;
    static int[][] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            sel = 0;
            result = Integer.MAX_VALUE;
            arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 부분집합 조합
            dfs(0);

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void dfs(int depth) {
        // 기저 조건
        if (depth == N) {
            int temp = 0;
            int aSum = 0, bSum = 0;
            for  (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if ( i == j) continue;  // 같은 경우 다음으로
                    if ((sel & 1 << i) != 0 && (sel & 1 << j) != 0 ) {  // A 요리
                        aSum += arr[i][j];
                    } else if ((sel & 1 << i) == 0 && (sel & 1 << j) == 0)  {   // B 요리
                        bSum += arr[i][j];
                    }
                }
            }
            temp = Math.abs(aSum - bSum);
            result = Math.min(result, temp);
            return;
        }

        // 선택하지 않는 경우
        dfs(depth+1);

        // 선택하는 경우
        sel |= 1 << depth;
        dfs(depth+1);
        sel &= ~(1 << depth);
    }

}