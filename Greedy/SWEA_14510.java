package Greedy;

import java.io.*;
import java.util.StringTokenizer;

/*
*  SWEA 나무 높이
* https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AYFofW8qpXYDFAR4&categoryId=AYFofW8qpXYDFAR4&categoryType=CODE
* ((가장 큰 나무) - (각 나무) ) % 3 을 하며 줄이기
*/

public class SWEA_14510 {
    public static void main(String[] args) throws IOException {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter  bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder	sb = new StringBuilder();
        int              T = Integer.parseInt(st.nextToken());  // 테스트 케이스 수

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());  // 나무 개수
            int[] arr = new int[N];
            int max = 0;
            int odd = 0;
            int even = 0;
            int result;

            // 나무 높이 입력 및 최대값 찾기
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                max = Math.max(max, arr[i]);  // 최대 높이 저장
            }

            // 각 나무가 최대 높이에 도달하기까지 필요한 성장량 계산
            for (int i = 0; i < N; i++) {
                odd += (max-arr[i]) % 2;    // 홀수 날짜의 개수
                even += (max-arr[i]) / 2;   // 짝수 날짜의 개수
            }

            int temp = Math.max(even - odd, 0) /3;  // 4 ,1 -> 1
            odd += temp *2; // 1 + 2 = 3
            even -= temp;   // 4 - 1 = 3
            int min = Math.min(even, odd);

            result = min * 2
                    + Math.max((odd - min) *2 - 1, 0)
                    + (even - min) / 2 * 3
                    +(even - min) % 2 * 2;
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}