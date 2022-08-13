package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;

public class BOJ_2667 {

	private static int[][] arr;
	private static boolean[][] visited;
	private static Queue<Integer> q;
	private int[] dx = {0, 0, -1, 1};
	private int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		
		arr = new int[num][num];
		for(int i = 0; i < num; i++) {
			String input = br.readLine();
			for(int j = 0; j < num; j++) {
				arr[i][j] = input.charAt(j);
			}
		}
		
		
	}
	
	public static void bfs(int x, int y) {
		
	}
}
