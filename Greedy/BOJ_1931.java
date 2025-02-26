package Greedy;

import java.io.*;
import java.util.StringTokenizer;

/*
 * BOJ Gold 5, 회의실 배정
 * https://www.acmicpc.net/problem/1931
 */

public class BOJ_1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] meeting = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            meeting[i][0] = Integer.parseInt(st.nextToken());
            meeting[i][1] = Integer.parseInt(st.nextToken());
        }

        // 끝나는 시간을 기준으로 정렬
        // 끝나는 시간이 같다면 시작 시간이 빠른 순으로 정렬
        // 끝나는 시간이 같고 시작 시간이 같다면 같은 회의라고 판단
        // 1. 끝나는 시간이 빠른 순으로 정렬
        // 2. 끝나는 시간이 같다면 시작 시간이 빠른 순으로 정렬
        // 3. 끝나는 시간이 같고 시작 시간이 같다면 같은 회의라고 판단
        // 4. 같은 회의라면 무조건 선택
        // 5. 다른 회의라면 선택
        // 6. 선택된 회의의 끝나는 시간을 다음 회의의 시작 시간으로 갱신
        // 7. 선택된 회의의 개수를 출력
        int cnt = 0;
        int end = 0;
        for (int i = 0; i < N; i++) {
            if (meeting[i][0] >= end) {
                end = meeting[i][1];
                cnt++;
            }
        }

    }
}
