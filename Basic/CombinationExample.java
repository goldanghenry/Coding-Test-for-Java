package Basic;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 순열 -> 순서가 의미 -> 중복으로 뽑을 수 있는가? -> 중복 순열
 * -> nPr : n * (n-1) * (n -2) * ... * (n -r +1)
 * -> nPn : n!
 * 조합 -> 순서가 의미 없음 -> 중복으로 뽑을 수 있는가? -> 중복 조합
 * -> nCr : n! / (n-r)! * r!
 */
public class CombinationExample {
    static int N, R;
    static int[] input, numbers;

    // 조합을 재귀적으로 구하는 메서드
    private static void combination(int cnt, int start) {
        if (cnt == R) {
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for (int i = start; i < N; i++) {
            numbers[cnt] = input[i];
            combination(cnt+1, i+1);    // 다음 자리의 수를 결정하러 이동
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();   // N개 중에서
        R = sc.nextInt();   // R개를 뽑는 조합
        input = new int[N];
        numbers = new int[R];
        
        for (int i = 0; i < N; i++) {
            input[i] = sc.nextInt();
        }


        System.out.println("조합(Combination) 결과:");
        combination(0, 0);
        sc.close();
    }
}

