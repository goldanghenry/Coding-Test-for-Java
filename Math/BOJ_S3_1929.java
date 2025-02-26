package Math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

/*
 * BOJ silver 3 소수 구하기
 * https://www.acmicpc.net/problem/1929
 * 에라토스테네스의 체
 */

class BOJ_S3_1929 {
    public static void main(String[] args) throws IOException {
        // BufferedReader를 사용해 빠른 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        
        // N+1 크기의 배열 생성, 0과 1은 소수가 아님
        boolean[] arr = new boolean[N+1];
        arr[0] = arr[1] = true;
        
        // 에라토스테네스의 체 알고리즘 적용
        for (int i = 2; i*i <= N; i++) {
            if (arr[i]) continue;
            for (int j = i*i; j <= N; j+=i) {
                arr[j] = true;
            }
        }
        
        // StringBuilder에 결과를 저장하여 출력 횟수를 최소화
        StringBuilder sb = new StringBuilder();
        for (int i = M; i <= N; i++) {
            if (!arr[i]) {
                sb.append(i).append("\n");
            }
        }
        System.out.print(sb.toString());
    }
}
