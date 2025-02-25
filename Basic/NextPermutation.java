package Basic;

import java.util.Scanner;
import java.util.Arrays;

/*  
    * Next Permutation
    * 1. 뒤쪽부터 탐색하며 교환위치(i-1) 찾기(i : 꼭대기)
        -> A[i-1] < A[i]를 만족하는 가장 큰 i를 찾는다.
    * 2. 뒤쪽부터 탐색하며 교환위치(i-1)와 교환할 큰 값 위치(j) 찾기
        -> i-1 보다 큰 값 중 가장 작은 값과 교환
        -> j >= i 이면서 A[j] > A[i-1]를 만족하는 가장 큰 j를 찾는다.
    * 3. 두 위치 값(i-1, j) 교환
        -> A[i-1]과 A[j]를 swap한다.
    * 4. 꼭대기 위치(i)부터 맨 뒤까지 오름차순 정렬
        -> A[i]부터 순열을 뒤집는다.

    ** 주의 : nPr 은 안된다. nPn만 가능함
*/ 

public class NextPermutation {
    static int N;
    static int[] input;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        input = new int[N];

        for (int i = 0; i < N ; i++) {
            input[i] = sc.nextInt();
        }

        // 주의!!! : 오름차순 정렬
        Arrays.sort(input);

        do {
            System.out.println(Arrays.toString(input));
        } while(np());

        sc.close();
    }

/* 현 상태의 순열에서 사전식 다음 순열 생성후, 
 * 다음 순열이 존재하면 true, 아니면 false 반환
 */
    static boolean np() {

        // step 1. 뒤쪽부터 탐색하며 꼭대기(i) 찾기!! (i-1 : 교환위치 찾기)
        int i = N -1;
        while(i > 0 && input[i-1] >= input[i]) i--;
        if (i==0) return false; // 마지막 순열까지 다 도달했으면 다음 순열 없음(가장 큰 순열)

        // step 2. 뒤쪽부터 탐색하며 교환할 큰 값 위치(j) 찾기
        int j = N -1;
        while(input[i-1] >= input[j]) j--; // i-1 보다 큰 값 중 가장 작은 값과 교환

        // step 3. 두 위치 값(i-1, j) 교환
        swap(i-1, j);

        // step 4. 꼭대기 위치(i)부터 맨 뒤까지 오름차순 정렬
        int k = N -1;
        while ( i < k) swap(i++, k--);

        return true;
    }

    static void swap(int i , int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}
