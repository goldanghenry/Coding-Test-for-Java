package Graph;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
            int[][] recode = new int[rows][cols];  // 세포가 배양된 날 기록
            int[][] state = new int[rows][cols];  // 세포의 상태
            // 세포의 상태 : 생명력x2로 초기화(비활성화) -> 날마다 -1 -> 생명력 >= 상태값(활성화) -> 0 사망

            // 미리 확장된 맵의 가운데에 데이터 받기    
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int life = Integer.parseInt(st.nextToken());
                    map[sr + i][sc + j] = life;
                    recode[sr + i][sc + j] = -1;
                    state[sr + i][sc + j] = life * 2;
                }
            }

            // 시뮬레이션 시작
            for(int hour = 1; hour < K; hour++)

                for(int i = sr - hour; i < rows; i++) {
                    for(int j = sc - hour; j < cols; j++) {
                        
                        // 세포인가?
                        if (map[i][j] == 0) continue;
                        
                        // 세포의 상태 업데이트
                        recode[i][j]--;

                        // 번식 시작

                        // 
                    }
                }


        }
        




    }
}