package Greedy;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

/*
 * BOJ Gold 5, 회의실 배정
 * https://www.acmicpc.net/problem/1931
 * activity-selection problem
 * 회의실을 사용할 수 있는 시간이 주어졌을 때, 가장 많은 회의를 할 수 있는 경우의 수를 구하는 문제
 * 
 * 전략
 * 빨리 끝나는 회의 순서대로 정렬하고, 끝나는 시간이 같다면 시작 시간이 빠른 순서대로 정렬
 * 회의가 끝나는 시간이 같을 때는 시작 시간이 빠른 순서대로 정렬
 * 그 이유는 회의가 끝나는 시간이 같다면 시작 시간이 빠른 순서대로 정렬해야 더 많은 회의를 할 수 있기 때문
 * 빨리 끝난다는 것은 빨리 시작했다는 것.
 */

public class BOJ_G5_1931 {
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

        // 끝나는 시간이 빠른 순서대로 오름차순 정렬
        Arrays.sort( meeting, (a,b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        int cnt = 0;
        int end = 0;    // 이전 회의가 끝나는 시간
        for (int i = 0; i < N; i++) {
            if (end <= meeting[i][0]) { // 이전 회의가 끝나는 시간보다 시작 시간이 같거나 늦다면
                end = meeting[i][1];    // 회의를 진행하고 끝나는 시간 갱신
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
