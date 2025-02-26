package PermCombSubs;

import java.io.*;
import java.util.StringTokenizer;

/*
 * SWEA. 준환이의 양팔저울
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWAe7XSKfUUDFAUw
 */

public class SWEA_D4_3234 {
    static int N, result;           // 무게추의 개수, 결과
    static int[] weight, sel;       // 무게추 배열, 실행 완료된 순열
    static boolean[] v;             // 사용 체크

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            weight = new int[N];
            sel = new int[N];
            v = new boolean[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N ; i++) {
                weight[i] = Integer.parseInt(st.nextToken());
            }

            result = 0;
            dfs(0);

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    // 왼쪽, 오른쪽 관계없이 올려놓는 순서를 정하는 '순열' 함수
    private static void dfs(int depth) {
        // 기저 조건
        if (depth == N) {    
            scale(0,0,0);
            return;
        }
        // 실행 내용
        for (int i = 0; i < N; i++) {   // 무게추 순환
            if (v[i]) continue;         // 해당 무게를 선택한 경우 다음 무게로
            sel[depth] = weight[i];     // 순열의 결과 저장(값)
            v[i] = true;                // 현재 인덱스 선택
            dfs(depth+1);               // 재귀 호출
            v[i] = false;               // 항상 원복
        }
    }

    private static void scale(int depth, int left, int right) {
        if (left < right) return;   // 계속 더해가기 때문
        if (depth == N) {           // 기저 조건
            result ++;
            return;
        }
        scale(depth+1, left, right+sel[depth]); // 왼쪽에  올렸을 때
        scale(depth+1, left+sel[depth], right); // 오른쪽에 올렸을 때
    }
}
