package Greedy;

import java.util.Scanner;

/*
 * BOJ silver 4 2839, 설탕 배달
 * https://www.acmicpc.net/problem/2839
 */

public class BOJ_S4_2839 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int cnt = 0;
        while(N > 0) {
            if (N % 5 == 0) {
                cnt += N/5;
                N = 0;
                break;
            }
            N -= 3;
            cnt++;
        }

        if (N != 0) cnt = -1;
        System.out.println(cnt);

    }
}
