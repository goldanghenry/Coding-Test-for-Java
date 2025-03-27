package Graph;

import java.io.*;
import java.util.*;

/*
 * BOJ 2146 Gold3, 다리 만들기
 * https://www.acmicpc.net/problem/2146
 */

public class BOJ_G3_2146 {
    static int N,result;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static List<Queue<Point>> queueList = new ArrayList<>();

    public static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void Mark(Point point, int mark) {
        Queue<Point> queue = new LinkedList<>();
        Queue<Point> recordQue = new LinkedList<>();
        queue.offer(point);
        recordQue.offer(point);
        map[point.x][point.y] = mark;

        while(!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (map[nx][ny] != 1) continue;
                map[nx][ny] = mark;
                Point newPoint = new Point(nx,ny);
                queue.offer(newPoint);
                recordQue.offer(newPoint);
            }
        }
        queueList.add(recordQue);
    }

    private static void findMinDist(Point point, int mark) {
        Queue<Point> queue = new LinkedList<>();
        int[][] v = new int[N][N];
        queue.offer(point);
        v[point.x][point.y] = -1;

        while(!queue.isEmpty()) {
            Point cur = queue.poll();
            
            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (map[nx][ny] == mark || v[nx][ny] > 0) continue;
                v[nx][ny] = v[cur.x][cur.y]+1 ;
                queue.offer(new Point(nx,ny));
                if (map[nx][ny] > 1) {
                    result = Math.min(result, v[nx][ny]);
                    return;
                }
            }
        }
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
            }
        }
        int idx = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 1) continue;
                Mark(new Point(i,j), idx++);
            }
        }

        result = Integer.MAX_VALUE;
        for (int i = 0; i < idx-2;i++) {
            Queue<Point> nation =  queueList.get(i);
            while(!nation.isEmpty()) {
                Point start = nation.poll();
                findMinDist(start, i+2);
            }
        }

        System.out.println(result);
    }
}
