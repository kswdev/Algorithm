package dfs.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_4963 {

	private static int[][] map;
	private static int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1};
	private static int[] dy = {0, 0, -1, 1, -1, 1, 1, -1};
	
	private static int a;
	private static int b;
	private static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		while(true) {
			
			String input = br.readLine();
			
			String[] inputSplit = input.split(" ");
			
			a = Integer.parseInt(inputSplit[0]);
			b = Integer.parseInt(inputSplit[1]);
			
			if(a == 0 && b == 0) break;
			
			map = new int[b][a];
			visited = new boolean[b][a];
			
			for(int i = 0; i < b; i++) {
				String input2 = br.readLine();
				String[] input2Split = input2.split(" ");
				for(int j = 0; j < a; j++) {
					map[i][j] = Integer.parseInt(input2Split[j]);
				}
			}
			int count = 0;
			for(int i = 0; i < b; i++) {
				for(int j = 0; j < a; j++) {
					if(!visited[i][j] && map[i][j] == 1) {
						count++;
						dfs(i, j);
					}
				}
			}
			System.out.println(count);
		}
	}
	public static void dfs(int n, int m) {
		visited[n][m] = true;
		
		for(int i = 0; i < 8; i++) {
			int curx = n + dx[i];
			int cury = m + dy[i];
			
			if(curx >= 0 && cury >= 0 && curx < b && cury < a) {
				if(!visited[curx][cury] && map[curx][cury] == 1) {
					dfs(curx, cury);
				}
			}
		}
	}
}
