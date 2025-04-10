package Implementation;

import java.io.*;
import java.util.*;

/*
 * BOJ 3190 Gold 4, 뱀
 * https://www.acmicpc.net/problem/3190
 */

public class BOJ_G4_3190 {
    static int[] dx = {0,1,0,-1};   // 시계방향(R) 오른 -> 아래 -> 왼 -> 위
    static int[] dy = {1,0,-1,0};
    static class  Cmd {
        int time;
        char command;
        Cmd(int time, char command) {
            this.time = time;
            this.command = command;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());    // 맵의 크기
        int K = Integer.parseInt(br.readLine());    // 사과의 개수

        int[][] map = new int[N][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1; // 사과의 좌표
            int c = Integer.parseInt(st.nextToken())-1;
            map[r][c] = 1;          // 사과 -> 1
        }

        int L = Integer.parseInt(br.readLine());    // 명령어 수
        Queue<Cmd> commands = new LinkedList<>();   // 명령어 저장
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());   // 행동하는 시간대
            char c = st.nextToken().charAt(0);    // 회전 방향
            commands.offer(new Cmd(x, c));
        }

        int x = 0,y = 0, dir = 0;   // 뱀은 계속해서 길어질 수 있으므로 큐에 먼저 들어간 부분이 꼬리의 제일 끝
        Queue<int[]> snake = new LinkedList<>();    
        snake.offer(new int[]{x,y});                // 뱀의 초기 위치
        boolean[][] visited = new boolean[N][N];
        visited[x][y] = true;
        
        int t = 0;
        while(true) {
            t++;    // 시간 증가
            int nx = x + dx[dir];   // 몸 길이를 늘려 머리를 다음 칸에 위치시킨다
            int ny = y + dy[dir];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) break;   // 벽이나 자기 자신의 몸과 부딪히면 게임 오버
            snake.offer(new int[]{nx,ny});  // 머리를 새로운 위치에 추가
            visited[nx][ny] = true;

            if (map[nx][ny] == 1) { // 만약 이동한 칸에 사과가 있다면
                map[nx][ny] = 0;    // 그 칸에 있던 사과가 없어지고
            } else {                // 만약 이동한 칸에 사과가 없다면
                int[] tail = snake.poll();
                visited[tail[0]][tail[1]] = false; // 몸 길이를 줄여서 꼬리가 위치한 칸을 비워준다.
            }
            
            // x 초가 끝난 뒤에 방향 전환
            if (!commands.isEmpty() && t == commands.peek().time)  {
                Cmd cmd = commands.poll();
                if (cmd.command == 'D') dir = (dir+1)%4;
                else dir = (dir+3)%4;
            }
            x = nx; // 헤드 이동
            y = ny;
        }
        System.out.println(t);
    }
}
