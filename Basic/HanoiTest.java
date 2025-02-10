package Basic;
import java.util.Scanner;

public class HanoiTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        hanoi(N, 1, 2, 3);
    }

    // 옮길 원판 수, 시작 기둥, 임시 기둥, 목적 기둥
    private static void hanoi(int n, int from, int temp, int to) {
        if (n==0) return;

        hanoi(n-1, from, to, temp);
        System.out.println(n+" "+from+"->"+to); // 출력량이 많다 ==> 최적화할것!!
        hanoi(n-1, temp, from, to);
    }
}
