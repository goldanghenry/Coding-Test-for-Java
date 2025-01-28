package Basic;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ArrayTest {
    public static void main(String[] args) {

        // 기본 배열
        int[] arr = {1,2,3,4,5};
        System.out.println(arr.length); // 배열의 길이
        Arrays.sort(arr);   // 오름차순 정렬, O(NlogN)
        int[] clone = arr.clone(); // 원본을 남겨두고 싶다면,

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

        // Stream
        // 컬렉션에서 스트림 생성
        List<String> list4 = List.of("Apple", "Banana", "Cherry");
        Stream<String> stream = list4.stream();

        // 배열에서 스트림 생성
        String[] array = {"Apple", "Banana", "Cherry"};
        Stream<String> stream1 = Arrays.stream(array);

        // Stream.of()로 작성
        Stream<String> stream2 = Stream.of("Apple", "Banana", "Cherry");

        // 숫자 스트림 생성
        IntStream intStream = IntStream.range(1,5);
        LongStream longStream = LongStream.of(1L,2L,3L);

        // 기본 스트림 처리
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        // 짝수만 필터링하고, 각 값에 2를 곱한 뒤, 결과를 출력
        numbers.stream()
                .filter(x -> x %2 == 0)
                .map(x -> x * 2)
                .forEach(System.out::println);

        // 문자열 리스트 처리
        List<String> fruits = Arrays.asList("apple", "banana", "cherry", "apple");

        // 중복 제거, 정렬, 대문자로 변환
        List<String> result = fruits.stream()
                .distinct()                     // 중복 제거
                .sorted()                       // 정렬
                .map(String::toUpperCase)       // 대문자로 변환
                .collect(Collectors.toList()); // 리스트로 수집

        System.out.println(result);

        // 숫자 합계 계산
        int sum = IntStream.range(1, 11) // 1부터 10까지 숫자
                .sum();      // 합계 계산

        System.out.println("Sum: " + sum);

    }
}
