package PermCombSubs;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
    Boj silver 5, 집합
    https://www.acmicpc.net/problem/11723
    1<=x<=20, 이므로 checkList에 마킹하면서 사용
    배열 : 시간초과 -> HashSet 사용 : 메모리 초과 -> 비트마스킹 사용
 */

public class BOJ_S5_11723_3 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int S = 0;  // 비트 마스킹 사용

        for (int n = 1; n <= N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();
            if (cmd.equals("all")) {    // S 0 ~ 19 자리의 비트를 모두 1로 채우기
                S = (1 << 21) - 1;
            }
            else if (cmd.equals("empty")) { // S -> 공집합
                S = 0;
            }
            else {
                int x = Integer.parseInt(st.nextToken());
                int mask = 1 << (x-1);
                switch (cmd) {
                    case "add" -> S |= mask;             // x를 S에 추가
                    case "remove" -> S &= ~mask;    // x를 제거
                    case "check" -> sb.append( (S & mask) != 0 ? "1\n" : "0\n");  // x가 있는가?
                    case "toggle" -> {  // x가 있으면 제거, 없으면 추가
                        S ^= mask;
                    }
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
