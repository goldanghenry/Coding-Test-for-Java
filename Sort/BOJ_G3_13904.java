package Sort;

/*
    BOJ Gold 3 과제
    https://www.acmicpc.net/problem/13904
    웅찬이는 과제가 많다. 하루에 한 과제를 끝낼 수 있는데, 과제마다 마감일이 있으므로 모든 과제를 끝내지 못할 수도 있다.
    과제마다 끝냈을 때 얻을 수 있는 점수가 있는데, 마감일이 지난 과제는 점수를 받을 수 없다.
    웅찬이는 가장 점수를 많이 받을 수 있도록 과제를 수행하고 싶다. 웅찬이를 도와 얻을 수 있는 점수의 최댓값을 구하시오.
 */

import java.io.*;
import java.util.*;

public class BOJ_G3_13904 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int maxDay = 0;

        // 마감일 별 점수 저장
        List<Integer>[] days = new ArrayList[1001];
        for (int i = 0; i <= 1000; i++) {
            days[i] = new ArrayList<>();
        }

        // N개의 과제 정보를 입력받아 days 배열에 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            days[d].add(w);
            maxDay = Math.max(maxDay, d);
        }

        // 점수 내림 차순
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int res = 0;

        // 마지막 날부터 1일까지 역순으로 확인
        for (int day = maxDay; day > 0; day--) {
            // 해당 날짜에 마감인 과제들 -> 우선순위 큐에 모두 추가
            for (int score : days[day]) {
                pq.offer(score);
            }

            // 가장 높은 점수인 과제 수행
            if (!pq.isEmpty()) {
                res += pq.poll();
            }
        }
        System.out.println(res);
    }
}
