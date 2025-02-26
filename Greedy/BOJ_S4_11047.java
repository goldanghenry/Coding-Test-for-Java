package Greedy;

import java.io.*;
import java.util.StringTokenizer;

/*
 * BOJ Silver 4, 동전 0
 * https://www.acmicpc.net/problem/11047
 * 동전의 개수를 최소로 하여 가치의 합이 K원이 되도록 하는 문제
 * 가치가 큰 동전부터 차례대로 사용하면 최소 동전의 개수를 구할 수 있다.
 * 동전의 가치가 오름차순으로 주어지므로, 가치가 큰 동전부터 차례대로 사용하면 최소 동전의 개수를 구할 수 있다.
 */

public class BOJ_S4_11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        for (int i = N-1; i >= 0; i--) {
            if (K == 0) break;
            if (coins[i] <= K) {
                count += K / coins[i];
                K %= coins[i];
            }
        }
        System.out.println(count);
    }
}
