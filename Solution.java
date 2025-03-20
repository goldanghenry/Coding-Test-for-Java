/*
 * N개 중 C개를 선택하는 조합
 */

 import java.io.*;
 import java.util.*;
 
 public class Solution {
     static int N, C, res;
     static int[] nums, sel;
     static boolean[] visited;
     
     private static void dfs(int depth, int s) {
         
         if (depth > C) {
             int[] templist = new int[C+1];
             for (int i = 1; i <= C; i++) {
                 templist[i] = nums[sel[i]];
             }
             
             Arrays.sort(templist);
             
             int localMin = Integer.MAX_VALUE;
             int pre = templist[1];
             
             for (int i = 2; i < C; i++) {
                 int cur = templist[i];
                 int next = templist[i+1];
                 int temp = Math.min(Math.abs(pre-cur),Math.abs(cur - next));	// 앞 뒤 비교
                 
                 localMin = Math.min(localMin, temp);
                 pre = cur;
             }
             res = Math.max(res, localMin);
             return;
         }
         
         for(int i = s; i <= N; i++) {
             if (visited[i]) continue;
             visited[i] = true;
             sel[depth] = i;
             dfs(depth+1, s+1);
             visited[i] = false;
         }
     }
 
     public static void main(String[] args) throws IOException {
         //--------------솔루션 코드를 작성하세요.--------------------------------
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st = new StringTokenizer(br.readLine());
         
         N = Integer.parseInt(st.nextToken());
         C = Integer.parseInt(st.nextToken());
         
         nums = new int[N+1];
         sel = new int[N+1];
         visited = new boolean[N+1];
         
         for (int i = 1; i <= N; i++) {
             nums[i] = Integer.parseInt(br.readLine());
         }
         
         res = -1;
         dfs(1, 1);
         
         System.out.println(res);
     }
 }
 
 
 
 