package Basic;

import java.util.Scanner;
import java.util.Arrays;

public class DiceTest {
    static int M, total;   // 던지는 주사위 횟수
    static int[] numbers;
    static boolean[] isSelected;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   // 순열 경우
        M = sc.nextInt();   // 던지는 주사위 횟수( M <= 6)

        numbers = new int[M];


        switch (N) {
            case 1:
                dice1(0);
                break;
            case 2:
                isSelected = new boolean[7];
                dice2(0);
                break;
            case 3:
                dice3(0, 1);
                break;
            case 4:
                dice4(0, 1);
                break;
        }
        System.out.println("경우의 수 : " + total);
    }

    // 중복 순열
    static void dice1(int cnt) {
        if ( cnt == M) {
            total++;
            System.out.println(Arrays.toString(numbers));
            return;
        }

        // 가능한 주사위 모든 눈 시도 ( 중복 허용 )
        for (int i = 1; i <= 6; i++) {
            numbers[cnt] = i;
            dice1(cnt +1);
        }
    }

    // 순열
    static void dice2(int cnt) {
        if ( cnt == M) {
            total++;
            System.out.println(Arrays.toString(numbers));
            return;
        }

        // 가능한 주사위 모든 눈 시도 ( 중복 허용 )
        for (int i = 1; i <= 6; i++) {
            if (isSelected[i]) continue;
            isSelected[i] = true;
            numbers[cnt] = i;
            dice2(cnt +1);
            isSelected[i] = false;
        }
    }

    // 중복 조합
    static void dice3(int cnt, int start) {
        if ( cnt == M) {
            total++;
            System.out.println(Arrays.toString(numbers));
            return;
        }

        // 가능한 주사위 모든 눈 시도 ( 중복 허용 )
        for (int i = start; i <= 6; i++) {
            numbers[cnt] = i;
            dice3(cnt +1, i);
        }
    }

    // 조합
    static void dice4(int cnt, int start) {
        if ( cnt == M) {
            total++;
            System.out.println(Arrays.toString(numbers));
            return;
        }

        // 가능한 주사위 모든 눈 시도 ( 중복 허용 )
        for (int i = start; i <= 6; i++) {
            numbers[cnt] = i;
            dice4(cnt +1, i+1);
        }
    }
}