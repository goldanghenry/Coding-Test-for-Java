package Basic;

public class Recursive {
    
    private static void printArray(int[] arr, int idx) {
        if (idx == arr.length) {
            System.out.println();
            return;
        }
        System.out.print(arr[idx]);
        printArray(arr, idx+1);
    }

    private static void printStar(int n) {
        if (n==0) return ;
        // 유도파트
        printStar(n-1);
        for (int i = 0; i < n ; i++) System.out.print("*");
        System.out.println();
    }

    public static void main(String[] args) {
        
    }


}
