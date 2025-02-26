package Math;

import java.util.Scanner;

/*
    Boj 1456 Gold 5, 거의 소수
    https://www.acmicpc.net/problem/1456
    - 어떤 수가 소수의 N제곱 -> 거의 소수
    - A <= 거의 소수 <= B
    - 에라토스테네스의 체를 sqrt(B)까지 수행
    - 구한 소수에 대해 소수의 제곱, 세제곱, ... 을 계산하며 범위 내 개수를 센다.
*/

public class BOJ_G5_1456 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long A = sc.nextLong();  // 입력 부분을 nextLong()으로 수정
        long B = sc.nextLong();
        int cnt = 0;

        // B의 제곱근 이하의 소수들을 구하기 위한 배열 크기
        int maxPrime = (int) Math.sqrt(B);
        boolean[] isComposite = new boolean[maxPrime + 1];

        // 에라토스테네스의 체: 2부터 maxPrime까지 소수 판별
        for (int i = 2; i <= maxPrime; i++) {
            if (!isComposite[i]) {
                for (int j = i * 2; j <= maxPrime; j += i) {
                    isComposite[j] = true;
                }
            }
        }

        // 구한 소수들을 가지고 거의 소수(소수의 거듭제곱)를 찾기
        for (int i = 2; i <= maxPrime; i++) {
            if (isComposite[i]) continue;  // 소수가 아니면 건너뜀

            long num = i;  // 소수의 초기값
            // 소수의 제곱, 세제곱, ... 을 구함
            while (true) {
                // 다음 거듭제곱이 B를 초과하면 break
                if (num > B / i) break; // 오버플로를 피하기 위해
                num *= i;
                if (num >= A && num <= B) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}