package Graph;

import java.io.*;
import java.util.StringTokenizer;

/*
 * SWEA 1210. Ladder1 (D4)
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14ABYKADACFAYh&
 * 0.17287s
 */

public class SWEA_1210_1 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int tc = 1; tc <= 10; tc++) {
            br.readLine(); // 테스트 케이스 번호 (버리기)
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
            
            int x = 99;
            int y = startY;
            
            // 위로 올라가면서 좌우 체크
            while (x > 0) {
                // 좌측이 이동 가능하면 왼쪽으로 계속 이동
                if (y - 1 >= 0 && map[x][y - 1] == 1) {
                    while (y - 1 >= 0 && map[x][y - 1] == 1) {
                        y--;
                        // 이미 방문한 길은 0으로 만들어 다시 방문하지 않도록 함
                        map[x][y] = 0;
                    }
                }
                // 우측이 이동 가능하면 오른쪽으로 계속 이동
                else if (y + 1 < 100 && map[x][y + 1] == 1) {
                    while (y + 1 < 100 && map[x][y + 1] == 1) {
                        y++;
                        map[x][y] = 0;
                    }
                }
                // 좌우 이동이 불가능하면 위로 한 칸 이동
                x--;
            }
            
            System.out.println("#" + tc + " " + y);
        }
    }
}
