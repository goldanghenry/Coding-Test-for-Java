package BruteForceSearch;

import java.io.*;
import java.util.StringTokenizer;

/* 
 * BOJ_S1_2468 안전 영역
 * https://www.acmicpc.net/problem/2468
 */

public class BOJ_S1_2468 {
	static int N, MAX, MIN, result;
	static int[][] map;
	static boolean[][] v;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());	// 행, 열
		map = new int[N][N];
		MAX = Integer.MIN_VALUE;
		MIN = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				MAX = Math.max(MAX, num);
				MIN = Math.min(MIN, num);
			}
		}
		
		result = 0;
		// 꽃가루 농도
		for (int k = MIN-1; k <= MAX ; k++) {
			
			// 마킹
			v = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0 ; j < N; j++) {
					if (map[i][j] <= k) v[i][j] = true;	// 농도 이하라면 알레르기 반응
				}
			}
			
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0 ; j < N; j++) {
					if (!v[i][j]) {
						dfs(i,j);
						cnt++;
					}
				}
			}
			result = Math.max(result,cnt);
		}
		System.out.println(result);
	}
	static void dfs(int i, int j) {
		v[i][j] = true;
		
		for (int d = 0; d < 4; d++) {
			int nx = i + dx[d];
			int ny = j + dy[d];
			
			if (nx < 0 || ny < 0 || nx >= N || ny >= N || v[nx][ny]) continue;
			v[nx][ny] = true;
			dfs(nx,ny);
		}
		
	}

}
