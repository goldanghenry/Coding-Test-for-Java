package BinarySearch;

import java.io.*;
import java.util.*;

/*
 * BOJ S2 1654, 랜선 자르기
 * https://www.acmicpc.net/problem/1654
 * parametric search
 */

public class BOJ_S2_1654 {
    static int K, N;
    static int[] arr;
    
    private static long parametricSearch() {
        long left = 1, right = arr[K-1];   // 랜선 길이
        long cnt, mid;

        while(left <= right) {
            cnt = 0;
            mid = (left+right) / 2;
            
            for (int i = 0; i < K; i++) cnt+= arr[i]/mid;

            if(cnt < N) right = mid-1;
            else left = mid+1;
        }
        return right;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());   // 기존 랜선의 수
        N = Integer.parseInt(st.nextToken());   // 만들어야 하는 랜선의 수

        arr = new int[K];
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 이분 탐색을 위한 정렬
        Arrays.sort(arr);

        // 이분 탐색 진행
        System.out.println(parametricSearch());
    }
}
