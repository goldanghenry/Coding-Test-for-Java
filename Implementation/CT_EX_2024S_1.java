package Implementation;

import java.util.*; 
import java.io.*;

/* Code Tree 2024 삼성전자 하반기 오후 1번 문제, 메두사와 전사들
 * https://www.codetree.ai/ko/frequent-problems/problems/medusa-and-warriors/description
 */

public class CT_EX_2024S_1 {
    static int N, M;
    static int[] s, e, answer;
    static int[][] town, sight;
    static List<int[]> warriors;
    static int[] dr = {-1, 1, 0, 0}, dr2 = {0, 0, -1, 1};
    static int[] dc = {0, 0, -1, 1}, dc2 = {-1, 1, 0, 0};
    
    public static void main(String[] args) throws IOException {
        
        // 입력 받기
        init();
				
        // 메두사 이동경로 생성
        List<int[]> route = bfs();

        // 도달 불가능 예외처리
        if (route == null) {
            System.out.println(-1);
            System.exit(0);
        }

        StringBuilder sb = new StringBuilder();

        // 메두사 이동경로대로 진행
        for (int i = route.size() - 2; i >= 1; i--) {
            int[] r = route.get(i);
            answer = new int[3];
            
            // 해당 위치 병사 삭제
            killWarriors(r);
            
            // 시선 생성
            see(r);
            
            // 병사 이동
            moveWarriors(r);
            
            sb.append(answer[0] + " " + answer[1] + " " + answer[2] + "\n");
        }
        System.out.print(sb);
        System.out.println(0);
    }


    // 시선 생성
    static void see(int[] now) {
        
        // town에 병사 위치 찍어놓기
        for (int i = 0; i < N; i++) Arrays.fill(town[i], 0);
        for (int[] warrior : warriors) town[warrior[0]][warrior[1]]++;

        // 4방향 시선 이동
        int total = 0; // 시선에서 보이는 병사 최댓값 저장
        for (int d = 0; d < 4; d++) {
            int[][] temp = new int[N][N];
            for (int i = 0; i < N; i++) Arrays.fill(temp[i], 0);

            // 현재 방향에서 보이는 총 병사 숫자 확인
            int cur = getSight(now, temp, d);
            
            // 현재 방향이 최대일때만 갱신
            if (total < cur) {
                sight = temp;
                total = cur;
            }
        }
    }

    // 상하좌우 -> 대각선 확인 및 변환을 위한 배열
    static int[][] dir = {{7, 1}, {3, 5}, {5, 7}, {1, 3}};
    static int[] changeDir = {0, 4, 6, 2};
    
    // 8방향 미리 지정
    static int[] dr3 = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc3 = {0, 1, 1, 1, 0, -1, -1, -1};
    
    // 시선 배열 채우기 + 각 방향에서 볼 수 있는 병사 반환
    static int getSight(int[] now, int[][] sight, int d) {
        int r = now[0], c = now[1], cnt = 0;
        boolean hide = false;
        
        // 최초 위치에서 대각선 확인
        cnt += seeDiagonal(now, sight, dir[d], d);
        

        while (true) {
            int nr = r + dr[d], nc = c + dc[d];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) break;
            
            // 1칸씩 시선 방향으로 이동하면서 대각선 확인
            cnt += seeDiagonal(new int[] {nr, nc}, sight, dir[d], d);

            // 처음으로 병사 등장하기 이전에는 1로 채우기(시선 표시)
            if (!hide) sight[nr][nc] = 1;
            
            // 처음으로 병사 등장하는 시점 확인
            if (!hide && town[nr][nc] != 0) {
            
                // 해당 위치에서 돌로 만들기
                makeStone(new int[] {nr, nc}, sight, new int[] {changeDir[d]});
                hide = true;
                
                // 병사 숫자 세기
                cnt += town[nr][nc];
            }
            
            r = nr; c = nc;
        }
        return cnt;
    }

    // 대각선 확인하기
    static int seeDiagonal(int[] now, int[][] sight, int[] ds, int d) {
        int cnt = 0;
        
        // 주어진 방향으로 뻗어나가면서 병사 있는지 확인
        for (int dd : ds) {
            int r = now[0], c = now[1];
            while (true) {
                int nr = r + dr3[dd], nc = c + dc3[dd];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) break;
                
                // 이미 가려진 위치가 있으면(2이면) 탐색 종료
                if (sight[nr][nc] == 2) break;
                
                // 병사 있으면 해당위치에서 돌로 만들고 탐색 종료
                if (town[nr][nc] != 0) {
                    cnt += town[nr][nc];
                    makeStone(new int[]{nr, nc}, sight, new int[] {changeDir[d], dd});
                    r = nr; c = nc; break;
                }
                
                // 해당 위치 1로 채우기(시선 표시)
                sight[nr][nc] = 1;
                r = nr; c = nc;
            }
        }
        return cnt;
    }

    // 돌로 만들기
    static void makeStone(int[] now, int[][] sight, int[] ds) {
    
        // 해당 위치 -2로 변경
        sight[now[0]][now[1]] = -2; 
		    
        // 주어진 방향으로 뻗어나가면서 가리기
        for (int d : ds) {
            int r = now[0], c = now[1];
            while (true) {
                int nr = r + dr3[d], nc = c + dc3[d];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) break;
                
                // 해당 위치 2로 변경(가림 표시)
                sight[nr][nc] = 2;
                r = nr; c = nc;
            }
        }
    }

    // 병사 이동하기
    static void moveWarriors(int[] now) {
    
        loop:
        // 중간에 병사를 제거하므로 뒤에서부터 확인
        for (int i = warriors.size() - 1; i >= 0; i--) {
            int[] warrior = warriors.get(i);
            
            // 해당 위치가 이미 돌이면 돌이 된 병사 숫자에 추가
            if (sight[warrior[0]][warrior[1]] == -2) {
                answer[1]++;
                continue;
            }
            
            // 상하좌우로 메두사 방향으로 이동
            for (int j = 0; j < 4; j++) {
                int nr = warrior[0] + dr[j];
                int nc = warrior[1] + dc[j];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || sight[nr][nc] == 1 || sight[nr][nc] == -2) continue;

                // 메두사와 가까워지는 방향으로만 이동
                if (Math.abs(now[0] - warrior[0]) + Math.abs(now[1] - warrior[1]) <= Math.abs(now[0] - nr) + Math.abs(now[1] - nc)) continue;
                warrior[0] = nr; warrior[1] = nc;
                
                // 이동거리에 추가
                answer[0]++;
                break;
            }
            
            // 해당위치에 메두사 있으면 병사 제거 후 공격 횟수 추가
            if (warrior[0] == now[0] && warrior[1] == now[1]) {
                warriors.remove(i);
                answer[2]++;
                continue loop;
            }
            
            // 좌우상하로 메두사 방향으로 이동(방향만 다르고 로직 동일)
            for (int j = 0; j < 4; j++) {
                int nr = warrior[0] + dr2[j];
                int nc = warrior[1] + dc2[j];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || sight[nr][nc] == 1 || sight[nr][nc] == -2) continue;
                if (Math.abs(now[0] - warrior[0]) + Math.abs(now[1] - warrior[1]) <= Math.abs(now[0] - nr) + Math.abs(now[1] - nc)) continue;
                warrior[0] = nr; warrior[1] = nc;
                answer[0]++;
                break;
            }
            
            // 해당위치에 메두사 있으면 병사 제거 후 공격 횟수 추가
            if (warrior[0] == now[0] && warrior[1] == now[1]) {
                warriors.remove(i);
                answer[2]++;
            }
        }
    }

    // 메두사 이동 후 해당위치 병사 삭제
    static void killWarriors(int[] now) {
        for (int i = warriors.size() - 1; i >= 0; i--) {
            if (warriors.get(i)[0] == now[0] && warriors.get(i)[1] == now[1]) {
                warriors.remove(i);
            }
        }
    }
    
    // BFS + 이전노드 탐색용
    static class Node {
        int r;
        int c;
        Node before;

        public Node(int r, int c, Node node) {
            this.r = r;
            this.c = c;
            this.before = node;
        }
    }

    // BFS로 메두사 이동 최단경로 구하기
    static List<int[]> bfs() {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        q.offer(new Node(s[0], s[1], null)); 
        visited[s[0]][s[1]] = true;

        while(!q.isEmpty()) {
            Node now = q.poll();

            // 도착하면 역순으로 이동 배열에 추가 후 반환
            if (now.r == e[0] && now.c == e[1]) {
                List<int[]> ret = new ArrayList<>();
                while (now != null) {
                    ret.add(new int[]{now.r, now.c});
                    now = now.before;
                }
                return ret;
            }

            // 상하좌우로 이동
            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || town[nr][nc] == 1) continue;
                visited[nr][nc] = true;
                q.offer(new Node(nr, nc, now));
            }
        }

        // 도착하지 못하면 null 반환
        return null;
    }
    
    // 입력 받기
    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        s = new int[2]; e = new int[2];
        s[0] = Integer.parseInt(st.nextToken());
        s[1] = Integer.parseInt(st.nextToken());
        e[0] = Integer.parseInt(st.nextToken());
        e[1] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        warriors = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            warriors.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        town = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) town[i][j] = Integer.parseInt(st.nextToken());
        }
    }
}