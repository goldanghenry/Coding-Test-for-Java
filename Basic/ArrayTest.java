package Basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ArrayTest {
    public static void main(String[] args) {

        // 기본 배열
        int[] arr = {1,2,3,4,5};
        System.out.println(arr.length); // 배열의 길이
        Arrays.sort(arr);   // 오름차순 정렬

        // 선언
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);    // 데이터 추가
        list.add(2);
        list.add(3);

        // list로 초기화
        ArrayList<Integer> list2 = new ArrayList<>(list);
        list.get(0);    // 가져오기
        list.get(2);

        // 삭제
        // O(N) -> 가운데 원소를 삭제하면 뒷 원소들을 복사해야하기에
        list.remove(1); // 삭제
        list.remove(list.size()-1); // 제일 뒤에 있는 원소 삭제

        // 생성과 동시에 초기화 및 정렬
        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(1,2,5,4,3));
        System.out.println(list3.size());
        System.out.println(list3.isEmpty());
        Collections.sort(list3);

    }
}
