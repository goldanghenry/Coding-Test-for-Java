package Graph;

import java.util.Scanner;

/*
 * N x N 체스판에 N개의 퀸을 서로 공격하지 않도록 배치하는 문제 (N-Queen 문제)
 * 
 * [문제 접근]
 * - 각 행에는 반드시 하나의 퀸만 배치됩니다.
 * - 퀸이 서로 공격하지 못하도록 같은 열과 두 대각선(우하단 대각선, 좌하단 대각선)에 
 *   다른 퀸이 배치되어 있으면 안 됩니다.
 *
 * [사용한 아이디어]
 * 1. 재귀(백트래킹)를 사용하여 1행부터 N행까지 순차적으로 퀸을 배치합니다.
 * 2. 각 행에서 가능한 열을 순회하며, 현재 위치가 안전한지 (가지치기) 검사합니다.
 * 3. 안전하다면 현재 열과 해당 위치에 해당하는 두 대각선을 사용중으로 표시한 후
 *    다음 행에 대해 재귀 호출을 진행합니다.
 * 4. 재귀 호출 후에는 백트래킹을 위해 사용했던 상태를 복구합니다.
 *
 * [대각선 체크 방법]
 * - 우하단 대각선(/): 같은 대각선에 속하는 좌표들은 행과 열의 합이 일정합니다.
 *   예를 들어 (row, col)에서 row+col이 같으면 같은 우하단 대각선에 있습니다.
 * - 좌하단 대각선(\): 같은 대각선에 속하는 좌표들은 행과 열의 차가 일정합니다.
 *   단, 차가 음수가 될 수 있으므로, 인덱스로 사용하기 위해 +N을 해줍니다.
 *   즉, (row - col + N)이 동일하면 같은 좌하단 대각선에 있습니다.
 */

public class BOJ_G4_9663 {
    static int N;         // 체스판의 크기 (행과 열의 수), 동시에 배치할 퀸의 개수
    static int ans;       // 가능한 퀸 배치 방법의 총 개수를 저장하는 변수
    // 각 배열은 해당 열 또는 대각선에 퀸이 배치되어 있는지 여부를 저장 (true: 배치됨)
    static boolean[] col;     // 열 검사: 인덱스 1 ~ N 사용 (0번 인덱스는 사용하지 않음)
    static boolean[] slash;   // 우하단 대각선 검사: (row + col)의 값이 동일하면 같은 대각선, 범위: 2 ~ 2*N, 크기는 2*N+1
    static boolean[] bSlash;  // 좌하단 대각선 검사: (row - col + N)를 인덱스로 사용, 범위: 1 ~ 2*N-1, 크기는 2*N

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 사용자로부터 체스판 크기 N 입력받기
        N = sc.nextInt();
        
        // 열 상태 배열 초기화: 1부터 N까지 사용 (0번 인덱스는 사용하지 않음)
        col = new boolean[N + 1];
        // 우하단 대각선 배열 초기화: 인덱스 0부터 2*N까지 사용 (합이 2부터 2*N까지 가능)
        slash = new boolean[2 * N + 1];
        // 좌하단 대각선 배열 초기화: 인덱스 0부터 2*N-1까지 사용
        bSlash = new boolean[2 * N];

        // 1행부터 시작하여 재귀적으로 퀸을 배치
        setQueens(1);
        // 가능한 모든 배치 방법의 개수를 출력
        System.out.println(ans);
    }
    
    /**
     * rowNo 행에 퀸을 배치하는 재귀 함수 (백트래킹)
     * @param rowNo 현재 배치할 행 번호 (1부터 시작)
     */
    private static void setQueens(int rowNo) {
        // 기저 조건: 행 번호가 N보다 크면, 모든 행에 퀸을 배치한 것이므로 하나의 해를 찾은 것
        if (rowNo > N) {
            ans++;  // 해의 개수를 증가시킴
            return;
        }
        
        // 현재 행(rowNo)의 각 열(c)에 대해 시도
        for (int c = 1; c <= N; c++) {
            // 현재 (rowNo, c) 위치에 퀸을 배치할 수 있는지 검사
            if (!isAvailable(rowNo, c))
                continue; // 만약 공격받는 위치라면 다음 열로 넘어감
            
            // 현재 위치에 퀸 배치: 
            // 해당 열, 우하단 대각선, 좌하단 대각선에 퀸이 있음을 표시
            col[c] = true;
            slash[rowNo + c] = true;
            bSlash[rowNo - c + N] = true;
            
            // 다음 행에 대해 재귀 호출하여 퀸을 배치
            setQueens(rowNo + 1);
            
            // 백트래킹: 다음 경우의 수를 위해 현재 배치 상태를 복구
            col[c] = false;
            slash[rowNo + c] = false;
            bSlash[rowNo - c + N] = false;
        }
    }
    
    /**
     * (rowNo, c) 위치에 퀸을 배치할 수 있는지 확인하는 함수.
     * 이미 같은 열 또는 대각선에 퀸이 배치되어 있다면 false를 반환.
     *
     * @param rowNo 현재 행 번호
     * @param c 현재 열 번호
     * @return 해당 위치가 안전하면 true, 아니면 false
     */
    private static boolean isAvailable(int rowNo, int c) {
        // 조건:
        // 1. 해당 열(col[c])에 퀸이 배치되어 있지 않아야 함.
        // 2. 우하단 대각선(slash[rowNo+c])에 퀸이 배치되어 있지 않아야 함.
        // 3. 좌하단 대각선(bSlash[rowNo-c+N])에 퀸이 배치되어 있지 않아야 함.
        return !col[c] && !slash[rowNo + c] && !bSlash[rowNo - c + N];
    }
}
