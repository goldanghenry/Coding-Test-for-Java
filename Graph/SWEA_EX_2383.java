package Graph;

import java.io.*;
import java.util.*;

/*
 * SWEA EX 2383. 점심 식사시간
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5-BEE6AK0DFAVl
 */

public class SWEA_EX_2383 {
    static int N, M;                 // 방 크기 N, 사람 수 M
    static int[][] list;            // 사람들의 위치 (최대 10명)
    static int[][] stairs;          // 계단 정보: [0] = {r, c, 길이K}, [1] = {r, c, 길이K}
    static int[] choice;            // choice[i] = 0번 계단 or 1번 계단
    static int result;              // 최소 시간

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());   // 테스트 케이스 수

        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            list   = new int[10][2];  // 최대 10명의 사람 위치
            stairs = new int[2][3];   // 2개 계단: (r, c, K)
            M = 0;                    // 사람 수

            boolean firstStair = true;    // 계단 정보를 저장하기 위한 플래그

            // 지도 입력 받기
            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    int val = Integer.parseInt(st.nextToken());
                    if(val == 1) {
                        // 사람
                        list[M][0] = i;
                        list[M][1] = j;
                        M++;
                    } else if(val > 1) {
                        // 계단 (길이 val)
                        if(firstStair) {
                            stairs[0][0] = i;
                            stairs[0][1] = j;
                            stairs[0][2] = val;
                            firstStair = false;
                        } else {
                            stairs[1][0] = i;
                            stairs[1][1] = j;
                            stairs[1][2] = val;
                        }
                    }
                }
            }

            // choice 배열: 각 사람마다 어느 계단(0/1)로 갈지 기록
            choice = new int[M];
            result = Integer.MAX_VALUE;

            // DFS를 통해 모든 배분 경우를 탐색
            dfs(0);

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }

        System.out.println(sb);
    }

    /**
     * DFS: 사람 idx번을 0번 계단으로 갈지, 1번 계단으로 갈지 결정
     */
    private static void dfs(int depth) {
        // 모든 사람에 대해 배정을 마친 경우 -> 시뮬레이션
        if(depth == M) {
            simulate();
            return;
        }

        // 0번 계단으로 배정
        choice[depth] = 0;
        dfs(depth + 1);

        // 1번 계단으로 배정
        choice[depth] = 1;
        dfs(depth + 1);
    }

    /**
     * 현재 choice[] 배열에 따라,
     * 각 계단에 도착할 사람들의 "도착 시간 리스트"를 만든 뒤,
     * 계단 이용 규칙(동시 최대 3명, 계단 길이 K, 도착 후 1분 후에 진입 등)에 따라
     * 최종 걸리는 시간을 계산 -> result 갱신
     */
    private static void simulate() {
        // 1. 각 계단별로 도착 시간 리스트 만들기
        ArrayList<Integer> arrivals0 = new ArrayList<>();
        ArrayList<Integer> arrivals1 = new ArrayList<>();

        for(int i=0; i<M; i++){
            // i번째 사람의 위치
            int r = list[i][0];
            int c = list[i][1];

            // 어느 계단?
            int stairIdx = choice[i];
            // 해당 계단 좌표
            int sr = stairs[stairIdx][0];
            int sc = stairs[stairIdx][1];
            // 맨해튼 거리
            int dist = Math.abs(r - sr) + Math.abs(c - sc);

            if(stairIdx == 0) {
                arrivals0.add(dist);
            } else {
                arrivals1.add(dist);
            }
        }

        // 오름차순 정렬 (도착 시간이 빠른 순으로)
        Collections.sort(arrivals0);
        Collections.sort(arrivals1);

        // 2. 두 계단 각각에 대해 "실제 내려가는 데 끝나는 시간" 계산
        int time0 = calcStairTime(arrivals0, stairs[0][2]);
        int time1 = calcStairTime(arrivals1, stairs[1][2]);

        // 3. 두 계단을 모두 내려가야 하므로, 더 큰 시간이 전체 완료 시간
        int finish = Math.max(time0, time1);

        // 4. 전역 result 갱신
        result = Math.min(result, finish);
    }

    /**
     * 하나의 계단에 대해, peopleArrivals(도착시간 리스트)와 계단 길이 K를 가지고
     * "모든 사람이 계단을 다 내려가는 데 걸리는 최종 시간"을 구해 반환한다.
     *
     * 규칙:
     * - 도착 후 1분 대기 후(=arrival+1)부터 내려가기 시작 가능
     * - 계단 위에는 동시 최대 3명만 존재할 수 있음
     * - 내려가는 데 K분 소요(시작 시각 + K = 그 사람이 계단을 끝까지 내려간 시각)
     */
    private static int calcStairTime(List<Integer> peopleArrivals, int K) {
        if(peopleArrivals.isEmpty()) return 0; // 해당 계단으로 오는 사람이 없으면 0

        // 각 사람의 "실제 계단을 다 내려놓는 시각"을 순차적으로 계산
        // 3명이 동시에 내려갈 수 있으므로, "현재 사용 중인 사람들의 '끝나는 시각' 관리"가 필요

        // '끝나는 시각'을 저장하는 작은 우선순위큐 - 현재 계단을 밟고 있는 사람들
        // 오름차순(가장 빨리 끝나는 사람부터 꺼내기)
        PriorityQueue<Integer> onStair = new PriorityQueue<>();

        int lastFinishTime = 0; // 전체 중 제일 늦게 끝난 시각

        for(int arrival : peopleArrivals){
            // 사람이 계단에 도착한 시각은 arrival
            int startPossible = arrival + 1; // 도착 후 1분 뒤부터 계단 내려가기 시작

            // 만약 계단에 이미 3명이 올라가 있다면,
            // 가장 빨리 끝나는 사람이 계단을 비워줄 때까지 대기해야 함
            if(onStair.size() == 3) {
                // 계단을 밟고 있는 사람 중 가장 빨리 끝나는 시간
                int earliestFinish = onStair.poll();
                // earliestFinish 시각이 되기 전까지는 새 사람은 못 들어감
                // 즉, startPossible은 적어도 earliestFinish 이후여야 함
                if(earliestFinish > startPossible) {
                    startPossible = earliestFinish;
                }
            }

            // 이제 startPossible 시각부터 내려가기 시작 → 내려가는 데 K분
            int finishTime = startPossible + K;

            // 그 사람을 onStair에 올린다(그 사람이 끝나는 시각 저장)
            onStair.offer(finishTime);

            // 전체 중 가장 늦게 끝난 시각 갱신
            lastFinishTime = Math.max(lastFinishTime, finishTime);
        }

        // 모든 사람을 순서대로 처리한 뒤, 결국 lastFinishTime이 그 계단에서의 최종 완료시각
        return lastFinishTime;
    }
}