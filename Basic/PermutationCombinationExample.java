package Basic;
/*
 * 순열 -> 순서가 의미 -> 중복으로 뽑을 수 있는가? -> 중복 순열
 * -> nPr : n * (n-1) * (n -2) * ... * (n -r +1)
 * -> nPn : n!
 * 조합 -> 순서가 의미 없음 -> 중복으로 뽑을 수 있는가? -> 중복 조합
 * -> nCr : n! / (n-r)! * r!
 */
public class PermutationCombinationExample {

    // 순열을 재귀적으로 구하는 메서드
    private static void permutation(int[] arr, boolean[] visited, int depth, int r) {
        if (depth == r) {
            // 원하는 r개를 모두 뽑아 나열했을 때 처리
            printArr(arr, r);
            return;
        }

        for (int i = depth; i < arr.length; i++) {
            swap(arr, depth, i);          // 현재 위치(depth)와 i를 교환
            permutation(arr, visited, depth + 1, r);
            swap(arr, depth, i);          // 원상 복구
        }
    }

    // 조합을 재귀적으로 구하는 메서드
    private static void combination(int[] arr, boolean[] visited, int start, int r) {
        if (r == 0) {
            printComb(arr, visited);
            return;
        }
        for (int i = start; i < arr.length; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, r - 1);
            visited[i] = false;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void printArr(int[] arr, int r) {
        for (int i = 0; i < r; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static void printComb(int[] arr, boolean[] visited) {
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        boolean[] visited = new boolean[arr.length];

        System.out.println("순열(Permutation) 결과:");
        permutation(arr, visited, 0, 2);

        System.out.println("조합(Combination) 결과:");
        combination(arr, visited, 0, 2);
    }
}

