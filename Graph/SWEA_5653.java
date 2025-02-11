package Graph;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());   // 입력 행
            int M = Integer.parseInt(st.nextToken());   // 입력 열
            int K = Integer.parseInt(st.nextToken());   // 배양시간

            int rows = N + 2 * K;   // 최대 확장 행
            int cols = M + 2 * K;   // 최대 확장 열
            int sr = rows/2 - N/2;  // 초기 시작 인덱스
            int sc = cols/2 - M/2; 
            int[][] map = new int[rows][cols];  // 세포의 생명력
            int[][] record = new int[rows][cols];  // 세포가 배양된 날 기록
            int[][] state = new int[rows][cols];  // 세포의 상태
            // 세포의 상태 : 생명력x2로 초기화(비활성화) -> 날마다 -1 -> 생명력 >= 상태값(활성화) -> 0 사망

            // 상하좌우 정의
            int[] dx = {-1, 1, 0 ,0};
            int[] dy = {0, 0, -1, 1};

            // 미리 확장된 맵의 가운데에 데이터 받기    
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int life = Integer.parseInt(st.nextToken());
                    map[sr + i][sc + j] = life;
                    record[sr + i][sc + j] = -1;
                    state[sr + i][sc + j] = life * 2;
                }
            }

            // 시뮬레이션 시작
            for(int hour = 1; hour < K; hour++) {
                for(int i = sr - hour+1; i < rows; i++) { // 여기도 탐색 범위를 줄일 수 있다.
                    if( i >= sr+N+hour ) break;
                    for(int j = sc - hour+1; j < cols; j++) {
                        if( j >= sc+M+hour ) break;
                        // 세포가 아님 or 동시간 확장한 세포 or 죽은 세포 continue
                        if (map[i][j] == 0 || record[i][j] == hour || state[i][j] == -1) continue;

                        // 비활성 세포인가?
                        if (state[i][j]-- > map[i][j]) continue;                

                        // 상하좌우 번식 시작
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            
                            // 방문하지 않은 셀이거나, 동시간에 접근하면서 생명력이 더 높은 경우 확장
                            if (record[nx][ny] == 0 || ( record[nx][ny] == hour && map[nx][ny] < map[i][j])) {
                                map[nx][ny] = map[i][j];
                                record[nx][ny] = hour;
                                state[nx][ny] = map[i][j] * 2;
                            }
                        }
                    }
                }
            }

            // 정답 출력
            int cnt = 0;
            for ( int i = sr - K ; i < rows; i++) {
                for (int j = sc- K; j < cols; j++) {
                    if (map[i][j]!=0 && state[i][j]>0) cnt++;
                }
            }
            System.out.println("#"+t+" "+cnt);
        }
    }
}