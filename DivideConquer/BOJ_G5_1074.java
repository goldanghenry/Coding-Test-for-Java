package DivideConquer;

import java.util.Scanner;

/*
 * BOJ 1074 Gold 5, Z
 * https://www.acmicpc.net/problem/1074
 * size 가 2가 될때까지 분기
 * 해당 구간에 없다면 전체 더하기
 */

public class BOJ_G5_1074 {
    static int N, r, c, result;
    static boolean find;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();
        N = 1;
        for (int i = 1; i <= n; i++) {
            N *=2;
        }

        result = -1;
        find = false;
        divideConquer(0,0,N );
        System.out.println(result);
    }

    private static void divideConquer(int startR, int startC, int size) {
        // startR, startC
        int nextSize = size/2;
        int[][] index = {{startR, startC},
                        {startR, startC+nextSize},
                        {startR+nextSize, startC},
                        {startR+nextSize, startC+nextSize}};

        // 4개의 블럭으로 나누기
        for (int k = 0; k < 4; k++) {
            if (find) return;
            // 해당 블럭에 있는가?
            if ((index[k][0] <= r && r < index[k][0]+nextSize) && (index[k][1] <= c && c < index[k][1]+nextSize)){
                // size 2에 도달
                if (size == 2) {
                    for (int i = index[k][0]; i < index[k][0]+nextSize; i++) {
                        for (int j = index[k][1]; j < index[k][1]+nextSize; j++) {
                            if (i == r && j == c) {
                                result++;
                                find = true;
                                return;
                            }
                        }
                    }
                }
                else {
                    divideConquer(index[k][0], index[k][1], nextSize);
                }
            } else {    // 해당 블럭에 없다면 그 블럭의 칸수만큼 더하기
                result+=nextSize*nextSize;
            }
        }

    }
}
