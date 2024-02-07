package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_7576 {
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	
	private static int a;
	private static int b;

	private static int[][] map;
	private static boolean[][] visited;
	
	private static Queue<int[]> q;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		
		String[] inputSplit = input.split(" ");
		
		q = new LinkedList<>();
		
		a = Integer.parseInt(inputSplit[1]);
		b = Integer.parseInt(inputSplit[0]);
		
		visited = new boolean[a][b];
		map		= new int[a][b];
		
		for(int i = 0; i < a; i++) {
			String input2 	= br.readLine();
			String[] input2Split = input2.split(" ");
			for(int j = 0; j < b; j++) {
				map[i][j] = Integer.parseInt(input2Split[j]);
				if(map[i][j] == 1) q.offer(new int[] {i, j});
			}
		}
		bfs();
	}

	private static void bfs() {
		int max = 1;
		while(!q.isEmpty()) {
			int nx = q.peek()[0];
			int ny = q.peek()[1];
			
			q.poll();
			for(int i = 0; i < 4; i++) {
				int curx = nx + dx[i];
				int cury = ny + dy[i];
				
				visited[nx][ny] = true;
				
				if(curx >= 0 && cury >= 0 && curx < a && cury < b) {
					if(!visited[curx][cury] && map[curx][cury] == 0) {
						visited[curx][cury] = true;
						q.offer(new int[] {curx, cury});
						map[curx][cury] = map[nx][ny] + 1;
						if(map[curx][cury] > max) {
							max = map[curx][cury];
						}
					}
				}
			}
		}
		for(int i = 0; i < a; i++) {
			for(int j = 0; j < b; j++) {
				if(map[i][j] == 0) max = 0;
			}
		}
		System.out.println(max-1);
	}
}
