package Greedy;

/*
    SWEA D3 2805, 농작물 수확하기
    https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7GLXqKAWYDFAXB
    중심과 좌표와의 거리를 구해 맨해튼 거리 이내라면 수확하기
 */

import java.io.*;

public class SWEA_D3_2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());    // 농장 크기
            int result = 0;                             // 수확 결과
            int center = N/2;                           // 중심

            for (int i = 0; i < N; i++) {
                String inputs = br.readLine();
                for (int j = 0; j < N; j++) {
                    if ( Math.abs(i-center) + Math.abs(j-center) <= center) // 맨해튼 거리 내라면
                        result += inputs.charAt(j) - '0';
                }
            }
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }
}
