package Array;

/*
 * 달팽이 나선형 배열
 */

public class Snail {
    public static void main(String[] args) {
        int N = 6;
        int[][] arr = makeSnailArr(N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static int[][] makeSnailArr(int N) {
        int[][] matrix = new int[N][N];
        int value = 1;

        // 달팽이 방향 정의 우 -> 아래 -> 왼 -> 위
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int dir = 0;

        int x = 0, y = 0;   // 시작 위치

        while( value <= N * N) {
            matrix[x][y] = value++; // 현재 좌표 넣기

            // 다음 좌표 계산
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 언제 방향 전환 해야하는가?
            // 경계를 벗어나거나, 이미 값이 채워진 경우 방향 전환
            if (nx < 0 || ny < 0 || nx >= N || ny >= N || matrix[nx][ny] != 0) {
                dir = (dir+1) %4;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }

            // 다음 좌표로 이동
            x = nx;
            y = ny;
        }
        return matrix;
    }
}
