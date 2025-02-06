package Basic;

import java.util.Stack;

public class DecimalToBinary {
    public static String solution(int decimal) {
        Stack<Integer> stack = new Stack<>();

        while ( decimal > 0) {
            int remainder = decimal % 2;
            decimal /= 2;
            stack.push(remainder);
        }

        // string의 +연산은 시간복잡도 상 좋지 않기에
        // StringBuilder를 사용한다. O(logN)
        StringBuilder sb = new StringBuilder();
        while( !stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}
