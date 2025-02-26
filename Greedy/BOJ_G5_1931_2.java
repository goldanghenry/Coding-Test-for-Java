package Greedy;

import java.io.*;
import java.util.*;

/*
 * BOJ Gold 5, 회의실 배정
 * https://www.acmicpc.net/problem/1931
 */

public class BOJ_G5_1931_2 {
    static class Meeting implements Comparable<Meeting> {
        int start, end; // 회의 시작, 종료 시간

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        // 회의 시작, 종료 시간이 같은 경우도 있을 경우 -> 시작 시간이 빠른 순서대로 정렬
        // (1,2), (2,3), (3,3) : 3개  ==> 답
        // (1,2), (3,3), (2,3) : 2개
        @Override
        public int compareTo(Meeting o) {
            return this.end != o.end ? this.end - o.end : this.start - o.start;
        }
        // 만약 값에 음수가 포함된다면, overflow가 발생할 수 있으므로 주의 -> Integer.compare 사용
        // return Integer.compare(this.end, o.end);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());    // 회의 수
        Meeting[] meetings = new Meeting[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            meetings[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(meetings);      // 오름차순 정렬
        List<Meeting> result = new ArrayList<>();
        result.add(meetings[0]);    // 첫 회의 배정

        for (int i = 1; i < N; i++) {
            if (result.get(result.size() - 1).end <= meetings[i].start) {
                result.add(meetings[i]);
            }
        }

        System.out.println(result.size());
        br.close();
    }

}
