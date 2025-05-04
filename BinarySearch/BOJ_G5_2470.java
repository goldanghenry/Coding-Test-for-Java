package BinarySearch;
import java.io.*;
import java.util.*;

/*
    BOJ 2470 Gold 5, 두 용액
    https://www.acmicpc.net/problem/2470
    산성 : 1~1000_000_000
    알칼리성 : -1 ~ -1000_000_000
    혼합액 : 두 용액 특성 값의 합 -> 0에 가까운 용액 만들기
    투포인터로 혼합액의 특성 값이 0보다 크다면, right--, / 작다면 left++
 */

public class BOJ_G5_2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);  // 투 포인터를 위한 정렬

        int left = 0;
        int right = N - 1;
        long min = Long.MAX_VALUE;
        long bestL = 0, bestR = 0;

        while (left < right) {
            long mix = arr[left] + arr[right];

            if (Math.abs(mix) < min) {
                min = Math.abs(mix);
                bestL = arr[left];
                bestR = arr[right];
            }

            if (mix > 0) right--;
            else if (mix < 0) left++;
            else break;  // mix == 0 이면 가장 가까우므로 즉시 종료
        }

        System.out.println(bestL + " " + bestR);
    }
}
