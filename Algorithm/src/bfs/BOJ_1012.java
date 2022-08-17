package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1012 {
	
	private static int[][] map;
	private static boolean[][] visited;
	private static Queue<int[]> q;
	private static int m;
	private static int n;
	
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < testCase; i++) {
			String input = br.readLine();
			String[] inputSplit = input.split(" ");
			
			int cnt = 0;
			
			m   = Integer.parseInt(inputSplit[0]);
			n   = Integer.parseInt(inputSplit[1]);
			int num = Integer.parseInt(inputSplit[2]);
			
			visited = new boolean[m][n];
			map		= new int[m][n];
			
			for(int j = 0; j < num; j++) {
				String xy = br.readLine();
				String[] xySplit = xy.split(" ");
				
				int a = Integer.parseInt(xySplit[0]);
				int b = Integer.parseInt(xySplit[1]);
				
				map[a][b] = 1;
			}
			for(int k = 0; k < m; k++) {
				for(int l = 0; l < n; l++) {
					if(map[k][l] == 1 && !visited[k][l]) {
						cnt++;
						bfs(k, l);
					}
				}
			}
			System.out.println(cnt);
		}
	}
	
	public static void bfs(int k, int l) {
		q = new LinkedList<>();
		q.offer(new int[] {k, l});
		visited[k][l] = true;
		while(!q.isEmpty()) {
			int nx = q.peek()[0];
			int ny = q.peek()[1];
			
			
			q.poll();
			for(int i = 0; i < 4; i++) {
				int curx = nx + dx[i];
				int cury = ny + dy[i];
				
				if(curx >= 0 && cury >= 0 && curx < m && cury < n) {
					if(!visited[curx][cury] && map[curx][cury] == 1) {
						visited[curx][cury] = true;
						q.offer(new int[] {curx, cury});
					}
				}
			}
		}
	}
}
