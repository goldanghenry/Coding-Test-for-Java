package Implementation;

import java.io.*;
import java.util.*;

/*
    BOJ 17135 Gold3, 캐슬 디펜스
    https://www.acmicpc.net/problem/17135
 */

public class BOJ_G3_17135 {
    // 전역 변수: 보드의 행(N), 열(M), 공격 사거리(D), 그리고 최대 제거 적 수(result)
    static int N, M, D, result;
    // 원본 보드를 저장 (0: 빈 칸, 1: 적)
    static int[][] board;
    // 현재 선택된 궁수의 위치(열 번호)를 저장하는 배열 (총 3명의 궁수)
    static int[] sel = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        // 보드 입력 받기
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;
        // M개의 열 중에서 3개의 궁수 위치를 뽑는 조합 생성
        dfs(0, 0);

        // 최대로 제거 가능한 적의 수 출력
        System.out.println(result);
    }

    // DFS를 이용해 M개의 열 중 3개의 궁수 위치를 선택하는 함수
    static void dfs(int cnt, int start) {
        if(cnt == 3) {
            // 선택된 궁수 위치로 게임 시뮬레이션 진행 후 결과 업데이트
            simulate();
            return;
        }
        for (int i = start; i < M; i++) {
            sel[cnt] = i; // 현재 궁수 위치 선택
            dfs(cnt + 1, i + 1);
        }
    }

    // 현재 궁수 위치에 대해 게임을 시뮬레이션하는 함수
    static void simulate() {
        // 시뮬레이션을 위한 보드 복사 (원본 보드는 그대로 유지)
        int[][] simulationBoard = new int[N][M];
        for (int i = 0; i < N; i++) {
            simulationBoard[i] = board[i].clone();
        }
        int killCount = 0; // 이번 시뮬레이션에서 제거한 적의 수

        // 게임은 최대 N턴 진행 (N턴 후엔 모든 적이 성을 통과)
        for (int turn = 0; turn < N; turn++) {
            // 각 턴마다 궁수들이 동시에 공격할 적의 좌표를 저장하는 set (중복 제거)
            Set<Enemy> targets = new HashSet<>();

            // 각 궁수에 대해 BFS를 이용하여 공격할 적 탐색
            for (int archCol : sel) {
                Enemy target = bfs(simulationBoard, archCol);
                if (target != null) {
                    targets.add(target);
                }
            }

            // 찾은 적들을 제거 (이미 제거된 적은 제외)
            for (Enemy e : targets) {
                if (simulationBoard[e.x][e.y] == 1) {
                    simulationBoard[e.x][e.y] = 0;
                    killCount++;
                }
            }

            // 적들을 한 행 아래로 이동 (성에 도달한 적은 제거됨)
            simulationBoard = moveEnemies(simulationBoard);
        }
        // 최대 제거 수 업데이트
        result = Math.max(result, killCount);
    }

    // BFS를 이용해 궁수(행: N, 열: archCol)로부터 공격할 적을 탐색하는 함수
    // 가장 가까운 적(최소 거리, 동일 거리면 왼쪽에 있는 적)을 반환
    static Enemy bfs(int[][] simBoard, int archCol) {
        Queue<Position> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        // 궁수 바로 위의 행부터 탐색 시작 (궁수는 N행에 위치하므로, 보드의 마지막 행은 N-1)
        if (N - 1 >= 0) {
            queue.offer(new Position(N - 1, archCol, 1));
            visited[N - 1][archCol] = true;
        }

        // 탐색 방향: 왼쪽, 위, 오른쪽 순 (동일 거리일 때 왼쪽 우선)
        int[] dx = {0, -1, 0};
        int[] dy = {-1, 0, 1};

        while (!queue.isEmpty()) {
            Position pos = queue.poll();
            // 공격 사거리를 초과하면 탐색 종료
            if (pos.dist > D) break;

            // 현재 위치에 적이 있으면 해당 적을 공격 대상으로 선택
            if (simBoard[pos.x][pos.y] == 1) {
                return new Enemy(pos.x, pos.y);
            }

            // 인접한 위치 탐색 (왼쪽, 위, 오른쪽 순)
            for (int i = 0; i < 3; i++) {
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];
                // 보드 범위 체크
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new Position(nx, ny, pos.dist + 1));
                }
            }
        }
        // 사거리 내에 공격할 적이 없는 경우
        return null;
    }

    // 적들을 한 행 아래로 이동시키는 함수
    // 마지막 행의 적은 성에 도착하여 제거됨
    static int[][] moveEnemies(int[][] simBoard) {
        int[][] newBoard = new int[N][M];
        // 행 0부터 N-2까지의 적들을 아래 행으로 이동 (행 i의 적은 행 i+1로)
        for (int i = N - 2; i >= 0; i--) {
            newBoard[i + 1] = simBoard[i].clone();
        }
        // 첫 번째 행은 새로운 적이 없으므로 모두 0으로 채움
        Arrays.fill(newBoard[0], 0);
        return newBoard;
    }

    // 보드 내의 위치와 현재까지의 거리를 저장하는 헬퍼 클래스
    static class Position {
        int x, y, dist;
        Position(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    // 적의 좌표를 나타내는 헬퍼 클래스
    // HashSet에 저장하기 위해 equals와 hashCode를 재정의함
    static class Enemy {
        int x, y;
        Enemy(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Enemy enemy = (Enemy) o;
            return x == enemy.x && y == enemy.y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}