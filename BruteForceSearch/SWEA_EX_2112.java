package BruteForceSearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
/*
 * SWEA 모의 SW 역량테스트 [ 보호 필름 ]
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V1SYKAaUDFAWu&categoryId=AV5V1SYKAaUDFAWu&categoryType=CODE&problemTitle=%EB%B3%B4%ED%98%B8&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 * 
 * - 두께와 가로 크기가 주어진 보호 필름 단면을 합격 기준 K를 만족시키는 최소한의 약품 처리 횟수를 구하는 문제
 * - 약품 -> 가로 방향으로 약품을 주입해 같은 종류의 특성으로 변경
 * - 조건 : 가로는 최대 20, 막은 최대 13개
 * - 특성
 * - 1. 최대 사용하는 약품의 개수는 K이하
 * 
 * - O(N^3)
 */

class SWEA_EX_2112 {

    static int D, W, K;
    static int result;
    static int[][] map;

    static void dfs(int[] condArr, int cur, int usedCnt) {
        // 가지치기 조건 : 약품 사용수 >= 최저 사용수
        if (usedCnt >= result) return;

        // 기저 조건
        if (cur == D) {
            // 성능 평가 진행
            if (evaluate(condArr)) {
                // result 업데이트
                result = Math.min(result, usedCnt);
            };
            return;
        }

        int backup = condArr[cur];

        dfs(condArr, cur + 1, usedCnt);    // 0 : x
        
        condArr[cur] = 1; 
        dfs(condArr, cur + 1, usedCnt + 1); // 1 : A
        
        condArr[cur] = 2;                  
        dfs(condArr, cur + 1, usedCnt + 1); // 2 : B

        condArr[cur] = backup;  // 백트레킹
    }

    static boolean evaluate(int[] condArr) {
    // K가 1이면 무조건 통과하므로 바로 true 반환
    if (K == 1) return true;
    
    // 각 열(i)에 대해 검사
    for (int i = 0; i < W; i++) {
        // 첫 번째 셀의 값을 결정 (약품 처리 여부에 따라)
        int prev = (condArr[0] != 0 ? condArr[0] : map[0][i]);
        int count = 1;          // 연속된 동일 특성의 개수
        boolean valid = false; // 현재 열이 조건을 만족하는지 여부
        
        // 두 번째 셀부터 순회
        for (int j = 1; j < D; j++) {
            int cur = (condArr[j] != 0 ? condArr[j] : map[j][i]);
            
            if (cur == prev) count++;
            else count = 1;
            prev = cur;

            // 조건 만족하면 더 이상 검사할 필요 없음
            if (count >= K) {
                valid = true;
                break;
            }
        }
        
        // 한 열이라도 조건을 만족하지 못하면 불합격
        if (!valid) return false;
    }
    return true;
}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 입력
        int T = Integer.parseInt(st.nextToken());
        for (int t = 1; t <= T; t++) {
            // 제한 조건 입력
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());   // 층의 높이(세로)
            W = Integer.parseInt(st.nextToken());   // 필름의 수(가로)
            K = Integer.parseInt(st.nextToken());   // 합격 기준(연속 K개를 만족)
            result = K;

            // 막 구성 입력
            map = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken()) + 1; // 1 : A, 2: B
                }
            }

            // DFS 완전 탐색 시작
            dfs(new int[D], 0,0);

            // 결과 저장
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}