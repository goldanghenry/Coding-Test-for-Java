	
package Graph;

import java.io.*;
import java.util.StringTokenizer;

public class T1_Solution {
    static int N, P; // 화분의 수, 연속했을 때 감소하는 수
    static int result;
    static int[][] arr;    // 0은 a, 1은 b

    static void dfs(int[] condArr, int curDepth) {
        if (curDepth == N) {
            int sum = 0;
            int pre= -1;

            for(int i = 0; i < N; i++) {
                if (i ==0) {    // 처음 위치의 경우 그대로 들어감
                    sum += arr[condArr[i]][i];
                    pre = condArr[i];
                }
                else {    // 두번째 이후부터는 연속됐는지 비교
                    if ( condArr[i] == pre) { // 연속인 경우
                        sum += arr[condArr[i]][i] - P;
                    } else {    // 연속이 아닌 경우
                        sum += arr[condArr[i]][i];
                    }
                }
            }
            // 계산 후 result 저장
            result = Math.max(result, sum);
            return;
        }
        condArr[curDepth] = 0;
        dfs(condArr, curDepth+1 );
        condArr[curDepth] = 1;
        dfs(condArr, curDepth+1);
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());

            arr = new int[N][N];
            result = 0;

            st = new StringTokenizer(br.readLine());
            for ( int i = 0; i < N; i++) {
                arr[0][i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for ( int i = 0; i < N; i++) {
                arr[1][i] = Integer.parseInt(st.nextToken());
            }

            int[] condArr = new int[N];
            dfs(condArr, 0);

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}