package Implementation;

import java.io.*;
import java.util.*;

/*
 * BOJ 30804 Silver 2, 과일 탕후루
 * https://www.acmicpc.net/problem/30804
 * 막대에 N개의 과일이 꽂혀있다.
 * 과일의 각 종류는 1~9까지 번호가 붙어 있다.
 * 막대의 앞(a), 뒤(b)에서 몇 개의 과일을 빼서 두 종류 이하의 과일만 남기기
 * 과일을 두 종류 이하로 사용한 탕후루 중, 과일의 개수가 가장 많은 탕후루의 과일 개수
 */

public class BOJ_S2_30804 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) - 1; // 0-based로 저장
        }

        int[] count = new int[9]; // 과일 종류 1~9 → 0~8
        int kind = 0;
        int maxLen = 0;
        int left = 0;

        for (int right = 0; right < N; right++) {
            // 오른쪽 포인터로 과일 추가
            if (count[arr[right]] == 0) kind++;
            count[arr[right]]++;

            // 종류가 2개 초과면 왼쪽 포인터 이동
            while (kind > 2) {
                count[arr[left]]--;
                if (count[arr[left]] == 0) kind--;
                left++;
            }

            // 최대 길이 갱신
            maxLen = Math.max(maxLen, right - left + 1);
        }

        System.out.println(maxLen);
    }
}

