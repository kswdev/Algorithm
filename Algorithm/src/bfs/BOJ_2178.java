package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2178 {
	
	private static int[][] map;
	private static boolean[][] visited;
	private static Queue<int[]> q;
	private static int[] dx = {1, -1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	
	private static int a;
	private static int b;
	private static int count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		
		String[] inputSplit = input.split(" ");
		
		a = Integer.parseInt(inputSplit[0]);
		b = Integer.parseInt(inputSplit[1]);
		
		map = new int[a][b];
		visited = new boolean[a][b];
		for(int i = 0; i < a; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < b; j++) {
				map[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
			}
		}
		bfs(0, 0);
		System.out.println(map[a-1][b-1]);
	}
	
	public static void bfs(int n, int m) {
		
		q = new LinkedList<>();
		q.offer(new int[] {n, m});
		
		
		while(!q.isEmpty()) {
			int nx = q.peek()[0];
			int ny = q.peek()[1];
			visited[nx][ny] = true;
			
			q.poll();
			for(int i = 0; i < 4; i++) {
				int curx = nx + dx[i];
				int cury = ny + dy[i];
				
				if(curx == a-1 && cury == b-1) {
					
					map[curx][cury] = map[nx][ny] + 1;
					return;
				}
				
				if(curx >= 0 && cury >= 0 && curx < a && cury < b) {
					if(!visited[curx][cury] && map[curx][cury] != 0) {
						visited[curx][cury] = true;
						map[curx][cury] = map[nx][ny] + 1;
						q.offer(new int[] {curx, cury});
						
					}
				}
			}
		}
	}
}
