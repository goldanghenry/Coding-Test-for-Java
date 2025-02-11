package Basic;

import java.io.*;
import java.util.StringTokenizer;

// 입력과 동시에 처리해도 된다.

public class SlidingWindow {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 초기 윈도우 생성
        // int i = 0, j = 0;   // 윈도우 시작, 끝
        int j = 0;
        long sum = 0;
        while (j < M) sum += arr[j++];

        long max = sum; // 최대값의 초기값으로 초기윈도우 값 생성

        // 윈도우 뒤로 하나씩 밀면서 처리
        while(j < N) {
            sum -= arr[j-M];
            // sum -= arr[i++];
            sum += arr[j++];
            max = Math.max(max, sum);
        }
        System.out.println(max);
    }
}
