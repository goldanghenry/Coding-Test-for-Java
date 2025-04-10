package String;

import java.io.*;
import java.util.*;

/*
 * BOJ 1786 Platinum 5, 찾기
 * https://www.acmicpc.net/problem/1786
 */

public class BOJ_P5_1786 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] text = br.readLine().toCharArray();
        char[] pattern = br.readLine().toCharArray();
        int tLength = text.length;
        int pLength = pattern.length;
        // 부분 일치 테이블(실패 함수) 만들기 : 패턴에서 불일치가 발생할 경우, 활용해서 패턴 포인터 이동 목적
        int[] pi = new int[pLength];        
        for (int i = 1 , j = 0 ; i < pLength; i++) { // 패턴에서 패턴을 찾는 원리를 이용
            // i : 패턴의 접미사, j : 패턴의 접두사
            // 두 포인터의 위치에서 불일치가 발생하면 맞은 직전위치의 정보를 활용해서
            // 불필요한 비교를 줄임
            while(j > 0 && pattern[i] != pattern[j]) {
                j = pi[j-1];
            }
            // j가 0이거나, 두 패턴이 일치하는 곳을 찾음
            // 현재 i 위치까지의 부분문자열의 접미사가 접두사와 일치하는 패턴의 최장길이 저장
            if(pattern[i] == pattern[j]) {
                pi[i] = ++j;
            } else {
                pi[i] = 0; // 일치하는 접두사 접미사 없음(생략가능)
            }
        }

        int cnt = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0, j = 0; i < tLength; i++) {

            while(j > 0 && text[i] != pattern[j]) {
                j = pi[j-1];
            }

            if (text[i] == pattern[j]) {
                if (j == pLength-1) {   // 일치가 발생한 위치가 패턴의 끝이면
                    ++cnt;  // 패턴 찾았으므로 카운트 증가
                    list.add(i-j);
                    j = pi[j];  // 해당 자리는 틀린 것이 아니기에 j 부터 
                } else {
                    ++j;
                }
            }
        }
        System.out.println(cnt);
        if (cnt > 0 ) {
            for (int lst : list) {
                System.out.print((lst+1)+" ");
            }
        }
    }
}
