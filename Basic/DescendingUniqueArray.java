package Basic;

import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.TreeSet;

/*
    배열 제어하기
    배열의 중복값을 제거하고 배열 데이터를 내림차순으로 정렬해서 반환
 */

public class DescendingUniqueArray {
    // O(NlogN)
    public static int[] useStreamAPI(int[] arr) {
//        Integer[] result = Arrays.stream(arr).boxed().distinct().toArray(Integer[]::new);
//        Arrays.sort(result, Collections.reverseOrder());
//        return Arrays.stream(result).mapToInt(Integer::intValue).toArray();
        return Arrays.stream(arr)
                .distinct()
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
    }

    // O(NlogN)
    public static int[] useTreeSet(int[] arr) {
        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());
        for (int num : arr) {
            set.add(num);
        }
        int[] result = new int[set.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = set.pollFirst();
        }
        return result;
    }


}


