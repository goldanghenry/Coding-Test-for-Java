package Stack;

import java.util.ArrayDeque;

/*
 * programmers Lv2 올바른 괄호
 * https://school.programmers.co.kr/learn/courses/30/lessons/12909?language=java
 */

class PRGS_LV2_12909 {
    public static boolean solution(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        
        char[] arr = s.toCharArray();
        for( char c : arr) {
            if ( c == '(') {
                stack.push(c);
            }
            else {
                if (stack.isEmpty() || stack.pop() == c) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}