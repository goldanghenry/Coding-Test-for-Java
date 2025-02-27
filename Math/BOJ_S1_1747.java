package Math;

import java.util.Scanner;

/*
 * Boj 1747 silver 1 소수 & 팰린드롬
 * https://www.acmicpc.net/problem/1747
 * 에라토스테네스의 체 + 팰린드롬
 */

public class BOJ_S1_1747 {
    static final int MAX_NUM = 10_000_000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        boolean[] primes = new boolean[MAX_NUM+1];
        primes[0] = primes[1] = true;

        // 에라토스테네스의 체
        for (int i = 2; i*i <= MAX_NUM; i++) {
            if (primes[i]) continue;
            for (int j = i * i; j <=MAX_NUM; j+=i) {
                primes[j] = true;
            }   
        }

        // 소수라면 팰린드롬 검사
        for (int i = N; i < MAX_NUM; i++) {
            if (primes[i]) continue;
            boolean flag = true;

            String str = Integer.toString(i);
            int left = 0, right = str.length()-1;
            while (left < right) {
                if (str.charAt(left) != str.charAt(right)) {
                    flag = false;
                    break;
                }
                left++;
                right--;
            }
            if (flag) {
                System.out.println(i);
                break;
            }
        }
    }
}