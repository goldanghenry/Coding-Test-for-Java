package Basic;

import java.util.Scanner;

public class SandGlass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("모래시계 크기(중앙의 별 줄 수)를 입력하세요: ");
        int n = sc.nextInt();
        
        // 상단 부분: 위에서부터 가운데까지
        for (int i = 0; i < n; i++) {
            // 앞쪽 공백 출력
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            // 별 출력 (별의 개수는 2*(n-i)-1)
            for (int j = 0; j < 2 * (n - i) - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        
        // 하단 부분: 가운데에서 아래로
        for (int i = n - 2; i >= 0; i--) {
            // 앞쪽 공백 출력
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            // 별 출력 (별의 개수는 2*(n-i)-1)
            for (int j = 0; j < 2 * (n - i) - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        
        sc.close();
    }
    
}
