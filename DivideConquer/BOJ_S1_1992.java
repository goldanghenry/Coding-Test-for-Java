package DivideConquer;

import java.io.*;

/*
 * BOJ S2 쿼드 트리
 * https://www.acmicpc.net/problem/1992
 * 분할 정복
 */

public class BOJ_S1_1992 {
    static StringBuilder sb;
    static int[][] map;

    private static void divideConquer(int startR, int startC, int size) {
        // 현재 범위가 모두 같은 숫자로 이루어져있는가?
        boolean flag = true;
        int color = map[startR][startC];
        for(int i = startR; i < startR+size; i++) {
            for (int j = startC; j < startC+size; j++) {
                if (map[i][j] == color) continue;
                flag = false;
                break;
            }
        }

        if (flag) sb.append(color);
        else {
            int nextSize = size/2;
            sb.append("(");
            divideConquer(startR, startC, nextSize);
            divideConquer(startR, startC+nextSize, nextSize);
            divideConquer(startR+nextSize, startC, nextSize);
            divideConquer(startR+nextSize, startC+nextSize, nextSize);
            sb.append(")");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j)-'0';
            }
        }

        // div
        divideConquer(0, 0, N);
        System.out.println(sb);
    }
    
}
