package PermCombSubs;

/*
 * 20240219 1320221 우성현
 * BabyGinGame
 */

class BabyGinGame {
    static int N;
    static int[] nums, sel;
    static boolean[] v;     // 사용 확인
    static boolean result;
    public static void main(String[] args) {
        String[] inputs = {"667767","054060", "101123"};        
        for (int t = 0; t < 3; t++) {

            // 입력 받기
            N = 6;              // 카드 길이
            nums = new int[N];  // 카드 원배열
            sel = new int[N];   // 새로운 배열

            for (int i = 0; i < N; i++) {
                nums[i] = inputs[t].charAt(i) -'0';
            }

            result = false; // 결과 초기화
            v = new boolean[N];

            dfs(0);

            if (result) {
                System.out.println(inputs[t] + "는 baby gin 입니다.");
            } else {
                System.out.println(inputs[t] + "는 baby gin 아닙니다.");
            }
        }
    }

    private static void dfs(int depth) {
        // 기저 조건
        if (depth == N) {
            isBaby(sel);
            return;
        }

        // 실행문
        for (int i = 0; i < N; i++) {
            if (v[i]) continue;    // 중복 방지
            v[i] = true;            // 사용 체크
            sel[depth] = nums[i];   // i번째 숫자 설정
            dfs(depth+1);           // depth+1 탐색
            v[i] = false;           // 원상 복수
        }
    }

    private static void isBaby(int[] sel) {

        boolean triple1 = (sel[0] == sel[1] && sel[1] == sel[2]);
        boolean triple2 = (sel[3] == sel[4] && sel[4] == sel[5]);

        boolean run1 = (sel[0] + 1 == sel[1] && sel[1] + 1 == sel[2]);
        boolean run2 = (sel[3] + 1 == sel[4] && sel[4] + 1 == sel[5]);
        
        if ((triple1 || run1) && (run2 || triple2)) {
            result = true;
        }
    }
}