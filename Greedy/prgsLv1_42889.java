package Greedy;

import java.util.HashMap;
import java.util.Map;

/*
    programmers Lv1 실패율
    https://school.programmers.co.kr/learn/courses/30/lessons/42889
    - 실패율 : 스테이지에 도달했으나, 아직 클리어하지 못한 플에이어수(참가) / 스테이지에 도달한 플레이어 수(클리어)
    - N : 전체 스테이지 개수
    - stages : 게임을 이용하는 사용자가 현재 멈춰 있는 스테이지 번호 배열
    - O( M + 2N + NlogN ) = O(NlogN)
 */

public class prgsLv1_42889 {
    public int[] solution(int N, int[] stages) {

        // 스테이지별 도전자 수 O(N + M) -> 계수
        // challenger[0]은 사용하지 않음
        // challenger[N]은 모두 클리어한 사용자 수
        int[] challenger = new int[N+2];
        for (int i = 0; i < stages.length; i++) {
            challenger[stages[i]] += 1;
        }

        // 실패한 사용자 수 = 현 스테이지 인원 / 클리어 인원
        // HashMap<stageNum, failRate>
        HashMap<Integer, Double> fails = new HashMap<>();
        double total = stages.length;   // 총 인원수

        // 각 스테이지를 순회하며, 실패율 계산 O(N)
        for (int i = 1; i <= N; i++) {
            if (challenger[i] == 0) {
                fails.put(i,0.);
            } else {
                fails.put(i, challenger[i] / total);
                total -= challenger[i];
            }
        }

        // 실패율이 높은 스테이지부터 내림차순으로 정렬 O(NlogN)
        return fails.entrySet().stream()
                .sorted((o1,o2) -> Double.compare(o2.getValue(), o1.getValue()))
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }
}
