package Math;

/*
 * 숫자의 합 bronze 4
 * https://www.acmicpc.net/problem/11720
 */

import java.io.*;

public class Boj_B4_11720 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        String numbers = br.readLine();

        int sum = 0;
        for (int i = 0; i < N; i++ ) {
            sum += numbers.charAt(i) -'0';
        }
        System.out.println(sum);
    }
}