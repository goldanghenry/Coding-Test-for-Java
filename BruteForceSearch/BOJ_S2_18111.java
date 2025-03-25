package BruteForceSearch;

import java.io.*;
import java.util.*;

/*
 * BOJ 18111 Silver2, 마인크래프트
 * https://www.acmicpc.net/problem/18111
 * N * M, 집터 내 땅의 높이를 모두 동일하게 만들자
 * [ 가능한 작업 ]
 * 1. i,j의 가장 위에 있는 블록을 제거 -> 인벤토리 넣기 // 2초
 * 2. 인벤토리 블럭 -> i,j에 쌓기   // 1초
 * [ 접근 방법 ]
 * -> 파라메트릭 서치, 가장 높은 것
 */

public class BOJ_S2_18111 {
    static int N,M,B, result, time;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        result = -1;
        time = 0;
        search(0, 256);
    }

    private static void search(int left, int right) {
        
        int mid = (left+right)/2;
        while(left>right) {
            int[] res = canBuild(mid);
            if(res[0]==1) {
                result = mid;
                time = res[1];
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
    }
    private static int[] canBuild(int target) {
        int added = 0;
        int needed = 0;

        // 땅 다지기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > target) {
                    added++;
                }
                else if(map[i][j] < target) {
                    needed++;
                }
            }
        }

        if (needed > B+added) return new int[]{0,-1};
        else {
            int t = added*2 + needed;
            return new int[]{1,t};
        }
    }
}
