package BruteForceSearch;

/*  programmers 모의고사
    https://school.programmers.co.kr/learn/courses/30/lessons/42840
 */

import java.util.ArrayList;
import java.util.Arrays;

public class PRGS_LV1_42840 {
    public static int[] solution(int[] answers) {
        int[][] pattern = {
                {1,2,3,4,5},
                {2,1,2,3,2,4,2,5},
                {3,3,1,1,2,2,4,4,5,5}
        };

        int[] scores = new int[3];
        for (int i = 0; i < answers.length; i++) {
            for (int j = 0; j < pattern.length; j++) {
                if (answers[i] == pattern[j][ i % pattern[j].length]) {
                    scores[j]++;
                }
            }
        }
        int maxScore = Arrays.stream(scores).max().getAsInt();

        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == maxScore) {
                answer.add(i+1);
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
