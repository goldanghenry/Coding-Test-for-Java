package Graph;

import java.io.*;
import java.util.*;

public class BOJ_G4_1987 {
    

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        char[][] map = new char[R][C];
        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0,1<<(map[0][0]-'A'),1});
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i =0 ;i < 4; i++) {
                
                int nx = cur[0] +dx[i];
                int ny = cur[1] +dy[i];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                
            }
        }
        
        
    }
}
