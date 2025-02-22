package PermCombSubs;

import java.io.*;
import java.util.StringTokenizer;

/*
    SWEA 3421 수제 버거 장인
    https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWErcQmKy6kDFAXi
    비트 마스킹으로 풀어보기
 */

public class SWEA_3421 {
    static int N, sel, cnt;
    static int[] Mrr;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());       // 재료 수
            int M = Integer.parseInt(st.nextToken());   // 궁합 맞지 않는 조건 수
            Mrr = new int[N]; // 각 재료와 궁합이 맞지 않는 재료 기록
            sel = 0;            // 선택한 재료 리스트
            cnt = 0;            // 가능한 조합의 수

            // 궁합이 맞지 않는 재료 입력 받기
            // 같은 쌍이 여러 번 주어 질 수 있으므로 a | mask 연산
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken())-1;
                int b = Integer.parseInt(st.nextToken())-1;
                int maskA = 1 << b;
                int maskB = 1 << a;
                Mrr[a] |= maskA;     // 추가
                Mrr[b] |= maskB;
            }

            // 부분집합 조합
            dfs(0);
            sb.append("#").append(t).append(" ").append(cnt).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void dfs(int depth) {
        // 기저 조건
        if (depth == N) {
            cnt++;
            return;
        }

        // 선택하지 않는 경우
        dfs(depth+1);

        // 선택하는 경우, 현재까지 선택한 재료(sel)와 새로 추가할 재료(depth) 사이에 궁합 충돌이 없는지 확인
        // Mrr[depth]는 depth번 재료와 함께하면 안되는 재료들의 비트마스크이므로,
        // 이미 선택한 재료(sel)와 겹치면 충돌이 발생
        if ((sel & Mrr[depth]) == 0) {  // & 연산을 하면 같은 자리 bit는 1이 나옴
            sel |= (1 << depth);
            dfs(depth+1);
            sel &= ~(1 << depth);
        }
    }
}
