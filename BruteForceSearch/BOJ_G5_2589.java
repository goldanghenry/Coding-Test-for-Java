package BruteForceSearch;

import java.io.*;
import java.util.*;

/*
 * BOJ 2589 Gold 5, 보물섬
 * https://www.acmicpc.net/problem/2589
 * N x M, 육지 - L, 바다 - W, 상하좌우로 인접한 육지로만 이동 가능
 * 
 */

public class BOJ_G5_2589 {
    static int N,M;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.ne  xtToken());



    }
}
