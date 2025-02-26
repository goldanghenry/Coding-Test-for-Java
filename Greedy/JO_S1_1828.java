package Greedy;

import java.io.*;
import java.util.*;

/*
 * JO silver 1 1828, 냉장고
 * https://jungol.co.kr/problem/1828?cursor=eyJwcm9ibGVtc2V0Ijo4LCJmaWVsZCI6Mn0%3D
 */

public class JO_S1_1828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        int[][] ranges = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (a,b)->{
            if (a[1] == b[1]) return Integer.compare(a[0],b[0]);
            return Integer.compare(a[1],b[1]);
        });

        int cnt = 1;
        
        ranges[0][0] = arr[0][0];
        ranges[0][1] = arr[0][1];

        for (int i = 1; i < N; i++) {
            boolean flag = true;
            int j = 0;
            while( j < cnt) {
                if (ranges[j][0] <= arr[i][1] && arr[i][0] <= ranges[j][1]) {
                    ranges[j][0] = Math.max(ranges[j][0], arr[i][0]);
                    ranges[j][1] = Math.min(ranges[j][1], arr[i][1]);
                    flag = false;
                    break;
                }
                j++;
            }
            // 냉장고 만들기
            if (flag) {
                ranges[cnt][0] = arr[i][0];
                ranges[cnt][1] = arr[i][1];
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
