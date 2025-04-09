package DynamicProgramming;

import java.util.*;
import java.io.*;

/*
 * SWEA 1263 D6, 사람 네트워크2
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV18P2B6Iu8CFAZN
 */

public class SWEA_D6_1263 {
    final static int INF = 99999;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[][] graph = new int[N][N];
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    if (num == 1) {
                        graph[i][j] = num;
                        graph[j][i] = num;
                    } else {
                        graph[i][j] = INF;
                        graph[j][i] = INF;
                    }
                }
                graph[i][i] = 0;
            }

            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if ( graph[i][k] != INF && graph[k][j] != INF 
                        && graph[i][j] > graph[i][k] + graph[k][j] ) 
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }

            int min = INF;
            for (int i = 0; i < N; i++) {
                int tmp = 0;
                for (int j = 0; j < N; j++) {
                    tmp += graph[i][j];
                }
                min = Math.min(min, tmp);
            }

            sb.append("#").append(t).append(" ").append(min).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}
