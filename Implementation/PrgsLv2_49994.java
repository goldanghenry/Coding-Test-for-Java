package Implementation;

import java.util.HashMap;
import java.util.HashSet;

/*
    programmers Lv2 방문 길이
    https://school.programmers.co.kr/learn/courses/30/lessons/49994
    - 좌표평면이나, 문제를 해결하는데 크게 중요하지 않으므로 N = 10 인 map으로 생각
    - start point 는 (0,0) -> (5,5)
    - HashSet을 통해 처음으로 방문한 길만 기록
 */

public class PrgsLv2_49994 {

    // 맵을 벗어나는지 체크
    private static boolean isValidMove(int nx, int ny) {
        return 0 <= nx && 0 <= ny && nx <= 10 && ny <= 10;
    }

    private static final HashMap<Character, int[]> map = new HashMap<>();

    private static void initDir() {
        map.put('U', new int[]{0,1});
        map.put('D', new int[]{0,-1});
        map.put('L', new int[]{-1,0});
        map.put('R', new int[]{1,0});
    }

    public int solution(String dirs) {
        initDir();
        int x = 5, y = 5;
        HashSet<String> answer = new HashSet<>();

        for ( int i = 0; i < dirs.length(); i++) {
            int[] offset = map.get(dirs.charAt(i));
            int nx = x + offset[0];
            int ny = y + offset[1];

            if (!isValidMove(nx, ny)) {
                continue;
            }

            // 반대의 경우도 고려
            answer.add(x + " " + y + " " + nx + " " + ny);
            answer.add(nx + " " + ny + " " + x + " " + y);

            // 좌표 이동
            x = nx;
            y = ny;
        }

        return answer.size() / 2;
    }
}
