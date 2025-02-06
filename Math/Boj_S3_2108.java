package Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    Boj silver 3, 통계학
    https://www.acmicpc.net/problem/2108
 */

public class Boj_S3_2108 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] frequency = new int[8001];
        int sum = 0, maxFreq = 0, min = 4000, max = -4000;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];

            // 빈도수 기록
            frequency[arr[i]+4000]++;
            maxFreq = Math.max(maxFreq, frequency[arr[i]+4000]);

            if (arr[i] < min) min = arr[i];
            if (arr[i] > max) max = arr[i];
        }

        Arrays.sort(arr);

        // 최빈값 찾기
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 8001; i++) {
            if (frequency[i] == maxFreq) {
                list.add(i-4000);
            }
        }
        Collections.sort(list);
        int mode = (list.size() > 1) ? list.get(1) : list.get(0);

        // result
        System.out.println(Math.round((double)sum/N));
        System.out.println(arr[N/2]);
        System.out.println(mode);
        System.out.println(max-min);
    }
}
