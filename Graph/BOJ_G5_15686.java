package Graph;

import java.io.*;
import java.util.*;

/*
 * BOJ 15686 Gold 5, 치킨 배달
 * https://www.acmicpc.net/problem/15686
 * N*N, 빈칸(0), 집(1), 치킨집(2)
 * 치킨 거리 -> 집과 가장 가까운 치킨집 사이의 거리 -> 맨해튼 거리
 * 도시의 치킨 거리 -> 모든 집의 치킨 거리의 합
 * M개를 고르고 나머지는 모두 페업
 */

public class BOJ_G5_15686 {
    static int N,M, result;
    static int[][] dp;
    static House[] sel;
    static int[][] map;
    static List<House> cList, hList;
    
    static class House {
        int x, y;
        public House(int x, int y) {
            this.x= x;
            this.y = y;
        }
    }

    private static void dfs(int depth, int start) {
        if (depth == M) {   // 기저조건 도달

            int sum = 0;
            for (House home : hList) {
                int minDist = Integer.MAX_VALUE;
                for (House chicken : sel) {
                    int dist = Math.abs(home.x-chicken.x)+Math.abs(home.y-chicken.y);
                    minDist = Math.min(dist, minDist);
                }
                sum+=minDist;
            }
            result = Math.min(result, sum);
            return;
        }

        for (int i = start; i < cList.size(); i++) {
            sel[depth] = cList.get(i);
            dfs(depth+1, i+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 맵의 크기 N*N
        M = Integer.parseInt(st.nextToken());   // 남길 치킨집의 수
        map = new int[N][N];
        cList = new ArrayList<>();
        hList = new ArrayList<>();

        // 맵 정보 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int type = Integer.parseInt(st.nextToken());
                map[i][j] = type;
                if (type == 2) cList.add(new House(i,j));
                else if (type == 1) hList.add(new House(i, j));
            }
        }

        // dfs 조합 -> M 개 고르기
        sel = new House[M];
        result = Integer.MAX_VALUE;
        dfs(0,0);

        System.out.println(result);

        
    }  
}
