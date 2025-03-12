import java.io.*;
import java.util.*;

public class Solution {
    static int M, result;                           // 사람 수
    static int[][] list, stairs;            // 사람 좌표, 계단 좌표
    static PriorityQueue<Integer>[]times;   // 각 입구까지 걸리는 시간
    private static void dfs(int depth) {
        // 기저 조건
        if (depth == M) {
            // 하나라도 빈다면 그때 멈춤
            int time = 1;
            Queue<Integer> downStairs[] = new ArrayDeque[2];
            downStairs[0] = new ArrayDeque<>();
            downStairs[1] = new ArrayDeque<>();

            // 모든 사람이 다 내려가는데 걸리는 시간
            int[] res = new int[2];
            // 모두 빠져나가면 종료
            while(true) {
                if(res[0] != 0 && res[1] != 0) break;

                // 계단 내려가기
                for (int cur = 0; cur < 2; cur++) {
                    if (res[cur] != 0) continue;
                    // 계단에 사람이 있다면 내려가기
                    if (downStairs[cur].size() > 0) {
                        int size = downStairs[cur].size();
                        for (int i = 0; i < size; i++) {
                            if (downStairs[cur].peek() == time) {  // 나갈 수 있는 시간이 되면
                                downStairs[cur].poll();            // 탈출
                            }
                        }
                    }
                }

                // 대기열에 시간 확인
                for (int cur = 0; cur < 2; cur++) {
                    if (res[cur] != 0) continue;
                    Queue<Integer> queue  = new ArrayDeque<>();
                    int size = times[cur].size();                   // 대기열 길이 확인
                    for (int i = 0; i < size; i++) {
                        if (!times[cur].isEmpty() && times[cur].peek() == time) {        // 현재 입장해야하는 순서라면
                            int next = times[cur].poll();       // 꺼내기
                            if (downStairs[cur].size() < 3) {   // 계단에 들어설 수 있다면
                                downStairs[cur].add(next+stairs[cur][2]);      // 시간 + 계단 길이 downStairs 계단에 넣기
                            } else {
                                queue.offer(next+1);   // 계단에 들어설 수 없다면, 대기시간 증가 후 다시 큐에 넣기
                            }
                        }
                        else break;          // 현재 시간을 모두 처리함
                    }
                    while(!queue.isEmpty()) {
                        times[cur].offer(queue.poll());
                    }
                }

                // 모두 다 빠져나갔는지 조사
                for (int cur = 0; cur < 2; cur++) {
                    if (times[cur].isEmpty() && downStairs[cur].isEmpty()) {
                        res[cur] = time;
                        if (res[cur] > result) return;  // 가지치기
                    }
                }
                time++; // 시간 증가
            }
            result = Math.min(result, Math.max(res[0],res[1]) );
            return;
        }

        // 실행
        PriorityQueue<Integer>[] backup = times;   // 백업
        for (int cur = 0; cur < 2; cur++) {
            times[cur].offer(Math.abs(list[depth][0] - stairs[cur][0]) + Math.abs(list[depth][1] - stairs[cur][1]));
            dfs(depth+1);
            times[cur] = backup[cur];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t<=T; t++) {
            int N = Integer.parseInt(br.readLine());

            list = new int[10][2];  // 사람 좌표
            stairs = new int[2][3]; // 계단 x, y, 길이
            M = 0;
            result = Integer.MAX_VALUE;
            boolean aFlag = true;

            // 입력 받기
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    if (num > 0) {
                        // 사람인 경우
                        if (num == 1) list[M++] = new int[]{i,j};
                            // 계단인 경우
                        else {
                            if (aFlag) {
                                stairs[0] = new int[]{i, j, num};
                                aFlag = false;
                            }
                            else stairs[1] = new int[]{i, j, num};
                        }
                    }
                }
            }

            // 백트래킹
            times = new PriorityQueue[2];  // 입구까지 걸리는 시간
            times[0] = new PriorityQueue<>();
            times[1] = new PriorityQueue<>();
            dfs(0);

            // 결과 출력
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }
}