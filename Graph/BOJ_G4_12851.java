package Graph;

import java.util.*;

/*
 * BOJ Gold4 숨바꼭질 2
 * https://www.acmicpc.net/problem/12851
 * bfs
 */

public class BOJ_G4_12851 {
    static int N,K;
    static int MAX = 100000;
    static int[] time = new int[MAX +1];    // 해당 위치까지 도달하는 최소 시간
    static long[] count = new long[MAX + 1];// 해당 최소 시간으로 도달하는 경우의 수

    private static void bfs(int start) {
        Arrays.fill(time, -1); // 아직 방문하지 않은 상태를 -1로 초기화
        Queue<Integer> queue = new LinkedList<>();
        time[start] = 0;    // 시작 위치의 시간은 0
        count[start] = 1;   // 시작 위치까지 도달하는 경우의 수는 1
        queue.offer(start);
        
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            int nextTime = time[cur] +1;

            // 현재 위치에서 이동 가능한 세 가지 경우
            for (int next : new int[]{cur-1, cur+1, cur*2}) {
                if (next < 0 || next > MAX) continue;

                // 아직 방문하지 않은 경우 : 최소 시간과 경우의 수를 갱신
                if (time[next] == -1) {
                    time[next] = nextTime;
                    count[next] = count[cur];   // 현재 경우의 수를 다음에 그대로 넣기
                    queue.offer(next);
                }
                // 이미 방문한 적이 있으나, 동일한 최소 시간으로 도달하는 경우
                else if (time[next] == nextTime) {
                    count[next] += count[cur];
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);    
        N = sc.nextInt();
        K = sc.nextInt();

        bfs(N);
        System.out.println(time[K]);
        System.out.println(count[K]);
    }
}
