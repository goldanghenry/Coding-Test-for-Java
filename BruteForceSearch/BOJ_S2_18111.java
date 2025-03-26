package BruteForceSearch;

import java.io.*;
import java.util.*;

/*
 * BOJ 18111 Silver2, 마인크래프트
 * https://www.acmicpc.net/problem/18111
 * N * M, 집터 내 땅의 높이를 모두 동일하게 만들자
 * [ 가능한 작업 ]
 * 1. i,j의 가장 위에 있는 블록을 제거 -> 인벤토리 넣기 (2초)
 * 2. 인벤토리 블럭 -> i,j에 쌓기 (1초)
 */

public class BOJ_S2_18111 {
    public static void main(String[] args) throws Exception {
        // 입력을 위한 BufferedReader 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N: 행 수, M: 열 수, B: 인벤토리 블록 개수
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // 각 높이의 빈도수를 저장할 해시맵과 최대 높이 저장 변수
        Map<Integer, Integer> heightCount = new HashMap<>();
        int maxHeight = 0;

        // 땅의 높이를 입력받으며 해시맵에 (높이, 개수) 형태로 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int h = Integer.parseInt(st.nextToken());
                // 최대 높이 갱신
                maxHeight = Math.max(maxHeight, h);
                // 해당 높이의 빈도수 증가
                heightCount.put(h, heightCount.getOrDefault(h, 0) + 1);
            }
        }

        // 해시맵의 항목들을 리스트로 변환 후, 내림차순 (높은 순)으로 정렬
        List<Map.Entry<Integer, Integer>> heightList = new ArrayList<>(heightCount.entrySet());
        heightList.sort((a, b) -> b.getKey() - a.getKey());

        // 결과 초기화: 최소 시간(resultTime)과 최적 높이(resultHeight)
        // 최소 시간은 매우 큰 값으로 초기화 (long 타입 사용)
        int resultTime = Integer.MAX_VALUE;
        int resultHeight = 0;

        // 후보 높이를 최대 높이부터 0까지 내림차순으로 탐색
        for (int target = maxHeight; target >= 0; target--) {
            int time = 0;    // 해당 후보 높이로 땅을 맞출 때 걸리는 시간
            int inventory = B; // 현재 인벤토리 블록 수
            boolean possible = true; // 현재 후보 높이로 작업이 가능한지 여부

            // 각 고유 높이에 대해 처리 (해당 높이가 target보다 높으면 제거, 낮으면 쌓기)
            for (Map.Entry<Integer, Integer> entry : heightList) {
                int height = entry.getKey();
                int count = entry.getValue();

                // 현재 높이가 target보다 높다면: 블록 제거
                if (height > target) {
                    // 제거해야 하는 블록 수 = (현재 높이 - target) * 해당 높이의 빈도수
                    int blocks = (height - target) * count;
                    // 제거 작업은 블록 당 2초 소요
                    time += (long) blocks * 2;
                    // 제거한 블록은 인벤토리에 추가됨
                    inventory += blocks;
                }
                // 현재 높이가 target보다 낮다면: 블록 쌓기
                else if (height < target) {
                    // 쌓아야 하는 블록 수 = (target - 현재 높이) * 해당 높이의 빈도수
                    int blocks = (target - height) * count;
                    // 인벤토리에 충분한 블록이 없는 경우 후보 높이 불가능 처리
                    if (blocks > inventory) {
                        possible = false;
                        break;
                    }
                    // 쌓기 작업은 블록 당 1초 소요
                    time += blocks;
                    // 사용한 블록은 인벤토리에서 차감
                    inventory -= blocks;
                }
                // 높이가 target과 동일하면 작업이 필요 없음
            }

            // 해당 후보 높이가 가능한 경우
            if (possible) {
                // 최소 시간을 갱신 (내림차순 탐색이므로, 같은 시간일 경우 높은 높이가 우선됨)
                if (time < resultTime) {
                    resultTime = time;
                    resultHeight = target;
                }
            }
        }

        // 결과 출력: 최소 시간과 그때의 최적 높이
        System.out.println(resultTime + " " + resultHeight);
    }
}