import java.io.*;
import java.util.Scanner;

/* 
 * BOJ silver 3. N과 M (1)
 * https://www.acmicpc.net/problem/15649
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열 -> nPm
 */

public class BOJ_15649 {
    static int N, M;
    static int[] nums, sel;
    static boolean[] v;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();
        N = sc.nextInt();
        M = sc.nextInt();
        nums = new int[N];
        sel = new int[M];
        v = new boolean[N];
        

        for(int i = 0; i < N; i++) {
            nums[i] = i+1;
        }

        // dfs
        dfs(0);

        bw.write(sb.toString().trim());
        bw.flush();
        bw.close();
        sc.close();
    }

    private static void dfs(int depth) {
        if (depth == M) {
            // 출력
            for (int i = 0; i < M; i++) {
                if (i == M-1) sb.append(sel[i]);
                else sb.append(sel[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (v[i]) continue;
            v[i] = true;
            sel[depth] = nums[i];
            dfs(depth +1);
            v[i] = false;
        }
    }
}
