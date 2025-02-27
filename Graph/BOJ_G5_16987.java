package Graph;

import java.io.*;
import java.util.StringTokenizer;

/*
 * BOJ gold5 16987, 계란으로 계란치기
 * https://www.acmicpc.net/problem/16987
 */

public class BOJ_G5_16987 {
    static int N, ans;
    static int[] eggs, weights;
    static boolean[] isBroken;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        eggs = new int[N];
        weights = new int[N];
        isBroken = new boolean[N];

        ans = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            eggs[i] = Integer.parseInt(st.nextToken());
            weights[i] = Integer.parseInt(st.nextToken());
        }

        // dfs
        dfs(0,0);

        System.out.println(ans);

    }

    static void dfs(int cur, int cnt) {
        // base case or 다 깨진 경우
        if ((cur >= N)||(cnt >= (N-1))) {
            ans = Math.max(ans, cnt);
            return;
        }

        // 현재 계란이 이미 깨져있다면 다음 계란으로
        if (isBroken[cur]) {
            dfs(cur+1, cnt);
            return;
        }

        // 타겟 고르기
        for (int i = 0; i < N; i++) {
            if (i == cur) continue;
            if (isBroken[i]) continue;

            // 박치기 시행
            eggs[cur] -= weights[i];
            eggs[i] -= weights[cur];

            int brokenCnt = 0;
            if (eggs[cur] <= 0) {
                isBroken[cur] = true;
                brokenCnt++;
            }
            if (eggs[i] <= 0) {
                isBroken[i] = true;
                brokenCnt++;
            }

            dfs(cur+1, cnt + brokenCnt);

            // 원복
            eggs[cur] += weights[i];
            eggs[i] += weights[cur];
            isBroken[cur] = false;
            isBroken[i] = false;
        }
    }
}
