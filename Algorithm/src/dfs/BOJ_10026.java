package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10026 {
	private static int N;
	private static boolean[][] visited;
	
	private static char[][] map;
	private static char[][] map2;
	
	private static int[] dx = {1, -1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N		= Integer.parseInt(br.readLine());
		visited = new boolean[N][N];
		
		char[][] map  = new char[N][N];
		char[][] map2 = new char[N][N];
		
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == 'G') {
					map2[i][j] = 'R';
				} else {
					map2[i][j] = map[i][j];
				}
			}
		}
		
		int count1 = 0;
		int count2 = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					count1++;
					dfs(i, j, map);
				}
			}
		}
		visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					count2++;
					dfs(i, j, map2);
				}
			}
		}
		System.out.println(count1);
		System.out.println(count2);
	}
	public static void dfs(int n, int m, char[][] map) {
		
		
		for(int i = 0; i < 4; i++) {
			int curx = n + dx[i];
			int cury = m + dy[i];
			
			visited[n][m] = true;
			
			if(curx >= 0 && cury >= 0 && curx < N && cury < N) {
				if(!visited[curx][cury] && map[curx][cury] == map[n][m]) {
					dfs(curx, cury, map);
				}
			}
		}
	}
}
