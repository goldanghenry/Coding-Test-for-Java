package Sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * Boj silver 4, 수 찾기
 * https://www.acmicpc.net/problem/1920
 * 이분 탐색으로 출력
 */

class Boj_S4_1920 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            System.out.println(binarySearch(arr, target)); 
        }

    }

    static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while( left <= right) {
            int mid = left + (right-left)/2;

            if (target == arr[mid]) return 1;
            else if (target < arr[mid]) right = mid -1;
            else left = mid +1;
        }
        return 0;
    }
}