package PermCombSubs;

import java.io.*;
import java.util.StringTokenizer;

/*
    Boj 12891 silver 2, DNA 비밀번호
    https://www.acmicpc.net/problem/12891
    슬라이딩 윈도우
 */
public class BOJ_S2_12891_2 {
    static int[] minCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        String inputs = br.readLine();
        minCnt = new int[4];
        int[] curCnt = new int[4];
        int result = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            minCnt[i] = Integer.parseInt(st.nextToken());
        }

        // 슬라이딩 전 초기화
        for (int i = 0; i < P; i++) {
            switch (inputs.charAt(i)) {
                case 'A': curCnt[0]++; break;
                case 'C': curCnt[1]++; break;
                case 'G': curCnt[2]++; break;
                case 'T': curCnt[3]++;
            }
        }

        if (checkNum(curCnt)) result++;

        // 슬라이딩
        for (int left = 1, right = P; right < S; left++, right++) {
            switch (inputs.charAt(left-1)) {
                case 'A': curCnt[0]--; break;
                case 'C': curCnt[1]--; break;
                case 'G': curCnt[2]--; break;
                case 'T': curCnt[3]--;
            }

            switch (inputs.charAt(right)) {
                case 'A': curCnt[0]++; break;
                case 'C': curCnt[1]++; break;
                case 'G': curCnt[2]++; break;
                case 'T': curCnt[3]++;
            }

            if (checkNum(curCnt)) result++;
        }
        System.out.println(result);
    }
    public static boolean checkNum(int[] curCnt) {
        for ( int i = 0; i < 4; i++) {
            if (curCnt[i] < minCnt[i]) return false;
        }
        return true;
    }
}