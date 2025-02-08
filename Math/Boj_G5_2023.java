package Math;

import java.util.Scanner;

/*
    BOj Gold 5, 신기한 소수
    https://www.acmicpc.net/problem/2023

    * 전략
    - 에라토스테네스의 체 등을 활용하더라도 또 다시 각 자리수가 소수인지 검사해야 하기에 시간 복잡도상 불가능
    - 메모리도 4MB 이기에 배열을 사용하더라도 int[1000] 밖에 저장하지 못하기에 메모이제이션 불가능
    - 신기한 소수의 조건을 추가 검사 조건이 아닌, 강한 제약 조건으로 생각의 전환 -> 루프 최소화 가능
    - 소수의 특징 1, 2를 활용해 백트래킹으로 하나씩 숫자를 붙여나가기
    - 기저 조건은 현재 문자의 길이가 N과 같은 경우 출력 후 종료

    ** 소수의 특징
    - 1) 1의 자리의 경우 [ 2, 3, 5, 7 ] 만 제일 앞에 올 수 있다.
    - 2) 2자리 이상 소수의 경우 반드시 [ 1, 3, 7, 9 ] 로 끝이 난다.
    - 1) 로 시작해 2)의 숫자들로만 붙여 나가자!

 */

public class Boj_G5_2023 {

    static int[] firstPrimeArr = {2,3,5,7}; // 1의 자리 소수
    static int[] lastPrimeArr = {1,3,7,9};  // 2자리 수 이상의 소수의 마지막에 올 수 있는 수
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // 첫 시작으로 가능한 숫자[ 2, 3, 5, 7 ]로 탐색 시작
        for (int i =0; i< 4; i++) {
            isSpecialNumber(N, new StringBuilder().append(firstPrimeArr[i]));
        }
        System.out.println(sb);
    }

    // 소수 판별 -> 제곱근 까지만 검사
    static boolean isPrime(int n) {
        if ( n < 2) return false;
        for (int i = 2; i*i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
    // 백트래킹
    public static void isSpecialNumber(int N, StringBuilder curStr) {
        // 기저 조건 : 현재 문자가 N자리에 도달한 경우
        if (N == curStr.toString().length()) {
            sb.append(curStr).append("\n");
            return;
        }

        for (int i = 0; i < 4 ; i++) {
            int curNumber = Integer.parseInt(curStr.toString()+lastPrimeArr[i]);    // 추가로 숫자 붙이기
            if (isPrime(curNumber)) {   // 소수 검사
                isSpecialNumber(N, curStr.append(lastPrimeArr[i])); // 다음 재귀 호출
                curStr.setLength(curStr.length()-1);    // 백트래킹에서 다음 루프 전 이전 상태로 되돌리기
            }
        }
    }
}
