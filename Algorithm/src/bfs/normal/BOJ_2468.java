package bfs.normal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2468 {
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};

	private static Queue<int[]> q;
	
	private static int[][] map;
	private static boolean[][] visited;
	
	private static int top = 0;
	private static int num;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		num = Integer.parseInt(br.readLine());
		
		map 	= new int[num][num];
		
		q		= new LinkedList<>();
		
		for(int i = 0; i < num; i++) {
			String line = br.readLine();
			String[] lineSplit = line.split(" ");
			for(int j = 0; j < num; j++) {
				map[i][j] = Integer.parseInt(lineSplit[j]);
				if(map[i][j] > top) {
					top = map[i][j];
				}
			}
		}
		
		int max = 1;
		for(int t = 1; t < top; t++) {
			visited = new boolean[num][num];
			int cnt = 0;
			for(int i = 0; i < num; i++) {
				for(int j = 0; j < num; j++) {	
					if(!visited[i][j] && map[i][j] > t) {
						bfs(i, j, t);
						cnt++;
					}
				}
			}
			if(max < cnt) max = cnt;
		}
		System.out.println(max);
	}
	
	public static void bfs(int x, int y, int t) {
		
		q.offer(new int[] {x, y});
		visited[x][y] = true;
		while(!q.isEmpty()) {
			int nx = q.peek()[0];
			int ny = q.peek()[1];
			
			q.poll();
			for(int i = 0; i < 4; i++) {
				int curx = nx + dx[i];
				int cury = ny + dy[i];
				
				visited[nx][ny] = true;
				
				if(curx >= 0 && cury >= 0 && curx < num && cury < num) {
					if(!visited[curx][cury] && map[curx][cury] > t) {
						visited[curx][cury] = true;
						q.offer(new int[] {curx, cury});
					}
				}
			}
		}
		
	}
}
