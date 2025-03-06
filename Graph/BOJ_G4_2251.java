package Graph;

import java.util.*;
/**
 * BOJ Gold 4 2251, 물통
 * https://www.acmicpc.net/problem/2251
 * 가능한 6가지 동작에 대해 bfs 진행
 */
public class BOJ_G4_2251 {

    public static class Comb {
        int a, b, c;
        public Comb(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    static int capaA, capaB, capaC;
    static PriorityQueue<Integer> ans;
    static boolean[][][] v;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        capaA = sc.nextInt();
        capaB = sc.nextInt();
        capaC = sc.nextInt();

        ans = new PriorityQueue<>();
        v = new boolean [201][201][201];

        bfs();

        StringBuilder sb = new StringBuilder();
        
        while (!ans.isEmpty()) {
            sb.append(ans.poll()).append(" ");
        }
        System.out.println(sb);

    }

    private static void bfs() {
        Queue<Comb> queue = new LinkedList<Comb>();
        queue.offer(new Comb(0,0,capaC));

        while(!queue.isEmpty()) {
            Comb cur = queue.poll();
            int a = cur.a;
            int b = cur.b;
            int c = cur.c;

            if (v[a][b][c]) continue;   // 이미 겪은 경우라면
            
            v[a][b][c] = true;          // 마킹
            if (a == 0) ans.add(c);     // a 물통이 비었다면 -> 정답에 추가

            // A -> B
            if (a+b >= capaB) queue.offer(new Comb(((a+b)-capaB),capaB,c)); // 넘치는 경우
            else queue.offer(new Comb(0, a+b, c));
            // A -> C
            if (a+c >= capaC) queue.offer(new Comb(((a+c)-capaC),b,capaC)); // 넘치는 경우
            else queue.offer(new Comb(0, b, a+c));
            // B -> A
            if (b+a >= capaA) queue.offer(new Comb(capaA,((a+b)-capaA),c)); // 넘치는 경우
            else queue.offer(new Comb(a+b, 0, c));
            // B -> C
            if (b+c >= capaC) queue.offer(new Comb(a,((b+c)-capaC),capaC)); // 넘치는 경우
            else queue.offer(new Comb(a, 0, b+c));
            // C -> A
            if (c+a >= capaA) queue.offer(new Comb(capaA,b,((a+c)-capaA))); // 넘치는 경우
            else queue.offer(new Comb(a+c, b, 0));
            // C -> B
            if (c+b >= capaB) queue.offer(new Comb(a,capaB,((b+c)-capaB))); // 넘치는 경우
            else queue.offer(new Comb(a, b+c, 0));
        }
    }
}
