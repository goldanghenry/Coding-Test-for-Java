package Implementation;

import java.io.*;
import java.util.*;

/*
 * BOJ 16918 Silver 1 봄버맨
 * https://www.acmicpc.net/problem/16918
 * 
 * [문제 요약]
 * - R x C 격자에 폭탄('O') 또는 빈칸('.')이 주어짐.
 * - 폭탄은 설치 후 3초 뒤에 폭발하며, 폭발 시 상하좌우 4칸도 함께 파괴됨.
 * - 폭발한 칸은 빈칸('.')이 됨.
 * 
 * [이동 규칙]
 * 1. 초기 상태: 일부 칸에 폭탄이 설치되어 있음 (동일한 시간에 설치)
 * 2. 1초 후: 아무 행동도 하지 않음.
 * 3. 2초 후: 빈 칸에 모두 폭탄 설치 (설치된 시간 동일하게 3초 타이머 부여)
 * 4. 3초 후: 3초 전에 설치된 폭탄이 모두 폭발 (인접 4칸도 폭발)
 * 5. 3, 4번 과정을 반복
 * 
 * -> N초가 지난 후의 격자판 상태를 출력
 */

class BOJ_S1_16918 {
    static int[][] map;
    static int R, C;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        // map : -1 => 빈 칸, 양수 => 폭탄 (남은 시간)
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                if (line.charAt(j) == 'O') {
                    map[i][j] = 3;  // 폭탄은 3초 후 폭발
                } else {
                    map[i][j] = -1; // 빈 칸
                }
            }
        }

        // N이 1초일 경우 초기 상태 그대로 출력
        if (N == 1) {
            printMap(sb);
            bw.write(sb.toString());
            bw.flush();
            bw.close();
            return;
        }

        // 1초부터 N초까지 시뮬레이션 진행
        for (int time = 1; time <= N; time++) {
            // 매 초 폭탄만 타이머 감소 (빈 칸은 그대로 유지)
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] > 0) {
                        map[i][j]--;
                    }
                }
            }

            // 첫 번째 초는 아무 행동도 하지 않음
            if (time == 1) continue;

            if (time % 2 == 0) {
                // 짝수 초: 빈 칸에 폭탄 설치 (3초 타이머 부여)
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        if (map[i][j] == -1) {
                            map[i][j] = 3;
                        }
                    }
                }
            } else {
                // 홀수 초: 타이머가 0인 폭탄 폭발 처리
                boolean[][] exploded = new boolean[R][C];
                // 폭발할 위치 마킹
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        if (map[i][j] == 0) { // 폭탄 폭발
                            exploded[i][j] = true;
                            for (int d = 0; d < 4; d++) {
                                int nx = i + dx[d];
                                int ny = j + dy[d];
                                if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                                exploded[nx][ny] = true;
                            }
                        }
                    }
                }
                // 폭발한 칸을 빈 칸(-1)으로 전환
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        if (exploded[i][j]) {
                            map[i][j] = -1;
                        }
                    }
                }
            }
        }
        printMap(sb);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    // 현재 맵 상태를 StringBuilder에 append
    static void printMap(StringBuilder sb) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == -1) {
                    sb.append(".");
                } else {
                    sb.append("O");
                }
            }
            sb.append("\n");
        }
    }
}
