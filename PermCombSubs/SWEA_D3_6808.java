package PermCombSubs;


import java.io.*;
import java.util.StringTokenizer;

/*
 * SWEA 6808. 규영이와 인영이의 카드 게임 (D3)
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWgv9va6HnkDFAW0
 */

public class SWEA_D3_6808 {
    static int N, win, lose;
    static int[] Arr, Brr, sel;
    static boolean[] checked;   // 규영 카드덱 체크
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // test case
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = 9;
            win = 0;
            lose = 0;
            Arr = new int[N];   // 규영
            Brr = new int[N];   // 인영
            checked = new boolean[19];  // 규영이덱 체크
            v = new boolean[N];     // 숫자 사용 체크
            sel = new int[N];

            // 규영이 카드덱 입력받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            for ( int i = 0; i < N; i ++) {
                Arr[i] = Integer.parseInt(st.nextToken());
                checked[Arr[i]] = true;
            }

            // 인영이 카드덱 구하기
            int idx = 0;
            for(int i = 1; i <= 18; i++) {
                if (!checked[i]) Brr[idx++] = i;
            }

            // dfs
            dfs(0);
            sb.append("#").append(t).append(" ").append(win).append(" ").append(lose).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int depth) {
        // 기저 조건
        if (depth == N) {
            int a = 0, b = 0;
            for (int i = 0; i < N; i++) {
                int score = Arr[i]+sel[i];
                if (Arr[i] > sel[i]) a += score;
                else if(Arr[i] < sel[i]) b += score;
            }
            
            if ( a > b) win++;
            else if( a < b) lose++;

            return;
        }

        for (int i = 0; i < N; i++) {
            if (v[i]) continue;
            sel[depth] = Brr[i];    // 선택
            v[i] = true;            // 마킹
            dfs(depth +1);          // dfs
            v[i] = false;           // 원상 복구
        }


    }
}
