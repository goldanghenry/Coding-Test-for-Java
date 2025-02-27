package Graph;

import java.io.*;
import java.util.StringTokenizer;

/*
    BOJ 6987 Gold4, 월드컵
    https://www.acmicpc.net/problem/6987
 */
public class BOJ_G4_6987 {
    // 15경기의 팀 조합 (6팀 중 2팀씩 고르는 조합)
    static int[][] games = new int[15][2];
    // 각 테스트 케이스별 팀 결과: [팀][0:승, 1:무, 2:패]
    static int[][] arr;
    // 가능한 결과인지 판별하는 플래그
    static boolean possible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 미리 15경기의 조합을 구합니다.
        int index = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                games[index][0] = i;
                games[index][1] = j;
                index++;
            }
        }

        // 4개의 테스트 케이스 처리
        for (int t = 0; t < 4; t++) {
            arr = new int[6][3];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // 기본적인 유효성 검사: 각 팀 경기수 5, 총 승수=총 패수, 무수는 짝수여야 함.
            if (!isValid(arr)) {
                sb.append("0 ");
                continue;
            }
            possible = false;
            dfs(0);
            sb.append(possible ? "1 " : "0 ");
        }
        System.out.println(sb.toString().trim());
    }

    // 각 팀의 경기 수와 전체 승패, 무 결과가 기본 조건을 만족하는지 검사
    static boolean isValid(int[][] arr) {
        int sumWins = 0, sumDraws = 0, sumLosses = 0;
        for (int i = 0; i < 6; i++) {
            if (arr[i][0] + arr[i][1] + arr[i][2] != 5) return false;
            sumWins += arr[i][0];
            sumDraws += arr[i][1];
            sumLosses += arr[i][2];
        }
        if (sumWins != sumLosses) return false;
        if (sumDraws % 2 != 0) return false;
        return true;
    }

    // matchIndex: 현재까지 채운 경기 수 (0 ~ 15)
    static void dfs(int matchIndex) {
        if (possible) return;
        if (matchIndex == 15) {
            possible = true;
            return;
        }
        int teamA = games[matchIndex][0];
        int teamB = games[matchIndex][1];

        // 경우 1: 팀 A 승 / 팀 B 패
        if (arr[teamA][0] > 0 && arr[teamB][2] > 0) {
            arr[teamA][0]--;
            arr[teamB][2]--;
            dfs(matchIndex + 1);
            arr[teamA][0]++;
            arr[teamB][2]++;
        }
        // 경우 2: 무승부
        if (arr[teamA][1] > 0 && arr[teamB][1] > 0) {
            arr[teamA][1]--;
            arr[teamB][1]--;
            dfs(matchIndex + 1);
            arr[teamA][1]++;
            arr[teamB][1]++;
        }
        // 경우 3: 팀 A 패 / 팀 B 승
        if (arr[teamA][2] > 0 && arr[teamB][0] > 0) {
            arr[teamA][2]--;
            arr[teamB][0]--;
            dfs(matchIndex + 1);
            arr[teamA][2]++;
            arr[teamB][0]++;
        }
    }
}