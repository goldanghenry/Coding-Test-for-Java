package BinarySearch;

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

class BOJ_S4_1920 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[N]; // 배열의 수

        // 배열 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 이분 탐색을 위한 정렬
        Arrays.sort(arr);

        // 타겟의 수
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            System.out.println(binarySearch(arr, target)); 
        }
    }

    // 이분 탐색
    static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;   // 인덱스 설정

        // 인덱스가 같거나, 교차할 때까지 반복
        while( left <= right) { 
            int mid = left + (right-left)/2;    // 가운데 분기점

            if (target == arr[mid]) return 1;   // 타겟 발견
            else if (target < arr[mid]) right = mid -1; // 타겟이 mid 보다 앞에 있다면 오른쪽 인덱스 당기기
            else left = mid +1; // 타겟이 mid 보다 뒤에 있다면, 왼쪽 인덱스 당기기
        }
        return 0;
    }
}