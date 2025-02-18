package Implementation;

import java.io.*;
import java.util.*;
/*
    SWEA 2382. 미생물 격리
    https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV597vbqAH0DFAVl
 */
public class SWEA_2382 {
    static int N, M, K;               // 셀의 크기, 격리 시간, 미생물 군집의 개수
    static boolean[] survival;        // 군집의 생존 여부
    static Micro[] groups;            // 군집 배열
    static int[] dx = {-1, 1, 0, 0};    // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    // 미생물 군집 클래스
    static class Micro {
        int x, y;    // 현재 좌표
        int cnt;     // 미생물 수
        int dir;     // 이동 방향 (0:상, 1:하, 2:좌, 3:우)

        Micro(int x, int y, int cnt, int dir) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        // 입출력 세팅
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            groups = new Micro[K + 1];
            survival = new boolean[K + 1];
            for (int i = 1; i <= K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1; // 1→0, 2→1, 3→2, 4→3
                groups[i] = new Micro(x, y, cnt, dir);
                survival[i] = true;
            }

            // M 시간 동안 시뮬레이션
            for (int time = 0; time < M; time++) {
                // 각 셀에 도착한 군집 번호를 저장할 2차원 리스트 배열 생성
                ArrayList<Integer>[][] cell = new ArrayList[N][N];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        cell[i][j] = new ArrayList<>();
                    }
                }

                // 각 군집 이동 처리
                for (int i = 1; i <= K; i++) {
                    if (!survival[i]) continue; // 소멸한 군집은 건너뜀
                    Micro m = groups[i];
                    int nx = m.x + dx[m.dir];
                    int ny = m.y + dy[m.dir];
                    m.x = nx;
                    m.y = ny;

                    // 약품 셀(가장자리)에 도착한 경우: 미생물 수 절반 감소, 방향 전환
                    if (nx == 0 || ny == 0 || nx == N - 1 || ny == N - 1) {
                        m.cnt /= 2;
                        if (m.cnt == 0) {
                            survival[i] = false;
                            continue;
                        }
                        m.dir ^= 1; // 0<->1, 2<->3
                    }

                    // 해당 셀에 군집 번호 추가
                    cell[nx][ny].add(i);
                }

                // 각 셀별로 병합 처리
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (cell[i][j].size() > 1) { // 여러 군집이 모인 경우
                            int maxIdx = cell[i][j].get(0);
                            int maxCnt = groups[maxIdx].cnt;
                            int sum = 0;
                            // 해당 셀에 모인 모든 군집의 미생물 수 합산 및 최대 군집 판별
                            for (int idx : cell[i][j]) {
                                int cnt = groups[idx].cnt;
                                sum += cnt;
                                if (cnt > maxCnt) {
                                    maxCnt = cnt;
                                    maxIdx = idx;
                                }
                            }
                            // 최대 미생물 수를 가진 군집에 합산 결과 반영
                            groups[maxIdx].cnt = sum;
                            // 나머지 군집은 소멸 처리
                            for (int idx : cell[i][j]) {
                                if (idx != maxIdx) {
                                    survival[idx] = false;
                                }
                            }
                        }
                    }
                }
            }

            // 남은 미생물 수 계산
            int result = 0;
            for (int i = 1; i <= K; i++) {
                if (survival[i]) {
                    result += groups[i].cnt;
                }
            }
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}