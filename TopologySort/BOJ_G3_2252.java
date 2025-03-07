package TopologySort;

import java.io.*;
import java.util.*;

/*
 * BOJ Gold 3, 줄 세우기
 */

public class BOJ_G3_2252 {
    static int N, M; // N 정점, M: 간선
    static int[] inD;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = sc.nextInt();
        M = sc.nextInt();
        list = new ArrayList[N+1];
        inD = new int[N+1];
        for(int i = 0; i < list.length; i++) {
            list[i] = new ArrayList();
        }

        int x, y;
        for(int i = 0; i < M; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            list[x].add(y); /// x -> y
            // 진입 차수 관리
            inD[y]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inD[i] == 0) {
                q.offer(i);
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        while(!q.isEmpty()) {
            int cur = q.poll();
            res.add(cur);

            for (int idx : list[cur]) {
                inD[idx]--;
                if (inD[idx] == 0) {
                    q.offer(idx);
                }
            }
        }

        // 판단
        for (int i : res) {
            sb.append(i).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
