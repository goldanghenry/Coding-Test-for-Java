package Sort;
import java.util.HashSet;
/*
    programmers 두 개 뽑아서 더하기
    https://school.programmers.co.kr/learn/courses/30/lessons/68644?language=java
    O(N^2log(N^2))
 */
public class PRGS_LV1_68644 {
    public int[] solution(int[] numbers) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < numbers.length -1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                set.add(numbers[i]+numbers[j]);
            }
        }

        return set.stream().sorted().mapToInt(Integer::intValue).toArray();
    }
}
