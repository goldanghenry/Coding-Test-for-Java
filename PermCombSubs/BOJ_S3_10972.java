package PermCombSubs;

import java.io.*;
import java.util.StringTokenizer;

/*
 * BOJ 다음 순열, silver 3
 * https://www.acmicpc.net/problem/10972
 */

public class BOJ_S3_10972 {
    static int[] arr;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (np()) {
            for ( int i = 0; i < N; i++) {
                sb.append(arr[i]).append(" ");
            }
        } else {
            sb.append("-1");
        }

        System.out.println(sb);

    }

    static boolean np() {
        // 꼭대기 찾기
        int i = N-1;
        while ( i > 0 && arr[i-1] >= arr[i]) i--;
        if (i == 0) return false;

        // 교환할 값 찾기
        int j = N-1;
        while( arr[i-1] >= arr[j]) j--;

        // 교환하기
        swap(i-1, j);

        // 오름차순 정렬하기
        int k = N-1;
        while(i < k) swap(i++, k--);

        return true;
    }

    static void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
