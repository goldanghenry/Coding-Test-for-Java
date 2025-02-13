package Basic;

public class CompleteBinaryTreeTest {
    public static void main(String[] args) {
        String[] names = {"이원준","김준영", "이권민", "윤진", "임규린", "이예린"};
        int size = names.length;
        CompleteBinaryTree<String> tree = new CompleteBinaryTree<>(size);

        // 트리 생성
        for (String name : names) {
            tree.add(name);
        }

        tree.bfs();

    }
}
