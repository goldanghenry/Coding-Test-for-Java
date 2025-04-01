package BruteForceSearch;

import java.util.*;
import java.io.*;

/*
 * BOJ 2531 Silver1, 회전 초밥
 * https://www.acmicpc.net/problem/2531
 * 같은 종류의 초밥도 있다
 * 한 위치부터 k개의 접시를 연속해서 먹는 경우 할인된 정액 가격으로 제공
 * 각 고객에게 초밥의 종류 하나가 쓰인 쿠폰, 한 번 행사에 참가 -> 이 쿠폰에 적혀진 종류의 초밥 하나를 추가로 무료 제공
 * 벨트위에 없다면 요리사가 새로 만들어 손님에게 제공
 * 가능한 다양한 종류의 초밥 먹기
 */

public class BOJ_S1_2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 초밥 벨트의 길이
        int d = Integer.parseInt(st.nextToken());   // 초밥의 가짓 수
        int k = Integer.parseInt(st.nextToken());   // 연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken());   // 쿠폰 번호

        int[] sushi = new int[N];
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int[] count = new int[d +1];    // 각 초밥의 종류를 저장할 배열
        int kind = 0;                   // 현재 윈도우 내 초밥 종류 수
        int maxKind = 0;

        // 쿠폰 초밥은 미리 먹은 것으로 처리
        count[c]++;
        kind++;

        // 첫 k개 윈도우 초기화
        for (int i = 0; i < k; i++) {
            if (count[sushi[i]] == 0) {
                kind++;
            }
            count[sushi[i]]++;
        }
        maxKind = kind;

        // 슬라이딩 윈도우로 순회 (원형이므로 모듈러 연산 사용)
        for (int i = 0; i < N; i++) {
            // 윈도우에서 제일 왼쪽 초밥 제거
            int leftSushi = sushi[i];
            count[leftSushi]--;
            if (count[leftSushi] == 0) {
                kind--;
            }
            
            // 윈도우에 새로 추가할 초밥 (원형 배열 처리)
            int newIndex = (i + k) % N;
            int rightSushi = sushi[newIndex];
            if (count[rightSushi] == 0) {
                kind++;
            }
            count[rightSushi]++;
            
            // 최대 초밥 종류 수 갱신
            maxKind = Math.max(maxKind, kind);
        }
        
        System.out.println(maxKind);
        br.close();

        



    }
}
