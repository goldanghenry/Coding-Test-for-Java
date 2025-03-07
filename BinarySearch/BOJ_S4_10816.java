package BinarySearch;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ S4 숫자 카드2
 * https://www.acmicpc.net/problem/10816
 * 이분 탐색으로 풀기
 * 몇개 가지고 있는가? -> upper bound - lower bound 
 */

public class BOJ_S4_10816 {
    static int[] arr;
    
    // target 이상의 값이 처음 나타나는 위치 (lower bound)
    private static int lowerBound(int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    
    // target보다 큰 값이 처음 나타나는 위치 (upper bound)
    private static int upperBound(int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);   // 이분 탐색을 위한 정렬
        
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            int lb = lowerBound(target);
            int ub = upperBound(target);
            int count = ub - lb;
            sb.append(count).append(" ");
        }
        System.out.println(sb);
    }
}