package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_7569 {
	
	private static int[][][] map;
	private static Queue<int[]> q;
	
	
	private static int[] dz = {1, -1, 0, 0, 0, 0};
	private static int[] dx = {0, 0, -1, 1, 0, 0};
	private static int[] dy = {0, 0, 0, 0, -1, 1};
	
	private static int x;
	private static int y;
	private static int z;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		
		String[] inputSplit = input.split(" ");
		
		y = Integer.parseInt(inputSplit[0]);
		x = Integer.parseInt(inputSplit[1]);
		z = Integer.parseInt(inputSplit[2]);
		
		map = new int[z][x][y];
		
		q	= new LinkedList<>();
		for(int k = 0; k < z; k++) {	
			for(int i = 0; i < x; i++) {
				String str = br.readLine();
				String[] strSplit = str.split(" ");
				for(int j = 0; j < y; j++) {
					map[k][i][j] = Integer.parseInt(strSplit[j]);
					if(map[k][i][j] == 1) q.offer(new int[] {k, i, j});
				}
			}
		}
		bfs();
	}
	private static void bfs() {
		int max = 1;
		while(!q.isEmpty()) {
			int nz = q.peek()[0];
			int nx = q.peek()[1];
			int ny = q.peek()[2];
			
			
			q.poll();
			
			for(int i = 0; i < 6; i++) {
				int curz = nz + dz[i];
				int curx = nx + dx[i];
				int cury = ny + dy[i];
				
				if(curx >= 0 && cury >= 0 && curz >= 0 && curx < x && cury < y && curz < z) {
					if(map[curz][curx][cury] == 0) {
						map[curz][curx][cury] = map[nz][nx][ny] + 1;
						q.offer(new int[] {curz, curx, cury});
						if(map[curz][curx][cury] > max) max = map[curz][curx][cury];
					}
				}
			}
		}
		for(int k = 0; k < z; k++) {	
			for(int i = 0; i < x; i++) {
				for(int j = 0; j < y; j++) {
					if(map[k][i][j] == 0) max = 0; 
				}
			}
		}
		System.out.println(max-1);
	}
}
