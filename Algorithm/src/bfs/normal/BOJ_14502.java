package bfs.normal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_14502 {

	private static int[] dx = {1, -1, 0, 0};
	private static int[] dy = {0, 0, 1, -1};
	
	private static int result = Integer.MIN_VALUE;
	
	private static int a;
	private static int b;
	
	private static Queue<int[]> q;
	
	
	private static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		String[] inputSplit = input.split(" ");
		
		a = Integer.parseInt(inputSplit[0]);
		b = Integer.parseInt(inputSplit[1]);
		
		
		map  	= new int[a][b];
		
		for(int i = 0; i < a; i++) {
			String input2 = br.readLine();
			String[] input2Split = input2.split(" ");
			for(int j = 0; j < b; j++) {
				map[i][j] = Integer.parseInt(input2Split[j]);
			}
		}
		
		dfs(0);
		System.out.println(result);
	}
	
	
	public static void dfs(int depth) {
		
		if(depth == 3) {
			bfs();
			return;
		}
		
		for(int i = 0; i < a; i++) {
			for(int j = 0; j < b; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					dfs(depth+1);
					map[i][j] = 0;
				}
			}
		}
	}
	
	public static void bfs() {
		
		q = new LinkedList<>();
		int[][] cMap = new int[a][b];
		for(int i = 0; i < a; i++) {
			cMap[i] = map[i].clone();
		}
		for(int i = 0; i < a; i++) {
			for(int j = 0; j < b; j++) {
				if(cMap[i][j] == 2) {
					q.offer(new int[] {i, j});
				}
			}
		}
		
		while(!q.isEmpty()) {
			int nx = q.peek()[0];
			int ny = q.peek()[1];
			q.poll();
			
			for(int i = 0; i < 4; i++) {
				int curx = nx + dx[i];
				int cury = ny + dy[i];
				
				if(curx >=0 && cury >= 0 && curx < a && cury < b) {
					if(cMap[curx][cury] == 0) {
						cMap[curx][cury] = 2;
						q.offer(new int[] {curx, cury});
					}
				}
			}
		}
		
		int count = 0;
		for(int i = 0; i < a; i++) {
			for(int j = 0; j < b; j++) {
				if(cMap[i][j] == 0) count++;
			}
		}
		//System.out.println(count + "ī��Ʈ");
		result = Math.max(count, result);
	}
}
