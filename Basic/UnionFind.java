/*
Union-Find(Disjoint Set) 개념
**Union-Find(또는 Disjoint Set, 서로소 집합)**는 여러 개의 요소가 존재할 때, 
서로소 집합(겹치지 않는 그룹)을 관리하는 자료구조입니다. 
주로 네트워크 연결, MST(최소 신장 트리) Kruskal 알고리즘, 동적 연결성 문제 등에 사용됩니다.

핵심 연산
1. Find(경로 압축 최적화)
- 특정 노드의 루트(parent)를 찾습니다.
- 경로 압축(Path Compression)을 적용하여 트리의 깊이를 줄여줍니다.
2. Union(랭크 최적화 포함)
- 두 개의 집합을 합칩니다.
- 보통 더 작은 트리를 큰 트리에 붙여 트리의 균형을 맞춥니다(랭크 최적화).
3. isSameParent(같은 집합 여부 확인)
- 두 노드가 같은 루트(부모)를 가지는지 확인합니다.
 */

package Basic;

public class UnionFind {
    public static int[] parent;
    public static int[] rank;

    // 부모 노드를 찾는 함수 (경로 압축 최적화)
    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);  // 경로 압축 (Path Compression)
    }

    // 두 집합을 합치는 함수 (Union by Rank)
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            if (rank[x] > rank[y]) {
                parent[y] = x;  // 작은 랭크를 큰 랭크에 붙임
            } else if (rank[x] < rank[y]) {
                parent[x] = y;
            } else {
                parent[y] = x;
                rank[x]++;  // 같은 랭크면 하나 증가
            }
        }
    }

    // 같은 집합인지 확인하는 함수
    public static boolean isSameParent(int x, int y) {
        return find(x) == find(y);
    }

    public static void main(String[] args) {
        int n = 10;  // 예제에서는 10개의 원소를 가정
        parent = new int[n + 1];
        rank = new int[n + 1];

        // 초기화
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;  // 처음엔 높이가 0
        }

        // Union 연산
        union(1, 2);
        union(2, 3);
        union(4, 5);
        union(6, 7);
        union(5, 6);
        union(3, 7);

        // 같은 집합인지 확인
        System.out.println("1과 7은 연결되어 있나요? " + isSameParent(1, 7));  // true
        System.out.println("4과 6은 연결되어 있나요? " + isSameParent(4, 6));  // true
        System.out.println("1과 5은 연결되어 있나요? " + isSameParent(1, 5));  // true
        System.out.println("8과 9는 연결되어 있나요? " + isSameParent(8, 9));  // false
    }
}

