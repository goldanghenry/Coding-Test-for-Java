package PermCombSubs;

import java.io.*;
import java.util.StringTokenizer;

/*
    Boj silver 5, 집합
    https://www.acmicpc.net/problem/11723
    1<=x<=20, 이므로 checkList에 마킹하면서 사용
    시간 초과 판정
 */

public class BOJ_S5_11723_1 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        boolean[] S = new boolean[N+1];
        for (int n = 1; n <= N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();
            if (cmd.equals("all")) {    // S -> {1, 2, 3, ... 20}
                for(int i = 1; i <= N; i++) {
                    S[i] = true;
                }
            }
            else if (cmd.equals("empty")) { // S -> 공집합
                S = new boolean[N+1];
            }
            else {
                int x = Integer.parseInt(st.nextToken());
                switch (cmd) {
                    case "add" -> S[x] = true;             // x를 S에 추가
                    case "remove" -> S[x] = false;    // x를 제거
                    case "check" -> sb.append(S[x] ? "1\n" : "0\n");  // x가 있는가?
                    case "toggle" -> S[x] = !S[x];     // x가 있으면 제거, 없으면 추가
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
