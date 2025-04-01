package Greedy;

import java.io.*;
import java.util.*;

/*
 * SWEA 8382 D4, 방향 전환
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWyNQrCahHcDFAVP
 */

public class SWEA_D4_8382 {
    private static int x1, x2, y1, y2, min;
    private static void move (boolean flag ) {
        int dx = x1, dy = y1, cnt = 0;

        while(true) {
            if (dx == x2 && dy == y2) {
                if (min > cnt) min = cnt;
                break;
            }
            if (flag) {
                if (dy > y2) dy--;
                else dy++;
                flag = false;
            } else {
                if (dx > x2) dx--;
                else dx++;
                flag = true;
            }
            cnt++;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            min = Integer.MAX_VALUE;
            move(false);
            move(true);

            System.out.println("#"+t+" "+min);
        }
    }
}

