package PrefixSum;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Boj Silver 1 구간합 구하기
 * https://www.acmicpc.net/problem/11660
 */

class Boj_S1_11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N+1][N+1];
        int[][] prefixSum = new int[N+1][N+1];

        // 1. 표 입력 받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 2. 누적 합 계산
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                prefixSum[i][j] = arr[i][j] 
                                    + prefixSum[i-1][j] 
                                    + prefixSum[i][j-1] 
                                    - prefixSum[i-1][j-1];
            }
        }

        // 3. 구간 합 계산
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < M; k++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int result = prefixSum[x2][y2]
                        - prefixSum[x1-1][y2]
                        - prefixSum[x2][y1-1]
                        + prefixSum[x1-1][y1-1];
            sb.append(result +"\n");
        }
        System.out.println(sb);
    }
}