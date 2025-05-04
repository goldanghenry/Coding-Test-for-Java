package Implementation;

/*
    BOJ Gold 5, AC
    https://www.acmicpc.net/problem/5430
    R -> 뒤집기
    D -> 첫 번째 수 버리기
    => ArrayDeq + flag 사용해서 처리
 */

import java.io.*;
import java.util.*;

public class BOJ_G5_5430 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            String fuc = br.readLine();
            int n = Integer.parseInt(br.readLine());
            int len = n + (n-1) + 2;
            String str = br.readLine();
            str = str.substring(1, str.length() - 1); // 대괄호 제거
            ArrayDeque<Integer> lst = new ArrayDeque<>();

            // 문자열 파싱
            if (n > 0) {
                String[] nums = str.split(",");
                for (String s : nums) {
                    lst.add(Integer.parseInt(s));
                }
            }

            // 명령어 실행
            boolean leftFlag = true;
            boolean errorFlag = false;
            for (int i = 0; i < fuc.length(); i++) {
                char cmd = fuc.charAt(i);
                if (cmd == 'R') {   // 리버스라면 플래그만 변경하기
                    leftFlag = !leftFlag;
                } else {        // 원소 삭제
                    if (lst.isEmpty()) {    // 배열이 비었다면 에러 플래그 후 벗어남
                        errorFlag = true;
                        break;
                    }   // 플래그에 따라 앞, 뒤로 원소 삭제
                    if (leftFlag) lst.removeFirst();
                    else lst.removeLast();
                }
            }

            if (errorFlag) sb.append("error").append('\n');
            else {
                sb.append("[");
                while (!lst.isEmpty()) {
                    sb.append(leftFlag ? lst.pollFirst() : lst.pollLast());
                    if (!lst.isEmpty()) sb.append(",");
                }
                sb.append("]\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
