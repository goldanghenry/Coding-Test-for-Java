package Graph;

import java.io.*;
import java.util.*;

/*
 * BOJ 1987 Gold4 알파벳
 * https://www.acmicpc.net/problem/1987
 */

public class BOJ_G4_1987 {
    static int R, C, result;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static char[][] map;
    static Map<Character, Boolean> visited;

    static private void dfs(int x, int y, int depth) {
        result = Math.max(result, depth);
        
        for (int i =0 ;i < 4; i++) {
                
            int nx = x +dx[i];
            int ny = y +dy[i];

            if (nx < 0 || ny < 0 || nx >= R || ny >= C || visited.get(map[nx][ny])) continue;
            
            visited.put(map[nx][ny],true);
            dfs(nx,ny,depth+1);
            visited.put(map[nx][ny],false);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
        }

        visited = new HashMap<>();
        for (int i = 'A'; i <='Z';i++) {
            visited.put((char)i, false);
        }
        
        result = 0;
        visited.put(map[0][0], true);
        
        dfs(0,0,1);

        System.out.println(result);
    }
}
