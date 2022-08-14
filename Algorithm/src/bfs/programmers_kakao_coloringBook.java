package bfs;

import java.util.*;
class Solution {
    private static boolean[][] visited;
    private static Queue<int[]> q;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static int cnt;
    private static int[] area;
    private static int[][] arr;
    private static int maxSizeOfOneArea = 0;
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        maxSizeOfOneArea = 0;
        arr = picture;
        visited = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(arr[i][j] != 0 && visited[i][j] != true) {
                    visited[i][j] = true;
                    numberOfArea++;
                    bfs(i, j, m, n);
                }
            }
        }
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public static void bfs(int x, int y, int m, int n) {
        cnt = 1;
        
        q = new LinkedList<>();
        q.offer(new int[]{x, y});
        
        while(!q.isEmpty()) {
            int nx = q.peek()[0];
            int ny = q.peek()[1];
            q.poll();
            for(int i = 0; i < 4; i++) {
                int ex = nx + dx[i];
                int ey = ny + dy[i];
                
                if(ex >= 0 && ey >= 0 && ex < m && ey < n) {
                    if(arr[ex][ey] == arr[x][y] && !visited[ex][ey]) {
                        q.offer(new int[] {ex, ey});
                        visited[ex][ey] = true;
                        cnt++;
                        if(cnt > maxSizeOfOneArea) maxSizeOfOneArea = cnt;
                    }    
                }
            }
        }
    }
}
