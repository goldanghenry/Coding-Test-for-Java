package Greedy;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
/*
 * Boj gold4, 수 묶기
 * https://www.acmicpc.net/problem/1744
 */

public class BOJ_1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> positivPQ = new PriorityQueue<>(Comparator.reverseOrder());   // 양수를 담는 우선순위 큐 -> 역순
        PriorityQueue<Integer> negativPQ = new PriorityQueue<>();   // 음수를 담는 우선순위 큐
        boolean zero = false;   // 0이 있는지 확인하는 변수
        int answer = 0;
        
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            
            if (num > 0) positivPQ.offer(num);
            else if (num < 0) negativPQ.offer(num);
            else zero = true;
        }

        // 양수 처리
        while(!positivPQ.isEmpty() && positivPQ.size() > 0) {
            if (positivPQ.size() == 1) {
                answer += positivPQ.poll();
                break;
            }
            int a = positivPQ.poll();
            int b = positivPQ.poll();
            if (a == 1 || b == 1 ) answer += a + b;
            else answer += a * b;
        }

        // 음수 처리
        while(!negativPQ.isEmpty() &&negativPQ.size() > 0) {
            if (negativPQ.size() == 1) {
                if (!zero) answer += negativPQ.poll();
                else negativPQ.poll();  
                break;
            }
            answer += negativPQ.poll() * negativPQ.poll();
        }

        System.out.println(answer);  
    }
}
