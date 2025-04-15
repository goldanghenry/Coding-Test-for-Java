package Implementation;

import java.io.*;
import java.util.*;

/*
 * BOJ 16236 Gold 3, 아기 상어
 * https://www.acmicpc.net/problem/16236
 * N x N, 물고기 M, 아기 상어 1.
 * 아기 상어와 물고기는 크기를 가지는데, 아기상어는 초기 2의 크기
 * -> 아기상어 : 1초에 상하좌우로 인접한 한 칸씩 이동
 * -> 자신의 크기보다 큰 물고기가 있는 칸은 지날 수 없고, 나머지 칸은 모두 지날 수 있음(먹을 수 있음)
 * 
 ** 아기상어가 어디로 이동할지 결정하는 방법
 *  1) 더 이상 먹을 수 있는 물고기가 공간에 없다면, 아기 상어는 엄마 상어에게 도움 요청
 *  2) 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
 *  3) 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
 *  -> 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 갈 때, 지나야 하는 칸의 개수의 최소값(같다면 행이 작거나, 열이 작은)
 *  4) 이동에는 1초, 먹는데는 소요 시간 없음, 빈칸이 됨
 *  5) 자신의 크기 만큼 먹으면 1 증가됨
 * 
 *  -> 즉 물고기를 모두 잡아먹는데 걸리는 시간
 */

public class BOJ_G3_16236 {
    static int N, time;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Shark shark;

    static class Shark {
        int x, y, size, eat;
        Shark(int x, int y) {
            this.x = x;
            this.y = y;
            this.size = 2;
            this.eat = 0;
        }
    }

    static class Fish implements Comparable<Fish>{
        int x,y,dist;
        Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Fish o) {
            if (this.dist != o.dist) return Integer.compare(this.dist, o.dist);
            else if (this.x != o.x) return Integer.compare(this.x, o.x);
            return Integer.compare(this.y, o.y);
        }
    }

    private static Fish bfs() {
        Queue<Fish> q = new LinkedList<>();
        PriorityQueue<Fish> pq = new PriorityQueue<>();
        visited = new boolean[N][N];
        visited[shark.x][shark.y] = true;
        q.offer(new Fish(shark.x, shark.y, 0));

        while(!q.isEmpty()) {
            Fish cur = q.poll();
            
            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (map[nx][ny] > shark.size || visited[nx][ny]) continue;  
                visited[nx][ny] = true;
                if (map[nx][ny] != 0 && map[nx][ny] < shark.size) { // 먹을 수 있는가?
                    pq.offer(new Fish(nx,ny,cur.dist+1));   // 물고기 저장
                }
                q.offer(new Fish(nx,ny,cur.dist+1));
            }
        }

        if (!pq.isEmpty()) return pq.poll();
        else return null;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {  // 아기 상어
                    shark = new Shark(i,j);
                    map[i][j] = 0;
                }
            }
        }

        while(true) {
            Fish target = bfs();
            // 기저조건 -> 다 먹음
            if (target == null) break;

            // 이동 및 시간 누적
            shark.x = target.x;
            shark.y = target.y;
            time += target.dist;
            shark.eat++;

            // 물고기 먹기
            map[target.x][target.y] = 0;

            // 크기 증가
            if (shark.eat == shark.size) {
                shark.eat = 0;
                shark.size++;
            }
        }
        
        System.out.println(time);
    }
}
