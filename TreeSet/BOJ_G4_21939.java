package TreeSet;

import java.io.*;
import java.util.*;

/*
 * BOJ 21939 Gold 4, 문제 추천 시스템 Version1
 * https://www.acmicpc.net/problem/21939
 * 문제 -> 번호, 난이도
 * recommend x : x == 1, 추천 문제 리스트에서 가장 어려운 문제 번호, 같다면 문제 번호 큰 것
 *               x == -1, 추천 문제 리스트에서 가장 쉬운 문제 번호, 같다면 문제 번호가 작은 것
 * add P L : 추천 문제 리스트 난이도가 L인 문제 번호 P를 추가
 * solved P : 추천 문제 리스트에서 문제 번호 P를 제거
 *               
 */

/*
 * TreeSet
 * Set 인터페이스를 구현하면서 정렬된(sorted) 요소들을 관리하는데 사용
 * Red-Black Tree(자기 균형 이진 검색 트리)를 기반으로 구현
 * 
 * [ 특징 ]
 * 1. 자동 정렬 -> 요소를 추가할 때마다 오름차순(또는 Comparator)
 * 2. 중복 허용 안됨 -> 이미 존재하는 요소를 추가하면 무시
 * 3. 시간 복잡도 -> 삽입, 삭제, 탐색 등의 연산 - O(log n)
 * 4. NavigableSet -> 특정 값의 바로 아래, 위 값, 가장 가까운 값 등을 찾을 수 있는 메서드
 * 5. null 값 주의 -> null을 저장할 경우 nullPointerException 발생
 */



public class BOJ_G4_21939 {
    private static class Problem {
        int no, rank;   // 난이도와 랭크
        public Problem(int no, int rank) {
            this.no = no;
            this.rank = rank;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());    // 추천 문제리스트의 문제 수

        // 추천 리스트 구현
        HashMap<Integer, Integer> map = new HashMap<>();    // 빠른 검색과 삭제
        TreeSet<Problem> set = new TreeSet<>(new Comparator<Problem>(){
            @Override
            public int compare(Problem o1, Problem o2) {
                if (o1.rank == o2.rank) return o1.no - o2.no;
                else return o1.rank - o2.rank;
            }
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int no = Integer.parseInt(st.nextToken());
            int rank = Integer.parseInt(st.nextToken());
            set.add(new Problem(no,rank));
            map.put(no,rank);
        }

        int M = Integer.parseInt(br.readLine());    // 명령의 개수
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            char cmd = st.nextToken().charAt(0);
            
            switch (cmd) {
                case 'a' : {
                        int no = Integer.parseInt(st.nextToken());
                        int rank = Integer.parseInt(st.nextToken());
                        map.put(no,rank);
                        set.add(new Problem(no, rank));
                        break;
                    }
                case 'r' : {
                        int num = Integer.parseInt(st.nextToken());
                        if (num == 1) {
                            sb.append(set.last().no).append("\n");
                        } else if(num == -1) {
                            sb.append(set.first().no).append("\n");
                        }     break;
                    }
                case 's': {
                        int num = Integer.parseInt(st.nextToken());
                        int rank = map.get(num);
                        set.remove(new Problem(num, rank));
                        map.remove(num);
                        break;
                    }
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
