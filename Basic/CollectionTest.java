package Basic;

import java.util.*;

public class CollectionTest {
    public static void main(String[] args) {
        // 컬렉션 프레임워크
        // 동적 크기
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.get(0);

        Stack<Long> stack = new Stack<>();

        Queue<Float> queue = new LinkedList<>();

        ArrayDeque<Double> arrayDeque = new ArrayDeque<>();

        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.get("a");
        map.get("b");
        map.put("a", 3); // 값 수정
        map.remove("b");    // 삭제
        System.out.println(map);
        map.containsKey("a");
        map.containsValue(1);

        // 문자열 연산
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(10);           // "10" 추가
        stringBuilder.append("ABC");        // "ABC"
        stringBuilder.deleteCharAt(1);// 1번째 인덱스 문자 삭제
        stringBuilder.insert(1,2);  // 1번째 인덱스에 2라는 문자 추가
        System.out.println(stringBuilder);

        // 람다식
        // (매개 변수) -> { 실행 코드 }

        List<Integer> genericList = new ArrayList<>();
    }
}