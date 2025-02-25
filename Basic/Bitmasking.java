package Basic;

import java.util.Arrays;

public class Bitmasking {
    static int N, R;
    static int[] input, sel;

    public static void main(String[] args) {
        N = 3;
        R = 2;
        sel = new int[R];

        input = new int[]{1, 2, 3};

        permutation(0, 0);
    }



    static void permutation (int cnt, int flag) {
        if (cnt == R) {
            System.out.println(Arrays.toString(sel));
            return;
        }

        for (int i = 0; i < N; i++) {   // 유도 파트
            if ((flag & 1 << i) != 0) continue; // 오른쪽에서 i비트만큼 떨어진 자리가 선택되었다
            sel[cnt] = input[i];    // 선택한 수 저장
            permutation(cnt + 1, flag | 1 << i);    // 다음 수 선택하러 고고씽, 현재 선택한 원소의 비트를 추가해서 가기!
        }
    }
}
