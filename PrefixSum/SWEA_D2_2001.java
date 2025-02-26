package PrefixSum;

import java.io.*;
import java.util.StringTokenizer;

// SWEA_2001 파리 퇴치 (D2)
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PzOCKAigDFAUq
// 2차원 배열에서 2차원 배열의 부분집합을 구하는 문제
// 2중 for문을 이용하여 2차원 배열의 부분집합을 구하고, 최대값을 갱신하는 방식으로 풀이

public class SWEA_D2_2001 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());z

            int max = Integer.MIN_VALUE;
            int[][] prefixSum = new int[N+1][N+1];

            // 누적합 구하기
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    int cur = Integer.parseInt(st.nextToken());
                    prefixSum[i][j] = cur
                                    + prefixSum[i-1][j]
                                    + prefixSum[i][j-1]
                                    - prefixSum[i-1][j-1];
                }
            }

            // 부분집합 구하기
            for (int i = 1; i <= N-M+1; i++) {
                for (int j = 1; j <= N-M+1; j++) {
                    int sum = prefixSum[i+M-1][j+M-1]
                            - prefixSum[i-1][j+M-1]
                            - prefixSum[i+M-1][j-1]
                            + prefixSum[i-1][j-1];
                    max = Math.max(max, sum);
                }
            }

            sb.append("#").append(t).append(" ").append(max).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
