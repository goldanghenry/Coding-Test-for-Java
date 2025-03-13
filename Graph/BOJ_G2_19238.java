package Graph; 
import java.io.*;
import java.util.*;

/*
 * BOJ 19238 스타트 택시
 * https://www.acmicpc.net/problem/19238
 */
public class BOJ_G2_19238 {
    static int N, M, fuel, x, y;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map, point;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        N = Integer.parseInt(st.nextToken());       // 맵의 크기
        M = Integer.parseInt(st.nextToken());       // 태워야하는 손님 수
        fuel = Integer.parseInt(st.nextToken());    // 초기 연료의 양
    
        // 맵 입력 받기 (0: 빈칸, 1: 벽)
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    
        // 운전을 시작하는 위치 (0-indexed)
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken()) - 1;   // 행
        y = Integer.parseInt(st.nextToken()) - 1;   // 열
    
        // 각 승객의 출발지와 도착지 정보 (승객 번호는 2부터 시작)
        point = new int[M + 2][2];
        for (int i = 2; i < M + 2; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int sy = Integer.parseInt(st.nextToken()) - 1;
            map[sx][sy] = i;  // 승객의 출발지 마킹
            int dx_ = Integer.parseInt(st.nextToken()) - 1;
            int dy_ = Integer.parseInt(st.nextToken()) - 1;
            point[i] = new int[] {dx_, dy_}; // 승객의 도착지 기록
        }
    
        // 시뮬레이션: 남은 승객(M)이 0이 될 때까지 반복
        while (M > 0) {
            // 1. 가장 가까운 승객 찾기 (한 레벨씩 BFS 탐색)
            int[] target = findPassenger(x, y);
            if (target == null) { // 승객을 찾지 못한 경우
                System.out.println(-1);
                return;
            }
            int distToPassenger = target[3];  // target 배열의 4번째 원소에 거리 저장
            if (fuel < distToPassenger) {
                System.out.println(-1);
                return;
            }
            fuel -= distToPassenger;
            // 택시 위치를 승객의 위치로 이동 및 승객 제거
            x = target[0];
            y = target[1];
            int passengerNum = target[2];
            map[x][y] = 0;
    
            // 2. 승객의 도착지까지 최단 경로 BFS 탐색
            int distToDest = findDestination(x, y, point[passengerNum][0], point[passengerNum][1]);
            if (distToDest == -1 || fuel < distToDest) {
                System.out.println(-1);
                return;
            }
            fuel -= distToDest;
            fuel += (distToDest * 2);
            // 택시 위치를 도착지로 이동
            x = point[passengerNum][0];
            y = point[passengerNum][1];
            M--;
        }
        System.out.println(fuel);
    }
    
    // BFS를 이용해 가장 가까운 승객을 찾는 메서드  
    // 리턴 배열: {승객 x, 승객 y, 승객 번호, 시작점부터의 거리}  
    static int[] findPassenger(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int[][] dist = new int[N][N];
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;
    
        ArrayList<int[]> candidates = new ArrayList<>();
        int minDist = Integer.MAX_VALUE;
    
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int cx = cur[0], cy = cur[1];
    
                // 승객이 있는 경우 후보 리스트에 추가
                if (map[cx][cy] > 1) {
                    candidates.add(new int[]{cx, cy, map[cx][cy], dist[cx][cy]});
                    minDist = dist[cx][cy];
                }
    
                // 인접 노드 탐색
                for (int d = 0; d < 4; d++) {
                    int nx = cx + dx[d];
                    int ny = cy + dy[d];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (visited[nx][ny] || map[nx][ny] == 1) continue;
                    visited[nx][ny] = true;
                    dist[nx][ny] = dist[cx][cy] + 1;
                    // 이미 후보 승객이 있는 레벨보다 멀면 더 이상 넣지 않음
                    if (dist[nx][ny] > minDist) continue;
                    queue.offer(new int[]{nx, ny});
                }
            }
            // 한 레벨을 모두 처리한 후 후보가 있다면 선택
            if (!candidates.isEmpty()) break;
        }
    
        if (candidates.isEmpty()) return null;
        // 여러 후보가 있으면 행 번호, 그 다음 열 번호 순으로 정렬
        Collections.sort(candidates, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
        return candidates.get(0);
    }
    
    // BFS를 이용해 출발지에서 도착지까지의 최단 거리를 구하는 메서드  
    // 도착할 수 없으면 -1 반환
    static int findDestination(int startX, int startY, int destX, int destY) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int[][] dist = new int[N][N];
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;
    
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int cx = cur[0], cy = cur[1];
            if (cx == destX && cy == destY) {
                return dist[cx][cy];
            }
            for (int d = 0; d < 4; d++){
                int nx = cx + dx[d];
                int ny = cy + dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny] || map[nx][ny] == 1) continue;
                visited[nx][ny] = true;
                dist[nx][ny] = dist[cx][cy] + 1;
                queue.offer(new int[]{nx, ny});
            }
        }
        return -1;
    }
}
