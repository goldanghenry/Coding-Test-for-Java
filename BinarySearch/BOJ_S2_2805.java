package BinarySearch;

import java.io.*;
import java.util.*;

/*
    BOJ S2 2805, 나무 자르기
    https://www.acmicpc.net/problem/2805
    적어도 M미터 이상의 나무를 가져가기 위한 절단기의 높이
 */

public class BOJ_S2_2805 {
    static int[] arr;
    static int N,M, maxHeight;

    // 절단기의 높이 찾기
    private static void parametricSearch() {
        int left = 0, right = maxHeight;
        long cnt;
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;

            // 나무 자르기
            cnt = 0;    //  반복마다 초기화
            for(int i = 0; i < N; i++) {
                if (arr[i] < mid) continue; // 나무의 높이가 절단기 높이보다 작을 때
                cnt += arr[i]-mid;
            }

            if(cnt >= M) left = mid + 1;
            else right = mid - 1;
        }
        System.out.println(right);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 나무의 수
        M = Integer.parseInt(st.nextToken());   // 목표

        arr = new int[N];
        maxHeight = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            maxHeight = Math.max(maxHeight, arr[i]);
        }

        parametricSearch();
    }
}
