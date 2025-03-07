package Greedy;

import java.io.*;

/*
    SWEA D5 6782 현주가 좋아하는 제곱근 놀이
    https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWgqsAlKr9sDFAW0
    N -> N +1 / sqrt(N), N == 2 가 되면 종료
    N+1 연산을 단축
 */

public class SWEA_D5_6782 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());    // test case
        for (int t = 1; t <= T; t++) {
            long N = Long.parseLong(br.readLine());

            int result = 0;
            while (N != 2) {
                double temp = Math.sqrt(N);
                if ((int)temp == temp) {    // 제곱근이 정수인가?
                    N = (int)temp;
                } else {    // 제곱근이 정수가 아님
                    temp = (int)temp +1;    // 올림
                    result += temp*temp -N;
                    N = (int)Math.sqrt(temp*temp);
                }
                result++;
            }
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}


