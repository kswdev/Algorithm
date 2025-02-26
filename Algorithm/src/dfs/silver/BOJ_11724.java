package dfs.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11724 {

	private static int[][] map;
	private static boolean[] visited;
	private static int num;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		String[] inputSplit = input.split(" ");
		
		num = Integer.parseInt(inputSplit[0]) + 1;
		int m 	= Integer.parseInt(inputSplit[1]);
		
		map = new int[num][num];
		visited = new boolean[num];
		for(int i = 0; i < m; i++) {
			String input2 = br.readLine();
			String[] input2Split = input2.split(" ");
			int a = Integer.parseInt(input2Split[0]);
			int b = Integer.parseInt(input2Split[1]);
			
			map[a][b] = 1;
			map[b][a] = 1;
		}
		int count = 0;
		for(int i = 1; i < num; i++) {
			if(!visited[i]) {
				count++;
				dfs(i);
			}
		}
		System.out.println(count);
	}
	public static void dfs(int n) {
		visited[n] = true;

		for(int i = 1; i < num; i++) {
			if(!visited[i] && map[n][i] != 0) {
				visited[i] = true;
				dfs(i);
			}
		}
	}
}
