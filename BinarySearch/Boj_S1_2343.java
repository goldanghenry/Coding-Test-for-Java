package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Boj sivler 1, 기타레슨
 * https://www.acmicpc.net/problem/2343
 * Binary Search + Parametric Search
 * 최소 블루레이 크기를 탐색
 * 최소(left) -> 강의 길이의 최댓값
 * 최대(right) -> 모든 강의의 총 합
 */

public class Boj_S1_2343 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //강의 수
        int M = Integer.parseInt(st.nextToken()); // 블루레이 수  

        int[] arr = new int[N];     // 강의의 길이를 저장할 배열

        // 각 강의 길이 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 탐색
        System.out.println(binarySearch(arr, N, M));
    }

    static int binarySearch(int[] arr, int N, int M) {
        int result = 0;
        int left = -1;  // 최소 값에는 가장 긴 강의의 길이
        int right = 0; // 최대 값에는 모든 강의 길이의 합

        for ( int i = 0; i < N; i++) {
            left = Math.max(left, arr[i]);
            right += arr[i];
        }

        while (left <= right) {
            int mid = left + (right - left)/2;

            if ( canSave(arr, N, M, mid)) {
                result = mid;       // 담을 수 있다면 저장 후,
                right = mid - 1;    // 최대 범위 당기기
            } else {
                left = mid + 1;     // 최소 범위 당기기 
            }
        }
        return result;
    }

    // 해당 size로 M개 이하의 블루레이에 모든 강의를 담을 수 있는가?
    static boolean canSave(int[] arr, int N, int M, int size ) {
        int cnt = 1;
        int sum = 0;
        for( int lesson : arr) {    
            // 한 블루레이에 더이상 담을 수 없다면 다음 블루레이에 저장장
            if (lesson+sum > size) {    
                if ( ++cnt > M) return false;   // 조기 종료료
                sum = 0;
            }
            sum += lesson;
        }
        return cnt <= M;    // M개의 블루레이를 사용해서 담을 수 있는가?
    }
}
