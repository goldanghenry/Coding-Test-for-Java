package Stack;

import java.io.*;
import java.util.ArrayDeque;

/*
    SWEA 1218. 괄호 짝짓기 (D4)
    https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14eWb6AAkCFAYD
 */

public class SWEA_1218 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t =1; t <= 10; t++) {
            int N = Integer.parseInt(br.readLine());
            char[] chars = br.readLine().toCharArray();
            ArrayDeque<Character> stack = new ArrayDeque<>();
            int res = 1;

            for (int i = 0; i < N; i++) {
                char c = chars[i];
                // 여는 괄호인 경우 push
                if (c == '(' || c == '{' || c == '[' || c == '<') {
                    stack.push(c);
                } else {    // 닫는 괄호인 경우
                    // stack이 비었다면 잘못된 괄호열
                    if (stack.isEmpty()) {
                        res = 0;
                        break;
                    }
                    // pop 후 짝이 맞는지 검사
                    char top = stack.pop();
                    if ((top == '(' && c != ')') ||
                        (top == '{' && c != '}') ||
                        (top == '[' && c != ']') ||
                        (top == '<' && c != '>')) {
                        res = 0;    // 유효하지 않음
                        break;
                    }
                }
            }
            // 스택에 괄호가 남아있다면 유효하지 않음
            if (!stack.isEmpty()) res = 0;
            System.out.println("#" + t + " " + res);
        }
    }
}
