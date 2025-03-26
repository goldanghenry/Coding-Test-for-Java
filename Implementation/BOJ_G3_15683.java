package Implementation;

import java.io.*;
import java.util.*;

/*
 * BOJ 15683 Gold 3, 감시
 * https://www.acmicpc.net/problem/15683
 * [ 5가지의 cctv 종류 ]
 * - 1번 : 한 방향, 2번 : 두 방향(반대), 3번 : 두 방향(직각), 4번 : 세방향, 5번 네 방향
 * 감시할 수 있는 방향에 있는 칸 전체를 감시 가능
 * 벽을 통과할 수 없다 (사각지대), cctv는 통과 가능
 * 회전은 90도로 가능(가로, 세로만 가능)
 * 0은 빈칸, 6은 벽, 1~5 cctv 번호
 * --> CCTV의 방향을 적절히 정해서 사각 지대의 최소 크기를 구하라
 */
public class BOJ_G3_15683 {
    static int N, M;
    static int[][] map;
    static int[] dx = {-1,0,1,0};   // 상, 우, 하, 좌
    static int[] dy = {0,1,0,-1};
    static List<int[]>[] cctvList = new ArrayList[6];   // 각 CCTV 타입별 좌표 저장 (1~5번)
    static int[] counts = new int[6];       // 각 타입별 CCTV 개수
    static int answer = Integer.MAX_VALUE;

    private static void dfs(int type, int instance, List<Integer> combi) {
        if (type == 6) {
            simulate(combi);
            return;
        }
        // 해당 타입의 cctv가 없거나,
        // 현재 타입의 모든 instance에 대해 방향지정이 끝났다면 다음 타입으로
        if (counts[type] ==0 || instance == counts[type]) {         
            dfs(type+1, 0, combi);
            return;
        }

        int[] options;  // 타입에 따라 가능한 회전 옵션 설정
        if (type == 5) options = new int[]{0};
        else if (type ==2) options = new int[]{0,1};
        else options = new int[]{0,1,2,3};

        for (int d : options) {
            combi.add(d);
            dfs(type, instance +1, combi);
            combi.remove(combi.size()-1);
        }
    }

    private static void simulate(List<Integer> combi) {
        // 시뮬레이션을 위한 맵 복사
        int[][] sim = new int[N][M];
        for (int i = 0; i < N; i++) {
            sim[i] = map[i].clone();
        }

        int index = 0;
        // cctv 타입별로 시뮬레이션 진행
        for (int type = 1; type <= 5; type++) {
            for (int i = 0; i < counts[type]; i++) {
                int[] pos = cctvList[type].get(i);
                int x = pos[0], y = pos[1];
                int rot = combi.get(index++);

                // 각 cctv 타입에 따라 감시할 방향 결정
                switch(type) {
                    case 1:
                        monitor(sim, x, y, rot);
                        break;
                    case 2:
                        if (rot == 0) {
                            monitor(sim, x, y, 3); // 왼쪽
                            monitor(sim, x, y, 1); // 오른쪽
                        } else {
                            monitor(sim, x, y, 0); // 왼쪽
                            monitor(sim, x, y, 2); // 왼쪽
                        }
                        break;
                    case 3:
                        // CCTV 3 : 회전값에 따라 직각 2방향 (예: 0: 위, 오른쪽 / 1: 오른쪽, 아래 / 2: 아래, 왼쪽 / 3: 왼쪽, 위)
                        if (rot == 0) {
                            monitor(sim, x, y, 0);
                            monitor(sim, x, y, 1);
                        } else if (rot == 1) {
                            monitor(sim, x, y, 1);
                            monitor(sim, x, y, 2);
                        } else if (rot == 2) {
                            monitor(sim, x, y, 2);
                            monitor(sim, x, y, 3);
                        } else {
                            monitor(sim, x, y, 3);
                            monitor(sim, x, y, 0);
                        }
                        break;
                    case 4:
                        // CCTV 4 : 세 방향 감시 (예: 0: 왼쪽, 위, 오른쪽 / 1: 위, 오른쪽, 아래 / 2: 오른쪽, 아래, 왼쪽 / 3: 아래, 왼쪽, 위)
                        if (rot == 0) {
                            monitor(sim, x, y, 3);
                            monitor(sim, x, y, 0);
                            monitor(sim, x, y, 1);
                        } else if (rot == 1) {
                            monitor(sim, x, y, 0);
                            monitor(sim, x, y, 1);
                            monitor(sim, x, y, 2);
                        } else if (rot == 2) {
                            monitor(sim, x, y, 1);
                            monitor(sim, x, y, 2);
                            monitor(sim, x, y, 3);
                        } else {
                            monitor(sim, x, y, 2);
                            monitor(sim, x, y, 3);
                            monitor(sim, x, y, 0);
                        }
                        break;
                    case 5:
                        // CCTV 5 : 네 방향 모두 감시
                        monitor(sim, x, y, 0);
                        monitor(sim, x, y, 1);
                        monitor(sim, x, y, 2);
                        monitor(sim, x, y, 3);
                        break;
                }
            }
        }
        // 사각지대의 수를 세어 최소값 갱신
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++){
                if (sim[i][j] == 0) cnt++;
            }
        }
        answer = Math.min(answer, cnt);
    }

    private static void monitor(int[][]board, int x, int y, int d) {
        int nx = x, ny = y;
        while(true) {
            nx += dx[d];
            ny += dy[d];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) break;  // 경계를 벗어나면 종료
            if (board[nx][ny] == 6) break;      // 벽을 만나면 종료
            if (board[nx][ny] == 0) board[nx][ny] = -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 행
        M = Integer.parseInt(st.nextToken());   // 열
        map = new int[N][M];

        for(int i = 1; i <= 5; i++ ){
            cctvList[i] = new ArrayList<>();
        }

        // 맵 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if (1 <= num && num <= 5) {
                    cctvList[num].add(new int[]{i,j});  // cctv 좌표 저장
                    counts[num]++;
                }   
            }
        }

        // dfs로 조합 구하기
        List<Integer> combi = new ArrayList<>();
        dfs(0,0,combi);
        System.out.println(answer);
    }
}
