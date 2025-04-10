import java.io.*;
import java.util.*;

public class SpiralMatrixCustomDirection {
    public static void main(String[] args) throws Exception {
        int N = 6;  // N*N 크기의 배열
        int[][] arr = generateSpiralMatrix(N);
        printMatrix(arr);
    }

    public static int[][] generateSpiralMatrix(int N) {
        int[][] matrix = new int[N][N];
        int value = 1;
        
        // 처음 방향을 아래 -> 오른쪽 -> 위 -> 왼쪽으로 설정
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 아래, 오른쪽, 위, 왼쪽
        int dirIndex = 0; // 현재 방향의 인덱스

        int x = 0, y = 0;  // 시작 좌표 (0, 0)
        int top = 0, bottom = N - 1, left = 0, right = N - 1;

        while (value <= N * N) {
            matrix[x][y] = value++;  // 현재 위치에 값을 넣음

            // 다음 좌표 계산
            int nextX = x + directions[dirIndex][0];
            int nextY = y + directions[dirIndex][1];

            // 경계를 벗어나거나 이미 값이 채워진 경우 방향 전환
            if (nextX > bottom || nextX < top || nextY > right || nextY < left || matrix[nextX][nextY] != 0) {
                dirIndex = (dirIndex + 1) % 4; // 방향을 변경
                nextX = x + directions[dirIndex][0];
                nextY = y + directions[dirIndex][1];
            }

            // 다음 좌표로 이동
            x = nextX;
            y = nextY;

            // 각 방향에 따라 경계 업데이트
            if (dirIndex == 0) {         // 아래로 가는 경우
                top++;  // 위쪽 경계 증가
            } else if (dirIndex == 1) {  // 오른쪽으로 가는 경우
                right--;  // 오른쪽 경계 감소
            } else if (dirIndex == 2) {  // 위로 가는 경우
                bottom--;  // 아래쪽 경계 감소
            } else if (dirIndex == 3) {  // 왼쪽으로 가는 경우
                left++;  // 왼쪽 경계 증가
            }
        }

        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%3d ", val);
            }
            System.out.println();
        }
    }
}
