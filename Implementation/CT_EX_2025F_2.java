package Implementation;

import java.io.*;
import java.util.*;

/*
 * Code Tree 2025 삼성전자 상반기 오전 2번 문제, 개구리의 여행
 * https://www.codetree.ai/ko/frequent-problems/problems/frog-journey/description
 * N x N, 개구리는 돌 위로만 돌아다닐 수 있다.
 * 세 종류의 돌 - 안전한 돌(.), 미끄러운 돌(S), 천적이 사는 돌(#)
 * 초기 점프력 1
 * 
 * 1. 점프 -> 1만큼 시간 소모
 * - 현재 위치에서 상하좌우 방향 -> 점프력만큼 이동
 *      -> 이동하려는 위치에 안전한 돌이 없다면, 그 위치로는 이동 불가
 *      -> 점프하면서 지나는 경로에 천적이 사는 돌이 있는 경우에도 이동 불가
 * 
 * 2. 점프력 증가 -> 증가 후,k -> k^2 만큼 시간 소모
 *      -> 최대 점프력은 5, 기존 점프력이 1~4인 경우에만 증가 가능
 * 
 * 3. 점프력 감소 -> 1만큼 소모
 *      -> 최소 점프력은 1.
 * 
 * => 최소 시간 출력. 도착이 불가능한 계획이 있다면 -1
 */

public class CT_EX_2025F_2 {
    static int N, Q, res;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static char[][] map;
    static boolean[][][] v;
    

    private static class Frog implements Comparable<Frog> {
        int x, y, jump, time;
        Frog(int x, int y, int jump, int time) {
            this.x = x;
            this.y = y;
            this.jump = jump;
            this.time = time;
        }

        @Override
        public int compareTo(Frog o) {
            return Integer.compare(this.time, o.time);
        }
    }

    private static int bfs(int x1, int y1, int x2, int y2) {
        PriorityQueue<Frog> pq = new PriorityQueue<>();
        pq.add(new Frog(x1, y1, 1, 0));
        v[x1][y1][1] = true;
        res = Integer.MAX_VALUE;

        while(!pq.isEmpty()) {
            Frog cur = pq.poll();

            if (cur.x == x2 && cur.y == y2) {
                res = Math.min(res,cur.time);
            }

            // 1. 점프 -> 사방향
            for (int k = 0; k < 4; k++) {
                int nx = x1;
                int ny = y1;

                // 점프가 가능한가?
                boolean flag = false;
                for (int j = 1; j <= cur.jump; j++) {
                    nx += dx[k];
                    ny += dy[k];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (v[nx][ny][cur.jump] || map[nx][ny]=='#') continue;
                    if (j == cur.jump && map[nx][ny] == 'S') continue;
                    if (j== cur.jump) flag = true;
                }
                if (flag) {
                    pq.add(new Frog(nx, ny, cur.jump, cur.time+1));
                    v[nx][ny][cur.jump] = true;
                }
            }

            // 2. 점프력 증가
            int tj = cur.jump+1;
            if (cur.jump < 5 && !v[cur.x][cur.y][tj]) {
                pq.add(new Frog(cur.x, cur.y, tj, cur.time + (tj * tj)));
                v[cur.x][cur.y][tj] = true;
            }

            // 3. 점프력 감소
            if (cur.jump > 1) {
                for (int i = cur.jump-1; i > 0; i++) {
                    if (!v[cur.x][cur.y][i]) {
                        pq.add(new Frog(cur.x, cur.y, i, cur.time + 1));
                        v[cur.x][cur.y][i] = true;
                    }
                }
            }
        }
        if (res == Integer.MAX_VALUE) {
            return -1;
        } else {
            return res;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        Q = Integer.parseInt(br.readLine());
        for (int t = 0; t < Q; t++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken())-1;  // 출발점
            int sy = Integer.parseInt(st.nextToken())-1;
            int tx = Integer.parseInt(st.nextToken())-1;  // 도착점
            int ty = Integer.parseInt(st.nextToken())-1;

            v = new boolean[N][N][6];

            int ans = bfs(sx,sy,tx,ty);
            sb.append(ans+"\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
