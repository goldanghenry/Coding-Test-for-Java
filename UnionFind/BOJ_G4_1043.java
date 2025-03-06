package UnionFind;

import java.io.*;
import java.util.*;

/*
 * BOJ Gold4 1043, 거짓말
 * https://www.acmicpc.net/problem/1043
 */

public class BOJ_G4_1043 {
    static int[] parent;
    static int cnt;

    static int find(int x) {
        if( x == parent[x]) return x;
        else return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 사람 수
        int M = Integer.parseInt(st.nextToken());   // 파티 수

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        cnt = 0;
        // 진실을 아는 사람이 없는 경우
        if (k == 0) {
            System.out.println(M);
            for(int i = 0; i < M; i++) {
                br.readLine();
            }
        }

        else {
            // 아는 사람 입력 받기
            int[] truth = new int[k];
            for (int i = 0; i < k; i++) {
                truth[i] = Integer.parseInt(st.nextToken());
            }

            // Union-find 초기화
            parent = new int[N+1];
            for(int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            // 모든 파티의 정보를 저장할 리스트
            List<int[]> parties = new ArrayList<>();

            // 파티 정보 저장
            for (int p = 0; p< M; p++) {
                st = new StringTokenizer(br.readLine());
                int partyNum = Integer.parseInt(st.nextToken());
                int[] partyMembers = new int[partyNum];

                for (int j = 0; j < partyNum; j++) {
                    partyMembers[j] = Integer.parseInt(st.nextToken());
                }
                parties.add(partyMembers);

                // 파티의 모든 사람을 연결
                for (int j = 1; j < partyNum; j++) {
                    union(partyMembers[0], partyMembers[j]);
                }
            }
            // 진실을 아는 사람 -> 집합
            for (int i = 1; i < k; i++) {
                union(truth[0], truth[i]);
            }
            int truthRep = find(truth[0]); // 진실 그룹 대표

            // 각 파티마다 진실을 아는 사람과 연결되어 있는지 확인
            int result = 0;
            for (int[] party: parties) {
                boolean canLie = true;
                for (int member : party) {
                    if(find(member) == truthRep) {
                        canLie =false;
                        break;
                    }
                }
                if (canLie) result++;
            }
            System.out.println(result);
        }
    }
}
