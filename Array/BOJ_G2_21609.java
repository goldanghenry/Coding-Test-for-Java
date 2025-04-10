package Array;

import java.io.*;
import java.util.*;

/*
 * 상어 중학교
 * https://www.acmicpc.net/problem/21609
 * NxN, 초기 모든 칸에는 블록이 하나씩 들어있다
 * 검은색 -1, 무지개 0, 일반블록은 M가지 생상
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
 * -> 검은색 블록으 제외한 모든 블록이 행의 번호가 큰 칸으로 이동, 즉 떨어짐
 * -> 검은색 위로 블록이 쌓일 수 있다.
 */

public class BOJ_G2_21609 {
    static int N, M;
    static int[][] map;
    static int[][] group;

    private static class group implements Comparable<group> {
        int cnt, rainbow, row, col;
        public group (int cnt, int rainbow, int row, int col) {
            this.cnt = cnt;
            this.rainbow = rainbow;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(group o) {
            if (this.cnt ==  o.cnt) {
                if(this.rainbow == o.rainbow) {
                    if (this.row == o.row) {
                        return Integer.compare(this.col, o.col);
                    } else return Integer.compare(this.row, o.row);
                } else return Integer.compare(this.rainbow, o.rainbow);
            } else return Integer.compare(this.cnt, o.cnt);
        }
    }
    // 그룹을 찾아서...
    private static int[] bfs(int x, int y, int idx) {
        Queue<int[]> queue = new LinkedList<>();



        return int[];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];    // 맵 생성
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        group = new int[N][N];  // 그룹 생성
        int idx = 1;            // 그룹 시작 번호
        // 삭제할 그룹 찾기 -> bfs
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j]==-1 || group[i][j] != 0) continue; // 검은색 블럭과 이미 그룹에 소속 됐거나, 
                bfs(i,j, idx);
            }
        }


    }
}
