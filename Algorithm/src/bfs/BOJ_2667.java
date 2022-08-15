package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2667 {

	private static int[][] arr;
	private static boolean[][] visited;
	private static ArrayList<Integer> danNum;
	private static int apartNum;
	private static Queue<int[]> q;
	private static int[] dx = {0, 0, -1, 1};
	private static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		danNum = new ArrayList<>();
		arr = new int[num][num];
		visited = new boolean[num][num];
		
		for(int i = 0; i < num; i++) {
			String input = br.readLine();
			for(int j = 0; j < num; j++) {
				arr[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
			}
		}
		
		for(int i = 0; i < num; i++) {
			for(int j = 0; j < num; j++) {
				if(arr[i][j] != 0 && !visited[i][j]) {
					visited[i][j] = true;
					bfs(i, j, num);
				}
			}
		}
		
		System.out.println(danNum.size());
		for(int i : danNum) {
			System.out.println(i);
		}
	}
	
	public static void bfs(int x, int y, int num) {
		apartNum = 1;
		
		q = new LinkedList<>();
		q.offer(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int nx = q.peek()[0];
			int ny = q.peek()[1];
			
			q.poll();
			
			for(int i = 0; i < 4; i++) {
				int curx = nx + dx[i];
				int cury = ny + dy[i];
				
				if(curx >= 0 && cury >= 0 && curx < num && cury < num) {
					if(!visited[curx][cury] && arr[curx][cury] != 0) {
						apartNum++;
						q.offer(new int[] {curx, cury});
						visited[curx][cury] = true;
					}
				}
			}
		}
		danNum.add(apartNum);
		Collections.sort(danNum);
	}
}
