package Math;

import java.util.Scanner;
/*
 * BOJ gold1 1016, 제곱 ㄴㄴ 수
 * https://www.acmicpc.net/problem/1016
 * 제곱수 : 정수의 제곱
 * 제곱ㄴㄴ수 : 1보다 큰 제곱수로 나누어 떨어지지 않는 수
 */

public class BOJ_G1_1016 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 구간의 시작과 끝
        long min = sc.nextLong();
        long max = sc.nextLong();
        // 구간의 크기
        int size = (int)(max-min+1);
        boolean[] checkArr = new boolean[size]; // idx = i - min

        // 제곱수 구하기 2 부터 sqrt(max)까지 반복
        // i는 제곱수를 구하기 위한 값
        for (long i = 2; i*i <= max; i++) {
            long square = i * i;

            // [min, max] 구간 내 square로 나누어 떨어지는 최초의 수 구하기
            // 만약 min이 square로 나누어 떨어지면 start = min
            // 그렇지 않다면, 해당 수부터
            long start = (min%square == 0) ? min : min+(square - (min % square));

            for (long j = start; j <= max; j += square) {
                checkArr[(int)(j-min)] = true;
            }

        }

        // 제곱 ㄴㄴ수 구하기
        int cnt = 0;
        for (int i = 0; i < size; i++) {
            if (checkArr[i]) continue;
            cnt++;
        }
        System.out.println(cnt);
    
    }
}
