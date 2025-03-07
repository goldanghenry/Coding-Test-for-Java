package DivideConquer;
import java.io.*;
import java.util.*;

/*
 * BOJ S2, 색종이 만들기
 * https://www.acmicpc.net/problem/2630
 * 분할 정복
 */
public class BOJ_S2_2630 {
    static int[] ans;
    static int[][] map;

    private static void divideConquer(int sRow, int sCol, int size) {
        // 검사
        boolean flag = true;
        int color = map[sRow][sCol];
        for (int i = sRow; i < sRow+size; i++) {
            for (int j = sCol; j < sCol+size; j++) {
                if (map[i][j] == color) continue;
                flag = false;
                break;
            }
        }

        if (flag) ans[color]++;
        else {
            int nextSize = size/2;
            divideConquer(sRow, sCol, nextSize);                // 왼쪽 위
            divideConquer(sRow, sCol + nextSize, nextSize);     // 오른쪽 위
            divideConquer(sRow + nextSize, sCol, nextSize);    // 왼쪽 아래
            divideConquer(sRow+ nextSize, sCol+nextSize, nextSize);    // 오른쪽 아래
        }
        
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ans = new int[]{0,0};
        divideConquer(0,0,N);

        System.out.println(ans[0]);
        System.out.println(ans[1]);
    }
}
