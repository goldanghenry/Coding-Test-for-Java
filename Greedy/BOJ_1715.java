package Greedy;

import java.util.PriorityQueue;
import java.io.*;

/*
 * Boj gold4, 카드 정렬하기
 * https://www.acmicpc.net/problem/1715
 */

public class BOJ_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0 ; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }
        int answer = 0;
        while (pq.size() > 1) {
            int sum = pq.poll() + pq.poll();
            answer += sum;
            pq.offer(sum);
        }
        System.out.println(answer);
    } 
}
