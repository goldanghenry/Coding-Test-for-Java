package BruteForceSearch;

import java.io.*;
import java.util.StringTokenizer;

/*
    Boj gold 4, 야구
    https://www.acmicpc.net/problem/17281
    순열 -> 중복x
 */

public class BOJ_G4_17281 {
    static int N, result;
    static int[] sel;
    static boolean[] v;
    static int[][] inning;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        inning = new int[N][9];     // 각 선수가 각 이닝의 성적
        sel = new int[9];           // 타순
        v = new boolean[9];         // 방문 기록
        result = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                inning[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 순열 완전 탐색 및 야구 게임 진행
        dfs(0);

        System.out.println(result);
    }

    // 2~9번 선수의 순열을 구한 뒤 4번 자리에 1번 선수 배치
    // 게임 진행
    static void dfs(int depth) {
        // 기저 조건
        if (depth == 8) {
            // sel 복사본 생성
            int[] order = new int[9];
            // 0 ~ 7까지 복사
            for (int i = 0; i < 8; i++) {
                order[i] = sel[i];
            }
            // 4번 타자 위치에 player 0 삽입 (오른쪽으로 한 칸 이동)
            for (int i = 8; i >= 4; i--) {
                order[i] = order[i-1];
            }
            order[3] = 0;

            // 복사본 order를 사용하여 게임 진행
            playGame(order);
            return;
        }

        for (int i = 1; i < 9; i++) {
            if (v[i]) continue;     // 이미 선택되었다면 다음으로
            v[i] = true;
            sel[depth] = i;
            dfs(depth + 1);
            v[i] = false;
        }
    }

    static void playGame(int[] order) {
        int curIdx = 0;     // 현재 타순
        int score = 0;      // 득점

        // N 이닝 진행
        for (int i = 0; i < N; i++) {
            int outCnt = 0;
            boolean[] base = new boolean[3]; // 1,2,3 루

            while (outCnt < 3) {
                curIdx %= 9;        // 10번은 다시 1번으로
                int hit =  inning[i][order[curIdx]];  // 성적 불러오기
                if (hit == 0) {     // 아웃
                    outCnt++;       // 아웃 카운트 증가
                    curIdx++;       // 다음 타순
                } else {            // 안타
                    for ( int j = 2; j >= 0; j--) {     // 기존 주자들 진루
                        if (base[j]) {                  // 주자가 있다면
                            if ( j+hit > 2) {           // 홈에 들어온다면
                                score++;                // 득점
                                base[j] = false;        // 베이스 비우기
                            } else {                    // 홈에 들어오지 못하면
                                base[j+hit] = true;     // 진루
                                base[j] = false;        // 베이스 비우기
                            }
                        }
                    }
                    // 타자 진루
                    if (hit == 4) score++;  // 홈런
                    else base[hit-1] = true;  // 진루
                    curIdx++;               // 다음 타순
                }
            }
        }
        result = Math.max(result, score);   // 최대 득점 업데이트
    }
}
