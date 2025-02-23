package Stack;

import java.util.Scanner;

public class SWEA_1225 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // test case
        for (int t = 1; t <= 10; t++) {
            sc.nextInt();
            int[] arr = new int[8];
            for (int i = 0; i < 8; i++) {
                arr[i] = sc.nextInt();
            }

            int weight = 0, idx = -1;
            while (true) {
                weight = weight % 5 + 1;
                idx = (idx + 1) % 8;
                arr[idx] -= weight;
                if (arr[idx] <= 0) {  // 음수가 되거나 0 이하인 경우 0으로 유지
                    arr[idx] = 0;
                    break;
                }
            }

            System.out.print("#" + t);
            int cnt = 0;
            // 현재 인덱스의 다음 위치부터 8개 숫자를 순서대로 출력
            while (cnt < 8) {
                idx = (idx + 1) % 8;
                System.out.print(" " + arr[idx]);
                cnt++;
            }
            System.out.println();
        }
    }
}