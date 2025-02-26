package Implementation;

import java.io.*;
import java.util.StringTokenizer;

/*
    Boj 16926 Gold 5, 배열 돌리기 1
    https://www.acmicpc.net/problem/16926
    각 회전 라인을 분리해 한번에 R번 회전
 */

public class BOJ_G5_16926 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        // 맵 입력 받기
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 전체 라인 수
        int layers = Math.min(N,M) / 2;

        // 각 레이어별 회전 수행
        for (int l = 0; l < layers; l++) {
            int top = l;
            int left = l;
            int bottom = N - 1 - l;
            int right = M - 1 - l;

            // 현재 레이어의 원소 개수(둘레의 길이)
            int perimeter = 2 * (bottom - top + right - left);
            // 회전 수 계산
            int rotations = R % perimeter;  // 둘레 이상 회전할 경우 필요한 만큼만 반복

            // 현재 레이어의 원소들을 순서대로(시계방향) 저장
            int[] elements = new int[perimeter];
            int idx = 0;
            // 위쪽 행 : 왼쪽 -> 오른쪽
            for (int j = left; j <= right; j++) {
                elements[idx++] = map[top][j];
            }
            // 오른쪽 열 : 위 -> 아래
            for (int i = top + 1; i <= bottom-1; i++) {
                elements[idx++] = map[i][right];
            }
            // 아래 행 : 오른쪽 -> 왼쪽
            for (int j = right; j >= left; j--) {
                elements[idx++] = map[bottom][j];
            }
            // 왼쪽 열 : 아래 -> 위
            for (int i = bottom - 1; i >= top + 1; i--) {
                elements[idx++] = map[i][left];
            }

            // 회전 -> 반시계 방향
            int[] rotated = new int[perimeter];
            for (int i = 0; i < perimeter; i++) {
                rotated[i] = elements[(i + rotations) % perimeter];
            }

            // 회전된 결과를 다시 배열에 넣기
            idx = 0;
            // 위쪽 행
            for (int j = left; j <= right; j++) {
                map[top][j] = rotated[idx++];
            }
            // 우측 행
            for (int i = top + 1; i <= bottom-1; i++) {
                map[i][right] = rotated[idx++];
            }
            // 아래 행
            for (int j = right; j >= left; j--) {
                map[bottom][j] = rotated[idx++];
            }
            // 왼쪽 행
            for (int i = bottom - 1; i >= top + 1; i--) {
                map[i][left] = rotated[idx++];
            }
        }

        // 결과
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
