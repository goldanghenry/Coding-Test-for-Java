package Graph;

import java.util.StringTokenizer;
import java.io.*;

/*
    SWEA. 무선 충전
    https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRDL1aeugDFAUo&
 */
public class SWEA_5664 {
    static class BC {
        int x, y, cover, perform;

        BC(int x, int y, int cover, int perform) {
            this.x = x;
            this.y = y;
            this.cover = cover;
            this.perform = perform;
        }
    }

    static int M, N;
    static int[] Arr;
    static int[] Brr;
    static BC[] bc;

    static int[] dx = {0, -1, 0, 1, 0};
    static int[] dy = {0, 0, 1, 0, -1};

    static int aX, aY, bX, bY, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스
        int T = Integer.parseInt(st.nextToken());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());   // 이동시간
            N = Integer.parseInt(st.nextToken());   // BC의 개수

            ans = 0;
            aX = 1;
            aY = 1;
            bX = 10;
            bY = 10;
            Arr = new int[M];         // A의 이동정보
            Brr = new int[M];         // B의 이동정보

            // A 이동 경로 입력 받기
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                Arr[i] = Integer.parseInt(st.nextToken());
            }
            // B 이동 경로 입력 받기
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                Brr[i] = Integer.parseInt(st.nextToken());
            }

            // BC 정보 입력받기, x, y 좌표, 범위, 성능
            bc = new BC[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int cover = Integer.parseInt(st.nextToken());
                int perform = Integer.parseInt(st.nextToken());
                bc[i] = new BC(y, x, cover, perform);   // 문제에서는 x, y가 반대...
            }

            // 시뮬레이션 시작
            charge();
            for (int step = 0; step < M; step++) {
                aX += dx[Arr[step]];
                aY += dy[Arr[step]];
                bX += dx[Brr[step]];
                bY += dy[Brr[step]];
                charge();
            }
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
    }
    static void charge() {
        int[] canA = new int[N];
        int[] canB = new int[N];
        int cntA = 0;
        int cntB = 0;

        // BC를 순회하며 충전 가능한지 조사
        for (int i = 0; i < N; i++) {
            BC tmp = bc[i];
            // A가 충전가능한가?
            if (tmp.cover >= Math.abs(aX - tmp.x) + Math.abs(aY - tmp.y)) {
                canA[i] = 1;
                cntA++;
            }
            // B가 충전가능한가?
            if (tmp.cover >= Math.abs(bX - tmp.x) + Math.abs(bY - tmp.y)) {
                canB[i] = 1;
                cntB++;
            }
        }

        // 둘다 충전 불가능한 경우
        if (cntA == 0 &&  cntB == 0) {}

        // A만 가능한 경우
        else if(cntB == 0) {
            int maxCharging = 0;
            for (int i = 0; i < N; i++) {
                if (canA[i] == 0) continue;
                maxCharging = Math.max(maxCharging, bc[i].perform);
            }
            ans += maxCharging;
        }
        // B만 가능한 경우
        else if(cntA == 0) {
            int maxCharging = 0;
            for (int i = 0; i < N; i++) {
                if (canB[i] == 0) continue;
                maxCharging = Math.max(maxCharging, bc[i].perform);
            }
            ans += maxCharging;
        }
        else {
            int maxCharging = 0;
            for (int i = 0; i < N; i++) {
                if (canA[i] == 0) continue;
                BC bcA = bc[i];
                int tempA = bcA.perform;

                for (int j = 0; j < N; j++) {
                    if (canB[j] == 0) continue;
                    BC bcB = bc[j];
                    int tempB = bcB.perform;

                    if (i != j) {   // 다른 충전기 일 경우
                        maxCharging = Math.max(maxCharging, tempA + tempB);
                    } else {    // 같은 충전기 일 경우
                        maxCharging = Math.max(maxCharging, tempA);
                    }
                }
            }
            ans += maxCharging;
        }
    }
}
