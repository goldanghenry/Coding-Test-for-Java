package Graph;

import java.util.StringTokenizer;
import java.io.*;
//  0.13239s
public class SWEA_1210_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dx = {0, 0,-1};  // 우, 좌, 상 -> 우선순위가 중요!
        int[] dy = {1,-1,0};
        
        for (int t = 1; t <= 10 ; t++) {
            br.readLine();  // test case 버리기
            int[][] map = new int[100][100];
            int startY = 0;
            
            // 맵 입력받기 및 출발점(2)의 위치 기록
            for (int i = 0; i < 100; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (i == 99 && map[i][j] == 2) {
                        startY = j;
                    }
                }
            }

            int x = 99, y = startY;
            int pre = 2;
            // 역순으로 탐색
            while(x > 0) {
                // 3방위 탐색
                for (int i = 0; i < 3; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    // 가능한지 검사
                    if (ny < 0 || ny >= 100 || map[nx][ny]==0) continue;
                    pre = i;
                    break;
                }
                map[x][y] = 0;
                x += dx[pre];
                y += dy[pre];
                
            }
            System.out.println("#"+t+" "+y);
        }   
    }
}
