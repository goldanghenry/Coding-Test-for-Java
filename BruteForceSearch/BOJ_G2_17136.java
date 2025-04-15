package BruteForceSearch;
import java.io.*;
import java.util.*;
/*
 * BOJ 17136 Gold2, 색종이 붙이기
 * https://www.acmicpc.net/problem/17136
 * 1x1 ... 5x5, 5종류의 색종이. 각 색종이는 5개
 * 0이 있는 칸에 색종이 x
 * 모든 1을 덮는데 필요한 색종이의 최소 개수
 * 완전 탐색 -> 백트래킹
 */

public class BOJ_G2_17136 {
    static int res,N = 10;
    static int[][] map;
    static int[] paper = {0, 5,5,5,5,5};    // 각 색종이의 개수
    static List<int[]> lst;
    private static void dfs(int x, int y, int cnt) {
        // 기저 조건
        if (x >= N) {
            res = Math.min(res,cnt);
        }

        // 가지치기 : 이미 현재까지 쓴 색종이 수가 최소값보다 많거나 같으면 return
        if (cnt >= res) return;

        // 다음 행으로 이동
        if ( y >= N) {
            dfs(x+1, 0, cnt);
            return;
        }

        if (map[x][y] == 1) {
            //  5x5 -> 1x1 붙이기 시도
            for (int size = 5; size >= 1; size--) {
                if (paper[size] >0 && canAttach(x,y,size)) {
                    Attach(x,y,size,0); // 붙이기
                    paper[size]--;
                    dfs(x,y+1,cnt+1);
                    Attach(x,y,size,1); // 떼기
                    paper[size]++;
                }
            }
        } else {
            dfs(x,y+1,cnt);
        }
    }

    private static void Attach(int x, int y , int size, int val) {
        for (int i = x; i < x +size; i++) {
            for (int j = y; j < y+size; j++) {  
                map[i][j] = val;
            }
        }
    }

    private static boolean canAttach(int x, int y, int size) {
        if (x + size > N || y+size > N) return false;

        for (int i = x; i < x +size; i++) {
            for (int j = y; j < y+size; j++) {  
                if (map[i][j] == 0) return false;   //v[i][j] || 
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        map = new int[N][N];
        lst = new ArrayList<>();
        res = Integer.MAX_VALUE;
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());{
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) lst.add(new int[]{i,j});
                }
            }
        }

        if (lst.size() == 0) sb.append("0");
        else {
            dfs(0,0,0);
            if (res == Integer.MAX_VALUE) sb.append("-1");
            else sb.append(res);
        }
        System.out.println(sb);
    }
}
