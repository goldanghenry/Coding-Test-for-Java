package PermCombSubs;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
    Boj silver 5, 집합
    https://www.acmicpc.net/problem/11723
    1<=x<=20, 이므로 checkList에 마킹하면서 사용
    배열 : 시간초과 -> HashSet 사용 : 메모리 초과
 */

public class BOJ_S5_11723_2 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        HashSet<Integer> set = new HashSet<>();

        int N = Integer.parseInt(br.readLine());

        for (int n = 1; n <= N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();
            if (cmd.equals("all")) {    // S -> {1, 2, 3, ... 20}
                for(int i = 1; i <= N; i++) {
                    set.add(i);
                }
            }
            else if (cmd.equals("empty")) { // S -> 공집합
                set.clear();
            }
            else {
                int x = Integer.parseInt(st.nextToken());
                switch (cmd) {
                    case "add" -> set.add(x);             // x를 S에 추가
                    case "remove" -> set.remove(x);    // x를 제거
                    case "check" -> sb.append(set.contains(x) ? "1\n" : "0\n");  // x가 있는가?
                    case "toggle" -> {  // x가 있으면 제거, 없으면 추가
                        if (set.contains(x)) {
                            set.remove(x);
                        } else {
                            set.add(x);
                        }
                    }
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
