package Greedy;

import java.util.Scanner;

/*
 * JO gold 3 1183, 동전 자판기
 * https://jungol.co.kr/problem/1183?cursor=eyJwcm9ibGVtc2V0Ijo4LCJmaWVsZCI6Mn0%3D
 * 최대 개수의 동전을 이용하여 자판기의 물건을 구입하는 방법
 * -> 어떠한 동전들의 조합으로도 정확한 물건값이 나오지 않는 경우는 없다고 가정하기에
 * -> (total - W ) + W 에서, (total - W) 를 최소한의 동전으로 구성하면, w는 최대한의 동전으로 구성된다.
 */

public class JO_G3_1183 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] coin = {0, 500, 100, 50, 10, 5, 1};
        int[] cnt = new int[7];
        int[] useCoin = new int[7];
        int total = 0;

        int W = sc.nextInt();   // 구입하려는 물건의 값
        for (int i = 1; i < 7; i++) {
            cnt[i] = sc.nextInt();  // 동전의 개수
            total += coin[i] * cnt[i];
        }

        total -= W; // 거스름돈 계산
        for (int i = 1; i < 7; i++) {
            if (cnt[i] == 0) continue;
            int needed = total / coin[i];             // 이상적으로 필요한 동전 수
            int use = Math.min(needed, cnt[i]);       // 실제로 사용 가능한 동전 수 (제한 고려)
            useCoin[i] = use;
            total -= use * coin[i];                   // 거스름돈 금액 차감
        }

        int sum = 0;
        for (int i = 1; i < 7; i++) {
            cnt[i] -= useCoin[i];
            sum += cnt[i];
        }

        System.out.println(sum);
        for (int i = 1; i < 7; i++) {
            System.out.print(cnt[i] + " ");
        }
        sc.close();
    }
}

