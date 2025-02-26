package PermCombSubs;

import java.io.*;
import java.util.StringTokenizer;

/*
    SWEA 4008, 숫자 만들기 (중복 순열 고려)
    연산자 카드의 개수를 count 배열로 관리하여 중복된 연산자 순서를
    한 번만 탐색하도록 구현한 DFS 방식의 코드입니다.
 */

public class SWEA_EX_4008 {
    static int N, maxNum, minNum;
    static int[] nums, opCount; // opCount[0]:'+', [1]:'-', [2]:'*', [3]:'/'

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine()); // 숫자의 개수
            opCount = new int[4];
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 연산자 카드의 개수 입력받기 ('+', '-', '*', '/')
            for (int i = 0; i < 4; i++) {
                opCount[i] = Integer.parseInt(st.nextToken());
            }

            nums = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            maxNum = Integer.MIN_VALUE;
            minNum = Integer.MAX_VALUE;

            // DFS: 인덱스 1부터 시작 (첫 번째 숫자는 초기값)
            dfs(1, nums[0]);

            // 최대값과 최소값의 차이를 출력
            sb.append("#").append(t).append(" ").append(maxNum - minNum).append("\n");
        }
        System.out.println(sb);
    }

    // idx: 현재 계산에 사용할 숫자의 인덱스, current: 지금까지 계산한 결과
    static void dfs(int idx, int current) {
        if (idx == N) {
            maxNum = Math.max(maxNum, current);
            minNum = Math.min(minNum, current);
            return;
        }

        // 각 연산자에 대해 남은 개수가 있다면 선택하여 다음 단계로 진행
        if (opCount[0] > 0) { // '+'
            opCount[0]--;
            dfs(idx + 1, current + nums[idx]);
            opCount[0]++;
        }
        if (opCount[1] > 0) { // '-'
            opCount[1]--;
            dfs(idx + 1, current - nums[idx]);
            opCount[1]++;
        }
        if (opCount[2] > 0) { // '*'
            opCount[2]--;
            dfs(idx + 1, current * nums[idx]);
            opCount[2]++;
        }
        if (opCount[3] > 0) { // '/'
            opCount[3]--;
            // 문제의 조건에 따라 나눗셈은 소수점 이하 버림
            dfs(idx + 1, current / nums[idx]);
            opCount[3]++;
        }
    }
}