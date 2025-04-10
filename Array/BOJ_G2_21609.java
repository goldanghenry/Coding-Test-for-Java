package Array;

import java.io.*;
import java.util.*;

/*
 * BOJ 21609 Gold2, 상어 중학교
 * https://www.acmicpc.net/problem/21609
 * NxN, 초기 모든 칸에는 블록이 하나씩 들어있다
 * 검은색 -1, 무지개 0, 일반블록은 M가지 생상, 빈칸은 -2
 * 블록 그룹은 연결된 블록 집합, 일반 블록이 적어도 하나는 있어야 하며, 그 색은 같아야 함
 * -> 검은색은 포함x, 무지개는 개수 무관, 최소 2이상, 모든 그룹이 연결되어야 함
 * -> 기존 블록 : 일반 블록 중 행의 번호가 가장 작은 블록, 같다면 열의 번호가 작은 블록
 * 
 * 오토 플레이 기능
 * 1. 크기가 가장 큰 그룹 > 무지개 블록의 수가 가장 많은 그룹 > 기준 블록의 행이 가장 큰 것 > 열이 가장 큰 것
 * 2. 1에서 찾은 블록 그룹의 모든 블록을 제거 -> 블록의 수^2 만큼 점수 획득
 * 3. 격자에 중력이 작용
 * 4. 90도 반시계 방향으로 회전
 * 5. 다시 격자에 중력이 작용
 * 
 * ** 중력 작용
 * -> 검은색 블록을 제외한 모든 블록이 행의 번호가 큰 칸으로 이동, 즉 떨어짐
 * -> 검은색 위로 블록이 쌓일 수 있다.
 */

public class BOJ_G2_21609 {
    static int N, M; // N: 격자 크기, M: 색상 종류 수
    static int[][] map;
    static int totalScore = 0;

    // 상하좌우 이동
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // 그룹 정보를 저장하는 클래스 (우선순위: 그룹 크기, 무지개 개수, 기준 블록의 행, 기준 블록의 열 순으로 결정)
    static class BlockGroup implements Comparable<BlockGroup> {
        int size;
        int rainbow;
        int standardRow;
        int standardCol;
        ArrayList<Point> blocks;

        public BlockGroup(int size, int rainbow, int standardRow, int standardCol, ArrayList<Point> blocks) {
            this.size = size;
            this.rainbow = rainbow;
            this.standardRow = standardRow;
            this.standardCol = standardCol;
            this.blocks = blocks;
        }

        @Override
        public int compareTo(BlockGroup other) {
            // 1. 그룹의 크기가 큰 것
            if (this.size != other.size) return other.size - this.size;
            // 2. 무지개 블록의 개수가 많은 것
            if (this.rainbow != other.rainbow) return other.rainbow - this.rainbow;
            // 3. 기준 블록의 행 번호가 큰 것
            if (this.standardRow != other.standardRow) return other.standardRow - this.standardRow;
            // 4. 기준 블록의 열 번호가 큰 것
            return other.standardCol - this.standardCol;
        }
    }

    // 좌표 저장용 클래스
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 오토 플레이: 제거 가능한 그룹이 없을 때까지 반복
        while (true) {
            BlockGroup group = findBestGroup();
            if (group == null) break;

            removeGroup(group);
            totalScore += group.size * group.size;

            applyGravity();
            rotate();
            applyGravity();
        }

        System.out.println(totalScore);
    }

    // 격자 내에서 제거 가능한 그룹 중 조건에 맞는 최선의 그룹을 찾는다.
    static BlockGroup findBestGroup() {
        boolean[][] visited = new boolean[N][N];
        PriorityQueue<BlockGroup> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 시작점은 일반 블록 (양수)여야 하며 아직 방문하지 않은 곳
                if (!visited[i][j] && map[i][j] > 0) {
                    BlockGroup group = bfs(i, j, map[i][j], visited);
                    if (group != null && group.size >= 2) {
                        pq.offer(group);
                    }
                }
            }
        }

        return pq.isEmpty() ? null : pq.poll();
    }

    // BFS를 통해 하나의 그룹을 찾는다.
    static BlockGroup bfs(int sr, int sc, int color, boolean[][] visited) {
        Queue<Point> q = new LinkedList<>();
        ArrayList<Point> groupBlocks = new ArrayList<>();
        ArrayList<Point> rainbowBlocks = new ArrayList<>();

        q.offer(new Point(sr, sc));
        groupBlocks.add(new Point(sr, sc));
        visited[sr][sc] = true;

        int cnt = 1;
        int rainbowCnt = 0;
        // 기준 블록 (일반 블록 중, 색이 0이 아닌) => 문제에서 "기준 블록"은 원래 행, 열이 가장 작은 것을 선택하지만
        // 최종 비교 시에는 "행이 큰, 열이 큰" 순으로 정렬하므로, 여기서는 **최솟값**으로 기록한다.
        int standardRow = sr, standardCol = sc;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dx[d];
                int nc = cur.c + dy[d];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (visited[nr][nc]) continue;
                // 검은 블록(-1)과 빈 칸(-2)은 탐색하지 않음
                if (map[nr][nc] == -1 || map[nr][nc] == -2) continue;
                // 같은 색이거나 무지개 블록(0)인 경우에 탐색
                if (map[nr][nc] == color || map[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    q.offer(new Point(nr, nc));
                    groupBlocks.add(new Point(nr, nc));
                    cnt++;
                    if (map[nr][nc] == 0) {
                        rainbowCnt++;
                        rainbowBlocks.add(new Point(nr, nc));
                    } else {
                        // 일반 블록일 경우, 기준 블록은 "행 번호가 가장 작은, 같다면 열 번호가 가장 작은" 것으로 갱신
                        if (nr < standardRow || (nr == standardRow && nc < standardCol)) {
                            standardRow = nr;
                            standardCol = nc;
                        }
                    }
                }
            }
        }

        // 무지개 블록은 다른 그룹에서도 사용 가능하도록 방문 여부 재설정
        for (Point p : rainbowBlocks) {
            visited[p.r][p.c] = false;
        }
        return new BlockGroup(cnt, rainbowCnt, standardRow, standardCol, groupBlocks);
    }

    // 선택된 그룹의 블록들을 제거하여 빈 칸(-2)으로 만든다.
    static void removeGroup(BlockGroup group) {
        for (Point p : group.blocks) {
            map[p.r][p.c] = -2;
        }
    }

    // 중력 작용: 각 열에 대해 검은 블록(-1)을 만나기 전까지 블록을 아래로 이동시킴
    static void applyGravity() {
        for (int j = 0; j < N; j++) {
            for (int i = N - 2; i >= 0; i--) {
                if (map[i][j] >= 0) { // 일반 또는 무지개 블록
                    int r = i;
                    while (r + 1 < N && map[r + 1][j] == -2) {
                        r++;
                    }
                    if (r != i) {
                        map[r][j] = map[i][j];
                        map[i][j] = -2;
                    }
                }
            }
        }
    }

    // 90도 반시계 방향 회전: (i, j) -> (N-1-j, i)
    static void rotate() {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[N - 1 - j][i] = map[i][j];
            }
        }
        map = temp;
    }
}