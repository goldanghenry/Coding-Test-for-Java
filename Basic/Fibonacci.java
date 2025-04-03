package Basic;

import java.util.*;

/*
 * 
 */

public class Fibonacci {
    static long totalCnt1, totalCnt2;
    static long[] callCnt1, callCnt2,memo;

    public static long fibo1(int n) {   // 비메모 버전
        totalCnt1++;
        callCnt1[n]++;  // 자신의 매개변수에 해당하는 호출이 얼마나 일어났는가
        if (n < 2) return n;
        return fibo1(n-1) + fibo1(n-2);
    }

    public static long fibo2(int n) {   // 메모 버전
        totalCnt2++;
        callCnt2[n]++;  // 자신의 매개변수에 해당하는 호출이 얼마나 일어났는가

        // 메모확인 후 안되있으면 연산
        if (memo[n] == -1) {
            memo[n] = fibo2(n-1) + fibo2(n-2);
        }
        return memo[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        callCnt1 = new long[N+1];
        callCnt2 = new long[N+1];

        memo = new long[N+1];

        // 메모이제이션 자료구조 초기화
        Arrays.fill(memo, -1);
        memo[0] = 0;
        memo[1] = 1;

    System.out.println("============비메모 버전============");
        System.out.println(fibo1(N));
        for (int i = 0; i < N+1; i++){
            System.out.println("fibo1("+i+") : "+callCnt1[i] );
        }
        System.out.println(totalCnt1);

        System.out.println("============메모 버전============");
        System.out.println(fibo2(N));
        for (int i = 0; i < N+1; i++){
            System.out.println("fibo2("+i+") : "+callCnt2[i] );
        }
        System.out.println(totalCnt2);


    }
}
