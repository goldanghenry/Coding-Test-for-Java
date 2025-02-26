package BinarySearch;

import java.util.Scanner;

/*
    Boj Gold 1, K번째 수
    https://www.acmicpc.net/problem/1300
    Parametric Search
    - A[i][j] = i * j 로 정의된 배열이 있지만, 실제로 정렬된 B 배열을 만들 필요는 없다.
    - 이분 탐색을 이용해 K번째 수가 될 수 있는 값(mid)을 찾아가자.
    - mid 이하의 숫자가 몇 개 존재하는지 카운트하며 탐색 진행
 */

public class BOJ_G1_1300 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int k = sc.nextInt();

        int left = 1;
        int right = k;  // 2차원 배열은 N행이 N의 배수로 구성되어 있으므로, 2차원 배열에서의 k번째 수는 k를 넘지 않는다.
        int answer = 0;

        while (left <= right) {
            int mid = (left+right) / 2;
            int count = 0;

            for (int i = 1; i <= N; i++) {
                count += Math.min(N, mid / i);
            }

            if (count >= k) {
                right = mid - 1;
                answer = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }
}