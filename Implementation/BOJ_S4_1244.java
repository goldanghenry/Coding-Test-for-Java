package Implementation;

import java.util.*;
import java.io.*;

/*
 * BOJ 1244 Silver 4, 스위치 켜고 끄기
 * https://www.acmicpc.net/problem/1244
 */

public class BOJ_S4_1244 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        boolean[] arr = new boolean[N];

        st = new StringTokenizer(br.readLine());
        int t = 0;
        for (int i = 0; i < N; i++) {
            t = Integer.parseInt(st.nextToken());
            arr[i] = t == 1? true: false;
        }

        int stNum = Integer.parseInt(br.readLine());
        List<int[]> stList = new ArrayList<>();
        for (int i = 0; i < stNum; i++) {
            st = new StringTokenizer(br.readLine());
            stList.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())-1});
        }

        for (int s = 0; s < stNum;s++) {
            int[] curS = stList.get(s);
            if (curS[0] == 1) {
                for (int i = curS[1]; i < N; i+=(curS[1]+1)) {
                    arr[i] = !arr[i];
                }
            } else {
                int cnt = 0;
                arr[curS[1]] = !arr[curS[1]];
                
                // 검사
                int left=curS[1], right = curS[1];
                while(true) {
                    // 좌우로 한칸씩 증가
                    left--;
                    right++;
                    if (left < 0 || right >=N) break;
                    if (arr[left] != arr[right]) break;
                    arr[left] = !arr[left];
                    arr[right] = !arr[right];
                    cnt++;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if (arr[i]) sb.append("1 ");
            else sb.append("0 ");
            // 20번째 스위치마다 개행 추가
    if ((i + 1) % 20 == 0) {
        sb.append("\n");
    }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
