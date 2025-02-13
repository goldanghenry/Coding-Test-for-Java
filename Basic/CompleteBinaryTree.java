package Basic;

import java.util.ArrayDeque;
import java.util.Queue;

public class CompleteBinaryTree<T> {
    private Object[] nodes;
    private final int MAX_SIZE;
    private int lastIndex;

    public CompleteBinaryTree(int size) {
        this.MAX_SIZE = size;
        nodes = new Object[size+1]; // 0 인덱스 공간 사용하지 않음
    }

    public boolean isEmpty() {
        return lastIndex == 0;
    }

    public boolean isFull() {
        return lastIndex == MAX_SIZE;
    }

    public void add(T e) {
        if(isFull()) throw new RuntimeException("트리가 포화상태입니다.");
        nodes[++lastIndex] = e;
    }
    public void bfs() {
        if(isEmpty()) throw new RuntimeException("빈트리 상태입니다..");

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1); // 시작노드인 루트 노드 큐에 기록하기

        while (!queue.isEmpty()) {  // 방문할 노드가 있을 때까지 반복
            int current = queue.poll();     // 방문할 노드 꺼내기
            System.out.println(nodes[current]);    // 노드로 처리할 로직 수행

            if (current*2 <= lastIndex) queue.offer(current*2); // 왼쪽 자식이 있다면 큐에 기록하기
            if (current*2+1 <= lastIndex) queue.offer(current*2+1); // 오른쪽 자식이 있다면 큐에 기록하기
        }
    }


    /* 탐색하는 대상의 레벨이 필요한 경우? 
    * 완전 이진트리의 경우 바로 계산도 할 수 있지만, 
    * 1. 일반적인 트리의 경우에는 큐에 넣을 때 레벨도 함께 넣기
    * 2. 
    */
        public void bfs2() {
        if(isEmpty()) throw new RuntimeException("빈트리 상태입니다..");

        int breadth = 0; // 너비
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);     // 시작노드인 루트 노드 큐에 기록하기
        
        while(!queue.isEmpty()) {

            System.out.print("너비 "+breadth+" :");
            // 큐에 있는 노드 수 체크 ( 새로운 노드가 큐에 들어가기 전의 큐의 노드 수)
            int size = queue.size();    // 같은 너비를 갖고 있는 노드 수

            while (--size >= 0) { 

                int current = queue.poll();     // 방문할 노드 꺼내기
                System.out.println(nodes[current]+"\t");    // 노드로 처리할 로직 수행

                if (current*2 <= lastIndex) queue.offer(current*2); // 왼쪽 자식이 있다면 큐에 기록하기
                if (current*2+1 <= lastIndex) queue.offer(current*2+1); // 오른쪽 자식이 있다면 큐에 기록하기
            }
            System.out.println();
            breadth++;
        }
    }

}

