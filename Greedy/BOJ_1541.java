package Greedy;
import java.util.Scanner;
/*
 * BOJ_1541 silver2 잃어버린 괄호
 * https://www.acmicpc.net/problem/1541
 * 식에서 '-' 기호 이후에 나오는 모든 수들의 합을 한꺼번에 빼면 최솟값
 */

public class BOJ_1541 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] sub = input.split("-");  
        int result = 0;
        for (int i = 0; i < sub.length; i++) {  
            String[] add = sub[i].split("\\+");
            int sum = 0;
            for (int j = 0; j < add.length; j++) { 
                sum += Integer.parseInt(add[j]);
            }
            if (i == 0) {
                result += sum;
            } else {
                result -= sum;
            }
        }
        System.out.println(result);
    }
}
