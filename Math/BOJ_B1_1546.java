package Math;

import java.io.*;
import java.util.StringTokenizer;

/*
 * 평균, bronze 1
 * https://www.acmicpc.net/problem/1546
 */

public class BOJ_B1_1546 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        
        double max = -1;
        double total = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] scores = new int[N];
        
        for (int i = 0; i < N; i++ ) {
            scores[i] = Integer.parseInt(st.nextToken());
            if (scores[i] > max) max = scores[i];
        }

        for (int i = 0; i < N; i++) {
            total += (scores[i]/max)*100;
        }

        System.out.println(total/N);

    }
}
