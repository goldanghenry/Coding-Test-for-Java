package Implementation;

import java.io.*;
import java.util.StringTokenizer;

/*
    SWEA 4013. 특이한 자석
    - https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeV9sKkcoDFAVH

    [풀이 전략]
    1. 각 자석의 현재 "화살표(지정된 톱니)" 위치를 순환 인덱스로 관리
       - redAllowIdx 배열을 통해 각 자석의 화살표가 가리키는 톱니의 인덱스를 저장 (초기값 0).
    2. 회전 명령이 들어올 때마다, DFS를 사용해 인접 자석들에 대해 회전 여부 판단
       - 현재 자석의 왼쪽 또는 오른쪽 자석과 맞닿은 톱니(각 자석의 왼쪽은 인덱스 (현재 인덱스+6)%8, 오른쪽은 (현재 인덱스+2)%8) 비교
       - 맞닿은 톱니의 극이 서로 다르면 인접 자석은 현재 자석과 반대 방향으로 회전
       - 이미 회전한 자석은 재방문하지 않도록 visited 배열 사용
    3. 실제 자석의 배열을 이동시키는 대신, "화살표 인덱스(redAllowIdx)"를 갱신하여 회전 효과를 시뮬레이션
       - 자석 회전 시, 새로운 인덱스 = (현재 인덱스 + 회전 방향 + 8) % 8
       - 단, 문제에서의 회전 방향과 인덱스 이동 규칙이 반대이므로, DFS 호출 시 입력된 방향에 (-1)을 곱해 내부 회전 방향으로 전환
         * 입력: 시계 방향 = 1, 반시계 방향 = -1
         * 내부: 시계 방향 = -1 (인덱스가 1 감소), 반시계 방향 = 1 (인덱스가 1 증가)
 */

public class SWEA_4013 {
    // 4개의 자석, 각 자석은 8개의 톱니 정보를 저장 (N극: 0, S극: 1)
    static int[][] magnetLst;
    // 각 자석의 "화살표(지정된 톱니)"가 가리키는 현재 인덱스 (초기값은 0)
    static int[] redAllowIdx;
    // DFS 진행 시, 이미 회전 처리가 완료된 자석을 기록하여 역전파(중복 회전)를 방지
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        // 입출력 객체 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 전체 테스트 케이스 수 T
        int T = Integer.parseInt(st.nextToken());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());   // 회전 명령 수

            // 자석 정보와 화살표 인덱스 초기화
            magnetLst = new int[4][8];    // 4개의 자석, 각 자석은 8개 톱니의 극 정보 저장
            redAllowIdx = new int[4];     // 모든 자석의 화살표 초기 위치는 0

            // 4개 자석의 톱니 극 정보 입력 (각 톱니: N극 0, S극 1)
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    magnetLst[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // K개의 회전 명령 처리
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                // 입력은 1번 자석부터 시작하므로, 인덱스 0부터 시작하도록 변환
                int target = Integer.parseInt(st.nextToken()) - 1;
                int direction = Integer.parseInt(st.nextToken());
                /*
                    주의: 문제에서는 시계 방향이 1, 반시계 방향이 -1로 주어지지만,
                    인덱스 회전 계산에서는 시계 방향(화살표가 왼쪽으로 이동)이 -1,
                    반시계 방향(화살표가 오른쪽으로 이동)이 1이므로 DFS 호출 시에 부호를 반전.
                */
                visited = new boolean[4]; // DFS 진행 시, 해당 명령에서 방문한 자석 기록 초기화
                dfs(target, direction * -1);   // DFS로 자석 회전 처리 (현재 자석부터 시작)
            }

            // 회전이 모두 끝난 후, 각 자석의 화살표가 가리키는 톱니의 극에 따라 점수 계산
            int total = 0, score = 1;
            for (int i = 0; i < 4; i++) {
                // 화살표가 가리키는 톱니가 S극(1)이면 해당 자석의 점수(score)를 더함
                if (magnetLst[i][redAllowIdx[i]] == 1) {
                    total += score;
                }
                score *= 2; // 자석 0,1,2,3에 대해 점수는 1, 2, 4, 8 순으로 증가
            }
            // 테스트 케이스 결과 저장
            sb.append("#").append(t).append(" ").append(total).append("\n");
        }
        // 결과 출력
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    /**
     * DFS를 사용하여 지정된 자석과 인접한 자석들의 회전 여부를 결정하고, 회전 처리 수행
     *
     * @param target    현재 회전 처리 중인 자석의 인덱스 (0 ~ 3)
     * @param direction 현재 자석을 회전시킬 방향
     *                  내부적으로는 시계 방향은 -1, 반시계 방향은 1로 사용 (인덱스 이동 기준)
     */
    private static void dfs(int target, int direction) {
        // 현재 자석의 왼쪽 및 오른쪽 자석이 회전해야 하는지 여부를 결정하는 플래그
        boolean leftFlag = false;
        boolean rightFlag = false;

        /*
            [왼쪽 자석 회전 조건 체크]
            - 왼쪽 자석이 존재하는지 확인 (target - 1 >= 0)
            - 현재 자석의 왼쪽 접합부 톱니 (인덱스: (redAllowIdx[target] + 6) % 8)
              와 왼쪽 자석의 오른쪽 접합부 톱니 (인덱스: (redAllowIdx[target - 1] + 2) % 8)를 비교
              => 두 톱니의 극이 다르면 회전해야 함.
            - 아직 방문(회전 처리)하지 않은 자석이어야 함 (visited[target - 1] == false)
        */
        if ((target - 1 >= 0) &&
                (magnetLst[target][(redAllowIdx[target] + 6) % 8] != magnetLst[target - 1][(redAllowIdx[target - 1] + 2) % 8]) &&
                (!visited[target - 1])) {
            leftFlag = true;
        }

        /*
            [오른쪽 자석 회전 조건 체크]
            - 오른쪽 자석이 존재하는지 확인 (target + 1 < 4)
            - 현재 자석의 오른쪽 접합부 톱니 (인덱스: (redAllowIdx[target] + 2) % 8)
              와 오른쪽 자석의 왼쪽 접합부 톱니 (인덱스: (redAllowIdx[target + 1] + 6) % 8)를 비교
              => 두 톱니의 극이 다르면 회전해야 함.
            - 아직 방문(회전 처리)하지 않은 자석이어야 함 (visited[target + 1] == false)
        */
        if ((target + 1 < 4) &&
                (magnetLst[target][(redAllowIdx[target] + 2) % 8] != magnetLst[target + 1][(redAllowIdx[target + 1] + 6) % 8]) &&
                (!visited[target + 1])) {
            rightFlag = true;
        }

        // 현재 자석 회전 처리
        // 회전 방향에 따라 화살표(지정된 톱니)의 인덱스를 업데이트
        // (현재 인덱스 + direction + 8) % 8 을 사용해 음수 인덱스 방지
        redAllowIdx[target] = (redAllowIdx[target] + direction + 8) % 8;
        // 현재 자석을 방문 처리하여 중복 회전을 방지
        visited[target] = true;

        // 조건에 따라 인접 자석을 재귀적으로 회전 처리 (반대 방향 회전)
        if (leftFlag)  dfs(target - 1, direction * -1);
        if (rightFlag) dfs(target + 1, direction * -1);
    }
}